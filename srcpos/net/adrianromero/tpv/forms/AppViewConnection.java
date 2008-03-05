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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.loader.Session;
import net.adrianromero.tpv.util.AltEncrypter;
//import org.apache.commons.dbcp.BasicDataSource;

public class AppViewConnection {
    
    private AppConfig m_config;   
    private Session m_s;
    
    /** Creates a new instance of AppViewConnection */
    public AppViewConnection() throws BasicException {
        
         // Leo la configuracion
        m_config = new AppConfig();
        m_config.load();
        
        m_s = null;
               
        // Inicializo la conexion contra la base de datos.
        try {   
            
            ClassLoader cloader = new URLClassLoader(new URL[] {new File(getProperty("db.driverlib")).toURI().toURL()});
            DriverManager.registerDriver(new DriverWrapper((Driver) Class.forName(getProperty("db.driver"), true, cloader).newInstance()));            
            
            String sDBUser = getProperty("db.user");
            String sDBPassword = getProperty("db.password");        
            if (sDBUser != null && sDBPassword != null && sDBPassword.startsWith("crypt:")) {
                // La clave esta encriptada.
                AltEncrypter cypher = new AltEncrypter("cypherkey" + sDBUser);
                sDBPassword = cypher.decrypt(sDBPassword.substring(6));
            }   

             m_s = new Session(getProperty("db.URL"), sDBUser,sDBPassword);     
            
//            BasicDataSource ds = new BasicDataSource();
//            ds.setUrl(getProperty("db.URL"));
//            ds.setUsername(sDBUser);
//            ds.setPassword(sDBPassword);
//
//            m_ds = ds;


        } catch (InstantiationException e) {
            throw new BasicException(AppLocal.getIntString("message.databasedrivererror"), e);
        } catch (IllegalAccessException eIA) {
            throw new BasicException(AppLocal.getIntString("message.databasedrivererror"), eIA);
        } catch (MalformedURLException eMURL) {
            throw new BasicException(AppLocal.getIntString("message.databasedrivererror"), eMURL);
        } catch (ClassNotFoundException eCNF) {
            throw new BasicException(AppLocal.getIntString("message.databasedrivererror"), eCNF);
        } catch (SQLException eSQL) {
            throw new BasicException(AppLocal.getIntString("message.databaseconnectionerror"), eSQL);
        }      
    }
    
    public void disconnect() {
        m_s.close();        
    }
    
    public Session getSession() {
        return m_s;
    }

    public String getHost() {
        return m_config.getProperty("machine.hostname");
    }
    
    public String getProperty(String sKey) {
        return m_config.getProperty(sKey);
    }   
}
