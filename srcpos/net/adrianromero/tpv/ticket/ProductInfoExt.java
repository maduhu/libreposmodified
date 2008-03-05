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
import java.awt.image.BufferedImage;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.SerializableRead;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.ImageUtils;

public class ProductInfoExt extends ProductInfo {
    
    protected BufferedImage m_Image;
    
    /** Creates new ProductInfo */
    public ProductInfoExt() {
        super();
        m_Image = null;
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sRef = dr.getString(1);
        m_sCode = dr.getString(2);
        m_sName = dr.getString(3);
        m_bCom = dr.getBoolean(4).booleanValue();
        m_bScale = dr.getBoolean(5).booleanValue();
        m_dPriceBuy = dr.getDouble(6).doubleValue();
        m_dPriceSell = dr.getDouble(7).doubleValue();
        m_TaxInfo = new TaxInfo(dr.getString(8), dr.getString(9), dr.getDouble(10).doubleValue());      
        m_sCategoryID = dr.getString(11);
        m_Image = ImageUtils.readImage(dr.getBytes(12));
    }
    
    public BufferedImage getImage() {
        return m_Image;
    }
    public void setImage(BufferedImage img) {
        m_Image = img;
    }
}
