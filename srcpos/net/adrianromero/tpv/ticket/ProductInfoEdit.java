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

import java.awt.image.BufferedImage;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.ImageUtils;
import net.adrianromero.data.loader.SerializableRead;
import net.adrianromero.data.loader.SerializableWrite;

/**
 *
 * @author adrianromero
 * Created on 21 de marzo de 2007, 21:28
 *
 */
public class ProductInfoEdit /* implements SerializableRead , SerializableWrite */ {
    
    protected String m_sRef;
    protected String m_sCode;
    protected String m_sName;
    protected boolean m_bCom;
    protected boolean m_bScale;
    protected String m_sCategoryID;    
    protected String m_sTaxID;
    protected double m_dPriceBuy;
    protected double m_dPriceSell; 
    protected BufferedImage m_Image;
    
    protected Double m_dStockCost;
    protected Double m_dStockVolume;
    protected Integer m_iCatalogOrder;
    
    /** Creates a new instance of ProductInfoEdit */
    public ProductInfoEdit() {
        m_sRef = "0000";
        m_sCode = "0000";
        m_sName = null;
        m_bCom = false;
        m_bScale = false;
        m_sCategoryID = null;
        m_sTaxID = null;
        m_dPriceBuy = 0.0;
        m_dPriceSell = 0.0;
        m_Image = null;
        m_dStockCost = null;
        m_dStockVolume = null;
        m_iCatalogOrder = null;            
    }
    
    public void readValues(DataRead dr) throws BasicException {
        m_sRef = dr.getString(1);
        m_sCode = dr.getString(2);
        m_sName = dr.getString(3);
        m_bCom = dr.getBoolean(4).booleanValue();
        m_bScale = dr.getBoolean(5).booleanValue();
        m_dPriceBuy = dr.getDouble(6).doubleValue();
        m_dPriceSell = dr.getDouble(7).doubleValue();
        m_sCategoryID = dr.getString(8);
        m_sTaxID =  dr.getString(9);      
        m_Image = ImageUtils.readImage(dr.getBytes(10));
        m_dStockCost =  dr.getDouble(11);
        m_dStockVolume =  dr.getDouble(12);
        m_iCatalogOrder = dr.getInt(13);                
    }
    
    public final String getReference(){
        return m_sRef;
    }
    public final void setReference(String sRef){
        m_sRef = sRef;
    }    
    public final String getCode(){
        return m_sCode;
    }
    public final void setCode(String sCode){
        m_sCode = sCode;
    }
    public final String getName() {            
        return m_sName;
    }
    public final void setName(String sName){            
        m_sName = sName;
    }
    public final boolean isCom() {            
        return m_bCom;
    }
    public final void setCom(boolean bValue){            
        m_bCom = bValue;
    }
    public final boolean isScale() {            
        return m_bScale;
    }
    public final void setScale(boolean bValue){            
        m_bScale = bValue;
    }
    public final String getCategoryID() {
        return m_sCategoryID;
    }
    public final void setCategoryID(String sCategoryID) {
        m_sCategoryID = sCategoryID;
    }
    public final String getTaxID() {
        return m_sTaxID;
    }
    public final void setTaxID(String sTaxID) {
        m_sTaxID = sTaxID;
    }    
    public final double getPriceBuy(){
        return m_dPriceBuy;
    }    
    public final void setPriceBuy(double dPrice) {
        m_dPriceBuy = dPrice;
    }        
    public final double getPriceSell(){        
        return m_dPriceSell;
    }
    public final void setPriceSell(double dPrice) {        
        m_dPriceSell = dPrice;
    }      

    public BufferedImage getImage() {
        return m_Image;
    }
    public void setImage(BufferedImage img) {
        m_Image = img;
    }
    
    public final String toString() {
        return m_sRef + " - " + m_sName;
    }
    
}
