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

package net.adrianromero.tpv.forms;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import net.adrianromero.tpv.data.*;
import net.adrianromero.tpv.ticket.*;
import net.adrianromero.data.loader.*;
import net.adrianromero.format.Formats;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.inventory.LocationInfo;
import net.adrianromero.tpv.inventory.MovementReason;
import net.adrianromero.tpv.mant.FloorsInfo;
import net.adrianromero.tpv.payment.PaymentInfo;

public abstract class SentenceContainer implements DataLogic {
    
    protected Session s;
    
    protected Datas[] stockdiaryDatas;
    protected Datas[] productcatDatas;
    protected Datas[] paymenttabledatas;
    protected Datas[] stockdatas;
    
    /** Creates a new instance of SentenceContainerGeneric */
    public SentenceContainer() {
        productcatDatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.DOUBLE, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT};
        stockdiaryDatas = new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE};
        paymenttabledatas = new Datas[] {Datas.INT, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE};
        stockdatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE};
    }
    
    public void init(Session s){
        this.s = s;
    }
    
    // Utilidades de productos
    public final ProductInfoExt getProductInfo(String sCode) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s
                , "SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAX, T.NAME, T.RATE, P.CATEGORY, P.IMAGE " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXES T ON P.TAX = T.ID WHERE P.CODE = ?"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(ProductInfoExt.class)).find(sCode);
    }
    public final ProductInfoExt getProductInfo2(String sReference) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s
                , "SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAX, T.NAME, T.RATE, P.CATEGORY, P.IMAGE " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXES T ON P.TAX = T.ID WHERE P.REFERENCE = ?"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(ProductInfoExt.class)).find(sReference);
    }
    
    // Catalogo de productos
    public final List<CategoryInfo> getRootCategories() throws BasicException {
        return new PreparedSentence(s
                , "SELECT ID, NAME, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL ORDER BY NAME"
                , null
                , new SerializerReadClass(CategoryInfo.class)).list();
    }
    public final List<CategoryInfo> getSubcategories(String category) throws BasicException  {
        return new PreparedSentence(s
                , "SELECT ID, NAME, IMAGE FROM CATEGORIES WHERE PARENTID = ? ORDER BY NAME"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(CategoryInfo.class)).list(category);
    }
    public final List<ProductInfoExt> getProductCatalog(String category) throws BasicException  {
        return new PreparedSentence(s
                , "SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAX, T.NAME, T.RATE, P.CATEGORY, P.IMAGE " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXES T ON P.TAX = T.ID LEFT OUTER JOIN CATEGORIES C ON P.CATEGORY = C.ID, PRODUCTS_CAT O WHERE P.REFERENCE = O.REFERENCE AND P.CATEGORY = ?" +
                "ORDER BY C.NAME, O.CATORDER, P.NAME"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(ProductInfoExt.class)).list(category);
    }
    public final List<ProductInfoExt> getProductComments(String sReference) throws BasicException {
        return new PreparedSentence(s
                , "SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAX, T.NAME, T.RATE, P.CATEGORY, P.IMAGE " +
                "FROM PRODUCTS P LEFT OUTER JOIN TAXES T ON P.TAX = T.ID, PRODUCTS_CAT O, PRODUCTS_COM M WHERE P.REFERENCE = O.REFERENCE AND P.REFERENCE = M.REFERENCE2 AND M.REFERENCE = ? " +
                "ORDER BY O.CATORDER, P.NAME"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(ProductInfoExt.class)).list(sReference);
    }
    
