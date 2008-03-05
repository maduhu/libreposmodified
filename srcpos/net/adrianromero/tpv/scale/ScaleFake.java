/*
 * ScaleFake.java
 *
 * Created on 13 de junio de 2006, 23:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.adrianromero.tpv.scale;

public class ScaleFake implements Scale {
    
    /** Creates a new instance of ScaleFake */
    public ScaleFake() {
    }
    
    public double readWeight() throws ScaleException {
        return Math.random() * 2.0;
    }
    
}
