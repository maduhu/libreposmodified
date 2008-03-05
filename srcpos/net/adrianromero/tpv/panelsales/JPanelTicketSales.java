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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.catalog.CatalogSelector;
import net.adrianromero.tpv.catalog.JCatalog;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.forms.UserView;
import net.adrianromero.tpv.ticket.ProductInfoExt;
import net.adrianromero.tpv.ticket.ThumbNailBuilderProduct;

public class JPanelTicketSales extends JPanelTicket {

    private CatalogSelector m_cat;
   
    /** Creates a new instance of JPanelTicketSales */
    public JPanelTicketSales(AppView oApp, UserView oUser) {
        super(oApp, oUser);
        
        m_ticketlines.addListSelectionListener(new CatalogSelectionListener());
    }
    
    public String getTitle() {
        return null;
    }
    
    protected Component getSouthComponent() {
        m_cat = new JCatalog(m_App,
                m_jbtnconfig.isPriceVisible() 
                ? (m_jbtnconfig.isTaxesIncluded() 
                    ? ThumbNailBuilderProduct.PRICE_SELLTAX 
                    : ThumbNailBuilderProduct.PRICE_SELL)
                : ThumbNailBuilderProduct.PRICE_NONE);
        m_cat.addActionListener(new CatalogListener());
        return m_cat.getComponent();
    }
    
    protected JTicketsBag getJTicketsBag() {
        return JTicketsBag.createTicketsBag(m_App.getProperty("machine.ticketsbag"), m_App, m_User, this);
    }
    
    public void activate() throws BasicException {      
        super.activate();
        m_cat.loadCatalog();
    }      
    
    private class CatalogListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttonTransition((ProductInfoExt) e.getSource());
        }  
    }
    
    private class CatalogSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {      
            
            if (!e.getValueIsAdjusting()) {
                int i = m_ticketlines.getSelectedIndex();
                
                // Buscamos el primer producto no Auxiliar.
                while (i >= 0 && m_oTicket.getLine(i).isProductCom()) {
                    i--;
                }
                
                // Mostramos el panel de catalogo adecuado...
                if (i >= 0) {
                    m_cat.showCatalogPanel(m_oTicket.getLine(i).getProductReference());
                } else {
                    m_cat.showCatalogPanel(null);
                }
            }
        }  
    }
}
