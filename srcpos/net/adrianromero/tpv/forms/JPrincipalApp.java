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

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import net.adrianromero.basic.BasicException;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.reflect.*;
import net.adrianromero.beans.RoundedBorder;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.data.gui.JMessageDialog;
import net.adrianromero.tpv.util.Hashcypher;

public class JPrincipalApp extends javax.swing.JPanel implements UserView {
    
    private JFrmTPV m_appview;
    private AppUser m_appuser;
    
    private JPrincipalNotificator m_principalnotificator;
    
    private JPanelView m_jLastView;    
    private Action m_actionfirst;
    
    private Map m_aCreatedViews;
    private Map<String, ProcessAction> m_aCreatedProcess;
        
    /** Creates new form JPrincipalApp */
    public JPrincipalApp(JFrmTPV appview, AppUser appuser) {
        
        m_appview = appview; 
        m_appuser = appuser;
        
        // Cargamos los permisos del usuario
        m_appuser.fillPermissions(m_appview);
        
        m_principalnotificator = new JPrincipalNotificator();
        
        m_actionfirst = null;
        m_jLastView = null;
        m_aCreatedViews = new HashMap();
        m_aCreatedProcess = new HashMap<String, ProcessAction>();
        
        initComponents();
        
        // m_jPanelTitle.setUI(new GradientUI());
        m_jPanelTitle.setBorder(RoundedBorder.createGradientBorder());
        m_jPanelTitle.setVisible(false);
        
        // Anado el panel nulo
        m_jPanelContainer.add(new JPanel(), "<NULL>");
        showView("<NULL>");       

        JTaskPane taskPane = new JTaskPane();
        JTaskPaneGroup taskGroup;
        MyMenu menunew;
        
        taskGroup = new JTaskPaneGroup();
        taskGroup.setFocusable(false);
        taskGroup.setRequestFocusEnabled(false);
        taskGroup.setTitle(AppLocal.getIntString("Menu.Main"));
        //taskGroup.setIcon();
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/mycomputer.png", "Menu.Ticket", "net.adrianromero.tpv.panelsales.JPanelTicketSales"));
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/mycomputer.png", "Menu.TicketEdit", "net.adrianromero.tpv.panelsales.JPanelTicketEdits"));
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/kontact.png", "Menu.QueryCard", "org.emar.prepaid.panel.JQueryCard"));
        // addTask(taskGroup, new MyAction("/net/adrianromero/images/mycomputer.png", "Menu.TicketRefund", "net.adrianromero.tpv.panelsales.JPanelTicketRefunds"));
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/mycomputer.png", "Menu.Payments", "net.adrianromero.tpv.panels.JPanelPayments"));        
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/mycomputer.png", "Menu.CloseTPV", "net.adrianromero.tpv.panels.JPanelCloseMoney"));        
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/appointment.png", "Menu.Closing", "net.adrianromero.tpv.reports.JReportClosedPos"));
        if (taskGroup.getContentPane().getComponentCount() > 0) taskPane.add(taskGroup);
        
        taskGroup = new JTaskPaneGroup();
        taskGroup.setFocusable(false);
        taskGroup.setRequestFocusEnabled(false);
        taskGroup.setTitle(AppLocal.getIntString("Menu.Backoffice"));
        menunew = new MyMenu("Menu.StockManagement"); 
        menunew.addMenuTitle("Menu.StockManagement.Edit");
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.Products", "net.adrianromero.tpv.inventory.ProductsPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.ProductsWarehouse", "net.adrianromero.tpv.inventory.ProductsWarehousePanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.Categories", "net.adrianromero.tpv.inventory.CategoriesPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.Taxes", "net.adrianromero.tpv.panels.JPanelTax"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.StockDiary", "net.adrianromero.tpv.inventory.StockDiaryPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.StockMovement", "net.adrianromero.tpv.inventory.StockManagement"));
        menunew.addMenuTitle("Menu.StockManagement.Reports");
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.Products", "net.adrianromero.tpv.reports.JReportProducts"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.Catalog", "net.adrianromero.tpv.reports.JReportCatalog"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.Inventory", "net.adrianromero.tpv.reports.JReportInventory"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.Inventory2", "net.adrianromero.tpv.reports.JReportInventory2"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.InventoryBroken", "net.adrianromero.tpv.reports.JReportInventoryBroken"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.InventoryDiff", "net.adrianromero.tpv.reports.JReportInventoryDiff"));
        addTask(taskGroup, menunew); 
        menunew = new MyMenu("Menu.SalesManagement");
        menunew.addMenuTitle("Menu.SalesManagement.Reports");
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.UserSells", "net.adrianromero.tpv.reports.JReportUserSales"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.ClosedProducts", "net.adrianromero.tpv.reports.JReportClosedProducts"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.ReportTaxes", "net.adrianromero.tpv.reports.JReportTaxes"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/cakes3d.png", "Menu.SalesChart", "net.adrianromero.tpv.reports.JChartSales"));
        addTask(taskGroup, menunew); 
        menunew = new MyMenu("Menu.Maintenance");
        // marcelo andrade
        menunew.addMenuTitle("Menu.Maintenance.PREPAID");        
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/yast_group_add.png", "Menu.Clientes", "org.emar.prepaid.panel.PanelCliente"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/kontact.png", "Menu.Tarjetas", "org.emar.prepaid.panel.PanelTarjeta"));
        menunew.addMenuItem(new ExecuteProcessAction("/net/adrianromero/images/openbravo.png", "Menu.Recargas", "org.emar.prepaid.RecargaMasiva"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/appointment.png", "Menu.ReporteSaldos", "org.emar.prepaid.reports.JReportSaldos"));
        menunew.addMenuTitle("Menu.Maintenance.POS");        
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/kdmconfig.png", "Menu.Users", "net.adrianromero.tpv.admin.PeoplePanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/yast_group_add.png", "Menu.Roles", "net.adrianromero.tpv.admin.RolesPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/clipart.png", "Menu.Resources", "net.adrianromero.tpv.admin.ResourcesPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/gohome.png", "Menu.Locations", "net.adrianromero.tpv.inventory.LocationsPanel"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.Floors", "net.adrianromero.tpv.mant.JPanelFloors"));
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.Tables", "net.adrianromero.tpv.mant.JPanelPlaces"));
        menunew.addMenuTitle("Menu.Maintenance.ERP");        
        menunew.addMenuItem(new ExecuteProcessAction("/net/adrianromero/images/openbravo.png", "Menu.ERPProducts", "com.openbravo.possync.ProductsSync"));
        menunew.addMenuItem(new ExecuteProcessAction("/net/adrianromero/images/openbravo.png", "Menu.ERPOrders", "com.openbravo.possync.OrdersSync"));
        addTask(taskGroup, menunew); 
        menunew = new MyMenu("Menu.ThirdParties");
        menunew.addMenuItem(new PanelAction("/net/adrianromero/images/bookmark.png", "Menu.ThirdPartiesManagement", "net.adrianromero.tpv.thirdparties.ThirdPartiesPanel"));
        addTask(taskGroup, menunew); 
        if (taskGroup.getContentPane().getComponentCount() > 0) taskPane.add(taskGroup);
                
        taskGroup = new JTaskPaneGroup();
        taskGroup.setFocusable(false);
        taskGroup.setRequestFocusEnabled(false);
        taskGroup.setTitle(AppLocal.getIntString("Menu.System"));
        //taskGroup.setIcon();
        addTask(taskGroup, new ChangePasswordAction("/net/adrianromero/images/yast_security.png", "Menu.ChangePassword"));
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/package_settings.png", "Menu.Configuration", "net.adrianromero.tpv.config.JPanelConfiguration"));
        addTask(taskGroup, new PanelAction("/net/adrianromero/images/fileprint.png", "Menu.Printer", "net.adrianromero.tpv.panels.JPanelPrinter"));
        addTask(taskGroup, new ExitAction("/net/adrianromero/images/gohome.png", "Menu.Exit"));
        if (taskGroup.getContentPane().getComponentCount() > 0) taskPane.add(taskGroup);
        
        m_jPanelLeft.setViewportView(taskPane);
        
        // arranco la primera opcion
        if (m_actionfirst != null) {
            m_actionfirst.actionPerformed(null);
            m_actionfirst = null;
        }       
    }
       
    private void addTask(JTaskPaneGroup elem, Action act) {    
        if (m_appuser.hasPermission((String) act.getValue(UserView.ACTION_TASKNAME))) {
            // Si tenemos permisos la anadimos...
            Component c = elem.add(act);
            c.setFocusable(false);
            //c.setRequestFocusEnabled(false);            
            
            if (m_actionfirst == null) {
                m_actionfirst = act;
            }
        }        
    }
    
    public JComponent getNotificator() {
        return m_principalnotificator;
    }
    
    public boolean deactivate() {
        if (m_jLastView == null) {
            return true;
        } else if (m_jLastView.deactivate()) {
            m_jLastView = null;
            showView("<NULL>");       
            return true;
        } else {
            return false;
        }
        
    }
    
    // Un menu
    private class MyMenu extends AbstractAction {
        
        private MenuDefinition m_menu;
        
        public MyMenu(String keytext) {
            
            m_menu = new MenuDefinition(keytext);
            
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource("/net/adrianromero/images/contents.png")));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(UserView.ACTION_TASKNAME, keytext);
        }
        
        public void addMenuTitle(String keytext) {
            m_menu.addMenuTitle(keytext);
        }
       
        public void addMenuItem(Action act) {
            m_menu.addMenuItem(act);
        }
        
        public void actionPerformed(ActionEvent evt) {
            
            showTask("net.adrianromero.tpv.forms.JPanelMenu", m_menu);            
        }
    }
    
    // La accion del selector
    private class PanelAction extends AbstractAction {
        
        private String m_sMyView;
        
        public PanelAction(String icon, String keytext, String sMyView) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(UserView.ACTION_TASKNAME, sMyView);
            m_sMyView = sMyView;
        }
        public void actionPerformed(ActionEvent evt) {
            
            showTask(m_sMyView, null);            
        }
    }
    
    // La accion del selector
    private class ExecuteProcessAction extends AbstractAction {
        
        private String m_sMyView;
        
        public ExecuteProcessAction(String icon, String keytext, String sMyView) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(UserView.ACTION_TASKNAME, sMyView);
            m_sMyView = sMyView;
        }
        public void actionPerformed(ActionEvent evt) {

            m_appview.waitCursorBegin();       

            if (m_appuser.hasPermission(m_sMyView)) {       
                ProcessAction myProcess = m_aCreatedProcess.get(m_sMyView);
                
                try {
                    // Primero me construyo
                    if (myProcess == null) {

                        Class<ProcessAction> viewClass = (Class<ProcessAction>) Class.forName(m_sMyView);

                        Constructor<ProcessAction> constMyView = null;

                        try {
                            constMyView = viewClass.getConstructor(new Class[] {AppView.class, UserView.class});
                            myProcess = constMyView.newInstance(new Object[] {m_appview, JPrincipalApp.this});
                        } catch (NoSuchMethodException e) {
                        }

                        // 3er constructor y ultima oportunidad, por ello no va en try-catch
                        if (constMyView == null) {
                            constMyView = viewClass.getConstructor(new Class[] {AppView.class});
                            myProcess = constMyView.newInstance(new Object[] {m_appview});
                        }
                        
                        m_aCreatedProcess.put(m_sMyView, myProcess);
                    }
                    
                    // ejecuto la tarea
                    try {
                        MessageInf m = myProcess.execute();    
                        if (m != null) {
                            // si devuelve un mensaje, lo muestro
                            JMessageDialog.showMessage(JPrincipalApp.this, m);
                        }
                    } catch (BasicException eb) {
                        // Si se produce un error lo muestro.
                        JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(eb));            
                    }
                    
                } catch (Exception e) {
                    JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("Label.LoadError"), e));            
