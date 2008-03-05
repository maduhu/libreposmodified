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

import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.QBFBuilder;
import net.adrianromero.data.loader.SentenceList;
import net.adrianromero.data.loader.SequenceForMySQL;
import net.adrianromero.data.loader.SerializerReadBasic;
import net.adrianromero.data.loader.SerializerReadInteger;
import net.adrianromero.data.loader.SerializerWriteBasic;
import net.adrianromero.data.loader.StaticSentence;

public class SentenceContainerMySQL extends SentenceContainer {
    
    /** Creates a new instance of SentenceContainerMySQL */
    public SentenceContainerMySQL() {
    }   
    
    public final SentenceList getProductCatQBF() {
        return new StaticSentence(s
            , new QBFBuilder("SELECT P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.CATEGORY, P.TAX, P.IMAGE, P.STOCKCOST, P.STOCKVOLUME, NOT (C.REFERENCE IS NULL), C.CATORDER FROM PRODUCTS P LEFT OUTER JOIN PRODUCTS_CAT C ON P.REFERENCE = C.REFERENCE WHERE ?(QBF_FILTER) ORDER BY P.NAME", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadBasic(productcatDatas));
    }    

    public final Integer getNextTicketIndex() throws BasicException {
        return (Integer) new SequenceForMySQL(s, "TICKETSNUM", SerializerReadInteger.INSTANCE).find();
    }  
}