/*
 * PrepaidTests.java
 *
 * Created on February 1, 2008, 12:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.emar.prepaid;

import java.util.Date;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.forms.AppViewConnection;

/**
 *
 * @author marceloandrade
 */
public class PrepaidTests {
    
    /** Creates a new instance of PrepaidTests */
    public PrepaidTests() {
    }
    
    public static void main(String[] args)
    {
        AppViewConnection m_appcnt = null;
        // Inicializo la conectividad
        try {
            m_appcnt = new AppViewConnection();
        } catch (BasicException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        Tarjeta tar=null;
        try {
            tar = Tarjeta.findId(m_appcnt.getSession(), "0401311527");
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
        if (tar==null) System.exit(2);
        System.out.println(tar.getSaldo());
        System.out.println(tar.getFecha_venta());
        System.out.println(tar.getCliente().getNombre());
        //tar.recargar(15);
       if (tar.cobrar(5))
       {
            System.out.println("Se ha cobrado");
       }
       else
       {
            System.out.println("No se ha cobrado");
            
       }
       try {
            MovimientosTarjeta movTar = new MovimientosTarjeta();
            movTar.setFecha_movimiento(new Date());
            movTar.setId_tarjeta(tar.getIdentificador());
            movTar.setObservacion("prueba");
            movTar.setUsuario("marcelo");
            movTar.setValor_transaccion(5);
            Tarjeta.save(m_appcnt.getSession(), tar, movTar);
        } catch (BasicException ex) {
            ex.printStackTrace();
        }
    }
    
}
