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

import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.reports.ReportException;
import net.adrianromero.tpv.reports.ReportObject;

public class ProductInfoReport extends ProductInfo implements ReportObject {
    
    protected String m_sCategoryName;
   
    /** Creates a new instance of ProductInfoReport */
    public ProductInfoReport() {
        super();
        m_sCategoryName = null;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sRef = dr.getString(1);
        m_sName = dr.getString(2);
        m_bCom = dr.getBoolean(3).booleanValue();
        m_bScale = dr.getBoolean(4).booleanValue();
        m_dPriceBuy = dr.getDouble(5).doubleValue();
        m_dPriceSell = dr.getDouble(6).doubleValue();
        m_TaxInfo = new TaxInfo(dr.getString(7), dr.getString(8), dr.getDouble(9).doubleValue());     
        m_sCategoryID = dr.getString(10);
        m_sCategoryName = dr.getString(11);
    }
    
    public Object getField(String field) throws ReportException {
        // return new String[]{"REFERENCE", "NAME", "PRICESELL", "TAXNAME", "TAXRATE", "CATEGORY", "CATEGORYNAME"};
        if ("REFERENCE".equals(field)) {
            return m_sRef;
        } else if ("NAME".equals(field)) {
            return m_sName;
        } else if ("PRICEBUY".equals(field)) {
            return new Double(m_dPriceBuy);
        } else if ("PRICESELL".equals(field)) {
            return new Double(m_dPriceSell);
        } else if ("PRICESELLTAX".equals(field)) {
            return new Double(getPriceSellTax());
        } else if ("TAXNAME".equals(field)) {
            return getTaxName();
        } else if ("TAXRATE".equals(field)) {
            return new Double(getTaxRate());
        } else if ("CATEGORY".equals(field)) {
            return m_sCategoryID;
        } else if ("CATEGORYNAME".equals(field)) {
            return m_sCategoryName;
        } else {
            throw new ReportException(AppLocal.getIntString("exception.unavailablefields"));
        }
    }
    
    public final String getCategoryName() {            
        return m_sCategoryName;
    }
    public final void setCategoryName(String sCategoryName){            
        m_sCategoryName = sCategoryName;
    }    
}
