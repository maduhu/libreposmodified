//    Librepos is a point of sales application designed for touch screens.
//    Copyright (C) 2005 Adrian Romero Corchado.
//    http://sourceforge.net/projects/librepos
//
//    This program is free software; you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation; either version 2 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program; if not, write to the Free Software
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package net.adrianromero.tpv.forms;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.UUID;
import javax.swing.*;
import javax.imageio.ImageIO;

import net.adrianromero.tpv.printer.*;

import net.adrianromero.beans.*;
import net.adrianromero.tpv.util.ThumbNailBuilder;


import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.data.gui.JMessageDialog;
import net.adrianromero.data.loader.BatchSentenceResource;
import net.adrianromero.data.loader.Session;
import net.adrianromero.instance.InstanceManager;
import net.adrianromero.instance.InstanceQuery;
import net.adrianromero.instance.AppMessage;
import net.adrianromero.tpv.data.DataLogic;
import net.adrianromero.tpv.data.DataLogicException;
import net.adrianromero.tpv.data.DataLogicProvider;
import net.adrianromero.tpv.scale.DeviceScale;
import net.adrianromero.tpv.scanpal2.DeviceScanner;
import net.adrianromero.tpv.scanpal2.DeviceScannerFactory;

public class JFrmTPV extends JFrame implements AppView, AppMessage {
 
    private AppViewConnection m_appcnt;     
    private DataLogicProvider m_dataprovider;
    
    private Properties m_propsdb = null;
    private String m_sActiveCashIndex;
    private Date m_dActiveCashDateStart;
    private Date m_dActiveCashDateEnd;
    
    private String m_sInventoryLocation;
   
    private DeviceScale m_Scale;
    private DeviceScanner m_Scanner;
    private DeviceTicket m_TP;   
    private TicketParser m_TTP;
    
    // private JPrincipalApp m_principalapp = null;
    private LinkedList m_principalset = new LinkedList();

    // Gestor de que haya solo una instancia corriendo en cada maquina.
    private InstanceManager m_instmanager = null;
    
    /** Creates new form JFrmTPV */
    private JFrmTPV() {
    }
    
    /** Arrancamos la aplicacion de TPV */
    private boolean startTPV() {    
        
        /////////////////////////////////////////////////////////////////////////////////////////
        
        // vemos si existe alguna instancia        
        InstanceQuery i = null;
        try {
            i = new InstanceQuery();
            // se ha podido crear, entonces existe una instancia ya de Librepos corriendo
            i.getAppMessage().restoreWindow(); // la mandamos restaurarse.
            return false;
        } catch (Exception e) {
            // no se ha podido crear, yo soy la primera instancia, entonces sigo.
        }    

        // Creo el instance manager ya que la instancia que corre voy a ser yo
        try {
            m_instmanager = new InstanceManager(this);
        } catch (Exception e) {
            // No se ha podido instanciar, bueno no importa.
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////
        
        // Inicializo los componentes visuales
        initComponents ();     
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));     
        // Los eventos de los elementos que existen
        
        /////////////////////////////////////////////////////////////////////////////////////////
        
