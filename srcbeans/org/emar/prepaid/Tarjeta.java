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
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.DataResultSet;
import net.adrianromero.data.loader.DataWrite;
import net.adrianromero.data.loader.Datas;
import net.adrianromero.data.loader.PreparedSentence;
import net.adrianromero.data.loader.SerializerWrite;
import net.adrianromero.data.loader.Session;
import net.adrianromero.data.loader.TableDefinition;
import net.adrianromero.format.Formats;
import net.adrianromero.tpv.util.RoundUtils;


/**
 * Class Tarjeta
 */
public class Tarjeta {
    
    //
    // Fields
    //
    
    /**
     * normalmente va a ser la cédula del usuario, si es la segunda sería la cédula + un secuencial.
     */
    private String identificador;
    private java.util.Date fecha_venta;
    private java.util.Date fecha_caduca;
    private double saldo = 0;
    private org.emar.prepaid.Cliente cliente;
    private String estado = "A";
    private String recargable = "S";
    
    //
    // Constructors
    //
    public Tarjeta() { };
    
    //
    // Methods
    //
    
    
    //
    // Accessor methods
    //
    
    /**
     * Set the value of identificador
     * normalmente va a ser la cédula del usuario, si es la segunda sería la cédula +
     * un secuencial.
     * @param newVar the new value of identificador
     */
    public void setIdentificador( String newVar ) {
        identificador = newVar;
    }
    
    /**
     * Get the value of identificador
     * normalmente va a ser la cédula del usuario, si es la segunda sería la cédula +
     * un secuencial.
     * @return the value of identificador
     */
    public String getIdentificador( ) {
        return identificador;
    }
    
    /**
     * Set the value of fecha_venta
     * @param newVar the new value of fecha_venta
     */
    public void setFecha_venta( java.util.Date newVar ) {
        fecha_venta = newVar;
    }
    
    /**
     * Get the value of fecha_venta
     * @return the value of fecha_venta
     */
    public java.util.Date getFecha_venta( ) {
        return fecha_venta;
    }
    
    /**
     * Set the value of fecha_caduca
     * @param newVar the new value of fecha_caduca
     */
    public void setFecha_caduca( java.util.Date newVar ) {
        fecha_caduca = newVar;
    }
    
    /**
     * Get the value of fecha_caduca
     * @return the value of fecha_caduca
     */
    public java.util.Date getFecha_caduca( ) {
        return fecha_caduca;
    }
    
    /**
     * Set the value of saldo
     * @param newVar the new value of saldo
     */
    public void setSaldo( double newVar ) {
        saldo = newVar;
    }
    
    /**
     * Get the value of saldo
     * @return the value of saldo
     */
    public double getSaldo( ) {
        return saldo;
    }
    
    /**
     * Set the value of cliente
     * @param newVar the new value of cliente
     */
    public void setCliente( org.emar.prepaid.Cliente newVar ) {
        cliente = newVar;
    }
    
    /**
     * Get the value of cliente
     * @return the value of cliente
     */
    public org.emar.prepaid.Cliente getCliente( ) {
        return cliente;
    }
    
    /**
     * Set the value of estado
     * @param newVar the new value of estado
     */
    public void setEstado( String newVar ) {
        estado = newVar;
    }
    
    /**
     * Get the value of estado
     * @return the value of estado
     */
    public String getEstado( ) {
        return estado;
    }
    
    public String getOwnerInfo()
    {
        return this.getCliente().getId() + " " + this.getCliente().getApellido() + " " + this.getCliente().getNombre();
    }
    
    //
    // Other methods
    //
    
    /**
     * función que permite disminuir el valor del saldo de la tarjeta, verificando las
     * reglas del negocio.
     * @return       boolean
     * @param        valor valor a cobrar
     */
    public boolean cobrar( double valor ) {
        
        if (!"A".equals(this.getEstado())) return false;
        
        valor = RoundUtils.round(valor);
        if ( valor < 0 )
            return false;
        
        
        if (valor <= this.getSaldo()) {
            this.setSaldo(RoundUtils.round( this.getSaldo() - valor ));
            return true;
        } else
            return false;
    }
    
    
    /**
     * permite aumentar el saldo de la tarjeta
     * @return       boolean
     * @param        valor valor a recargar
     */
    public boolean recargar( double valor ) {
        
        if (!"A".equals(this.getEstado())) return false;
        
        if (valor < 0 )
            return false;
        
        this.setSaldo(RoundUtils.round(this.getSaldo() + valor));
        return true;
    }
    
    
    /**
     * @return       double
     */
    public double obtener_saldo(  ) {
        return this.getSaldo();
    }
    
