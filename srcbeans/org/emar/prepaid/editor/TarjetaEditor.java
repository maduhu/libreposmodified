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
import javax.swing.*;
import net.adrianromero.beans.DateUtils;
import net.adrianromero.beans.JCalendarDialog;

import net.adrianromero.format.Formats;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.DirtyManager;
import net.adrianromero.tpv.forms.AppViewConnection;
import org.emar.prepaid.MovimientosTarjeta;
import org.emar.prepaid.Tarjeta;

public class TarjetaEditor extends JPanel implements EditorRecord {
    
    private Object m_oId;
    
    /** Creates new form ClientEditor */
    public TarjetaEditor(DirtyManager dirty) {
        initComponents();

        m_jId.getDocument().addDocumentListener(dirty);
        m_jFechaVenta.getDocument().addDocumentListener(dirty);
        m_jFechaCaduca.getDocument().addDocumentListener(dirty);
        m_jSaldo.getDocument().addDocumentListener(dirty);
        m_jCliente.getDocument().addDocumentListener(dirty);
        m_jEstado.getDocument().addDocumentListener(dirty);
        m_jRecargable.getDocument().addDocumentListener(dirty);
        
        writeValueEOF();
    }
    public void writeValueEOF() {
        m_oId = null;
        m_jId.setText(null);
        m_jFechaVenta.setText(null);
        m_jFechaCaduca.setText(null);
        m_jSaldo.setText(null);
        m_jCliente.setText(null);
        m_jEstado.setText(null);
        m_jRecargable.setText(null);
        
        m_jId.setEnabled(false);        
        m_jFechaVenta.setEnabled(false);
        m_jFechaCaduca.setEnabled(false);
        m_jSaldo.setEnabled(false);
        m_jCliente.setEnabled(false);
        m_jEstado.setEnabled(false);
        m_jRecargable.setEnabled(false);
    }
    public void writeValueInsert() {
        m_oId = null;
        m_jId.setText(null);
        m_jFechaVenta.setText(Formats.TIMESTAMP.formatValue(DateUtils.getTodayMinutes()));
        m_jFechaCaduca.setText(null);
        m_jSaldo.setText("0");
        m_jCliente.setText(null);
        m_jEstado.setText("A");
        m_jRecargable.setText("N");
        
        m_jId.setEnabled(true);        
        m_jFechaVenta.setEnabled(false);
        m_jFechaCaduca.setEnabled(true);
        m_jSaldo.setEnabled(false);
        m_jCliente.setEnabled(true);
        m_jEstado.setEnabled(true);
        m_jRecargable.setEnabled(true);
        
        
    }
    public void writeValueDelete(Object value) {

        Object[] tarjeta = (Object[]) value;
        m_oId = tarjeta[0];
        m_jId.setText(Formats.STRING.formatValue(m_oId));
        m_jFechaVenta.setText(Formats.TIMESTAMP.formatValue(tarjeta[1]));
        m_jFechaCaduca.setText(Formats.TIMESTAMP.formatValue(tarjeta[2]));
        m_jSaldo.setText(Formats.DOUBLE.formatValue(tarjeta[3]));
        m_jCliente.setText(Formats.STRING.formatValue(tarjeta[4]));
        m_jEstado.setText(Formats.STRING.formatValue(tarjeta[5]));
        m_jRecargable.setText(Formats.STRING.formatValue(tarjeta[6]));
        
        m_jId.setEnabled(false);        
        m_jFechaVenta.setEnabled(false);
        m_jFechaCaduca.setEnabled(false);
        m_jSaldo.setEnabled(false);
        m_jCliente.setEnabled(false);
        m_jEstado.setEnabled(false);
        m_jRecargable.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {

        Object[] tarjeta = (Object[]) value;
        m_oId = tarjeta[0];
         m_jId.setText(Formats.STRING.formatValue(m_oId));
        m_jFechaVenta.setText(Formats.TIMESTAMP.formatValue(tarjeta[1]));
        m_jFechaCaduca.setText(Formats.TIMESTAMP.formatValue(tarjeta[2]));
        m_jSaldo.setText(Formats.DOUBLE.formatValue(tarjeta[3]));
        m_jCliente.setText((String)tarjeta[4]);
        m_jEstado.setText(Formats.STRING.formatValue(tarjeta[5]));
        m_jRecargable.setText(Formats.STRING.formatValue(tarjeta[6]));
        
        
        m_jId.setEnabled(true);        
        m_jFechaVenta.setEnabled(false);
        m_jFechaCaduca.setEnabled(true);
        m_jSaldo.setEnabled(false);
        m_jCliente.setEnabled(true);
        m_jEstado.setEnabled(true);
        m_jRecargable.setEnabled(true);
    }

    public Object createValue() throws BasicException {
        
        Object[] tarjeta = new Object[7];

        tarjeta[0] = m_jId.getText();
        tarjeta[1] = Formats.TIMESTAMP.parseValue(m_jFechaVenta.getText());
        tarjeta[2] = Formats.TIMESTAMP.parseValue(m_jFechaCaduca.getText());
        tarjeta[3] = Formats.DOUBLE.parseValue(m_jSaldo.getText());
        tarjeta[4] = m_jCliente.getText();
        tarjeta[5] = m_jEstado.getText().trim();
        tarjeta[6] = m_jRecargable.getText().trim();
        
        if ("I".equals(m_jEstado.getText()))
        {
            // mover saldo
            JSearchCard jsc = new JSearchCard();
            jsc.setVisible(true);
            if ("".equals(jsc.getM_iNumeroTarjeta()))
            {
                return null;
            }
            else
            {
                Tarjeta card2 = Tarjeta.findId(new AppViewConnection().getSession(), jsc.getM_iNumeroTarjeta());
                card2.recargar(((Double)Formats.DOUBLE.parseValue(m_jSaldo.getText())).doubleValue());
                tarjeta[3] = Formats.DOUBLE.parseValue("0.0");
                MovimientosTarjeta movTar = new MovimientosTarjeta();
                movTar.setFecha_movimiento(new Date());
                movTar.setId_tarjeta(card2.getIdentificador());
                movTar.setObservacion("traspaso-desde-"+m_jId.getText());
                movTar.setUsuario("generico");
                movTar.setValor_transaccion(((Double)Formats.DOUBLE.parseValue(m_jSaldo.getText())).doubleValue());
                Tarjeta.save(new AppViewConnection().getSession(),card2,movTar);
            }
        }
            

        return tarjeta;
    }    
     
    public Component getComponent() {
        return this;
    }
    
    private void m_jbtnDate1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(m_jFechaVenta.getText());
        } catch (BasicException e) {
            date = null;
        }        
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            m_jFechaVenta.setText(Formats.TIMESTAMP.formatValue(date));
        }
        
    }    
    
    private void m_jbtnDate2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        Date date;
        try {
            date = (Date) Formats.TIMESTAMP.parseValue(m_jFechaCaduca.getText());
        } catch (BasicException e) {
            date = null;
        }        
        date = JCalendarDialog.showCalendarTime(this, date);
        if (date != null) {
            m_jFechaCaduca.setText(Formats.TIMESTAMP.formatValue(date));
        }
        
    }    
    
    
    private void m_jbtnClienteActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        JSearchCliente scl = new JSearchCliente();
        scl.setVisible(true);
        if ( scl.isIsOk() )
        {
            m_jCliente.setText(scl.getIdCliente());
        }
        
    }   
    
    private void m_jbtnAnularActionPerformed(java.awt.event.ActionEvent evt) {                                           
        m_jEstado.setText("I");                
    }   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        m_jId = new javax.swing.JTextField();
        m_jFechaVenta = new javax.swing.JTextField();
        m_jFechaCaduca = new javax.swing.JTextField();
        m_jSaldo = new javax.swing.JTextField();
        m_jCliente = new javax.swing.JTextField();
        m_jEstado = new javax.swing.JTextField();
        m_jRecargable = new javax.swing.JTextField();
        m_jbtnDate1 = new javax.swing.JButton();
        m_jbtnDate2 = new javax.swing.JButton();
        m_jbtnCliente = new javax.swing.JButton();
        m_jbtnAnular = new javax.swing.JButton();
        

        setLayout(null);
        
        jLabel1.setText("ID-Pasar por el lector");
        add(jLabel1);
        jLabel1.setBounds(20, 20, 150, 15);        
        add(m_jId);
        m_jId.setBounds(180, 20, 150, 19);
        
        jLabel2.setText("Fecha Venta");
        add(jLabel2);
        jLabel2.setBounds(20, 50, 100, 15);
        add(m_jFechaVenta);
        m_jFechaVenta.setBounds(180, 50, 200, 19);        
        m_jbtnDate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/date.png")));
        m_jbtnDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnDate1ActionPerformed(evt);
            }
        });
        add(m_jbtnDate1);
        m_jbtnDate1.setBounds(390, 50, 40, 25);
        
        jLabel3.setText("Fecha Caduca (Vacio si no caduca)");
        add(jLabel3);
        jLabel3.setBounds(20, 80, 160, 15);
        add(m_jFechaCaduca);
        m_jFechaCaduca.setBounds(180, 80, 200, 19);        
        m_jbtnDate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/date.png")));
        m_jbtnDate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnDate2ActionPerformed(evt);
            }
        });
        add(m_jbtnDate2);
        m_jbtnDate2.setBounds(390, 80, 40, 25);
        
        jLabel4.setText("Saldo Inicial");
        add(jLabel4);
        jLabel4.setBounds(20, 110, 100, 15);        
        add(m_jSaldo);
        m_jSaldo.setBounds(180, 110, 150, 19);
        
        jLabel5.setText("Cliente Dueño");
        add(jLabel5);
        jLabel5.setBounds(20, 140, 100, 15);        
        add(m_jCliente);
        m_jCliente.setBounds(180, 140, 150, 19);
        m_jCliente.setEditable(false);        
        m_jbtnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/search.png")));
        m_jbtnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnClienteActionPerformed(evt);
            }
        });
        add(m_jbtnCliente);
        m_jbtnCliente.setBounds(390,140,40,25);
        
        jLabel6.setText("Estado (A o I)");
        add(jLabel6);
        jLabel6.setBounds(20, 170, 150, 15);        
        add(m_jEstado);
        m_jEstado.setBounds(200, 170, 150, 19);
        m_jEstado.setEditable(false);
        m_jbtnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/adrianromero/images/button_cancel.png")));
        m_jbtnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnAnularActionPerformed(evt);
            }
        });
        add(m_jbtnAnular);
        m_jbtnAnular.setBounds(390, 170, 40, 25);
        
        jLabel7.setText("Recarga Masiva (S o N)");
        add(jLabel7);
        jLabel7.setBounds(20, 200, 150, 15);        
        add(m_jRecargable);
        m_jRecargable.setBounds(200, 200, 150, 19);
        
        
    }// </editor-fold>                        
    
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;    
    private javax.swing.JLabel jLabel7;    

    private javax.swing.JTextField m_jId;    
    private javax.swing.JTextField m_jFechaVenta;
    private javax.swing.JTextField m_jFechaCaduca;
    private javax.swing.JTextField m_jSaldo;
    private javax.swing.JTextField m_jCliente;
    private javax.swing.JTextField m_jEstado;
    private javax.swing.JTextField m_jRecargable;
    
    private javax.swing.JButton m_jbtnDate1;
    private javax.swing.JButton m_jbtnDate2;
    private javax.swing.JButton m_jbtnCliente;
    private javax.swing.JButton m_jbtnAnular;
    
    // End of variables declaration                   
    
}
