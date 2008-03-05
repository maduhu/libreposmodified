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

package net.adrianromero.data.loader;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocalRes {
    
    private static ResourceBundle m_messages = ResourceBundle.getBundle("net/adrianromero/data/i18n/messages");     
    
    /** Creates a new instance of LocalRes */
    private LocalRes() {
    }
       
    public static String getIntString(String sKey) {
        try {            
            return m_messages.getString(sKey);
        } catch (MissingResourceException e) {
            return "** " + sKey + " **";
        }
    }         
}
