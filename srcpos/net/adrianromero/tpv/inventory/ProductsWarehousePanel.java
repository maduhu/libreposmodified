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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListCellRenderer;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.RenderStringBasic;
import net.adrianromero.data.loader.SentenceExec;
import net.adrianromero.data.loader.SentenceExecTransaction;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.SerializerWriteBasicExt;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.loader.VectorerBasic;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.format.Formats;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.panels.JPanelTable;
import net.adrianromero.tpv.reports.JParamsLocation;
import net.adrianromero.tpv.reports.JParamsLocationWithFirst;

/**
 *
 * @author adrianromero
 */
public class ProductsWarehousePanel extends JPanelTable {

    private JParamsLocation m_paramslocation;
    
    private ProductsWarehouseEditor jeditor;
    private ListProvider lpr;
    private SaveProvider spr;
    
    /** Creates a new instance of ProductsWarehousePanel */
    public ProductsWarehousePanel(AppView oApp) {
        super(oApp);
        
        m_paramslocation =  new JParamsLocation(m_App);
        m_paramslocation.addActionListener(new ReloadActionListener());
        
        final Datas[] prodstock = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE};

        lpr = new ListProviderCreator(new PreparedSentence(m_App.getSession(),
                "SELECT PRODUCTS.REFERENCE, PRODUCTS.NAME, ?," +
                "S.STOCKSECURITY, S.STOCKMAXIMUM, COALESCE(S.UNITS, 0) " +
                "FROM PRODUCTS LEFT OUTER JOIN " +
                "(SELECT PRODUCT, LOCATION, STOCKSECURITY, STOCKMAXIMUM, UNITS FROM STOCKCURRENT WHERE LOCATION = ?) S " +
                "ON PRODUCTS.REFERENCE = S.PRODUCT ORDER BY PRODUCTS.NAME",
                new SerializerWriteBasicExt(new Datas[] {Datas.OBJECT, Datas.STRING}, new int[]{1, 1}),
                new SerializerReadBasic(prodstock)),
                m_paramslocation);
        
        
        SentenceExec updatesent =  new SentenceExecTransaction(m_App.getSession()) {
            public int execInTransaction(Object params) throws BasicException {
                if (new PreparedSentence(m_App.getSession()
                        , "UPDATE STOCKCURRENT SET STOCKSECURITY = ?, STOCKMAXIMUM = ? WHERE LOCATION = ? AND PRODUCT = ?"
                        , new SerializerWriteBasicExt(prodstock, new int[] {3, 4, 2, 0})).exec(params) == 0) {
                    return new PreparedSentence(m_App.getSession()
                        , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, STOCKSECURITY, STOCKMAXIMUM, UNITS) VALUES (?, ?, ?, ?, 0)"
                        , new SerializerWriteBasicExt(prodstock, new int[] {2, 0, 3, 4})).exec(params);
                } else {
                    return 1;
                }
            }
        };     
        
        spr = new SaveProvider(updatesent, null, null);
         
        jeditor = new ProductsWarehouseEditor(m_Dirty);   
        
    }
    
    public ListProvider getListProvider() {
        return lpr;
    }
    
    public SaveProvider getSaveProvider() {
        return spr;        
    }    
    public Vectorer getVectorer() {
        return  new VectorerBasic(
                new String[] {AppLocal.getIntString("label.prodref"), AppLocal.getIntString("label.prodbarcode")},
                new Formats[] {Formats.STRING, Formats.STRING},
                new int[] {0, 1});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return null;
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(new RenderStringBasic(new Formats[] {Formats.STRING, Formats.STRING}, new int[]{0, 1}));
    }
    
    public Component getFilter() {
        return m_paramslocation;
    }  
    
    public EditorRecord getEditor() {
        return jeditor;
    }  
    
    public void activate() throws BasicException {
        
        m_paramslocation.activate(); 
        super.activate();
    }     
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.ProductsWarehouse");
    }      
    
    private class ReloadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ProductsWarehousePanel.this.m_bd.actionLoad();
            } catch (BasicException w) {
            }
        }
    }
}