//    // Editor de productos
//    public final List getProductComments2(String sReference) throws BasicException {
//        return new PreparedSentence(s
//            , "SELECT PRODUCTS.REFERENCE, PRODUCTS.NAME FROM PRODUCTS, PRODUCTS_COM " +
//              "WHERE PRODUCTS.REFERENCE = PRODUCTS_COM.REFERENCE2 AND PRODUCTS_COM.REFERENCE = ? " +
//              "ORDER BY PRODUCTS.NAME"
//            , SerializerWriteString.INSTANCE
//            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.STRING})).list(sReference);
//    }
    
    // Listado de productos
    public final SentenceList getProductList() {
        return new StaticSentence(s
                , new QBFBuilder("SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAX, T.NAME, T.RATE, P.CATEGORY, P.IMAGE FROM PRODUCTS P LEFT OUTER JOIN TAXES T ON P.TAX = T.ID WHERE ?(QBF_FILTER) ORDER BY P.NAME", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"})
                , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
                , new SerializerReadClass(ProductInfoExt.class));
    }
    
    // Listados para combo
    public final SentenceList getTaxList() {
        return new StaticSentence(s
                , "SELECT ID, NAME, RATE FROM TAXES ORDER BY NAME"
                , null
                , new SerializerReadClass(TaxInfo.class));
    }
    public final SentenceList getCategoriesList() {
        return new StaticSentence(s
                , "SELECT ID, NAME, IMAGE FROM CATEGORIES ORDER BY NAME"
                , null
                , new SerializerReadClass(CategoryInfo.class));
    }
    public final SentenceList getLocationsList() {
        return new StaticSentence(s
                , "SELECT ID, NAME, ADDRESS FROM LOCATIONS ORDER BY NAME"
                , null
                , new SerializerReadClass(LocationInfo.class));
    }
    public final SentenceList getFloorsList() {
        return new StaticSentence(s
                , "SELECT ID, NAME FROM FLOORS ORDER BY NAME"
                , null
                , new SerializerReadClass(FloorsInfo.class));
    }
    
    public final TicketInfo loadTicket(Integer ticketid) throws BasicException {
        TicketInfo ticket = (TicketInfo) new PreparedSentence(s
                , "SELECT TICKETID, DATENEW, MONEY, PEOPLE.ID, PEOPLE.NAME FROM TICKETS LEFT OUTER JOIN PEOPLE ON TICKETS.PERSON = PEOPLE.ID WHERE TICKETID = ?"
                , SerializerWriteInteger.INSTANCE
                , new SerializerReadClass(TicketInfo.class)).find(ticketid);
        if (ticket != null) {
            ticket.setLines(new PreparedSentence(s
                    , "SELECT TICKETID, TICKETLINE, PRODUCT, NAME, ISCOM, UNITS, PRICE, TAXID, TAXRATE FROM PRODUCTSOUT WHERE TICKETID = ?"
                    , SerializerWriteInteger.INSTANCE
                    , new SerializerReadClass(TicketLineInfo.class)).list(ticketid));
        }
        return ticket;
    }
    
    public final void saveTicket(final TicketInfo ticket, final String location) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                
//                // Set Receipt Id
//                if (ticket.getId() == 0) {
//                    ticket.setId(getNextTicketIndex().intValue());
//                }
                
                // new ticket
                new PreparedSentence(s
                        , "INSERT INTO TICKETS (TICKETID, DATENEW, MONEY, PERSON) VALUES (?, ?, ?, ?)"
                        , SerializerWriteBuilder.INSTANCE).exec(ticket);
                
                SentenceExec ticketlineinsert = new PreparedSentence(s
                        , "INSERT INTO PRODUCTSOUT (TICKETID, TICKETLINE, PRODUCT, NAME, ISCOM, UNITS, PRICE, TAXID, TAXRATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
                        , SerializerWriteBuilder.INSTANCE);
                
                for (TicketLineInfo l : ticket.getLines()) {
                    ticketlineinsert.exec(l);
                    if (l.getProductReference() != null)  {
                        // Hay que actualizar el stock si el hay producto
                        Object[] diary = new Object[7];
                        diary[0] = UUID.randomUUID().toString();
                        diary[1] = ticket.getDate();
                        diary[2] = l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey();
                        diary[3] = location;
                        diary[4] = l.getProductReference() ;
                        diary[5] = new Double(-l.getMultiply());
                        diary[6] = new Double(l.getPrice());
                        getStockDiaryInsert().exec(diary);
                    }
                }
                
                SentenceExec paymentinsert = new PreparedSentence(s
                        , "INSERT INTO PAYMENTS (ID, TICKETID, PAYMENT, TOTAL) VALUES (?, ?, ?, ?)"
                        , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.INT, Datas.STRING, Datas.DOUBLE}));
                for (PaymentInfo p : ticket.getPayments()) {
                    Object[] payment = new Object[4];
                    payment[0] = UUID.randomUUID().toString();
                    payment[1] = new Integer(ticket.getId());
                    payment[2] = p.getName();
                    payment[3] = new Double(p.getTotal());
                    paymentinsert.exec(payment);
                }
                return null;
            }
        };
        t.execute();
    }
    
    public final void deleteTicket(final TicketInfo ticket, final String location) throws BasicException {
        
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {
                
                // actualizamos el inventario.
                Date d = new Date();
                for (int i = 0; i < ticket.getLinesCount(); i++) {
                    if (ticket.getLine(i).getProductReference() != null)  {
                        // Hay que actualizar el stock si el hay producto
                        Object[] diary = new Object[7];
                        diary[0] = UUID.randomUUID().toString();
                        diary[1] = d;
                        diary[2] = ticket.getLine(i).getMultiply() >= 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey();
                        diary[3] = location;
                        diary[4] = ticket.getLine(i).getProductReference() ;
                        diary[5] = new Double(ticket.getLine(i).getMultiply());
                        diary[6] = new Double(ticket.getLine(i).getPrice());
                        getStockDiaryInsert().exec(diary);
                    }
                }
                // Y borramos el ticket definitivamente
                new StaticSentence(s
                        , "DELETE FROM PAYMENTS WHERE TICKETID = ?"
                        , SerializerWriteInteger.INSTANCE).exec(new Integer(ticket.getId()));
                new StaticSentence(s
                        , "DELETE FROM PRODUCTSOUT WHERE TICKETID = ?"
                        , SerializerWriteInteger.INSTANCE).exec(new Integer(ticket.getId()));
                new StaticSentence(s
                        , "DELETE FROM TICKETS WHERE TICKETID = ?"
                        , SerializerWriteInteger.INSTANCE).exec(new Integer(ticket.getId()));
                return null;
            }
        };
        t.execute();
    }
    
    public abstract Integer getNextTicketIndex() throws BasicException;
    
    public abstract SentenceList getProductCatQBF();
    
    public final SentenceExec getProductCatInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                int i = new PreparedSentence(s
                        , "INSERT INTO PRODUCTS (REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAX, IMAGE, STOCKCOST, STOCKVOLUME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                        , new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})).exec(params);
                if (i > 0 && ((Boolean)values[12]).booleanValue()) {
                    return new PreparedSentence(s
                            , "INSERT INTO PRODUCTS_CAT (REFERENCE, CATORDER) VALUES (?, ?)"
                            , new SerializerWriteBasicExt(productcatDatas, new int[] {0, 13})).exec(params);
                } else {
                    return i;
                }
            }
        };
    }
    
    public final SentenceExec getProductCatUpdate() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                int i = new PreparedSentence(s
                        , "UPDATE PRODUCTS SET REFERENCE = ?, CODE = ?, NAME = ?, ISCOM = ?, ISSCALE = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAX = ?, IMAGE = ?, STOCKCOST = ?, STOCKVOLUME = ? WHERE REFERENCE = ?"
                        , new SerializerWriteBasicExt(productcatDatas, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0})).exec(params);
                if (i > 0) {
                    if (((Boolean)values[12]).booleanValue()) {
                        if (new PreparedSentence(s
                                , "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE REFERENCE = ?"
                                , new SerializerWriteBasicExt(productcatDatas, new int[] {13, 0})).exec(params) == 0) {
                            new PreparedSentence(s
                                    , "INSERT INTO PRODUCTS_CAT (REFERENCE, CATORDER) VALUES (?, ?)"
                                    , new SerializerWriteBasicExt(productcatDatas, new int[] {0, 13})).exec(params);
                        }
                    } else {
                        new PreparedSentence(s
                                , "DELETE FROM PRODUCTS_CAT WHERE REFERENCE = ?"
                                , new SerializerWriteBasicExt(productcatDatas, new int[] {0})).exec(params);
                    }
                }
                return i;
            }
        };
    }
    
    public final SentenceExec getProductCatDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                        , "DELETE FROM PRODUCTS_CAT WHERE REFERENCE = ?"
                        , new SerializerWriteBasicExt(productcatDatas, new int[] {0})).exec(params);
                return new PreparedSentence(s
                        , "DELETE FROM PRODUCTS WHERE REFERENCE = ?"
                        , new SerializerWriteBasicExt(productcatDatas, new int[] {0})).exec(params);
            }
        };
    }
    
    public final SentenceExec getStockDiaryInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s
                        , "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ?"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {5, 3, 4})).exec(params) == 0) {
                    new PreparedSentence(s
                            , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, UNITS) VALUES (?, ?, ?)"
                            , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {3, 4, 5})).exec(params);
                }
                return new PreparedSentence(s
                        , "INSERT INTO STOCKDIARY (ID, DATENEW, REASON, LOCATION, PRODUCT, UNITS, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?)"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {0, 1, 2, 3, 4, 5, 6})).exec(params);
            }
        };
    }
    
    public final SentenceExec getStockDiaryDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s
                        , "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE LOCATION = ? AND PRODUCT = ?"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {5, 3, 4})).exec(params) == 0) {
                    new PreparedSentence(s
                            , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, UNITS) VALUES (?, ?, -(?))"
                            , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {3, 4, 5})).exec(params);
                }
                return new PreparedSentence(s
                        , "DELETE FROM STOCKDIARY WHERE ID = ?"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {0})).exec(params);
            }
        };
    }
    
    public final SentenceExec getPaymentMovementInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                        , "INSERT INTO TICKETS (TICKETID, DATENEW, MONEY, PERSON) VALUES (?, ?, ?, ?)"
                        , new SerializerWriteBasicExt(paymenttabledatas, new int[] {0, 1, 2, 3})).exec(params);
                return new PreparedSentence(s
                        , "INSERT INTO PAYMENTS (ID, TICKETID, PAYMENT, TOTAL) VALUES (?, ?, ?, ?)"
                        , new SerializerWriteBasicExt(paymenttabledatas, new int[] {4, 0, 5, 6})).exec(params);
            }
        };
    }
    
    public final SentenceExec getPaymentMovementDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                        , "DELETE FROM PAYMENTS WHERE ID = ?"
                        , new SerializerWriteBasicExt(paymenttabledatas, new int[] {4})).exec(params);
                return new PreparedSentence(s
                        , "DELETE FROM TICKETS WHERE TICKETID = ?"
                        , new SerializerWriteBasicExt(paymenttabledatas, new int[] {0})).exec(params);
            }
        };
    }
    
    public final SentenceList getProductStock() {
        return new PreparedSentence(s
                , "SELECT L.ID, L.NAME, ?, COALESCE(S.UNITS, 0.0), S.STOCKSECURITY, S.STOCKMAXIMUM " +
                "FROM LOCATIONS L LEFT OUTER JOIN (" +
                "SELECT PRODUCT, LOCATION, STOCKSECURITY, STOCKMAXIMUM, UNITS FROM STOCKCURRENT WHERE PRODUCT = ?) S " +
                "ON L.ID = S.LOCATION"
                , new SerializerWriteBasicExt(productcatDatas, new int[]{0, 0})
                , new SerializerReadBasic(stockdatas));
    }
    
    public final SentenceExec getStockUpdate() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(s
                        , "UPDATE STOCKCURRENT SET STOCKSECURITY = ?, STOCKMAXIMUM = ? WHERE LOCATION = ? AND PRODUCT = ?"
                        , new SerializerWriteBasicExt(stockdatas, new int[] {4, 5, 0, 2})).exec(params) == 0) {
                    return new PreparedSentence(s
                            , "INSERT INTO STOCKCURRENT(LOCATION, PRODUCT, UNITS, STOCKSECURITY, STOCKMAXIMUM) VALUES (?, ?, 0.0, ?, ?)"
                            , new SerializerWriteBasicExt(stockdatas, new int[] {0, 2, 4, 5})).exec(params);
                } else {
                    return 1;
                }
            }
        };
    }
    
    public final SentenceExec getCatalogCategoryAdd() {
        return new StaticSentence(s
                , "INSERT INTO PRODUCTS_CAT(REFERENCE, CATORDER) SELECT REFERENCE, NULL FROM PRODUCTS WHERE CATEGORY=?"
                , SerializerWriteString.INSTANCE);
    }
    public final SentenceExec getCatalogCategoryDel() {
        return new StaticSentence(s
                , "DELETE FROM PRODUCTS_CAT WHERE REFERENCE = ANY (SELECT REFERENCE FROM PRODUCTS WHERE CATEGORY=?)"
                , SerializerWriteString.INSTANCE);
    }
    
    public final TableDefinition getTableCategories() {
        return new TableDefinition(s,
                "CATEGORIES"
                , new String[] {"ID", "NAME", "PARENTID", "IMAGE"}
        , new String[] {"ID", AppLocal.getIntString("Label.Name"), "", AppLocal.getIntString("label.image")}
        , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE}
        , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.NULL}
        , new int[] {0}
        );
    }
    public final TableDefinition getTableTaxes() {
        return new TableDefinition(s,
                "TAXES"
                , new String[] {"ID", "NAME", "RATE"}
        , new String[] {"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("label.dutyrate")}
        , new Datas[] {Datas.STRING, Datas.STRING, Datas.DOUBLE}
        , new Formats[] {Formats.STRING, Formats.STRING, Formats.PERCENT}
        , new int[] {0}
        );
    }
    
    public final TableDefinition getTableClientes() {
        return new TableDefinition(s,
                "CLIENTE"
                , new String[] {"ID", "NOMBRE", "APELLIDO", "OBSERVACIONES", "FECHA_CREACION"}
        , new String[] {"ID", "Nombre", "Apellido", "Observaciones", "Fecha Creación" }
        , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP}
        , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING,Formats.TIMESTAMP}
        , new int[] {0}
        );        
    }
    
    public final TableDefinition getTableTarjetas() {
        return new TableDefinition(s,
                "TARJETA"
                , new String[] {"ID", "FECHA_VENTA", "FECHA_CADUCA", "SALDO", "CLIENTE", "ESTADO", "RECARGABLE"}
        , new String[] {"ID", "Fecha Venta", "Fecha Caduca", "Saldo", "Cliente", "Estado", "Recargable" }
        , new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING,Datas.STRING}
        , new Formats[] {Formats.STRING, Formats.TIMESTAMP, Formats.TIMESTAMP, Formats.DOUBLE, Formats.STRING, Formats.STRING,Formats.STRING}
        , new int[] {0}
        );        
    }
    
    
    public final TableDefinition getTableLocations() {
        return new TableDefinition(s,
                "LOCATIONS"
                , new String[] {"ID", "NAME", "ADDRESS"}
        , new String[] {"ID", AppLocal.getIntString("label.locationname"), AppLocal.getIntString("label.locationaddress")}
        , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING}
        , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING}
        , new int[] {0}
        );
    }
}
