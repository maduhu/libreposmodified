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

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import javax.xml.rpc.ServiceException;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import com.openbravo.possync.DataLogicIntegration;
import net.adrianromero.tpv.forms.ProcessAction;
import net.adrianromero.tpv.forms.UserView;
import net.adrianromero.tpv.payment.PaymentInfo;
import net.adrianromero.tpv.ticket.TicketInfo;
import net.adrianromero.tpv.ticket.TicketLineInfo;
import net.opentrends.openbravo.ws.types.Order;
import net.opentrends.openbravo.ws.types.OrderIdentifier;
import net.opentrends.openbravo.ws.types.OrderLine;
import net.opentrends.openbravo.ws.types.Payment;

public class OrdersSync implements ProcessAction {
    
    private AppView m_app;    

    /** Creates a new instance of OrdersSync */
    public OrdersSync(AppView app, UserView user) {
        m_app = app;
    }
    
    public MessageInf execute() throws BasicException {        
        
        try {
        
            ExternalSalesHelper externalsales = new ExternalSalesHelper(m_app);                    
            DataLogicIntegration datalogic = m_app.lookupDataLogic(DataLogicIntegration.class);

            // Obtenemos los tickets
            List<TicketInfo> ticketlist = datalogic.getTickets();
            for (TicketInfo ticket : ticketlist) {
                ticket.setLines(datalogic.getTicketLines(ticket.getId()));
                ticket.setPayments(datalogic.getTicketPayments(ticket.getId()));
            }
            
            if (ticketlist.size() == 0) {
                
                return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.zeroorders"));
            } else {

                // transformo tickets en ordenes
                Order[] orders = transformTickets(ticketlist);

                // subo las ordenes
                externalsales.uploadOrders(orders);

                // actualizo los tickets como subidos
                datalogic.execTicketUpdate();

                return new MessageInf(MessageInf.SGN_SUCCESS, AppLocal.getIntString("message.syncordersok"), AppLocal.getIntString("message.syncordersinfo", orders.length));
            }

        } catch (ServiceException e) {            
            throw new BasicException(AppLocal.getIntString("message.serviceexception"), e);
        } catch (RemoteException e){
            throw new BasicException(AppLocal.getIntString("message.remoteexception"), e);
        } catch (MalformedURLException e){
            throw new BasicException(AppLocal.getIntString("message.malformedurlexception"), e);
        }
    }  
    
    private Order[] transformTickets(List<TicketInfo> ticketlist) {

        // Transformamos de tickets a ordenes
        Order[] orders = new Order[ticketlist.size()];
        for (int i = 0; i < ticketlist.size(); i++) {
            TicketInfo ticket = ticketlist.get(i);

            orders[i] = new Order();

            OrderIdentifier orderid = new OrderIdentifier();
            Calendar datenew = Calendar.getInstance();
            datenew.setTime(ticket.getDate());
            orderid.setDateNew(datenew);
            orderid.setDocumentNo(Integer.toString(ticket.getId()));

            orders[i].setOrderId(orderid);
            orders[i].setState(800175);
            orders[i].setBusinessPartner(null);

            //Saco las lineas del pedido
            OrderLine[] orderLine = new OrderLine[ticket.getLines().size()];
            for (int j = 0; j < ticket.getLines().size(); j++){
                TicketLineInfo line = ticket.getLines().get(j);

                orderLine[j] = new OrderLine();
                orderLine[j].setOrderLineId(line.getTicketLine()); // o simplemente "j"
                if (line.getProductReference() == null) {
                    orderLine[j].setProductId(0);
                } else {
                    orderLine[j].setProductId(parseInt(line.getProductReference())); // capturar error
                }
                orderLine[j].setUnits(line.getMultiply());
                orderLine[j].setPrice(line.getPrice());
                orderLine[j].setTaxId(parseInt(line.getTaxInfo().getID()));     
            }
            orders[i].setLines(orderLine);

            //Saco las lineas de pago
            Payment[] paymentLine = new Payment[ticket.getPayments().size()];
            for (int j = 0; j < ticket.getPayments().size(); j++){       
                PaymentInfo payment = ticket.getPayments().get(j);

                paymentLine[j] = new Payment();
                paymentLine[j].setAmount(payment.getTotal());
                if ("magcard".equals(payment.getName())) {
                    paymentLine[j].setPaymentType("K");
                } else if ("cheque".equals(payment.getName())) {
                    paymentLine[j].setPaymentType("2");
                } else if ("cash".equals(payment.getName())) {
                    paymentLine[j].setPaymentType("B");
                } else {
                    paymentLine[j].setPaymentType(null); // desconocido
                }        
            }     
            orders[i].setPayment(paymentLine);                    
        }
        
        return orders;
    }
    
    private static int parseInt(String sValue) {
        
        try {
            return Integer.parseInt(sValue); 
        } catch (NumberFormatException eNF) {
            return 0;
        }
    }
}
