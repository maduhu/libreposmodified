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
import net.adrianromero.data.user.EditorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.data.loader.BaseSentence;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.QBFBuilder;
import net.adrianromero.data.loader.SerializerWriteBasicComposed;

public class JReportInventoryDiff extends JPanelReport {
    
    private JParamsDatesInterval m_paramsdates;
    private JParamsLocation m_paramslocation;
    private JParamsReason m_paramsreason;
    private JParamsComposed m_params;
    
    /** Creates a new instance of JReportInventoryDiff */
    public JReportInventoryDiff(AppView oApp) {
        super(oApp);
    }
    public void activate() throws BasicException {
        m_paramslocation.activate();
        super.activate();
    } 
    public String getTitle() {
        return AppLocal.getIntString("Menu.InventoryDiff");
    }   
    protected String getReport() {
        return "/net/adrianromero/reports/inventorydiff";
    }
    protected String getResourceBundle() {
        return "net/adrianromero/reports/inventorydiff";
    }
    protected BaseSentence getSentence() {
        return new StaticSentence(m_App.getSession()
            , new QBFBuilder("SELECT " +
                "LOCATIONS.ID AS LOCATIONID, LOCATIONS.NAME AS LOCATIONNAME, " +
                "PRODUCTS.REFERENCE, PRODUCTS.NAME, PRODUCTS.CATEGORY, CATEGORIES.NAME AS CATEGORYNAME, " +
                "SUM(CASE WHEN STOCKDIARY.UNITS <0 THEN STOCKDIARY.UNITS ELSE 0 END) AS UNITSOUT, " +
                "SUM(CASE WHEN STOCKDIARY.UNITS <0 THEN STOCKDIARY.UNITS * STOCKDIARY.PRICE ELSE 0 END) AS TOTALOUT, " +
                "SUM(CASE WHEN STOCKDIARY.UNITS >=0 THEN STOCKDIARY.UNITS ELSE 0 END) AS UNITSIN, SUM(CASE WHEN STOCKDIARY.UNITS >=0 THEN STOCKDIARY.UNITS * STOCKDIARY.PRICE ELSE 0 END) AS TOTALIN, " +
                "SUM(STOCKDIARY.UNITS) AS UNITSDIFF, " +
                "SUM(STOCKDIARY.UNITS * STOCKDIARY.PRICE) AS TOTALDIFF " +
                "FROM STOCKDIARY JOIN LOCATIONS ON STOCKDIARY.LOCATION = LOCATIONS.ID, " +
                "PRODUCTS LEFT OUTER JOIN CATEGORIES ON PRODUCTS.CATEGORY = CATEGORIES.ID " +
                "WHERE PRODUCTS.REFERENCE = STOCKDIARY.PRODUCT " +
                "AND ?(QBF_FILTER) " +
                "GROUP BY LOCATIONS.ID, LOCATIONS.NAME, PRODUCTS.REFERENCE, PRODUCTS.NAME, PRODUCTS.CATEGORY, CATEGORIES.NAME " +
                "ORDER BY LOCATIONS.ID, CATEGORIES.NAME, PRODUCTS.NAME",  new String[] {"STOCKDIARY.DATENEW", "STOCKDIARY.DATENEW", "LOCATIONS.ID", "STOCKDIARY.REASON"})
            , new SerializerWriteBasicComposed(
                new Datas[] {Datas.OBJECT, Datas.TIMESTAMP, Datas.OBJECT, Datas.TIMESTAMP},
                new Datas[] {Datas.OBJECT, Datas.STRING},
                new Datas[] {Datas.OBJECT, Datas.INT})
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE}));
    }
    protected ReportFields getReportFields() {
        return new ReportFieldsArray(new String[]{"LOCATIONID", "LOCATIONNAME", "REFERENCE", "NAME", "CATEGORY", "CATEGORYNAME", "UNITSOUT", "TOTALOUT", "UNITSIN", "TOTALIN", "UNITSDIFF", "TOTALDIFF"});
    }
    protected EditorCreator createEditorCreator() {
        
        m_paramsdates =  new JParamsDatesInterval();
        m_paramslocation =  new JParamsLocationWithFirst(m_App);
        m_paramsreason = new JParamsReason();
                
        m_params = new JParamsComposed(m_paramsdates, m_paramslocation, m_paramsreason);
        m_params.add(m_paramsdates);
        m_params.add(m_paramslocation);
        m_params.add(m_paramsreason);
        
        return m_params;        
    }        
}
