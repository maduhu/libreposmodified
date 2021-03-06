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

package net.adrianromero.tpv.panelsales.restaurant;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.*;
import java.util.*;

import net.adrianromero.beans.*;
import net.adrianromero.data.gui.*;
import net.adrianromero.data.loader.*;
import net.adrianromero.data.user.*;

import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.format.Formats;
import net.adrianromero.basic.BasicException;

public class JTicketsBagRestaurantRes extends javax.swing.JPanel implements EditorRecord {

    private AppView m_App;
    private JTicketsBagRestaurantMap m_restaurantmap;
    
    private DirtyManager m_Dirty;
    private String m_sID;
    private Date m_dCreated;
    private JTimePanel m_timereservation;
    private boolean m_bReceived;
    private BrowsableEditableData m_bd;
        
    private Date m_dcurrentday;
    
    private JCalendarPanel m_datepanel;    
    private JTimePanel m_timepanel;
    private boolean m_bpaintlock = false;

    // private Date dinitdate = new GregorianCalendar(1900, 0, 0, 12, 0).getTime();
    
    /** Creates new form JPanelReservations */
    public JTicketsBagRestaurantRes(AppView oApp, JTicketsBagRestaurantMap restaurantmap) {
        
        m_App = oApp;        
        m_restaurantmap = restaurantmap;
        
        m_dcurrentday = null;
        
        initComponents();
        
        m_datepanel = new JCalendarPanel();
        jPanelDate.add(m_datepanel, BorderLayout.CENTER);
        m_datepanel.addPropertyChangeListener("Date", new DateChangeCalendarListener());
        
        m_timepanel = new JTimePanel(null, JTimePanel.BUTTONS_HOUR);
        m_timepanel.setPeriod(3600000L); // Los milisegundos que tiene una hora.
        jPanelTime.add(m_timepanel, BorderLayout.CENTER);
        m_timepanel.addPropertyChangeListener("Date", new DateChangeTimeListener());
        
        m_timereservation = new JTimePanel(null, JTimePanel.BUTTONS_MINUTE);
        m_jPanelTime.add(m_timereservation, BorderLayout.CENTER);
        
        SentenceList sentload = new StaticSentence(oApp.getSession()
            , "SELECT ID, CREATED, DATENEW, TITLE, CHAIRS, ISDONE, DESCRIPTION FROM RESERVATIONS WHERE DATENEW >= ? AND DATENEW < ?"
            , new SerializerWriteBasic(new Datas[] {Datas.TIMESTAMP, Datas.TIMESTAMP})
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT, Datas.BOOLEAN, Datas.STRING}));            
            
        TableDefinition treservations = new TableDefinition(oApp.getSession(),
            "RESERVATIONS"
            , new String[] {"ID", "CREATED", "DATENEW", "TITLE", "CHAIRS", "ISDONE", "DESCRIPTION"}
            , new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.STRING, Datas.INT, Datas.BOOLEAN, Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.TIMESTAMP, Formats.TIMESTAMP, Formats.STRING, Formats.INT, Formats.BOOLEAN, Formats.STRING}
            , new int[] {0}
        );     
        
        m_jtxtTitle.addEditorKeys(m_jKeys);
        m_jtxtChairs.addEditorKeys(m_jKeys);
        m_jtxtDescription.addEditorKeys(m_jKeys);

        m_Dirty = new DirtyManager();
        m_timereservation.addPropertyChangeListener("Date", m_Dirty);
        m_jtxtTitle.addPropertyChangeListener("Text", m_Dirty);
        m_jtxtChairs.addPropertyChangeListener("Text", m_Dirty);
        m_jtxtDescription.addPropertyChangeListener("Text", m_Dirty);
        
        writeValueEOF();
        
        ListProvider lpr = new ListProviderCreator(sentload, new MyDateFilter());            
        SaveProvider spr = new SaveProvider(treservations);        
        
        m_bd = new BrowsableEditableData(lpr, spr, new CompareReservations(), this, m_Dirty);           
        
        JListNavigator nl = new JListNavigator(m_bd, true);
        nl.setCellRenderer(new JCalendarItemRenderer());  
        m_jPanelList.add(nl, BorderLayout.CENTER);
        
        // La Toolbar
        m_jToolbar.add(new JLabelDirty(m_Dirty));
        m_jToolbar.add(new JCounter(m_bd));
        m_jToolbar.add(new JNavigator(m_bd));
        m_jToolbar.add(new JSaver(m_bd));       
    }
    
    private class MyDateFilter implements EditorCreator {
        public Object createValue() throws BasicException {           
            return new Object[] {m_dcurrentday, new Date(m_dcurrentday.getTime() + 3600000L)};   // m_dcurrentday ya no tiene ni minutos, ni segundos.             
        }
    }
    
    public void activate() {
        reload(DateUtils.getTodayHours(new Date()));
    }
    
    public boolean deactivate() {
        try {
            return m_bd.actionClosingForm(this);
        } catch (BasicException eD) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.CannotMove"), eD);
            msg.show(this);
            return false;
        }
    }
    
    public void writeValueEOF() {
        m_sID = null;
        m_dCreated = null;
        m_timereservation.setDate(null);
        m_jtxtTitle.reset();
        m_jtxtChairs.reset();
        m_bReceived = false;
        m_jtxtDescription.reset();
        m_timereservation.setEnabled(false);
        m_jtxtTitle.setEnabled(false);
        m_jtxtChairs.setEnabled(false);
        m_jtxtDescription.setEnabled(false);
        m_jKeys.setEnabled(false);
        
        m_jbtnReceive.setEnabled(false);
    }    
    public void writeValueInsert() {
        m_sID = null;
        m_dCreated = null;
        m_timereservation.setCheckDates(m_dcurrentday, new Date(m_dcurrentday.getTime() + 3600000L));
        m_timereservation.setDate(m_dcurrentday);
        m_jtxtTitle.reset();
        m_jtxtChairs.setValueInteger(2);
        m_bReceived = false;
        m_jtxtDescription.reset();
        m_timereservation.setEnabled(true);
        m_jtxtTitle.setEnabled(true);
        m_jtxtChairs.setEnabled(true);
        m_jtxtDescription.setEnabled(true);
        m_jKeys.setEnabled(true);
        
        m_jbtnReceive.setEnabled(true);
        
        m_jtxtTitle.activate();
    }
    public void writeValueDelete(Object value) {
        Object[] res = (Object[]) value;
        m_sID = (String) res[0];
        m_dCreated = (Date) res[1];
        m_timereservation.setCheckDates(m_dcurrentday, new Date(m_dcurrentday.getTime() + 3600000L));
        m_timereservation.setDate((Date) res[2]);
        m_jtxtTitle.setText(Formats.STRING.formatValue(res[3]));
        m_jtxtChairs.setValueInteger(((Integer)res[4]).intValue());
        m_bReceived = ((Boolean)res[5]).booleanValue();
        m_jtxtDescription.setText(Formats.STRING.formatValue(res[6]));
        m_timereservation.setEnabled(false);
        m_jtxtTitle.setEnabled(false);
        m_jtxtChairs.setEnabled(false);
        m_jtxtDescription.setEnabled(false);
        m_jKeys.setEnabled(false);
        
        m_jbtnReceive.setEnabled(false); 
    }  
    public void writeValueEdit(Object value) {
        Object[] res = (Object[]) value;
        m_sID = (String) res[0];
        m_dCreated = (Date) res[1];
        m_timereservation.setCheckDates(m_dcurrentday, new Date(m_dcurrentday.getTime() + 3600000L));
        m_timereservation.setDate((Date) res[2]);
        m_jtxtTitle.setText(Formats.STRING.formatValue(res[3]));
        m_jtxtChairs.setValueInteger(((Integer)res[4]).intValue());
        m_bReceived = ((Boolean)res[5]).booleanValue();
        m_jtxtDescription.setText(Formats.STRING.formatValue(res[6]));
        m_timereservation.setEnabled(true);
        m_jtxtTitle.setEnabled(true);
        m_jtxtChairs.setEnabled(true);
        m_jtxtDescription.setEnabled(true);
        m_jKeys.setEnabled(true);

        m_jbtnReceive.setEnabled(!m_bReceived); // se habilita si no se ha recibido al cliente

        m_jtxtTitle.activate();
    }    

    public Object createValue() throws BasicException {
        
        Object[] res = new Object[7];
        
        res[0] = m_sID == null ? UUID.randomUUID().toString() : m_sID; 
        res[1] = m_dCreated == null ? new Date() : m_dCreated; 
        res[2] = m_timereservation.getDate();
        res[3] = m_jtxtTitle.getText();
        res[4] = new Integer(m_jtxtChairs.getValueInteger());
        res[5] = new Boolean(m_bReceived);
        res[6] = m_jtxtDescription.getText();

        return res;
    }    
    
    public Component getComponent() {
        return this;
    }  
    
    private static class CompareReservations implements Comparator {
        public int compare(Object o1, Object o2) {
            Object[] a1 = (Object[]) o1;
            Object[] a2 = (Object[]) o2;
            Date d1 = (Date) a1[2];
            Date d2 = (Date) a2[2];
            int c = d1.compareTo(d2);
            if (c == 0) {
                d1 = (Date) a1[1];
                d2 = (Date) a2[1];
                return d1.compareTo(d2);
            } else {
                return c;
            }
        }
    }
    
    private void reload(Date dDate) {
        
        if (!dDate.equals(m_dcurrentday)) {
   
            Date doldcurrentday = m_dcurrentday;
            m_dcurrentday = dDate;
            try {
                m_bd.actionLoad();
            } catch (BasicException eD) {
                MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.noreload"), eD);
                msg.show(this);
                m_dcurrentday = doldcurrentday; // nos retractamos...
            }
        }    
        
        // pinto la fecha del filtro...
        paintDate();
    }
    
    private void paintDate() {
        
        m_bpaintlock = true;
        m_datepanel.setDate(m_dcurrentday);
        m_timepanel.setDate(m_dcurrentday);
        m_bpaintlock = false;
    }
    
    private class DateChangeCalendarListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            if (!m_bpaintlock) {
                reload(DateUtils.getTodayHours(DateUtils.getDate(m_datepanel.getDate(), m_timepanel.getDate())));
            }
        }        
    }
        
    private class DateChangeTimeListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            if (!m_bpaintlock) {
                reload(DateUtils.getTodayHours(DateUtils.getDate(m_datepanel.getDate(), m_timepanel.getDate())));
            }
        }        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jPanelDate = new javax.swing.JPanel();
        jPanelTime = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jToolbarContainer = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        m_jbtnTables = new javax.swing.JButton();
        m_jbtnReceive = new javax.swing.JButton();
        m_jToolbar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        m_jPanelList = new javax.swing.JPanel();
        m_jPanelTime = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        m_jtxtDescription = new net.adrianromero.editor.JEditorString();
        m_jtxtChairs = new net.adrianromero.editor.JEditorIntegerPositive();
        m_jtxtTitle = new net.adrianromero.editor.JEditorString();
        jPanel5 = new javax.swing.JPanel();
        m_jKeys = new net.adrianromero.editor.JEditorKeys();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(10, 210));
        jPanelDate.setLayout(new java.awt.BorderLayout());

        jPanelDate.setPreferredSize(new java.awt.Dimension(310, 190));
        jPanel3.add(jPanelDate);

        jPanelTime.setLayout(new java.awt.BorderLayout());

        jPanelTime.setPreferredSize(new java.awt.Dimension(310, 190));
        jPanel3.add(jPanelTime);

        add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        m_jToolbarContainer.setLayout(new java.awt.BorderLayout());

        m_jbtnTables.setText(AppLocal.getIntString("button.tables"));
        m_jbtnTables.setFocusPainted(false);
        m_jbtnTables.setFocusable(false);
        m_jbtnTables.setRequestFocusEnabled(false);
        m_jbtnTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnTablesActionPerformed(evt);
            }
        });

        jPanel4.add(m_jbtnTables);

        m_jbtnReceive.setText(AppLocal.getIntString("button.receive"));
        m_jbtnReceive.setFocusPainted(false);
        m_jbtnReceive.setFocusable(false);
        m_jbtnReceive.setRequestFocusEnabled(false);
        m_jbtnReceive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnReceiveActionPerformed(evt);
            }
        });

        jPanel4.add(m_jbtnReceive);

        m_jToolbarContainer.add(jPanel4, java.awt.BorderLayout.WEST);

        m_jToolbarContainer.add(m_jToolbar, java.awt.BorderLayout.CENTER);

        jPanel2.add(m_jToolbarContainer, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(null);

        m_jPanelList.setLayout(new java.awt.BorderLayout());

        jPanel1.add(m_jPanelList);
        m_jPanelList.setBounds(10, 10, 250, 370);

        m_jPanelTime.setLayout(new java.awt.BorderLayout());

        jPanel1.add(m_jPanelTime);
        m_jPanelTime.setBounds(280, 30, 240, 120);

        jLabel1.setText(AppLocal.getIntString("rest.label.date"));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(280, 10, 240, 14);

        jLabel2.setText(AppLocal.getIntString("rest.label.customer"));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(280, 160, 240, 14);

        jLabel3.setText(AppLocal.getIntString("rest.label.chairs"));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(280, 210, 240, 14);

        jLabel4.setText(AppLocal.getIntString("rest.label.notes"));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(280, 260, 240, 14);

        jPanel1.add(m_jtxtDescription);
        m_jtxtDescription.setBounds(280, 280, 270, 80);

        jPanel1.add(m_jtxtChairs);
        m_jtxtChairs.setBounds(280, 230, 140, 25);

        jPanel1.add(m_jtxtTitle);
        m_jtxtTitle.setBounds(280, 180, 270, 25);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel5.add(m_jKeys, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel5, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void m_jbtnReceiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnReceiveActionPerformed
             
        
        // marco el cliente como recibido...
        m_bReceived = true;
        m_Dirty.setDirty(true);
        
        try {
            m_bd.saveData();
            m_restaurantmap.viewTables();                    
        } catch (BasicException eD) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, LocalRes.getIntString("message.nosave"), eD);
            msg.show(this);
        }       
        
    }//GEN-LAST:event_m_jbtnReceiveActionPerformed

    private void m_jbtnTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnTablesActionPerformed

        m_restaurantmap.viewTables();
        
    }//GEN-LAST:event_m_jbtnTablesActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelDate;
    private javax.swing.JPanel jPanelTime;
    private net.adrianromero.editor.JEditorKeys m_jKeys;
    private javax.swing.JPanel m_jPanelList;
    private javax.swing.JPanel m_jPanelTime;
    private javax.swing.JPanel m_jToolbar;
    private javax.swing.JPanel m_jToolbarContainer;
    private javax.swing.JButton m_jbtnReceive;
    private javax.swing.JButton m_jbtnTables;
    private net.adrianromero.editor.JEditorIntegerPositive m_jtxtChairs;
    private net.adrianromero.editor.JEditorString m_jtxtDescription;
    private net.adrianromero.editor.JEditorString m_jtxtTitle;
    // End of variables declaration//GEN-END:variables
    
}
