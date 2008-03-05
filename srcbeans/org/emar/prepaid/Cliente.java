//    Librepos is a point of sales application designed for touch screens.
//    Class for manage Magnetic Prepaid Cards
//    Copyright (C) 2008 Marcelo Andrade
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

package org.emar.prepaid;
import java.util.Date;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataRead;
import net.adrianromero.data.loader.DataResultSet;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.SerializerWrite;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.format.Formats;


/**
 * Class Cliente
 */
public class Cliente {
    
    //
    // Fields
    //
    
    private String id;
    private String nombre;
    private String apellido;
    private String observaciones;
    private java.util.Date fecha_creacion;
    
    //
    // Constructors
    //
    public Cliente() { };
    
    //
    // Methods
    //
    
    
    //
    // Accessor methods
    //
    
    /**
     * Set the value of id
     * @param newVar the new value of id
     */
    public void setId( String newVar ) {
        id = newVar;
    }
    
    /**
     * Get the value of id
     * @return the value of id
     */
    public String getId( ) {
        return id;
    }
    
    /**
     * Set the value of nombre
     * @param newVar the new value of nombre
     */
    public void setNombre( String newVar ) {
        nombre = newVar;
    }
    
    /**
     * Get the value of nombre
     * @return the value of nombre
     */
    public String getNombre( ) {
        return nombre;
    }
    
    /**
     * Set the value of apellido
     * @param newVar the new value of apellido
     */
    public void setApellido( String newVar ) {
        apellido = newVar;
    }
    
    /**
     * Get the value of apellido
     * @return the value of apellido
     */
    public String getApellido( ) {
        return apellido;
    }
    
    /**
     * Set the value of observaciones
     * @param newVar the new value of observaciones
     */
    public void setObservaciones( String newVar ) {
        observaciones = newVar;
    }
    
    /**
     * Get the value of observaciones
     * @return the value of observaciones
     */
    public String getObservaciones( ) {
        return observaciones;
    }
    
    /**
     * Set the value of fecha_creacion
     * @param newVar the new value of fecha_creacion
     */
    public void setFecha_creacion( java.util.Date newVar ) {
        fecha_creacion = newVar;
    }
    
    /**
     * Get the value of fecha_creacion
     * @return the value of fecha_creacion
     */
    public java.util.Date getFecha_creacion( ) {
        return fecha_creacion;
    }
    
    //
    // Other methods
    //
    
    /**
     * @return       org.emar.prepaid.Tarjeta
     * @param        valor_inicial
     */
    public org.emar.prepaid.Tarjeta adquirir_tarjeta( double valor_inicial ) {
        Tarjeta tarjeta_nueva = new Tarjeta();
        return tarjeta_nueva;
    }
    
    
    /**
     * @return       boolean
     * @param        id_tarjeta
     */
    public boolean anular_tarjeta( org.emar.prepaid.Tarjeta id_tarjeta ) {
        Tarjeta tarjeta = new Tarjeta();
        return true;
    }
    
    
    public static Cliente findId(Session s, String id) throws BasicException {
        TableDefinition tcliente =  new TableDefinition(s,
                "CLIENTE"
                , new String[] {"ID", "NOMBRE", "APELLIDO", "OBSERVACIONES", "FECHA_CREACION"}
        , new String[] {"ID", "Nombre", "Apellido", "Observaciones", "Fecha Creación" }
        , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP}
        , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.STRING,Formats.TIMESTAMP}
        , new int[] {0}
        );
        
        String sSQL = " SELECT ID, NOMBRE, APELLIDO, OBSERVACIONES, FECHA_CREACION FROM CLIENTE WHERE ID = ? " ;
        
        PreparedSentence ps = new PreparedSentence(s, sSQL, new SerializerWrite() {
            public void writeValues(DataWrite dp, Object obj) throws BasicException {
                Datas.STRING.setValue(dp, 1, obj);
            }
        }, tcliente.getSerializerReadBasic());
        
        DataResultSet drs=null;
        drs = ps.openExec(id);
        if (drs.next() ){
            Cliente clien = new Cliente();
            clien.setId(drs.getString(1));
            clien.setNombre(drs.getString(2));
            clien.setApellido(drs.getString(3));
            clien.setObservaciones(drs.getString(4));
            clien.setFecha_creacion(drs.getTimestamp(5));
            
            return clien;
        }else return null;
        
    }
}
