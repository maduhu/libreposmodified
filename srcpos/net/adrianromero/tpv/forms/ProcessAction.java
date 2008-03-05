/*
 * ProcessAction.java
 *
 * Created on January 29, 2007, 12:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.adrianromero.tpv.forms;

import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.MessageInf;

public interface ProcessAction {
   
    public MessageInf execute() throws BasicException;
}
