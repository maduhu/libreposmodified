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

/*
 * JMessageDialog.java
 *
 * Created on 18 de marzo de 2004, 13:31
 */

package net.adrianromero.data.gui;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import net.adrianromero.data.loader.LocalRes;
/**
 *
 * @author  adrian
 */
public class JMessageDialog extends javax.swing.JDialog {
    
    /** Creates new form JMessageDialog */
    private JMessageDialog(java.awt.Frame parent, boolean modal) {        
        super(parent, modal);       
    }
    /** Creates new form JMessageDialog */
    private JMessageDialog(java.awt.Dialog parent, boolean modal) {        
        super(parent, modal);       
    }
    
    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }
    
    public static void showMessage(Component parent, MessageInf inf) {
        
        Window window = getWindow(parent);      
        
        JMessageDialog myMsg;
        if (window instanceof Frame) { 
            myMsg = new JMessageDialog((Frame) window, true);
        } else {
            myMsg = new JMessageDialog((Dialog) window, true);
        }
        
        myMsg.initComponents();
        myMsg.jscrException.setVisible(false);
        
        myMsg.getRootPane().setDefaultButton(myMsg.jcmdOK);
        
        myMsg.jlblIcon.setIcon(inf.getSignalWordIcon());
        myMsg.jlblErrorCode.setText(inf.getErrorCodeMsg());
        myMsg.jlblMessage.setText("<html>" + inf.getMessageMsg());
        
        // Capturamos el texto de la excepcion...
        if (inf.getCause() == null) {
            myMsg.jtxtException.setText(null);
        } else {            
            StringBuffer sb = new StringBuffer(); 
            
            if (inf.getCause() instanceof Throwable) {
                Throwable t = (Throwable) inf.getCause();
                while (t != null) {
                    sb.append(t.getClass().getName());
                    sb.append(": \n");
                    sb.append(t.getMessage());
                    sb.append("\n\n");
                    t = t.getCause();
                }
            } else if (inf.getCause() instanceof Throwable[]) {
                Throwable[] m_aExceptions = (Throwable[]) inf.getCause();
                for (int i = 0; i < m_aExceptions.length; i++) {
                    sb.append(m_aExceptions[i].getClass().getName());
                    sb.append(": \n");
                    sb.append(m_aExceptions[i].getMessage());
                    sb.append("\n\n");
                }             
            } else if (inf.getCause() instanceof Object[]) {
                Object [] m_aObjects = (Object []) inf.getCause();
                for (int i = 0; i < m_aObjects.length; i++) {
                    sb.append(m_aObjects[i].toString());
                    sb.append("\n\n");
                }             
            } else if (inf.getCause() instanceof String) {
                sb.append(inf.getCause().toString());
            } else {
                sb.append(inf.getCause().getClass().getName());
                sb.append(": \n");
                sb.append(inf.getCause().toString());
            }
            myMsg.jtxtException.setText(sb.toString());  
        }       
        myMsg.jtxtException.setCaretPosition(0);            
        
        //myMsg.show();
        myMsg.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jlblErrorCode = new javax.swing.JLabel();
        jlblMessage = new javax.swing.JLabel();
        jscrException = new javax.swing.JScrollPane();
        jtxtException = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jcmdOK = new javax.swing.JButton();
        jcmdMore = new javax.swing.JButton();
        jlblIcon = new javax.swing.JLabel();

        setTitle(LocalRes.getIntString("title.message"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(null);

        jlblErrorCode.setFont(new java.awt.Font("Dialog", 0, 10));
        jlblErrorCode.setText("jlblErrorCode");
        jPanel1.add(jlblErrorCode);
        jlblErrorCode.setBounds(10, 10, 280, 14);

        jlblMessage.setText("jlblMessage");
        jlblMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jlblMessage);
        jlblMessage.setBounds(10, 30, 280, 80);

        jtxtException.setEditable(false);
        jscrException.setViewportView(jtxtException);

        jPanel1.add(jscrException);
        jscrException.setBounds(0, 120, 390, 110);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jcmdOK.setText(LocalRes.getIntString("button.ok"));
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });

        jPanel2.add(jcmdOK);

        jcmdMore.setText(LocalRes.getIntString("button.information"));
        jcmdMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdMoreActionPerformed(evt);
            }
        });

        jPanel2.add(jcmdMore);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        jlblIcon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlblIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(jlblIcon, java.awt.BorderLayout.WEST);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-463)/2, (screenSize.height-181)/2, 463, 181);
    }// </editor-fold>//GEN-END:initComponents

    private void jcmdMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdMoreActionPerformed
        
        // Add your handling code here:
        jcmdMore.setEnabled(false);
        jscrException.setVisible(true);
        setSize(getWidth(), 310);
        validate();
        
    }//GEN-LAST:event_jcmdMoreActionPerformed

    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed
        // Add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jcmdOKActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jcmdMore;
    private javax.swing.JButton jcmdOK;
    private javax.swing.JLabel jlblErrorCode;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblMessage;
    private javax.swing.JScrollPane jscrException;
    private javax.swing.JTextArea jtxtException;
    // End of variables declaration//GEN-END:variables
    
}
