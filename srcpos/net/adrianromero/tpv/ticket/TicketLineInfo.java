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

import java.io.*;
import net.adrianromero.tpv.util.StringUtils;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.SerializableRead;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.format.Formats;
import net.adrianromero.data.loader.SerializableWrite;
import net.adrianromero.basic.BasicException;

public class TicketLineInfo implements SerializableWrite, SerializableRead, Serializable {
    
    private int m_iTicket;
    private int m_iTicketLine;
    
    private double m_dMultiply;    
    private double m_dPrice;
    
    private String m_sProdRef;
    private String m_sProdName;
    private boolean m_bProdCom;
    
    private TaxInfo m_TaxInfo;

    /** Creates new TicketLineInfo */    
    public TicketLineInfo(ProductInfoExt oProduct, double dMultiply, double dPrice) {
        if (oProduct == null) {
            m_sProdRef = null;
            m_sProdName = null;
            m_bProdCom = false;
            m_TaxInfo = null;
        } else {
            m_sProdRef = oProduct.getReference();
            m_sProdName = oProduct.getName();
            m_bProdCom = oProduct.isCom();
            m_TaxInfo = oProduct.getTaxInfo();
        }    
        m_dMultiply = dMultiply;
        m_dPrice = dPrice;
        m_iTicket = -1;
        m_iTicketLine = -1;
    }    
    public TicketLineInfo(ProductInfoExt oProduct, double dPrice) {       
        this(oProduct, 1.0, dPrice);
    }
     public TicketLineInfo() {
        this(null, 0.0, 0.0);
    }
    public TicketLineInfo(TicketLineInfo line) {        
        m_sProdRef = line.m_sProdRef;
        m_sProdName = line.m_sProdName;
        m_bProdCom = line.m_bProdCom;
        m_TaxInfo = line.m_TaxInfo;
        m_dMultiply = line.m_dMultiply;
        m_dPrice = line.m_dPrice;
        m_iTicket = -1;
        m_iTicketLine = -1;
    }
     
    public void setTicket(int ticket, int ticketline) {
        m_iTicket = ticket;
        m_iTicketLine = ticketline;
    }
    
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setInt(1, new Integer(m_iTicket));
        dp.setInt(2, new Integer(m_iTicketLine));
        dp.setString(3, m_sProdRef);
        dp.setString(4, m_sProdName);
        dp.setBoolean(5, new Boolean(m_bProdCom));
        dp.setDouble(6, new Double(m_dMultiply));
        dp.setDouble(7, new Double(m_dPrice));
        dp.setString(8, m_TaxInfo.getID());
        dp.setDouble(9, new Double(m_TaxInfo.getRate()));
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_iTicket = dr.getInt(1).intValue();
        m_iTicketLine = dr.getInt(2).intValue();
        m_sProdRef = dr.getString(3);
        m_sProdName = dr.getString(4);
        m_bProdCom = dr.getBoolean(5).booleanValue();
        m_dMultiply = dr.getDouble(6).doubleValue();
        m_dPrice = dr.getDouble(7).doubleValue();
        m_TaxInfo = new TaxInfo(dr.getString(8), "", dr.getDouble(9).doubleValue());
//        m_iProdTax = dr.getInt(8);
//        Double d;
//        m_dProdTaxRate = ((d = dr.getDouble(9)) == null) ? 0.0 : d.doubleValue();
    }
    
    public TicketLineInfo cloneTicketLine() {
        TicketLineInfo l = new TicketLineInfo();
        l.m_iTicket = m_iTicket;
        l.m_iTicketLine = m_iTicketLine;
        l.m_dMultiply = m_dMultiply;    
        l.m_dPrice = m_dPrice;
        l.m_sProdRef = m_sProdRef;
        l.m_sProdName = m_sProdName;
        l.m_bProdCom = m_bProdCom;
        l.m_TaxInfo = m_TaxInfo; 
        return l;
    }
    
    public int getTicketLine() {
        return m_iTicketLine;
    }
    
    public String getProductReference() {
        return m_sProdRef;
    }    
    
    public String getProductName() {
        return m_sProdName;
    } 
    public void setProductName(String sValue) {
        if (m_sProdRef == null) {
            m_sProdName = sValue;
        }
    }
 
    public boolean isProductCom() {
        return m_bProdCom;
    }
    public void setProductCom(boolean bValue) {
        if (m_sProdRef == null) {
            m_bProdCom = bValue;
        }
    }    
    
    public double getMultiply() {
        return m_dMultiply;
    }
    
    public void setMultiply(double dValue) {
        m_dMultiply = dValue;
    }
    
    public double getPrice() {
        return m_dPrice;
    }
    
    public void setPrice(double dValue) {
        m_dPrice = dValue;
    }
    
    public TaxInfo getTaxInfo() {
        return m_TaxInfo;
    }
//    
//    public Integer getProductTax() {
//        return m_iProdTax;
//    }
    
    public double getPriceTax() {
        return m_dPrice * (1.0 + getTaxRate());
    }
    
    public void setPriceTax(double dValue) {
        m_dPrice = dValue / (1.0 + getTaxRate());
    }
    
    public double getTaxRate() {
        return m_TaxInfo == null ? 0.0 : m_TaxInfo.getRate();
    }
    
    public double getSubValue() {
        return m_dMultiply * m_dPrice;
    }
    
    public double getTax() {
        return m_dMultiply * m_dPrice * getTaxRate();
    }
    
    public double getValue() {
        return m_dMultiply * m_dPrice * (1.0 + getTaxRate());
    }
    
    public void addMore(double dPor) {
        m_dMultiply +=dPor;
    }
    public void addOneMore() {
        m_dMultiply ++;
    }
    public void remOneMore() {
        m_dMultiply --;
    }
    
    public String printName() {
         return m_sProdName == null ? "" : StringUtils.encodeXML(m_sProdName);
    }
    public String printMultiply() {
        return Formats.DOUBLE.formatValue(new Double(m_dMultiply));
    }
    public String printPrice() {
        if (m_dMultiply == 1.0) {
            return "";
        } else {
            return Formats.CURRENCY.formatValue(new Double(getPrice()));
        }
    }    
    public String printPriceTax() {
        if (m_dMultiply == 1.0) {
            return "";
        } else {
            return Formats.CURRENCY.formatValue(new Double(getPriceTax()));
        }
    }    
    public String printTax() {
        return Formats.CURRENCY.formatValue(new Double(getTax()));
    }
    public String printTaxRate() {
        if (getTaxRate() == 0.0) {
            return "";
        } else {
            return Formats.PERCENT.formatValue(new Double(getTaxRate()));        
        }
    }
    public String printSubValue() {
        return Formats.CURRENCY.formatValue(new Double(getSubValue()));
    }
    public String printValue() {
        return Formats.CURRENCY.formatValue(new Double(getValue()));
    }
}
