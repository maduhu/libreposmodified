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

package net.adrianromero.tpv.mant;
import javax.swing.ListCellRenderer;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.ListCellRendererBasic;
import net.adrianromero.tpv.forms.AppLocal;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.format.Formats;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.Vectorer;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.SaveProvider;
import net.adrianromero.data.user.ListProvider;
import net.adrianromero.data.user.ListProviderCreator;
import net.adrianromero.tpv.panels.*;

public class JPanelPlaces extends JPanelTable {
    
    private TableDefinition tplaces;
    private PlacesEditor jeditor;
    
    /** Creates a new instance of JPanelPlaces */
    public JPanelPlaces(AppView oApp) {
        super(oApp);
        
        tplaces = new TableDefinition(m_App.getSession(),
            "PLACES"
            , new String[] {"ID", "NAME", "X", "Y", "FLOOR"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), "X", "Y", AppLocal.getIntString("label.placefloor")}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.INT, Datas.INT, Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.INT, Formats.INT, Formats.NULL}
            , new int[] {0}
        ); 
        jeditor = new PlacesEditor(oApp, m_Dirty);    
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(tplaces);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tplaces);      
    }
    
    public Vectorer getVectorer() {
        return tplaces.getVectorerBasic(new int[]{1});
    }
    
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tplaces.getRenderStringBasic(new int[]{1}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.Tables");
    }      
    
    public void activate() throws BasicException {
        jeditor.activate(); // primero activo el editor 
        super.activate();   // segundo activo el padre        
    }     
}
