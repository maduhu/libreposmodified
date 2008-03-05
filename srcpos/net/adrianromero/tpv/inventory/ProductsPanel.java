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
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.data.loader.ComparatorCreatorBasic;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.RenderStringBasic;
import net.adrianromero.data.loader.SentenceList;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.loader.VectorerBasic;
import net.adrianromero.data.user.BrowsableData;
import net.adrianromero.data.user.EditorListener;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.format.Formats;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.forms.SentenceContainer;
import net.adrianromero.tpv.panels.JPanelTable;
import net.adrianromero.tpv.ticket.ProductFilter;

/**
 *
 * @author adrianromero
 * Created on 1 de marzo de 2007, 22:15
 *
 */
public class ProductsPanel extends JPanelTable implements EditorListener {
    
    private SentenceList liststock;
    private BrowsableData m_bdstock;

    private ProductsEditor jeditor;
    private ProductFilter jproductfilter;    
    
    // private Datas[] productcatDatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.DOUBLE, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT};
    
    /** Creates a new instance of ProductsPanel2 */
    public ProductsPanel(AppView oApp) {
        super(oApp);
        
        // el panel del filtro
        jproductfilter = new ProductFilter(m_App);
        
        // el panel del editor
        jeditor = new ProductsEditor(m_App, m_Dirty);       

        liststock = m_App.lookupDataLogic(SentenceContainer.class).getProductStock();

        // El editable data del stock
        m_bdstock = new BrowsableData(null, new SaveProvider(
                m_App.lookupDataLogic(SentenceContainer.class).getStockUpdate(),
                null,
                null));     
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(m_App.lookupDataLogic(SentenceContainer.class).getProductCatQBF(), jproductfilter);

    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(
            m_App.lookupDataLogic(SentenceContainer.class).getProductCatUpdate(), 
            m_App.lookupDataLogic(SentenceContainer.class).getProductCatInsert(), 
            m_App.lookupDataLogic(SentenceContainer.class).getProductCatDelete());        
    }
    
    public Vectorer getVectorer() {
        return  new VectorerBasic(
                new String[]{AppLocal.getIntString("label.prodref"), AppLocal.getIntString("label.prodbarcode"), AppLocal.getIntString("label.prodname"), "ISCOM", "ISSCALE", AppLocal.getIntString("label.prodpricebuy"), AppLocal.getIntString("label.prodpricesell"), AppLocal.getIntString("label.prodcategory"), AppLocal.getIntString("label.prodtax"), "IMAGE", "STOCKCOST", "STOCKVOLUME"},
                new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.BOOLEAN, Formats.BOOLEAN, Formats.CURRENCY, Formats.CURRENCY, Formats.STRING, Formats.STRING, Formats.NULL, Formats.CURRENCY, Formats.DOUBLE},
                new int[] {0, 1, 2, 5, 6});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return new ComparatorCreatorBasic(
                new String[]{AppLocal.getIntString("label.prodref"), AppLocal.getIntString("label.prodbarcode"), AppLocal.getIntString("label.prodname"), "ISCOM", "ISSCALE", AppLocal.getIntString("label.prodpricebuy"), AppLocal.getIntString("label.prodpricesell"), AppLocal.getIntString("label.prodcategory"), AppLocal.getIntString("label.prodtax"), "IMAGE", "STOCKCOST", "STOCKVOLUME"},
                // El productCatDatas del SentenceContainer, igualito
                new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.BOOLEAN, Datas.BOOLEAN, Datas.DOUBLE, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.IMAGE, Datas.DOUBLE, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT}, 
                new int[]{0, 1, 2, 5, 6, 7, 8});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(new RenderStringBasic(new Formats[] {Formats.STRING, Formats.NULL, Formats.STRING}, new int[]{0, 2}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    public Component getFilter() {
        return jproductfilter;
    }  
    
    public Component getToolbarExtras() {
        
        JButton btnScanPal = new JButton();
        btnScanPal.setText("ScanPal");
        btnScanPal.setEnabled(m_App.getDeviceScanner() != null);
        btnScanPal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanPalActionPerformed(evt);
            }
        });      
        
        return btnScanPal;
    }
    
    private void btnScanPalActionPerformed(java.awt.event.ActionEvent evt) {                                           
  
        JDlgUploadProducts.showMessage(this, m_App.getDeviceScanner(), m_bd);
    }  
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.Products");
    } 
    
    public void activate() throws BasicException {
        
        jeditor.activate(); 
        jproductfilter.activate();
        
        super.activate();
    } 
    
    public void updateValue(Object value) {
        
        // recargo 
        try {
            m_bdstock.loadList(liststock.list(value));
        } catch (BasicException e) {
            m_bdstock.loadList(null);
        }
    }    
}
