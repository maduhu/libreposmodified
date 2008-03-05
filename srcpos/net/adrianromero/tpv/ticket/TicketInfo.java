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

package net.adrianromero.tpv.ticket;

import java.util.*;
import java.io.*;
import net.adrianromero.tpv.util.*;
import net.adrianromero.tpv.payment.PaymentInfo;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.SerializableRead;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.format.Formats;
import net.adrianromero.data.loader.SerializableWrite;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.forms.AppUser;

public class TicketInfo implements SerializableWrite, SerializableRead, Externalizable {
   
    private int m_iId;
    private java.util.Date m_dDate;
    private UserInfo m_User;
    private String m_sActiveCash;
    private List<PaymentInfo> m_aPayment;    
    private List<TicketLineInfo> m_aLines;
    
    /** Creates new TicketModel */
    public TicketInfo() {
        m_iId = 0; // incrementamos
        m_dDate = null;
        m_User = null;
        m_sActiveCash = null;
        m_aPayment = new ArrayList<PaymentInfo>();        
        m_aLines = new ArrayList<TicketLineInfo>(); // vacio de lineas
    }
    public void writeExternal(ObjectOutput out) throws IOException  {
        // esto es solo para serializar tickets que no estan en la bolsa de tickets pendientes
        out.writeInt(m_iId);        
        out.writeObject(m_aLines);
    }   
    
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // esto es solo para serializar tickets que no estan en la bolsa de tickets pendientes
        m_iId = in.readInt();
        m_dDate = null;
        m_User = null; //(String) in.readObject();
        m_sActiveCash = null;
        m_aPayment = new ArrayList<PaymentInfo>();        
        m_aLines = (List<TicketLineInfo>) in.readObject();
    }
   
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setInt(1, new Integer(m_iId));
        dp.setTimestamp(2, m_dDate);
        dp.setString(3, m_sActiveCash);
        dp.setString(4, m_User.getId());       
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_iId = dr.getInt(1).intValue();
        m_dDate = dr.getTimestamp(2);
        m_sActiveCash = dr.getString(3);
        m_User = new UserInfo(dr.getString(4), dr.getString(5)); 
        m_aPayment = new ArrayList<PaymentInfo>(); 
        m_aLines = new ArrayList<TicketLineInfo>();
    }
    
    public TicketInfo cloneTicket() {
        TicketInfo t = new TicketInfo();
        t.m_iId = m_iId; // incrementamos
        t.m_dDate = m_dDate;
        t.m_User = m_User;
        t.m_sActiveCash = m_sActiveCash;
        
        t.m_aPayment = new LinkedList<PaymentInfo>(); 
        for (PaymentInfo p : m_aPayment) {
            t.m_aPayment.add(p.clonePayment());
        }
        
        t.m_aLines = new ArrayList<TicketLineInfo>(); 
        for (TicketLineInfo l : m_aLines) {
            t.m_aLines.add(l.cloneTicketLine());
        }
        
        return t;
    }
    
    public int getId(){
        return m_iId;
    }
    public void setId(int iId) {
        m_iId = iId;
        refreshLines();
    }   
    public java.util.Date getDate() {
        return m_dDate;
    }
    public void setDate(java.util.Date dDate) { 
        m_dDate = dDate;
    }
    public UserInfo getUser() {
        return m_User;
    }    
    public void setUser(UserInfo value) {        
        m_User = value;
    }   
    public void setActiveCash(String value) {     
        m_sActiveCash = value;
    }    
   
    public TicketLineInfo getLine(int index){
        return m_aLines.get(index);
    }
    
    public void addLine(TicketLineInfo oLine) {

       oLine.setTicket(m_iId, m_aLines.size());
       m_aLines.add(oLine);
    }
    
    public void insertLine(int index, TicketLineInfo oLine) {
        m_aLines.add(index, oLine);
        refreshLines();        
    }
    
    public void removeLine(int index) {
        m_aLines.remove(index);
        refreshLines();        
    }
    
    private void refreshLines() {         
        for (int i = 0; i < m_aLines.size(); i++) {
            getLine(i).setTicket(m_iId, i);
        } 
    }
    
    public int getLinesCount() {
        return m_aLines.size();
    }
    
    public double getArticlesCount() {
        double dArticles = 0.0;
        TicketLineInfo oLine;
            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            dArticles += oLine.getMultiply();
        }
        
        return dArticles;
    }
    
    public double getSubTotal() {
        double dSuma = 0.0;
        TicketLineInfo oLine;            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            dSuma += oLine.getSubValue();
        }        
        return dSuma;
    }
    
    public double getTax() {
        double dSuma = 0.0;
        TicketLineInfo oLine;            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            dSuma += oLine.getTax();
        }        
        return dSuma;
    }
    
    public double getTotal() {        
        double dSuma = 0.0;
        TicketLineInfo oLine;            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            dSuma += oLine.getValue();
        }        
        return dSuma;
    }
    
    public List<PaymentInfo> getPayments() {
        return m_aPayment;
    }
    
    public void setPayments(List<PaymentInfo> l) {
        m_aPayment = l;
    }
    
    public void resetPayments() {
        m_aPayment = new ArrayList<PaymentInfo>();
    }
    
    public List<TicketLineInfo> getLines() {
        return m_aLines;
    }    
    
    public void setLines(List<TicketLineInfo> l) {
        m_aLines = l;
    }
    
    public TicketTaxInfo[] getTaxLines() {
        
        Map<TaxInfo, TicketTaxInfo> m = new HashMap<TaxInfo, TicketTaxInfo>();
        
        double dSuma = 0.0;
        TicketLineInfo oLine;            
        for (Iterator<TicketLineInfo> i = m_aLines.iterator(); i.hasNext();) {
            oLine = i.next();
            
            TicketTaxInfo t = m.get(oLine.getTaxInfo());
            if (t == null) {
                t = new TicketTaxInfo(oLine.getTaxInfo());
                m.put(t.getTaxInfo(), t);
            }            
            t.add(oLine.getSubValue());
        }        
        
        // return dSuma;       
        Collection<TicketTaxInfo> avalues = m.values();
        return avalues.toArray(new TicketTaxInfo[avalues.size()]);
    }
    
    public String printId() {
        return Formats.INT.formatValue(new Integer(m_iId));
    }
    public String printDate() {
        return Formats.TIMESTAMP.formatValue(m_dDate);
    }
    public String printUser() {
        return m_User == null ? "" : m_User.getName();
    }
    public String printArticlesCount() {
        return Formats.DOUBLE.formatValue(new Double(getArticlesCount()));
    }
    
    public String printSubTotal() {
        return Formats.CURRENCY.formatValue(new Double(getSubTotal()));
    }
    public String printTax() {
        return Formats.CURRENCY.formatValue(new Double(getTax()));
    }    
    public String printTotal() {
        return Formats.CURRENCY.formatValue(new Double(getTotal()));
    }
    public String printTotalPts() {
        return Formats.INT.formatValue(new Double(CurrencyChange.changeEurosToPts(getTotal())));
    } 
}