        // Inicializo la conectividad
        try {
            m_appcnt = new AppViewConnection();
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, e.getMessage(), e));
            return false;
        }

        // Inicializo el dataLogic Provider
        try {
            m_dataprovider = new DataLogicProvider(m_appcnt.getSession());
        } catch (DataLogicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, e.getMessage(), e));
            return false;
        }
      
        // Comprobamos si existe la base de datos        
        String sDBVersion = getDataBaseVersion();
        String sScript = lookupDataLogic(DataLogicSystem.class).getInitScript() + "_" + sDBVersion + ".sql";
        
        if (JFrmTPV.class.getResource(sScript) != null) {
            // hay un script para actualizar o crear la base de datos.
            if (JOptionPane.showConfirmDialog(this
                    , AppLocal.getIntString("create".equals(sDBVersion) ? "message.createdatabase" : "message.updatedatabase")
                    , AppLocal.getIntString("message.title")
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {  
                
                do { // ejecutamos todos los scripts de upgrade disponibles...
                    try {
                        java.util.List l = new BatchSentenceResource(m_appcnt.getSession(), sScript).list();
                        if (l.size() > 0) {
                            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("Database.ScriptWarning"), l.toArray(new Throwable[l.size()])));
                        }
                   } catch (BasicException e) {
                        JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_DANGER, AppLocal.getIntString("Database.ScriptError"), e));
                        m_appcnt.disconnect();
                        return false;
                    }     
                    sScript = lookupDataLogic(DataLogicSystem.class).getInitScript() + "_" + getDataBaseVersion() + ".sql";                   
                } while (JFrmTPV.class.getResource(sScript) != null);
            } else {
                // sin base de datos no hay registradora.
                m_appcnt.disconnect();
                return false;
            }
        }
        
        // Ponemos la version en el aption que no lo haciamos.
        sDBVersion = getDataBaseVersion();
        setTitle("Librepos " + sDBVersion);
        
        // Cargamos las propiedades de base de datos
        m_propsdb = lookupDataLogic(DataLogicSystem.class).getResourceAsProperties(getHost() + "/properties");
        if (m_propsdb == null) {
            m_propsdb = new Properties();            
            // Compatibilidad hacia atras
            String soldvalue = getProperty("machine.activecash");
            if (soldvalue != null) {
                m_propsdb.setProperty("activecash", soldvalue);
            }
        }
        
        // creamos la caja activa si esta no existe      
        try {
            String sActiveCashIndex = getMachineProperty("activecash");
            Object[] valcash = lookupDataLogic(DataLogicSystem.class).findActiveCash(sActiveCashIndex);
            if (valcash == null || !getHost().equals(valcash[0])) {
                // no la encuentro o no es de mi host por tanto creo una...
                setActiveCash(UUID.randomUUID().toString(), new Date(), null);

                // creamos la caja activa      
                lookupDataLogic(DataLogicSystem.class).execInsertCash(
                        new Object[] {getActiveCashIndex(), getHost(), getActiveCashDateStart(), getActiveCashDateEnd()});                  
            } else {
                setActiveCash(sActiveCashIndex, (Date) valcash[1], (Date) valcash[2]);
            }
        } catch (BasicException e) {
            // Casco. Sin caja no hay pos
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.cannotclosecash"), e);
            msg.show(this);
            m_appcnt.disconnect();
            return false;
        }  
        
        // Leo la localizacion de la caja (Almacen).
        m_sInventoryLocation = getMachineProperty("location");
        if (m_sInventoryLocation == null) {
            m_sInventoryLocation = "0";
            setMachineProperty("location", m_sInventoryLocation);
            saveMachineProperties();
        }
        
        // Inicializo la impresora...
        m_TP = new DeviceTicket(m_appcnt);   
        
        // Inicializamos 
        m_TTP = new TicketParser(getDeviceTicket(), lookupDataLogic(DataLogicSystem.class));
        printerStart();
        
        // Inicializamos la bascula
        m_Scale = new DeviceScale(m_appcnt);
        
        // Inicializamos la scanpal
        m_Scanner = DeviceScannerFactory.createInstance(m_appcnt);
        
        // Leemos los recursos basicos
        BufferedImage imgicon = lookupDataLogic(DataLogicSystem.class).getResourceAsImage("Window.Logo");
        m_jLblTitle.setIcon(imgicon == null ? null : new ImageIcon(imgicon));
        m_jLblTitle.setText(lookupDataLogic(DataLogicSystem.class).getResourceAsText("Window.Title"));  
        
        String sWareHouse;
        try {
            sWareHouse = lookupDataLogic(DataLogicSystem.class).findLocationName(m_sInventoryLocation);
        } catch (BasicException e) {
            sWareHouse = null; // no he encontrado el almacen principal
        }        
        m_jHost.setText(sWareHouse + " (" + getHost() + ")");
        
        // Show Login
        listPeople();
        showView("login");
