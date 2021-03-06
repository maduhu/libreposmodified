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

package net.adrianromero.tpv.payment;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.format.Formats;
import net.adrianromero.tpv.util.RoundUtils;

public class JPaymentCashPos extends javax.swing.JPanel implements JPaymentInterface {
    
    // Variable numerica
    private final static int NUMBER_ZERO = 0;
    private final static int NUMBER_INT = 1;
    private final static int NUMBER_DEC = 2; 
    
    private JPaymentNotifier m_notifier;
    
    private int m_iNumberStatus;
    private double m_dPaid;
    private double m_dTotal;    
    
    /** Creates new form JPaymentCash */
    public JPaymentCashPos(JPaymentNotifier notifier) {
        
        m_notifier = notifier;
        
        initComponents();  
        
        m_jTendered.addPropertyChangeListener("Edition", new RecalculateState());
        m_jTendered.addEditorKeys(m_jKeys);
    }
    
    public void activate(String sTransaction, double dTotal) {
        
        m_dTotal = dTotal;
        
        m_jTendered.reset();
        m_jTendered.activate();
        
        printState();        
    }
    public PaymentInfo executePayment() {
        if (m_dPaid - m_dTotal >= 0.0) {
            // pago completo
            return new PaymentInfoCash(m_dTotal, m_dPaid);
        } else {
            // pago parcial
            return new PaymentInfoCash(m_dPaid, m_dPaid);
        }        
    }
    public Component getComponent() {
        return this;
    }
    
    private void printState() {

        try {
            m_dPaid = m_jTendered.getValue();
            m_dPaid = m_dPaid == 0.0 ? m_dTotal : m_dPaid; // si es 0.0 entonces es como si estuviera pagado.
        } catch (BasicException e){
            m_dPaid = m_dTotal;
        }   

        int iCompare = RoundUtils.compare(m_dPaid, m_dTotal);
        
        m_jMoneyEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dPaid)));
        m_jChangeEuros.setText(iCompare > 0 
                ? Formats.CURRENCY.formatValue(new Double(m_dPaid - m_dTotal))
                : null); 
        
        m_notifier.setAddEnabled(m_dPaid > 0.0 && iCompare < 0);
        m_notifier.setOKEnabled(iCompare >= 0);
    }
    
    private class RecalculateState implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
            printState();
        }
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        m_jKeys = new net.adrianromero.editor.JEditorKeys();
        jPanel3 = new javax.swing.JPanel();
        m_jTendered = new net.adrianromero.editor.JEditorCurrencyPositive();
        jPanel4 = new javax.swing.JPanel();
        m_jChangeEuros = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        m_jMoneyEuros = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jPanel1.add(m_jKeys);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(m_jTendered, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(null);

        m_jChangeEuros.setBackground(java.awt.Color.white);
        m_jChangeEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jChangeEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jChangeEuros.setOpaque(true);
        m_jChangeEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel4.add(m_jChangeEuros);
        m_jChangeEuros.setBounds(100, 50, 150, 25);

        jLabel6.setText(AppLocal.getIntString("Label.ChangeCash")); // NOI18N
        jPanel4.add(jLabel6);
        jLabel6.setBounds(20, 50, 80, 20);

        jLabel8.setText(AppLocal.getIntString("Label.InputCash")); // NOI18N
        jPanel4.add(jLabel8);
        jLabel8.setBounds(20, 20, 80, 20);

        m_jMoneyEuros.setBackground(new java.awt.Color(153, 153, 255));
        m_jMoneyEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jMoneyEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jMoneyEuros.setOpaque(true);
        m_jMoneyEuros.setPreferredSize(new java.awt.Dimension(150, 25));
        m_jMoneyEuros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m_jMoneyEurosKeyTyped(evt);
            }
        });
        jPanel4.add(m_jMoneyEuros);
        m_jMoneyEuros.setBounds(100, 20, 150, 25);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void m_jMoneyEurosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m_jMoneyEurosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jMoneyEurosKeyTyped
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel m_jChangeEuros;
    private net.adrianromero.editor.JEditorKeys m_jKeys;
    private javax.swing.JLabel m_jMoneyEuros;
    private net.adrianromero.editor.JEditorCurrencyPositive m_jTendered;
    // End of variables declaration//GEN-END:variables
    
}
