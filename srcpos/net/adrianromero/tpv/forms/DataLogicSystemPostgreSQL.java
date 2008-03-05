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

import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.SerializerReadInteger;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.StaticSentence;

public class DataLogicSystemPostgreSQL extends DataLogicSystem {
    
    /** Creates a new instance of DataLogicSystemPostgre */
    public DataLogicSystemPostgreSQL() {
    }
    
    public void init(Session s) {
        super.init(s);
        
        m_sInitScript = "/net/adrianromero/scripts/scriptpostgresql";
 
        m_peoplevisible = new StaticSentence(s
            , "SELECT ID, NAME, APPPASSWORD, ROLE, IMAGE FROM PEOPLE WHERE VISIBLE = TRUE"
            , null
            , new SerializerReadBasic(new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE}));    
    }    
}
