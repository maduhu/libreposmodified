//    Librepos is a point of sales application designed for touch screens.
//    http://sourceforge.net/projects/librepos
//
//    Copyright (c) 2007 openTrends Solucions i Sistemes, S.L
//    Modified by Openbravo SL on March 22, 2007
//    These modifications are copyright Openbravo SL
//    Author/s: A. Romero
//    You may contact Openbravo SL at: http://www.openbravo.com
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

package com.openbravo.possync;

import java.util.List;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.data.loader.ImageUtils;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.SentenceExec;
import net.adrianromero.data.loader.SentenceList;
import net.adrianromero.data.loader.SerializerRead;
import net.adrianromero.data.loader.SerializerReadClass;
import net.adrianromero.data.loader.SerializerWrite;
import net.adrianromero.data.loader.SerializerWriteInteger;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.data.loader.Transaction;
import net.adrianromero.tpv.data.DataLogic;
import net.adrianromero.tpv.payment.PaymentInfoTicket;
import net.adrianromero.tpv.ticket.CategoryInfo;
import net.adrianromero.tpv.ticket.ProductInfoExt;
import net.adrianromero.tpv.ticket.TaxInfo;
import net.adrianromero.tpv.ticket.TicketInfo;
import net.adrianromero.tpv.ticket.TicketLineInfo;

/**
 *
 * @author adrianromero
 * Created on 5 de marzo de 2007, 19:56
 *
 */
public class DataLogicIntegration implements DataLogic {
    
    protected Session s;
    
    private SentenceExec m_taxinsert;
    private SentenceExec m_taxupdate;
    
    private SentenceExec m_categoryinsert;
    private SentenceExec m_categoryupdate;
    
    private SentenceExec m_productinsert;
    private SentenceExec m_productupdate;
    
    private SentenceExec m_productsbefore;
    private SentenceExec m_productsafter;
    
    private SentenceList m_ticketlist;
    private SentenceList m_ticketlines;
    private SentenceList m_ticketpayments;
    
    private SentenceExec m_ticketupdate;

    /** Creates a new instance of DataLogicIntegration */
    public DataLogicIntegration() {
    }
    
    public void init(Session s){
        
        this.s = s;                    
    }  
    
    public void syncProductsBefore() throws BasicException {
        new StaticSentence(s, "DELETE FROM PRODUCTS_CAT").exec();
    }   
    
    public void syncTax(final TaxInfo tax) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Tax in a transaction
                
