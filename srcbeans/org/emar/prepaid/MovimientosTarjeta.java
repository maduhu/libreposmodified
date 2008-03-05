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
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.format.Formats;

/**
 * Class MovimientosTarjeta
 */
public class MovimientosTarjeta {

  //
  // Fields
  //

  private long id = 1;
  private String id_tarjeta;
  private Date fecha_movimiento;
  private double valor_transaccion;
  private String observacion;
  private String usuario;
  
  //
  // Constructors
  //
  public MovimientosTarjeta () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  
  //
  // Other methods
  //
  
  public static boolean save(Session s, MovimientosTarjeta movTarj) throws BasicException {
        TableDefinition tmovtarjeta =  new TableDefinition(s,
                "MOVIMIENTOSTARJETA"
                , new String[] {"ID_TARJETA", "FECHA_MOVIMIENTO", "VALOR_TRANSACCION", "OBSERVACION", "USUARIO"}
        , new String[] {"ID_TARJETA", "FECHA_MOVIMIENTO", "VALOR_TRANSACCION", "OBSERVACION", "USUARIO"}
        , new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING}
        , new Formats[] {Formats.STRING, Formats.TIMESTAMP, Formats.DOUBLE, Formats.STRING, Formats.STRING}
        , new int[] {0}
        );
        
        String sSQL = tmovtarjeta.getInsertSQL();
        
        PreparedSentence ps = new PreparedSentence(s, sSQL, tmovtarjeta.getSerializerInsertBasic(), tmovtarjeta.getSerializerReadBasic());
        
        Object [] params = new Object[5];
        
        params[0] = movTarj.getId_tarjeta();
        params[1] = movTarj.getFecha_movimiento();
        params[2] = movTarj.getValor_transaccion();
        params[3] = movTarj.getObservacion();
        params[4] = movTarj.getUsuario();
        
        ps.exec( params );
        
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(String id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public Date getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(Date fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public double getValor_transaccion() {
        return valor_transaccion;
    }

    public void setValor_transaccion(double valor_transaccion) {
        this.valor_transaccion = valor_transaccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
