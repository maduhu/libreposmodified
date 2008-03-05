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

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

public class AppLocal {
  
    private static List<ResourceBundle> m_messages;
    
    static {
        m_messages = new LinkedList<ResourceBundle>();
        m_messages.add(ResourceBundle.getBundle("net/adrianromero/tpv/i18n/messages"));
        m_messages.add(ResourceBundle.getBundle("com/openbravo/i18n/messageserp"));
    }
    
    /** Creates a new instance of AppLocal */
    private AppLocal() {
    }
    
    public static String getIntString(String sKey) {
        
        if (sKey == null) {
            return null;
        } else  {            
            for (ResourceBundle r : m_messages) {
                try {
                    return r.getString(sKey);
                } catch (MissingResourceException e) {
                    // Next
                }
            }
            
            // MissingResourceException in all ResourceBundle
            return "** " + sKey + " **";
        }
    }
    
    public static String getIntString(String sKey, Object ... sValues) {
        
        if (sKey == null) {
            return null;
        } else  {
            for (ResourceBundle r : m_messages) {
                try {
                    return MessageFormat.format(r.getString(sKey), sValues);
                } catch (MissingResourceException e) {
                    // Next
                }
            }
            
            // MissingResourceException in all ResourceBundle
            StringBuffer sreturn = new StringBuffer();
            sreturn.append("** ");
            sreturn.append(sKey);            
            for (Object value : sValues) {
                sreturn.append(" < ");
                sreturn.append(value.toString());
            }
            sreturn.append("** ");
            
            return sreturn.toString();
        }
    }
}
