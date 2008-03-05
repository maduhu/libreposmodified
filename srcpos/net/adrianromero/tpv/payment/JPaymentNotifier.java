/*
 * JPaymentNotifier.java
 *
 * Created on 2 de noviembre de 2005, 23:21
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package net.adrianromero.tpv.payment;

/**
 *
 * @author adrian
 */
public interface JPaymentNotifier {
    
    public void setOKEnabled(boolean bValue);
    public void setAddEnabled(boolean bValue);    
}
