/*
 * SerializerReadBytes.java
 *
 * Created on February 12, 2005, 9:08 PM
 */

package net.adrianromero.data.loader;

import net.adrianromero.basic.BasicException;



/**
 *
 * @author Adrian
 */
public class SerializerReadBytes implements SerializerRead {
    
    public static final SerializerRead INSTANCE = new SerializerReadBytes();
    
    /** Creates a new instance of SerializerReadBytes */
    private SerializerReadBytes() {
    }
    
    public Object readValues(DataRead dr) throws BasicException {
        return Datas.BYTES.getValue(dr,1);
    }       
}
