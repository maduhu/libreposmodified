/*
 * RoleExtInfo.java
 */

package net.adrianromero.tpv.admin;

import java.util.Comparator;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.Vectorer;

/**
 *
 * @author adrianromero
 * Created on 27 de febrero de 2007, 23:46
 *
 */
public class RoleExtInfo extends RoleInfo {
    
    protected byte[] m_aPermissions;
    
    /** Creates a new instance of RoleExtInfo */
    public RoleExtInfo() {
        super();
        m_aPermissions = null;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sName = dr.getString(1);
        m_aPermissions = dr.getBytes(2);
    }   
    
    //  implements Vectorer, ComparatorCreator
    public static String[] getHeaders() {
        return new String[] {"Name"};
    }
    
    public String[] toStringArray() {
        return new String[] {m_sName};
    } 
    public Comparable[] toComparableArray() {
        return new Comparable[] {m_sName};
    }
}
