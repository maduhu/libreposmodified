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

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import javax.swing.Icon;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import net.adrianromero.tpv.ticket.UserInfo;
import net.adrianromero.tpv.util.Hashcypher;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AppUser {
    
    private static SAXParser m_sp = null;
    
    private String m_sId;
    private String m_sName;
    private String m_sPassword;
    private String m_sRole;
    private Icon m_Icon;
    
    private Set m_apermissions;
    
    /** Creates a new instance of AppUser */
    public AppUser(String id, String name, String password, String role, Icon icon) {
        m_sId = id;
        m_sName = name;
        m_sPassword = password;
        m_sRole = role;
        m_Icon = icon;
        m_apermissions = null;
    }
    
    public Icon getIcon() {
        return m_Icon;
    }
    
    public String getId() {
        return m_sId;
    }    
    
    public String getName() {
        return m_sName;
    }
    
    public void setPassword(String sValue) {
        m_sPassword = sValue;
    }
    
    public String getPassword() {
        return m_sPassword;
    }
    
    public String getRole() {
        return m_sRole;
    }
    
    public boolean authenticate() {
        return m_sPassword == null || m_sPassword.equals("") || m_sPassword.startsWith("empty:");
    }
    public boolean authenticate(String sPwd) {
        return Hashcypher.authenticate(sPwd, m_sPassword);
    }
    
    public void fillPermissions(AppView app) {
        
        // inicializamos los permisos
        m_apermissions = new HashSet();
        // Y lo que todos tienen permisos
        m_apermissions.add("net.adrianromero.tpv.forms.JPanelMenu");
        m_apermissions.add("Menu.Exit");        
        
        String sRolePermisions = app.lookupDataLogic(DataLogicSystem.class).findRolePermissions(m_sRole);
       
        if (sRolePermisions != null) {
            try {
                if (m_sp == null) {
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    m_sp = spf.newSAXParser();
                }
                m_sp.parse(new InputSource(new StringReader(sRolePermisions)), new ConfigurationHandler());

            } catch (ParserConfigurationException ePC) {
                System.out.println("Error en el analizador XML. Consulte con su administrador");
            } catch (SAXException eSAX) {
                System.out.println("El archivo no es un documento XML valido. Error de analisis.");
            } catch (IOException eIO) {
                System.out.println("Error al leer el archivo. Consulte con su administrador.");
            }
        }         

    }
    
    public boolean hasPermission(String sObject) {
        return (m_apermissions == null) ? false : m_apermissions.contains(sObject);        
    }   
    
    public UserInfo getUserInfo() {
        return new UserInfo(m_sId, m_sName);
    }
    
    private class ConfigurationHandler extends DefaultHandler {       
        public void startDocument() throws SAXException {}
        public void endDocument() throws SAXException {}    
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
            if ("class".equals(qName)){
                m_apermissions.add(attributes.getValue("name"));
            }
        }      
        public void endElement(String uri, String localName, String qName) throws SAXException {}
        public void characters(char[] ch, int start, int length) throws SAXException {}
    }     
}
