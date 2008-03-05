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
import net.adrianromero.data.user.EditorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.data.loader.SerializerWriteBasic;
import net.adrianromero.data.loader.BaseSentence;
import net.adrianromero.data.loader.QBFBuilder;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.Datas;

public class JReportClosedProducts extends JPanelReport {
    
    /** Creates a new instance of JReportClosedProducts */
    public JReportClosedProducts(AppView oApp) {
        super(oApp);
    }
    public String getTitle() {
        return AppLocal.getIntString("Menu.ClosedProducts");
    } 
    protected String getReport() {
        return "/net/adrianromero/reports/closedproducts";
    }
    protected String getResourceBundle() {
        return "net/adrianromero/reports/closedproducts";
    }
    protected BaseSentence getSentence() {
       
         return new StaticSentence(m_App.getSession()
            , new QBFBuilder("SELECT " +
                "CLOSEDCASH.HOST, " +
                "CLOSEDCASH.MONEY, " +
                "CLOSEDCASH.DATESTART, " +
                "PRODUCTS.REFERENCE, " +
                "PRODUCTS.NAME, " +
                "SUM(PRODUCTSOUT.UNITS) AS UNITS, " +
                "SUM(PRODUCTSOUT.UNITS * PRODUCTSOUT.PRICE) AS TOTAL " +
                "FROM CLOSEDCASH, TICKETS, PRODUCTSOUT LEFT OUTER JOIN PRODUCTS ON PRODUCTSOUT.PRODUCT = PRODUCTS.REFERENCE " +
                "WHERE CLOSEDCASH.MONEY = TICKETS.MONEY AND TICKETS.TICKETID = PRODUCTSOUT.TICKETID AND ?(QBF_FILTER) " +
                "GROUP BY CLOSEDCASH.HOST, CLOSEDCASH.MONEY, CLOSEDCASH.DATESTART, PRODUCTS.REFERENCE, PRODUCTS.NAME " +
                "ORDER BY PRODUCTS.NAME, CLOSEDCASH.HOST, CLOSEDCASH.DATESTART",  new String[] {"CLOSEDCASH.DATESTART", "CLOSEDCASH.DATESTART"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.TIMESTAMP, Datas.OBJECT, Datas.TIMESTAMP})
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE}));            
    }
    protected ReportFields getReportFields() {
        return new ReportFieldsArray(new String[]{"HOST", "MONEY", "DATESTART", "REFERENCE", "NAME", "UNITS", "TOTAL"});  
    }
    protected EditorCreator createEditorCreator() {
        return new JParamsDatesInterval();
    }   
    
}
