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

/*
 * DataRecord.java
 *
 * Created on 7 de octubre de 2002, 23:17
 */

package net.adrianromero.data.loader;

import net.adrianromero.basic.BasicException;

/**
 *
 * @author  adrian
 */
public interface DataRead {
    
    public Integer getInt(int columnIndex) throws BasicException;
    public String getString(int columnIndex) throws BasicException;
    public Double getDouble(int columnIndex) throws BasicException;
    public Boolean getBoolean(int columnIndex) throws BasicException;
    public java.util.Date getTimestamp(int columnIndex) throws BasicException;

    //public java.io.InputStream getBinaryStream(int columnIndex) throws DataException;
    public byte[] getBytes(int columnIndex) throws BasicException;
    public Object getObject(int columnIndex) throws BasicException ;
    
//    public int getColumnCount() throws DataException;
    public DataField[] getDataField() throws BasicException;
}
