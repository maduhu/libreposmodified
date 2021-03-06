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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class AppConfig {
     
    private Properties m_propsconfig;
    
    /** Creates a new instance of AppConfig */
    public AppConfig() {
        m_propsconfig = new Properties();
    }
    
    public void setProperty(String sKey, String sValue) {
        m_propsconfig.setProperty(sKey, sValue);
    }
    
    public String getProperty(String sKey) {
        return m_propsconfig.getProperty(sKey);
    }
    
    private File getConfigFile() {
        return new File(new File(System.getProperty("user.home")), "librepos.properties");
    }
    
    private String getLocalHostName() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (java.net.UnknownHostException eUH) {
            return "localhost";
        }
    }
    
    public boolean delete() {
        return getConfigFile().delete();
    }
    
    public void load() {
        
        m_propsconfig = new Properties();

        m_propsconfig.setProperty("db.driverlib", "lib/hsqldb.jar");
        m_propsconfig.setProperty("db.driver", "org.hsqldb.jdbcDriver");
        m_propsconfig.setProperty("db.URL", "jdbc:hsqldb:file:" + new File(new File(System.getProperty("user.home")), "libreposdb").getAbsolutePath() + ";shutdown=true");
        m_propsconfig.setProperty("db.user", "sa");         
        m_propsconfig.setProperty("db.password", "");
        
//        m_propsconfig.setProperty("db.driver", "com.mysql.jdbc.Driver");
//        m_propsconfig.setProperty("db.URL", "jdbc:mysql://localhost:3306/librepos");
//        m_propsconfig.setProperty("db.user", "root");         
//        m_propsconfig.setProperty("db.password", "root");
        
//        m_propsconfig.setProperty("db.driver", "org.postgresql.Driver");
//        m_propsconfig.setProperty("db.URL", "jdbc:postgresql://localhost:5432/librepos");
//        m_propsconfig.setProperty("db.user", "libreposuser");         
//        m_propsconfig.setProperty("db.password", "libreposuser");        

        m_propsconfig.setProperty("machine.hostname", getLocalHostName());
        m_propsconfig.setProperty("machine.printer", "screen");
        m_propsconfig.setProperty("machine.printer.2", "Not defined");
        m_propsconfig.setProperty("machine.printer.3", "Not defined");
        m_propsconfig.setProperty("machine.display", "screen");
        m_propsconfig.setProperty("machine.scale", "Not defined");
        m_propsconfig.setProperty("machine.screenmode", "window"); // fullscreen / window
        m_propsconfig.setProperty("machine.ticketsbag", "standard");
        m_propsconfig.setProperty("machine.scanner", "Not defined");
        
        m_propsconfig.setProperty("payment.gateway", "external");
        m_propsconfig.setProperty("payment.magcardreader", "Not defined");
        m_propsconfig.setProperty("payment.testmode", "false");
        m_propsconfig.setProperty("payment.commerceid", "");
        m_propsconfig.setProperty("payment.commercepassword", "");

        // Cargo las propiedades
        try {
            InputStream in = new FileInputStream(getConfigFile());
            if (in != null) {
                m_propsconfig.load(in);
                in.close();
            }
        } catch (IOException e){
        }
    
    }
    
    public void save() throws IOException {
        OutputStream out = new FileOutputStream(getConfigFile());
        if (out != null) {
            m_propsconfig.store(out, "Librepos configuration file");
            out.close();
        }
    }
}
