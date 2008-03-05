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

package net.adrianromero.tpv.inventory;

import net.adrianromero.format.Formats;
import net.adrianromero.tpv.ticket.ProductInfoExt;

public class InventoryLine {
    
    private double m_dMultiply;    
    private double m_dPrice;
    
    private String m_sProdRef;
    private String m_sProdName;
 
    /** Creates a new instance of InventoryLine */
    public InventoryLine(ProductInfoExt oProduct) {
        m_sProdRef = oProduct.getReference();
        m_sProdName = oProduct.getName();
        m_dMultiply = 1.0;
        m_dPrice = oProduct.getPriceBuy();;
    }
    
    public InventoryLine(ProductInfoExt oProduct, double dpor, double dprice) {
        m_sProdRef = oProduct.getReference();
        m_sProdName = oProduct.getName();
        m_dMultiply = dpor;
        m_dPrice = dprice;
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
    
    public double getSubValue() {
        return m_dMultiply * m_dPrice;
    }
    
    public String printName() {
        return m_sProdName;
    }
    
    public String printPrice() {
        if (m_dMultiply == 1.0) {
            return "";
        } else {
            return Formats.CURRENCY.formatValue(new Double(getPrice()));
        }
    }
    
    public String printMultiply() {
        return Formats.DOUBLE.formatValue(new Double(m_dMultiply));
    }
    
    public String printSubValue() {
        return Formats.CURRENCY.formatValue(new Double(getSubValue()));
    }
    
}
