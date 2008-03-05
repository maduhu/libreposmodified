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

package org.emar.prepaid.panel;

import javax.swing.ListCellRenderer;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.data.loader.ComparatorCreator;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.panels.JPanelTable;
import net.adrianromero.tpv.ticket.*;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;
import net.adrianromero.tpv.forms.SentenceContainer;
import org.emar.prepaid.editor.ClientEditor;
import org.emar.prepaid.editor.TarjetaEditor;

public class PanelTarjeta extends JPanelTable {

    private TableDefinition ttarjeta;
    private TarjetaEditor jeditor;
    
    /** Creates a new instance of JPanelDuty */
    public PanelTarjeta(AppView oApp) {
        super(oApp);
        
        ttarjeta = m_App.lookupDataLogic(SentenceContainer.class).getTableTarjetas();
        jeditor = new TarjetaEditor(m_Dirty);    
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(ttarjeta);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(ttarjeta);      
    }
    
    public Vectorer getVectorer() {
        return ttarjeta.getVectorerBasic(new int[]{0, 4, 5});
    }
    
    public ComparatorCreator getComparatorCreator() {
        return ttarjeta.getComparatorCreator(new int[] {0, 4, 5});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(ttarjeta.getRenderStringBasic(new int[]{0,4,5}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
        
    public String getTitle() {
        return AppLocal.getIntString("Menu.Tarjetas");
    }     
}
