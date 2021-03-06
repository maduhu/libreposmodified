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

package net.adrianromero.tpv.forms;

import java.util.ArrayList;
import javax.swing.Action;

public class MenuDefinition {
    
    private String m_sTitle;
    
    private ArrayList m_aMenuElements;
    
    /** Creates a new instance of MenuDefinition */
    public MenuDefinition(String stitle) {
        m_sTitle = stitle;
        m_aMenuElements = new ArrayList();
    }
    
    public String getTitle() {
        return AppLocal.getIntString(m_sTitle);
    }   
    
    public void addMenuItem(Action act) {
        MenuItemDefinition menuitem = new MenuItemDefinition(act);
        m_aMenuElements.add(menuitem);
    }
    
    public void addMenuTitle(String keytext) {
        MenuTitleDefinition menutitle = new MenuTitleDefinition();
        menutitle.KeyText = keytext;
        m_aMenuElements.add(menutitle);
    }
    
    public MenuElement getMenuElement(int i) {
        return (MenuElement) m_aMenuElements.get(i);
    }
    public int countMenuElements() {
        return m_aMenuElements.size();
    }

}
