/*
 * JEditorStringNumber.java
 *
 * Created on 3 de mayo de 2007, 19:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.adrianromero.editor;

/**
 *
 * @author adrian
 */
public class JEditorStringNumber extends JEditorText {
    
    /** Creates a new instance of JEditorStringNumber */
    public JEditorStringNumber() {
        super();
    }
    
    protected int getMode() {
        return EditorKeys.MODE_INTEGER_POSITIVE;
    } 
        
    protected int getStartMode() {
        return MODE_123;
    }    
}