//        showLogonView("logonname");
      
        // Nos ponemos a la resolucion que hay que ponerse
        changeDisplayMode(1024, 768);
        
        // Mostramos el formulario principal...
        setVisible(true);   
        
        // Finalizamos con exito.
        return true;
    }

    public static void main (String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
//                Locale.setDefault(new Locale("es", "MX"));
                
//                try {
//                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "INSERT YOUR LICENSE KEY HERE", "my company");
//                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");                    
//                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (Exception e) {
//                }
                JFrmTPV app = new JFrmTPV(); 
                if (!app.startTPV()) {
                    // No se ha iniciado correctamente, entonces nos vamos con un disgusto.
                    System.exit(1);
                }  
            }
        });    
    }
    
    private String getDataBaseVersion() {
             
        try {
            return lookupDataLogic(DataLogicSystem.class).findVersion();
        } catch (BasicException ed) {
        }
        
        // Comprobamos si existe la base de datos
        try {
            lookupDataLogic(DataLogicSystem.class).execDummy();
            return "0.0.7";
        } catch (BasicException e) {
            return "create";
        } 
    }
    
    // Interfaz de aplicacion
    public DeviceTicket getDeviceTicket(){
        return m_TP;
    }
    
    public DeviceScale getDeviceScale() {
        return m_Scale;
    }
    public DeviceScanner getDeviceScanner() {
        return m_Scanner;
    }
    
    public Session getSession() {
        return m_appcnt.getSession();
    } 
    
    public String getHost() {
        return m_appcnt.getHost();
    }
    public String getInventoryLocation() {
        return m_sInventoryLocation;
    }   
    public String getActiveCashIndex() {
        return m_sActiveCashIndex;
    }
    public Date getActiveCashDateStart() {
        return m_dActiveCashDateStart;
    }
    public Date getActiveCashDateEnd(){
        return m_dActiveCashDateEnd;
    }
    public void setActiveCash(String sIndex, Date dStart, Date dEnd) {
        m_sActiveCashIndex = sIndex;
        m_dActiveCashDateStart = dStart;
        m_dActiveCashDateEnd = dEnd;
        
        setMachineProperty("activecash", m_sActiveCashIndex);
        saveMachineProperties();     
    }   
    
    // Propiedades en base de datos
    private void  setMachineProperty(String sKey, String sValue) {
        m_propsdb.setProperty(sKey, sValue);
    }
    private String getMachineProperty(String sKey) {
        return m_propsdb.getProperty(sKey);
    }
    private void saveMachineProperties() {
        lookupDataLogic(DataLogicSystem.class).setResourceAsProperties(getHost() + "/properties", m_propsdb);
    }
    
    public String getProperty(String sKey) {
        return m_appcnt.getProperty(sKey);
    }
    
    public <T extends DataLogic> T lookupDataLogic(Class<T> clazz) {
        return m_dataprovider.lookup(clazz);
    }
    
    public void restoreWindow() throws RemoteException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (getExtendedState() == JFrame.ICONIFIED) {
                    setExtendedState(JFrame.NORMAL);
                }
            }
        });
    }
 
    private void changeDisplayMode(int iwidth, int iheight) {
        
        if ("fullscreen".equals(getProperty("machine.screenmode"))) {       

            // muestro la ventana
            GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            if (myDevice.isFullScreenSupported()) {         

                // Buscamos el modo que mejor se ajusta
                // tiene que ser 1024 x 768 y mejor que 8 bpp y mejor que 60 hz
                DisplayMode[] modes = myDevice.getDisplayModes();
                DisplayMode bestDisplayMode = null;
                int iBestRefreshRate = 60;
                int iBestBitDepth = 16;
                for (int i = 0; i < modes.length; i++) {
                    // if (modes[i].getWidth() == 1280 && modes[i].getHeight() == 1024) {
                    if (modes[i].getWidth() == iwidth && modes[i].getHeight() == iheight) {
                        if (modes[i].getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && modes[i].getBitDepth() >= iBestBitDepth &&
                            modes[i].getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && modes[i].getRefreshRate() >= iBestRefreshRate) {
                            bestDisplayMode = modes[i];
                            iBestRefreshRate = modes[i].getRefreshRate();
                            iBestBitDepth = modes[i].getBitDepth();
                        }                           
                    }
                }

                if (bestDisplayMode == null) {
                    JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, Integer.toString(iwidth) + "x" + Integer.toString(iheight) + " not supported." ));
                } else {
                    if (myDevice.getDisplayMode().equals(bestDisplayMode)) {
                        // ya estamos en el modo adecuado
                        setUndecorated(true);
                        myDevice.setFullScreenWindow(this);
                        return; // terminamos
                    } else {
                        if (!myDevice.isDisplayChangeSupported()) {
                            try {
                                setUndecorated(true);
                                myDevice.setFullScreenWindow(this);
                                myDevice.setDisplayMode(bestDisplayMode);
                                return; // terminamos
                            } catch (IllegalArgumentException eIA) {
                                JMessageDialog.showMessage(this, new MessageInf(eIA));
                            }
                        } else {
                            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, "Cannot change display mode"));
                        }
                    }
                }
            } else {
                JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, "Full screen mode not supported"));
            }     
        }
            
        // No es pantalla completa.    
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - iwidth) / 2, (screenSize.height - iheight) / 2, iwidth, iheight);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);            
    }
    
    private void printerStart() {
        
        String sresource = lookupDataLogic(DataLogicSystem.class).getResourceAsXML("Printer.Start");
        if (sresource == null) {
            m_TP.writeTimeVisor(AppLocal.getIntString("Visor.Title")); // Pinto algo bonito para empezar
        } else {
            try {
                m_TTP.printTicket(sresource);
            } catch (TicketPrinterException eTP) {
                m_TP.writeTimeVisor(AppLocal.getIntString("Visor.Title")); // Pinto algo bonito para empezar
            }
        }        
    }
    
    private void listPeople() {
        
        try {
           
            jScrollPane1.getViewport().setView(null);

            JFlowPanel jPeople = new JFlowPanel();
            jPeople.setOpaque(false);
           
            java.util.List people = lookupDataLogic(DataLogicSystem.class).listPeopleVisible();
            
            Image defimg;
            try {
                defimg = ImageIO.read(getClass().getClassLoader().getResourceAsStream("net/adrianromero/images/yast_sysadmin.png"));               
            } catch (Exception fnfe) {
                defimg = null;
            }            
            ThumbNailBuilder tnb = new ThumbNailBuilder(32, 32, defimg);
            
            
            for (int i = 0; i < people.size(); i++) {
                Object[] value = (Object[]) people.get(i);
                 
                AppUser user = new AppUser(                       
                        (String) value[0],
                        (String) value[1],
                        (String) value[2],
                        (String) value[3],
                        new ImageIcon(tnb.getThumbNail((Image) value[4])));

                JButton btn = new JButton(new AppUserAction(user));
                btn.setFocusPainted(false);
                btn.setFocusable(false);
                btn.setRequestFocusEnabled(false);
                btn.setHorizontalAlignment(SwingConstants.LEADING);
                // btn.setMargin(new Insets(2, 2, 2, 2));
                btn.setMaximumSize(new Dimension(150, 50));
                btn.setPreferredSize(new Dimension(150, 50));
                btn.setMinimumSize(new Dimension(150, 50));
        
                jPeople.add(btn);    
                
            }
            jScrollPane1.getViewport().setView(jPeople);
            
        } catch (BasicException ee) {
            ee.printStackTrace();
        }
    }
    // La accion del selector
    private class AppUserAction extends AbstractAction {
        
        private AppUser m_actionuser;
        
        public AppUserAction(AppUser user) {
            m_actionuser = user;
            putValue(Action.SMALL_ICON, m_actionuser.getIcon());
            putValue(Action.NAME, m_actionuser.getName());
        }
        
        public AppUser getUser() {
            return m_actionuser;
        }
        
        public void actionPerformed(ActionEvent evt) {
            // String sPassword = m_actionuser.getPassword();
            if (m_actionuser.authenticate()) {
                // p'adentro directo, no tiene password        
                openAppView(m_actionuser);         
            } else {
                // comprobemos la clave antes de entrar...
                String sPassword = JPasswordDialog.showEditPassword(JFrmTPV.this, 
                        AppLocal.getIntString("Label.Password"),
                        m_actionuser.getName(),
                        m_actionuser.getIcon());
                if (sPassword != null) {
                    if (m_actionuser.authenticate(sPassword)) {
                        openAppView(m_actionuser);                
                    } else {
                        JOptionPane.showMessageDialog(JFrmTPV.this,
                                AppLocal.getIntString("message.BadPassword"),
                                m_actionuser.getName(),
                                JOptionPane.WARNING_MESSAGE);
                    }
                }   
            }
        }
    }
    
    private void showView(String view) {
        CardLayout cl = (CardLayout)(m_jPanelContainer.getLayout());
        cl.show(m_jPanelContainer, view);  
    }
    
    private void openAppView(AppUser user) {
        
        // creo el app quiza no deberia crearla si a existe 
        JPrincipalApp principalapp = new JPrincipalApp(this, user);
        
        // el conjunto
        m_principalset.add(principalapp);
        // el indicador
        m_jPanelDown.add(principalapp.getNotificator());
        m_jPanelDown.revalidate();
        // el panel principal
        m_jPanelContainer.add(principalapp, "_" + principalapp.getUser().getId());
        showView("_" + principalapp.getUser().getId());
    }
    
    public boolean closeAllAppView() {
        
        while (m_principalset.size() > 0) {
            JPrincipalApp principal = (JPrincipalApp) m_principalset.getLast();
            if (!closeAppView(principal)) {
                return false;
            }
        }
        return true; // los hemos conseguido borrar a todos
    }
    
    public void showAppView(JPrincipalApp principal) {
        showView("_" + principal.getUser().getId());        
    }
    
    public boolean closeAppView(JPrincipalApp principal) {
        
        // trato de desactivar la aplicacion principal
        if (!principal.deactivate()) {
            return false;
        }
        
        // el conjunto
        m_principalset.remove(principal);
        // el indicador
        m_jPanelDown.remove(principal.getNotificator());
        m_jPanelDown.revalidate();
        m_jPanelDown.repaint();
        
        // el panel principal
        m_jPanelContainer.remove(principal);
        
        // Show Login
        listPeople();
        showView("login");
//        showLogonView("logonname");        
        
        // inicializo la impresora
        printerStart();        
        
        return true;
    }
    
    public void waitCursorBegin() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
    public void waitCursorEnd(){
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    private void tryToClose() {   
        
        if (closeAllAppView()) {
            // apago el visor
            m_TP.clearVisor();
            // me desconecto de la base de datos.
            m_appcnt.disconnect();
            // Descargo el formulario
            dispose();
        }
    }
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        m_jPanelTitle = new javax.swing.JPanel();
        m_jLblTitle = new javax.swing.JLabel();
        m_jPanelDown = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        m_jHost = new javax.swing.JLabel();
        m_jPanelContainer = new javax.swing.JPanel();
        m_jPanelLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        m_jLogonName = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        m_jClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Librepos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        m_jPanelTitle.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleBackground"));
        m_jLblTitle.setForeground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleForeground"));
        m_jLblTitle.setText("Window.Title");
        m_jPanelTitle.add(m_jLblTitle);

        getContentPane().add(m_jPanelTitle, java.awt.BorderLayout.NORTH);

        m_jPanelDown.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5)));
        m_jHost.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/display.png")));
        m_jHost.setText("*Host");
        jPanel3.add(m_jHost, java.awt.BorderLayout.CENTER);

        m_jPanelDown.add(jPanel3);

        getContentPane().add(m_jPanelDown, java.awt.BorderLayout.SOUTH);

        m_jPanelContainer.setLayout(new java.awt.CardLayout());

        m_jPanelLogin.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/logo.jpg")));
        jLabel1.setText("<html><center>Librepos is a point of sale application designed for touch screens.<br>\nCopyright (c) 2005 Adri\u00e1n Romero Corchado.<br>\nhttp://librepos.sourceforge.net<br>\nadrianromero@users.sourceforge.net<br>\n<br>\nThis program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.<br>\n<br>\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.<br>\n<br>\nYou should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA<br></center>");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 100, 10, 100));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        m_jPanelLogin.add(jLabel1, java.awt.BorderLayout.CENTER);

        m_jLogonName.setLayout(new java.awt.BorderLayout());

        m_jLogonName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(510, 118));
        m_jLogonName.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        m_jClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/exit.png")));
        m_jClose.setText(AppLocal.getIntString("Button.Close"));
        m_jClose.setFocusPainted(false);
        m_jClose.setFocusable(false);
        m_jClose.setPreferredSize(new java.awt.Dimension(115, 35));
        m_jClose.setRequestFocusEnabled(false);
        m_jClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCloseActionPerformed(evt);
            }
        });

        jPanel8.add(m_jClose);

        jPanel2.add(jPanel8, java.awt.BorderLayout.NORTH);

        m_jLogonName.add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel5.add(m_jLogonName);

        m_jPanelLogin.add(jPanel5, java.awt.BorderLayout.SOUTH);

        m_jPanelContainer.add(m_jPanelLogin, "login");

        getContentPane().add(m_jPanelContainer, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        System.exit(0);
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        tryToClose();
        
    }//GEN-LAST:event_formWindowClosing

    private void m_jCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCloseActionPerformed

        tryToClose();
        
    }//GEN-LAST:event_m_jCloseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton m_jClose;
    private javax.swing.JLabel m_jHost;
    private javax.swing.JLabel m_jLblTitle;
    private javax.swing.JPanel m_jLogonName;
    private javax.swing.JPanel m_jPanelContainer;
    private javax.swing.JPanel m_jPanelDown;
    private javax.swing.JPanel m_jPanelLogin;
    private javax.swing.JPanel m_jPanelTitle;
    // End of variables declaration//GEN-END:variables
}