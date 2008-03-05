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

package org.emar.prepaid.editor;

import java.awt.Component;
import java.util.Date;
import java.util.UUID;
import javax.swing.*;
import net.adrianromero.beans.DateUtils;
import net.adrianromero.beans.JCalendarDialog;

import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.format.Formats;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.DirtyManager;

public class ClientEditor extends JPanel implements EditorRecord {
    
    private Object m_oId;
    
    /** Creates new form ClientEditor */
    public ClientEditor(DirtyManager dirty) {
        initComponents();

        m_jId.getDocument().addDocumentListener(dirty);
        m_jNombre.getDocument().addDocumentListener(dirty);
        m_jApellido.getDocument().addDocumentListener(dirty);
        m_jObservaciones.getDocument().addDocumentListener(dirty);
        m_jFechaCreacion.getDocument().addDocumentListener(dirty);
        
        
        writeValueEOF();
    }
    public void writeValueEOF() {
        m_oId = null;
        m_jId.setText(null);
        m_jNombre.setText(null);
        m_jApellido.setText(null);
        m_jObservaciones.setText(null);
        m_jFechaCreacion.setText(null);
        
        m_jId.setEnabled(false);
        m_jNombre.setEnabled(false);
        m_jApellido.setEnabled(false);
        m_jObservaciones.setEnabled(false);
        m_jFechaCreacion.setEnabled(false);
    }
    public void writeValueInsert() {
        m_oId = null;
        m_jId.setText(null);
        m_jNombre.setText(null);
        m_jApellido.setText(null);
        m_jObservaciones.setText(null);
        m_jFechaCreacion.setText(Formats.TIMESTAMP.formatValue(DateUtils.getTodayMinutes()));
        
        m_jId.setEnabled(true);
        m_jNombre.setEnabled(true);
        m_jApellido.setEnabled(true);
        m_jObservaciones.setEnabled(true);
        m_jFechaCreacion.setEnabled(true);
        
    }
    public void writeValueDelete(Object value) {

        Object[] cliente = (Object[]) value;
        m_oId = cliente[0];
        m_jId.setText(Formats.STRING.formatValue(m_oId));
        m_jNombre.setText(Formats.STRING.formatValue(cliente[1]));
        m_jApellido.setText(Formats.STRING.formatValue(cliente[2]));
        m_jObservaciones.setText(Formats.STRING.formatValue(cliente[3]));
        m_jFechaCreacion.setText(Formats.TIMESTAMP.formatValue(cliente[4]));
        
        
        m_jId.setEnabled(false);
        m_jNombre.setEnabled(false);
        m_jApellido.setEnabled(false);
        m_jObservaciones.setEnabled(false);
        m_jFechaCreacion.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {

        Object[] cliente = (Object[]) value;
        m_oId = cliente[0];
        
        m_jId.setText(Formats.STRING.formatValue(m_oId));
        m_jNombre.setText(Formats.STRING.formatValue(cliente[1]));
        m_jApellido.setText(Formats.STRING.formatValue(cliente[2]));
        m_jObservaciones.setText(Formats.STRING.formatValue(cliente[3]));
        m_jFechaCreacion.setText(Formats.TIMESTAMP.formatValue(cliente[4]));
        
        
        m_jId.setEnabled(true);
        m_jNombre.setEnabled(true);
        m_jApellido.setEnabled(true);
        m_jObservaciones.setEnabled(true);
        m_jFechaCreacion.setEnabled(true);
    }

    public Object createValue() throws BasicException {
        
        Object[] cliente = new Object[6];

        cliente[0] = m_jId.getText();
        cliente[1] = m_jNombre.getText();
        cliente[2] = m_jApellido.getText();
        cliente[3] = m_jObservaciones.getText();
        cliente[4] = Formats.TIMESTAMP.parseValue(m_jFechaCreacion.getText());

        return cliente;
    }    
     
    public Component getComponent() {
        return this;
    }
    
    private void m_jbtndateActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(m_jFechaCreacion.getText());
        } catch (BasicException e) {
            date = null;
        }        
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            m_jFechaCreacion.setText(Formats.TIMESTAMP.formatValue(date));
        }
        
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {        
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        m_jId = new javax.swing.JTextField();
        m_jNombre = new javax.swing.JTextField();
        m_jApellido = new javax.swing.JTextField();
        m_jObservaciones = new javax.swing.JTextField();
        m_jFechaCreacion = new javax.swing.JTextField();
        m_jbtndate = new javax.swing.JButton();
        

        setLayout(null);

        jLabel2.setText("Cédula/ID");
        add(jLabel2);
        jLabel2.setBounds(20, 20, 100, 15);
        add(m_jId);
        m_jId.setBounds(120, 20, 150, 19);
    
        jLabel3.setText("Nombre");
        add(jLabel3);
        jLabel3.setBounds(20, 50, 100, 15);
        add(m_jNombre);
        m_jNombre.setBounds(120, 50, 200, 19);
        
        jLabel4.setText("Apellido");
        add(jLabel4);
        jLabel4.setBounds(20, 80, 100, 15);
        add(m_jApellido);
        m_jApellido.setBounds(120, 80, 200, 19);
        
        jLabel5.setText("Observaciones");
        add(jLabel5);
        jLabel5.setBounds(20, 110, 100, 15);
        add(m_jObservaciones);
        m_jObservaciones.setBounds(120, 110, 200, 19);
        
        jLabel6.setText("Fecha Creación");
        add(jLabel6);
        jLabel6.setBounds(20, 140, 100, 15);
        add(m_jFechaCreacion);
        m_jFechaCreacion.setBounds(120, 140, 200, 19);
        
        m_jbtndate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/date.png")));
        m_jbtndate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtndateActionPerformed(evt);
            }
        });

        add(m_jbtndate);
        m_jbtndate.setBounds(350, 140, 40, 25);
        
    }// </editor-fold>                        
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;    

    private javax.swing.JTextField m_jId;    
    private javax.swing.JTextField m_jNombre;
    private javax.swing.JTextField m_jApellido;
    private javax.swing.JTextField m_jObservaciones;
    private javax.swing.JTextField m_jFechaCreacion;
    
    private javax.swing.JButton m_jbtndate;    
    // End of variables declaration                   
    
}
