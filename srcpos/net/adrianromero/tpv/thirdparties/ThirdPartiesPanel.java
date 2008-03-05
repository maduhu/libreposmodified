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

package net.adrianromero.tpv.thirdparties;
import javax.swing.ListCellRenderer;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.panels.*;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;

public class ThirdPartiesPanel extends JPanelTable {
    
    private TableDefinition tthirdparties;
    private ThirdPartiesView jeditor;
    
    /** Creates a new instance of JPanelPeople */
    public ThirdPartiesPanel(AppView oApp) {
        super(oApp);
        
        tthirdparties = m_App.lookupDataLogic(DataLogicThirdParties.class).getTableThirdParties();        
        jeditor = new ThirdPartiesView(m_App, m_Dirty);    
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(tthirdparties);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tthirdparties);      
    }
    
    public Vectorer getVectorer() {
        return tthirdparties.getVectorerBasic(new int[]{1, 2, 3, 4});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return tthirdparties.getComparatorCreator(new int[] {1, 2, 3, 4});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tthirdparties.getRenderStringBasic(new int[]{1, 2}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }       
    
    public String getTitle() {
        return AppLocal.getIntString("Menu.ThirdPartiesManagement");
    }     
}