    public static Tarjeta findId(Session s, String id) throws BasicException {
        TableDefinition ttarjeta =  new TableDefinition(s,
                "TARJETA"
                , new String[] {"ID", "FECHA_VENTA", "FECHA_CADUCA", "SALDO", "CLIENTE", "ESTADO", "RECARGABLE"}
        , new String[] {"ID", "Fecha Venta", "Fecha Caduca", "Saldo", "Cliente", "Estado", "Recargable" }
        , new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING,Datas.STRING}
        , new Formats[] {Formats.STRING, Formats.TIMESTAMP, Formats.TIMESTAMP, Formats.DOUBLE, Formats.STRING, Formats.STRING, Formats.STRING}
        , new int[] {0}
        );
        
        String sSQL = " SELECT ID, FECHA_VENTA, FECHA_CADUCA, SALDO, CLIENTE, ESTADO, RECARGABLE FROM TARJETA WHERE ID = ? " ;
        
        PreparedSentence ps = new PreparedSentence(s, sSQL, new SerializerWrite() {
            public void writeValues(DataWrite dp, Object obj) throws BasicException {
                Datas.STRING.setValue(dp, 1, obj);
            }
        }, ttarjeta.getSerializerReadBasic());
        DataResultSet drs=null;
        drs = ps.openExec(id);
        if (drs.next() ){            
            Tarjeta tarj = new Tarjeta();
            tarj.setIdentificador(drs.getString(1));
            tarj.setFecha_venta(drs.getTimestamp(2));
            tarj.setFecha_caduca(drs.getTimestamp(3));
            tarj.setSaldo(drs.getDouble(4));
            Cliente cliente = Cliente.findId(s, drs.getString(5));
            tarj.setCliente(cliente);
            tarj.setEstado(drs.getString(6));
            tarj.setRecargable(drs.getString(7));
            
            return tarj;
        } else return null;
        
    }
    
    public static boolean save(Session s, Tarjeta tarj, MovimientosTarjeta movTar) throws BasicException {
        TableDefinition ttarjeta =  new TableDefinition(s,
                "TARJETA"
                , new String[] {"ID", "FECHA_VENTA", "FECHA_CADUCA", "SALDO", "CLIENTE", "ESTADO", "RECARGABLE"}
        , new String[] {"ID", "Fecha Venta", "Fecha Caduca", "Saldo", "Cliente", "Estado" ,"RECARGABLE" }
        , new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.TIMESTAMP, Datas.DOUBLE, Datas.STRING, Datas.STRING, Datas.STRING}
        , new Formats[] {Formats.STRING, Formats.TIMESTAMP, Formats.TIMESTAMP, Formats.DOUBLE, Formats.STRING, Formats.STRING, Formats.STRING}
        , new int[] {0}
        );
        
        String sSQL = ttarjeta.getUpdateSQL();
        
        PreparedSentence ps = new PreparedSentence(s, sSQL, ttarjeta.getSerializerUpdateBasic(), ttarjeta.getSerializerReadBasic());
        
        Object [] params = new Object[7];
        
        params[0] = tarj.getIdentificador();
        params[1] = tarj.getFecha_venta();
        params[2] = tarj.getFecha_caduca();
        params[3] = tarj.getSaldo();
        params[4] = tarj.getCliente().getId();
        params[5] = tarj.getEstado();
        params[6] = tarj.getRecargable();
        
        ps.exec( params );
        
        MovimientosTarjeta.save(s, movTar);
        
        return true;
    }

    public String getRecargable() {
        return recargable;
    }

    public void setRecargable(String recargable) {
        this.recargable = recargable;
    }
    
}
