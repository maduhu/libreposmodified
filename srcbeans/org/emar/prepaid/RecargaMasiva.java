/*
 * RecargaMasiva.java
 *
 * Created on February 6, 2008, 1:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.emar.prepaid;

import java.sql.SQLException;
import java.sql.Statement;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.forms.ProcessAction;
import net.adrianromero.tpv.forms.UserView;
import org.emar.prepaid.editor.JRecargaValor;

/**
 *
 * @author marceloandrade
 */
public class RecargaMasiva implements ProcessAction{
    private AppView m_app;
    private UserView m_user;
    /** Creates a new instance of RecargaMasiva */
    public RecargaMasiva(AppView app, UserView user) {
        m_app = app;
        m_user = user;
    }
    
    public MessageInf execute() throws BasicException {
        
        JRecargaValor jdValor = new JRecargaValor();
        jdValor.setVisible(true);
        
        double valor = jdValor.getM_dValor();
        boolean encerar = jdValor.getM_bEncerar();
        
        if ( valor == 0 ) return new MessageInf(MessageInf.SGN_NOTICE, "Se ha cancelado la operación" );
        
        Statement stmt=null;
        try {
            stmt = m_app.getSession().getConnection().createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        int totalEncerada=0;
        if (encerar){
            try {
                stmt.executeUpdate("INSERT INTO MOVIMIENTOSTARJETA (ID_TARJETA, FECHA_MOVIMIENTO, VALOR_TRANSACCION, OBSERVACION, USUARIO) SELECT ID, NOW(), (SALDO * (-1)), 'encerar_masiva', '"+ m_user.getUser().getName() + "' FROM TARJETA  WHERE RECARGABLE = 'S' AND ESTADO = 'A' ");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                stmt.executeUpdate("UPDATE TARJETA SET SALDO = 0 WHERE RECARGABLE = 'S' AND ESTADO = 'A' ");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                totalEncerada = stmt.getUpdateCount();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        try {
            stmt.executeUpdate("UPDATE TARJETA SET SALDO = SALDO + " + (new Double(valor)).toString()  +  " WHERE RECARGABLE = 'S' AND ESTADO = 'A' ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int total=0;
        try {
            total = stmt.getUpdateCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            stmt = m_app.getSession().getConnection().createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            stmt.executeUpdate("INSERT INTO MOVIMIENTOSTARJETA (ID_TARJETA, FECHA_MOVIMIENTO, VALOR_TRANSACCION, OBSERVACION, USUARIO) SELECT ID, NOW(), " + (new Double(valor)).toString() + ", 'recarga_masiva', '"+ m_user.getUser().getName() + "' FROM TARJETA  WHERE RECARGABLE = 'S' AND ESTADO = 'A' ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        int total2=0;
        try {
            total2 = stmt.getUpdateCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String msg = "";
        msg = "Se ha acreditado correctamente a " + (new Integer(total).toString())  + " tarjetas recargables activas.";
        if (encerar)
        {
            msg += "\nY se enceraron a " + (new Integer(totalEncerada).toString()) + " tarjetas.";
        }
        return new MessageInf(MessageInf.SGN_SUCCESS, msg);
    }
    
}