//                } catch (ClassNotFoundException eCNF) {
//                } catch (NoSuchMethodException eNSM) {
//                } catch (SecurityException eS) {
//                } catch (InstantiationException eI) {
//                } catch (IllegalAccessException eIA) {
//                } catch (IllegalArgumentException eIAr) {
//                } catch (InvocationTargetException eIT) {
                }

            } else  {
                // No hay permisos para ejecutar la accion...
                JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notpermissions")));            
            }
           m_appview.waitCursorEnd();     
        }
    }
    
    private class ExitAction extends AbstractAction {
        
        public ExitAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(UserView.ACTION_TASKNAME, keytext);
        }
        public void actionPerformed(ActionEvent evt) {
            m_appview.closeAppView(JPrincipalApp.this);
        }
    }
    
    
    // La accion de cambio de password..
    private class ChangePasswordAction extends AbstractAction {
        public ChangePasswordAction(String icon, String keytext) {
            putValue(Action.SMALL_ICON, new ImageIcon(JPrincipalApp.class.getResource(icon)));
            putValue(Action.NAME, AppLocal.getIntString(keytext));
            putValue(UserView.ACTION_TASKNAME, keytext);

        }
        public void actionPerformed(ActionEvent evt) {
                       
            String sNewPassword = Hashcypher.changePassword(JPrincipalApp.this, m_appuser.getPassword());
//            String sNewPassword = JDlgChangePassword.showMessage(JPrincipalApp.this, m_appuser.getPassword());
            if (sNewPassword != null) {
                try {
                    m_appview.lookupDataLogic(DataLogicSystem.class).execChangePassword(new Object[] {sNewPassword, m_appuser.getId()});
                    m_appuser.setPassword(sNewPassword);
                } catch (BasicException e) {
                    JMessageDialog.showMessage(JPrincipalApp.this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotchangepassword")));             
                }
            }
        }
    }
    
    private void showView(String sView) {
        CardLayout cl = (CardLayout)(m_jPanelContainer.getLayout());
        cl.show(m_jPanelContainer, sView);       
    }
    
    public AppUser getUser() {
        return m_appuser;
    }
    
    public boolean showTask(String sTaskClass, Object params) {
         
        m_appview.waitCursorBegin();       
         
        if (m_appuser.hasPermission(sTaskClass)) {       
            JPanelView m_jMyView = (JPanelView) m_aCreatedViews.get(sTaskClass);

            // cierro la antigua
            if ((m_jLastView != null && m_jMyView != m_jLastView && m_jLastView.deactivate())
                || m_jLastView == null) {

                // Primero me construyo
                if (m_jMyView == null) {
                    try {
                        Class viewClass = Class.forName(sTaskClass);
                        
                        Constructor constMyView = null;
                        
                        // 1er constructor 
                        try {
                            constMyView = viewClass.getConstructor(new Class[] {AppView.class, UserView.class, Object.class});
                            m_jMyView = (JPanelView) constMyView.newInstance(new Object[] {m_appview, this, params});
                        } catch (NoSuchMethodException e) {
                        }
                        
                        // 2 constructor
                        if (constMyView == null) {
                            try {
                                constMyView = viewClass.getConstructor(new Class[] {AppView.class, UserView.class});
                                m_jMyView = (JPanelView) constMyView.newInstance(new Object[] {m_appview, this});
                            } catch (NoSuchMethodException e) {
                            }
                        
                            // 3er constructor y ultima oportunidad, por ello no va en try-catch
                            if (constMyView == null) {
                                constMyView = viewClass.getConstructor(new Class[] {AppView.class});
                                m_jMyView = (JPanelView) constMyView.newInstance(new Object[] {m_appview});
                            }
                        }

                    } catch (Exception e) {
                        m_jMyView = new JPanelNull(m_appview, e);
    //                } catch (ClassNotFoundException eCNF) {
    //                } catch (NoSuchMethodException eNSM) {
    //                } catch (SecurityException eS) {
    //                } catch (InstantiationException eI) {
    //                } catch (IllegalAccessException eIA) {
    //                } catch (IllegalArgumentException eIAr) {
    //                } catch (InvocationTargetException eIT) {
                    }
                    
                    m_jPanelContainer.add(m_jMyView.getComponent(), sTaskClass);
                    m_aCreatedViews.put(m_jMyView, sTaskClass);
                }
                
                // ejecuto la tarea
                try {
                    m_jMyView.activate();
                } catch (BasicException e) {
                    JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notactive"), e));            
                }

                // se tiene que mostrar el panel                
                m_jLastView = m_jMyView;

                showView(sTaskClass);   
                // Y ahora que he cerrado la antigua me abro yo            
                String sTitle = m_jMyView.getTitle();
                m_jPanelTitle.setVisible(sTitle != null);
                m_jTitle.setText(sTitle);

                m_appview.waitCursorEnd();     
                return true;
            } else {
                m_appview.waitCursorEnd();       
                return false;
            }
        } else  {
            // No hay permisos para ejecutar la accion...
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.notpermissions")));            
            m_appview.waitCursorEnd();       
            return false;
        }
    }
    
    private class JPrincipalNotificator extends javax.swing.JPanel {
        
        private javax.swing.JLabel jLabel1;  
        
        public JPrincipalNotificator() {

            initComponents();

            jLabel1.setText(m_appuser.getName());
            jLabel1.setIcon(m_appuser.getIcon());
        }

        private void initComponents() {
            jLabel1 = new javax.swing.JLabel();

            setLayout(new java.awt.BorderLayout());
            setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray), new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 5, 1, 5))));
            add(jLabel1, java.awt.BorderLayout.WEST);
        }                                        
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        m_jPanelLeft = new javax.swing.JScrollPane();
        m_jPanelRight = new javax.swing.JPanel();
        m_jPanelTitle = new javax.swing.JPanel();
        m_jTitle = new javax.swing.JLabel();
        m_jPanelContainer = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(220);
        jSplitPane1.setDividerSize(8);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.add(m_jPanelLeft, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel1);

        m_jPanelRight.setLayout(new java.awt.BorderLayout());

        m_jPanelTitle.setLayout(new java.awt.BorderLayout());

        m_jTitle.setFont(new java.awt.Font("SansSerif", 1, 18));
        m_jTitle.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        m_jPanelTitle.add(m_jTitle, java.awt.BorderLayout.NORTH);

        m_jPanelRight.add(m_jPanelTitle, java.awt.BorderLayout.NORTH);

        m_jPanelContainer.setLayout(new java.awt.CardLayout());

        m_jPanelRight.add(m_jPanelContainer, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(m_jPanelRight);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel m_jPanelContainer;
    private javax.swing.JScrollPane m_jPanelLeft;
    private javax.swing.JPanel m_jPanelRight;
    private javax.swing.JPanel m_jPanelTitle;
    private javax.swing.JLabel m_jTitle;
    // End of variables declaration//GEN-END:variables
    
}
