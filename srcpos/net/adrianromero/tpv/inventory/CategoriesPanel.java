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

import javax.swing.ListCellRenderer;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.panels.*;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;
import net.adrianromero.tpv.forms.SentenceContainer;

public class CategoriesPanel extends JPanelTable {
    
    private TableDefinition tcategories;
    private CategoriesEditor jeditor;
    
    /** Creates a new instance of JPanelCategories */
    public CategoriesPanel(AppView oApp) {
        super(oApp);
        
        tcategories = m_App.lookupDataLogic(SentenceContainer.class).getTableCategories();
        jeditor = new CategoriesEditor(oApp, m_Dirty);    
    }   
    
    public void activate() throws BasicException { 
        
        jeditor.activate();         
        super.activate();
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(tcategories);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tcategories);      
    }
    
    public Vectorer getVectorer() {
        return tcategories.getVectorerBasic(new int[]{1});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return tcategories.getComparatorCreator(new int[]{1});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tcategories.getRenderStringBasic(new int[]{1}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.Categories");
    }        
}
