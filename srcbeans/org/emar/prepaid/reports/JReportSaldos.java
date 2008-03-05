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

package org.emar.prepaid.reports;
import net.adrianromero.beans.DateUtils;
import net.adrianromero.data.user.EditorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.StaticSentence;
import net.adrianromero.data.loader.SerializerWriteBasic;
import net.adrianromero.data.loader.BaseSentence;
import net.adrianromero.data.loader.QBFBuilder;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.tpv.reports.JPanelReport;
import net.adrianromero.tpv.reports.JParamsDatesInterval;
import net.adrianromero.tpv.reports.ReportFields;
import net.adrianromero.tpv.reports.ReportFieldsArray;

public class JReportSaldos extends JPanelReport {
    
    /**
     * Creates a new instance of JReportSaldos
     */
    public JReportSaldos(AppView oApp) {
        super(oApp);
    }
    public String getTitle() {
        return AppLocal.getIntString("Menu.ReporteSaldos");
    }
    protected String getReport() {
        return "/org/emar/prepaid/reports/saldos";
    }
    protected String getResourceBundle() {
        return "org/emar/prepaid/reports/saldos";
    }
    protected BaseSentence getSentence() {
        
         return new StaticSentence(m_App.getSession()
            , new QBFBuilder("SELECT  B.ID CLIENTE_ID, B.NOMBRE, B.APELLIDO, A.ID CARD_ID, A.SALDO, A.ESTADO, A.RECARGABLE " +
                            " FROM TARJETA A , CLIENTE B " +
                            " WHERE A.CLIENTE = B.ID AND ?(QBF_FILTER) " +
                            " ORDER BY B.APELLIDO, B.NOMBRE" , new String[]{"ID","NOMBRE", "APELLIDO"})
            , new SerializerWriteBasic(new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING})            
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING, Datas.STRING}));            
    }
    protected ReportFields getReportFields() {
        return new ReportFieldsArray(new String[]{"CLIENTE_ID", "NOMBRE", "APELLIDO", "CARD_ID", "SALDO", "ESTADO","RECARGABLE"});  
    }
    protected EditorCreator createEditorCreator() {
        return null;
    }    
}
