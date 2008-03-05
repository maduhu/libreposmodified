/*
 * AbstractSentenceEnum.java
 *
 * Created on 26 de diciembre de 2005, 23:09
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package net.adrianromero.data.loader;

import net.adrianromero.basic.BasicException;

/**
 *
 * @author adrian
 */
public class BasicSentenceEnum implements SentenceEnum {
    
    BaseSentence sent;
    DataResultSet SRS;
    
    /** Creates a new instance of AbstractSentenceEnum */
    public BasicSentenceEnum(BaseSentence sent) {
        this.sent = sent;
        this.SRS = null;
    }
    
    public void load() throws BasicException {
        load(null);
    }
    public void load(Object params) throws BasicException {
        SRS = sent.openExec(params);
    }

    public Object getCurrent() throws BasicException {
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        } 
        
        return SRS.getCurrent();  
    }
    
    public boolean next() throws BasicException {
        if (SRS == null) {
            throw new BasicException(LocalRes.getIntString("exception.nodataset"));
        } 
        
        if (SRS.next()) {
            return true;  
        } else {
            SRS.close();
            SRS = null;
            sent.closeExec();
            return false;
        }
    }
}
