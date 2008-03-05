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
import javax.xml.rpc.ServiceException;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.data.loader.ImageUtils;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import com.openbravo.possync.DataLogicIntegration;
import net.adrianromero.tpv.forms.ProcessAction;
import net.adrianromero.tpv.forms.UserView;
import net.adrianromero.tpv.ticket.CategoryInfo;
import net.adrianromero.tpv.ticket.ProductInfoExt;
import net.adrianromero.tpv.ticket.TaxInfo;
import net.opentrends.openbravo.ws.types.Product;

public class ProductsSync implements ProcessAction {
    
    private AppView m_app;
    
    /** Creates a new instance of ProductsSync */
    public ProductsSync(AppView app, UserView user) {
        m_app = app;
    }
    
    public MessageInf execute() throws BasicException {
        
        try {
        
            ExternalSalesHelper externalsales = new ExternalSalesHelper(m_app);
            Product[] products = externalsales.getProductsCatalog();

            if (products == null){
                throw new BasicException(AppLocal.getIntString("message.returnnull"));
            } else if(products.length == 0){
                return new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.zeroproducts"));
            } else {

                DataLogicIntegration datalogic = m_app.lookupDataLogic(DataLogicIntegration.class);

                datalogic.syncProductsBefore();
                
                for (int i = 0; i < products.length; i++) {
                    // Synchonization of taxes
                    TaxInfo t = new TaxInfo();
                    t.setID(Integer.toString(products[i].getTax().getId()));
                    t.setName(products[i].getTax().getName());
                    t.setRate(products[i].getTax().getPercentage() / 100);                        
                    datalogic.syncTax(t);

                    // Synchonization of categories
                    CategoryInfo c = new CategoryInfo();
                    c.setID(Integer.toString(products[i].getCategory().getId()));
                    c.setName(products[i].getCategory().getName());
                    c.setImage(null);                        
                    datalogic.syncCategory(c);

                    // Synchonization of products
                    ProductInfoExt p = new ProductInfoExt();
                    p.setReference(Integer.toString(products[i].getId()));
                    p.setCode(products[i].getEan());
                    p.setName(products[i].getName());
                    p.setCom(false);
                    p.setScale(false);
                    p.setPriceBuy(products[i].getPurchasePrice());
                    p.setPriceSell(products[i].getListPrice());
                    p.setCategoryID(c.getID());
                    p.setTaxInfo(t);
                    p.setImage(ImageUtils.readImage(products[i].getImageUrl()));
                    datalogic.syncProduct(p);                      
                }
                
                // datalogic.syncProductsAfter();
            
                return new MessageInf(MessageInf.SGN_SUCCESS, AppLocal.getIntString("message.syncproductsok"), AppLocal.getIntString("message.syncproductsinfo", products.length));
            }
                
        } catch (ServiceException e) {            
            throw new BasicException(AppLocal.getIntString("message.serviceexception"), e);
        } catch (RemoteException e){
            throw new BasicException(AppLocal.getIntString("message.remoteexception"), e);
        } catch (MalformedURLException e){
            throw new BasicException(AppLocal.getIntString("message.malformedurlexception"), e);
        }
    }   
}
