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

package net.adrianromero.tpv.panelsales;

import java.util.List;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.SerializerReadClass;
import net.adrianromero.data.loader.SerializerWriteBasicExt;
import net.adrianromero.data.loader.SerializerWriteString;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.tpv.data.DataLogic;
import net.adrianromero.tpv.ticket.TicketInfo;

/**
 *
 * @author adrianromero
 */
public class DataLogicReceipts implements DataLogic {
    
    private Session s;
    
    /** Creates a new instance of DataLogicReceipts */
    public DataLogicReceipts() {
    }
    
    public void init(Session s){
        this.s = s;
    }
     
    public final TicketInfo getSharedTicket(String Id) throws BasicException {
        
        if (Id == null) {
            return null; 
        } else {
            Object[]record = (Object[]) new StaticSentence(s
                    , "SELECT CONTENT FROM SHAREDTICKETS WHERE ID = ?"
                    , SerializerWriteString.INSTANCE
                    , new SerializerReadBasic(new Datas[] {Datas.SERIALIZABLE})).find(Id);
            return record == null ? null : (TicketInfo) record[0];
        }
    } 
    
    public final List<SharedTicketInfo> getSharedTicketList() throws BasicException {
        
        return (List<SharedTicketInfo>) new StaticSentence(s
                , "SELECT ID, NAME FROM SHAREDTICKETS ORDER BY ID"
                , null
                , new SerializerReadClass(SharedTicketInfo.class)).list();
    }
    
    public final void updateSharedTicket(final String id, final TicketInfo ticket) throws BasicException {
         
        Object[] values = new Object[] {id, Integer.toString(ticket.getId()), ticket};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.SERIALIZABLE};
        new PreparedSentence(s
                , "UPDATE SHAREDTICKETS SET NAME = ?, CONTENT = ? WHERE ID = ?"
                , new SerializerWriteBasicExt(datas, new int[] {1, 2, 0})).exec(values);
    }
    
    public final void insertSharedTicket(final String id, final TicketInfo ticket) throws BasicException {
        
        Object[] values = new Object[] {id, Integer.toString(ticket.getId()), ticket};
        Datas[] datas = new Datas[] {Datas.STRING, Datas.STRING, Datas.SERIALIZABLE};
        
        new PreparedSentence(s
            , "INSERT INTO SHAREDTICKETS (ID, NAME,CONTENT) VALUES (?, ?, ?)"
            , new SerializerWriteBasicExt(datas, new int[] {0, 1, 2})).exec(values);
    }
    
    public final void deleteSharedTicket(final String id) throws BasicException {

        new StaticSentence(s
            , "DELETE FROM SHAREDTICKETS WHERE ID = ?"
            , SerializerWriteString.INSTANCE).exec(id);      
    }    
}
