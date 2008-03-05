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

package net.adrianromero.tpv.reports;

import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.BaseSentence;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.QBFBuilder;
import net.adrianromero.data.loader.SerializerReadClass;
import net.adrianromero.data.loader.SerializerWriteBasic;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.data.user.EditorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.ticket.ProductFilter;
import net.adrianromero.tpv.ticket.ProductInfoReport;

public class JReportProducts extends JPanelReport {
    
    private ProductFilter m_productfilter;
    
    /** Creates a new instance of JReportProducts */
    public JReportProducts(AppView oApp) {
        super(oApp);
    }    
    public void activate() throws BasicException {
        m_productfilter.activate();
        super.activate();
    }              
    public String getTitle() {
        return AppLocal.getIntString("Menu.Products");
    }      
    protected String getReport() {
        return "/net/adrianromero/reports/products";
    }
    protected String getResourceBundle() {
        return "net/adrianromero/reports/products";
    }
    protected BaseSentence getSentence() {
        return new StaticSentence(m_App.getSession()
            , new QBFBuilder(
              "SELECT PRODUCTS.REFERENCE, PRODUCTS.NAME, PRODUCTS.ISCOM, PRODUCTS.ISSCALE, PRODUCTS.PRICEBUY, PRODUCTS.PRICESELL, PRODUCTS.TAX, TAXES.NAME AS TAXNAME, TAXES.RATE AS TAXRATE, PRODUCTS.CATEGORY, CATEGORIES.NAME AS CATEGORYNAME " +
              "FROM PRODUCTS LEFT OUTER JOIN CATEGORIES ON PRODUCTS.CATEGORY = CATEGORIES.ID LEFT OUTER JOIN TAXES ON PRODUCTS.TAX = TAXES.ID " +
              "WHERE ?(QBF_FILTER) " +
              "ORDER BY CATEGORIES.NAME, PRODUCTS.NAME",
               new String[] {"NAME", "PRICEBUY", "PRICESELL", "CATEGORY", "CODE"} )
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadClass(ProductInfoReport.class));
    }
    protected ReportFields getReportFields() {
        return ReportFieldsBuilder.INSTANCE;
    }    
    
    protected EditorCreator createEditorCreator() {
        m_productfilter =  new ProductFilter(m_App) ;
        return m_productfilter;
    }         
}
