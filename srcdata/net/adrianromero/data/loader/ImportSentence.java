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
 * ImportSentence.java
 *
 * Created on 15 de enero de 2004, 18:54
 */

package net.adrianromero.data.loader;

import net.adrianromero.basic.BasicException;

/**
 *
 * @author  adrian
 */
public class ImportSentence extends BaseSentence {
    
    /** Creates a new instance of ImportSentence */
    public ImportSentence() {
    }
    
    public void closeExec() throws BasicException {
    }
    
    public DataResultSet moreResults() throws BasicException {
        return null;
    }
    
    public DataResultSet openExec(Object params) throws BasicException {
        return null;
    }
}