                // Try to update                
                if (new PreparedSentence(s, 
                            "UPDATE TAXES SET NAME = ?, RATE = ? WHERE ID = ?", 
                            new SerializerWrite() {
                                public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                    TaxInfo t = (TaxInfo) obj;
                                    dp.setString(1, t.getName());
                                    dp.setDouble(2, t.getRate());
                                    dp.setString(3, t.getID());
                                }
                            }).exec(tax) == 0) {
                       
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                        "INSERT INTO TAXES(ID, NAME, RATE) VALUES (?, ?, ?)", 
                        new SerializerWrite() {
                            public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                TaxInfo t = (TaxInfo) obj;
                                dp.setString(1, t.getID());
                                dp.setString(2, t.getName());
                                dp.setDouble(3, t.getRate());
                            }
                        }).exec(tax);
                }
                
                return null;
            }
        };
        t.execute();                   
    }
    
    public void syncCategory(final CategoryInfo cat) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Category in a transaction
                
                // Try to update
                if (new PreparedSentence(s, 
                            "UPDATE CATEGORIES SET NAME = ?, IMAGE = ? WHERE ID = ?", 
                            new SerializerWrite() {
                                public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                    CategoryInfo c = (CategoryInfo) obj;
                                    dp.setString(1, c.getName());
                                    dp.setBytes(2, ImageUtils.writeImage(c.getImage()));
                                    dp.setString(3, c.getID());
                                }
                            }).exec(cat) == 0) {
                       
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                        "INSERT INTO CATEGORIES(ID, NAME, IMAGE) VALUES (?, ?, ?)", 
                        new SerializerWrite() {
                            public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                CategoryInfo c = (CategoryInfo) obj;
                                dp.setString(1, c.getID());
                                dp.setString(2, c.getName());
                                dp.setBytes(3, ImageUtils.writeImage(c.getImage()));
                            }
                        }).exec(cat);
                }
                return null;        
            }
        };
        t.execute();        
    }    
    
    public void syncProduct(final ProductInfoExt prod) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                // Sync the Product in a transaction
                
                // Try to update
                if (new PreparedSentence(s, 
                            "UPDATE PRODUCTS SET CODE = ?, NAME = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAX = ?, IMAGE = ? WHERE REFERENCE = ?", 
                            new SerializerWrite() {
                                public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                    ProductInfoExt p = (ProductInfoExt) obj;
                                    dp.setString(1, p.getCode());
                                    dp.setString(2, p.getName());
                                    // dp.setBoolean(x, p.isCom());
                                    // dp.setBoolean(x, p.isScale());
                                    dp.setDouble(3, p.getPriceBuy());
                                    dp.setDouble(4, p.getPriceSell());
                                    dp.setString(5, p.getCategoryID());
                                    dp.setString(6, p.getTaxID());
                                    dp.setBytes(7, ImageUtils.writeImage(p.getImage()));
                                    // dp.setDouble(x, 0.0);
                                    // dp.setDouble(x, 0.0);
                                    dp.setString(8, p.getReference());                    
                                }
                            }).exec(prod) == 0) {
                            
                    // If not updated, try to insert
                    new PreparedSentence(s, 
                        "INSERT INTO PRODUCTS (REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAX, IMAGE, STOCKCOST, STOCKVOLUME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                         new SerializerWrite() {
                            public void writeValues(DataWrite dp, Object obj) throws BasicException {
                                ProductInfoExt p = (ProductInfoExt) obj;
                                dp.setString(1, p.getReference());
                                dp.setString(2, p.getCode());
                                dp.setString(3, p.getName());
                                dp.setBoolean(4, p.isCom());
                                dp.setBoolean(5, p.isScale());
                                dp.setDouble(6, p.getPriceBuy());
                                dp.setDouble(7, p.getPriceSell());
                                dp.setString(8, p.getCategoryID());
                                dp.setString(9, p.getTaxID());
                                dp.setBytes(10, ImageUtils.writeImage(p.getImage()));
                                dp.setDouble(11, 0.0);
                                dp.setDouble(12, 0.0);
                            }
                        }).exec(prod);
                }
                        
                // Insert in catalog
                new StaticSentence(s, 
                    "INSERT INTO PRODUCTS_CAT(REFERENCE, CATORDER) VALUES (?, NULL)",
                    new SerializerWrite() {
                        public void writeValues(DataWrite dp, Object obj) throws BasicException {
                            ProductInfoExt p = (ProductInfoExt) obj;
                            dp.setString(1, p.getReference());                    
                        }
                    }).exec(prod);   
                
                return null;        
            }
        };
        t.execute();     
    }
    
    public List getTickets() throws BasicException {
        return new PreparedSentence(s
                , "SELECT TICKETID, DATENEW, MONEY, PERSON FROM TICKETS WHERE STATUS = 0"
                , null
                , new SerializerReadClass(TicketInfo.class)).list();
    }
    public List getTicketLines(final Integer ticketid) throws BasicException {
        return new PreparedSentence(s
                , "SELECT TICKETID, TICKETLINE, PRODUCT, NAME, ISCOM, UNITS, PRICE, TAXID, TAXRATE FROM PRODUCTSOUT WHERE TICKETID = ?"
                , SerializerWriteInteger.INSTANCE
                , new SerializerReadClass(TicketLineInfo.class)).list(ticketid);
    }
    public List getTicketPayments(final Integer ticketid) throws BasicException {
        return new PreparedSentence(s
                , "SELECT TOTAL, PAYMENT FROM PAYMENTS WHERE TICKETID = ?"
                , SerializerWriteInteger.INSTANCE
                , new SerializerRead() {
                    public Object readValues(DataRead dr) throws BasicException {
                        return new PaymentInfoTicket(
                                dr.getDouble(1),
                                dr.getString(2));
                    }                
                }).list(ticketid);
    }    

    public void execTicketUpdate() throws BasicException {
        new StaticSentence(s, "UPDATE TICKETS SET STATUS = 1 WHERE STATUS = 0").exec();
    }
}
