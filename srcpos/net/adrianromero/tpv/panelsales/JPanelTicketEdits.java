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

package net.adrianromero.tpv.panelsales;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.forms.UserView;
import net.adrianromero.tpv.ticket.ProductInfoExt;

public class JPanelTicketEdits extends JPanelTicket {
    
    // private JRefundLines m_reflines;
    private JTicketCatalogLines m_catandlines;
    
    /** Creates a new instance of JPanelTicketRefunds */
    public JPanelTicketEdits(AppView oApp, UserView oUser) {
        super(oApp, oUser);
    }
    public String getTitle() {
        return null;
    }
    
    public void activate() throws BasicException {      
        super.activate();
        m_catandlines.loadCatalog();
    }
    
    public void showCatalog() {
        m_jbtnconfig.setVisible(true);
        m_catandlines.showCatalog();
    }
    
    public void showRefundLines(List aRefundLines) {
        // anado las lineas de refund
        // m_reflines.setLines(aRefundLines);
        m_jbtnconfig.setVisible(false);
        m_catandlines.showRefundLines(aRefundLines);
    }
    
    protected JTicketsBag getJTicketsBag() {
        return new JTicketsBagTicket(m_App, m_User, this);
    }    
    protected Component getSouthComponent() {
//        m_reflines = new JRefundLines(m_App, this);
//        return m_reflines;
        m_catandlines = new JTicketCatalogLines(m_App, this);
        m_catandlines.addActionListener(new CatalogListener());
        return m_catandlines;
    } 
    
    private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttonTransition((ProductInfoExt) e.getSource());
        }  
    }  
    
//    private class CatalogSelectionListener implements ListSelectionListener {
//        public void valueChanged(ListSelectionEvent e) {      
//            
//            if (!e.getValueIsAdjusting()) {
//                int i = m_ticketlines.getSelectedIndex();
//                
//                // Buscamos el primer producto no Auxiliar.
//                while (i >= 0 && m_oTicket.getLine(i).isProductCom()) {
//                    i--;
//                }
//                
//                // Mostramos el panel de catalogo adecuado...
//                if (i >= 0) {
//                    m_catandlines.loadProduct(m_oTicket.getLine(i).getProductReference());
//                } else {
//                    m_catandlines.loadProduct(null);
//                }
//            }
//        }  
//    }    
}
