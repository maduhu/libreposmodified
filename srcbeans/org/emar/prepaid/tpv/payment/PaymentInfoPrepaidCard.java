/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.emar.prepaid.tpv.payment;

import net.adrianromero.format.Formats;
import net.adrianromero.tpv.payment.PaymentInfo;

public class PaymentInfoPrepaidCard extends PaymentInfo {
    
    private double m_dTotal;
    private double m_dSaldo;
    private String m_sOwner;
    
    /** Creates a new instance of PaymentInfoCash */
    public PaymentInfoPrepaidCard(double dTotal, double dSaldo, String sOwner) {
        m_dTotal = dTotal;
        m_dSaldo = dSaldo;
        m_sOwner = sOwner;
    }
    
    public PaymentInfo clonePayment(){
        return new PaymentInfoPrepaidCard(m_dTotal, m_dSaldo, m_sOwner);
    }
    
    public String getName() {
        return "prepaidcard";
    }   
    public double getTotal() {
        return m_dTotal;
    }   
    public double getSaldo() {
        return m_dSaldo;
    }
    
    public String printPaid() {
        return Formats.CURRENCY.formatValue(new Double(m_dTotal));
    }   
    public String printBalance() {
        return Formats.CURRENCY.formatValue(new Double(m_dSaldo));
    } 
    public String printOwner() {
        return m_sOwner;
    } 
}

