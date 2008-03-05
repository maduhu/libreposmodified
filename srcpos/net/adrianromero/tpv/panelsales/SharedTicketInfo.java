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

package net.adrianromero.tpv.panelsales;

import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.data.loader.SerializableRead;
import net.adrianromero.data.loader.SerializableWrite;

/**
 *
 * @author adrianromero
 */
public class SharedTicketInfo implements SerializableRead, SerializableWrite {
    
    private String id;
    private String name;
    
    /** Creates a new instance of SharedTicketInfo */
    public SharedTicketInfo() {
    }
    
    public void readValues(DataRead dr) throws BasicException {
        id = dr.getString(1);
        name = dr.getString(2);
    }   
    public void writeValues(DataWrite dp) throws BasicException {
        dp.setString(1, id);
        dp.setString(2, name);
    }
    
    public String getId() {
        return id;
    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }
//
//    public void setName(String name) {
//        this.name = name;
//    }    
}
