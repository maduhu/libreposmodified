/*
 * ResourceType.java
 */

package net.adrianromero.tpv.admin;

import net.adrianromero.tpv.panels.ComboItemLocal;

/**
 *
 * @author adrianromero
 * Created on February 12, 2007, 11:50 PM
 *
 */
public class ResourceType extends ComboItemLocal {
    
    public static final ResourceType TEXT = new ResourceType(0, "resource.text");
    public static final ResourceType IMAGE = new ResourceType(1, "resource.image");
    public static final ResourceType BINARY = new ResourceType(2, "resource.binary");
    
    private ResourceType(Integer iKey, String sKeyValue) {
        super(iKey, sKeyValue);
    }     
}
