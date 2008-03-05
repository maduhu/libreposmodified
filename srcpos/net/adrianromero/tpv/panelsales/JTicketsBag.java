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

import net.adrianromero.basic.BasicException;
import net.adrianromero.tpv.panelsales.simple.JTicketsBagSimple;
import net.adrianromero.tpv.ticket.*; 
import net.adrianromero.tpv.forms.*; 
import javax.swing.*;
import net.adrianromero.data.gui.MessageInf;

// las implementaciones
import net.adrianromero.tpv.panelsales.restaurant.JTicketsBagRestaurantMap;
import net.adrianromero.tpv.panelsales.shared.JTicketsBagShared;

public abstract class JTicketsBag extends JPanel {
    
    protected AppView m_App;
    protected UserView m_User;        
    protected TicketsEditor m_panelticket;    

    
    /** Creates new form JTicketsBag */
    public JTicketsBag(AppView oApp, UserView user, TicketsEditor panelticket) {        
        m_App = oApp;     
        m_User = user;
        m_panelticket = panelticket;
        // initComponents();
    }
    
    public abstract void activate();
    public abstract boolean deactivate();
    public abstract void cancelTicket();
    public abstract void saveTicket();
    
    protected abstract JComponent getBagComponent();
    protected abstract JComponent getNullComponent();
    
    public static JTicketsBag createTicketsBag(String sName, AppView oApp, UserView user, TicketsEditor panelticket) {
        
        if ("standard".equals(sName)) {
            // return new JTicketsBagMulti(oApp, user, panelticket);
            return new JTicketsBagShared(oApp, user, panelticket);
        } else if ("restaurant".equals(sName)) {
            return new JTicketsBagRestaurantMap(oApp, user, panelticket);
        } else { // "simple"
            return new JTicketsBagSimple(oApp, user, panelticket);
        }
    }
    
    protected final TicketInfo createTicketModel(){

        // creo el nuevo ticket
        TicketInfo ticket = new TicketInfo();

        try {
            Integer index = m_App.lookupDataLogic(SentenceContainer.class).getNextTicketIndex();
            ticket.setId(index.intValue());
        } catch (BasicException eD) {
            ticket.setId(0);
        }       

        // Pinto el numero del ticket
        return ticket;
    }    
    
    protected final void saveTicket(TicketInfo ticket) {

        try {
            m_App.lookupDataLogic(SentenceContainer.class).saveTicket(ticket, m_App.getInventoryLocation());                       
        } catch (BasicException eData) {
            MessageInf msg = new MessageInf(MessageInf.SGN_NOTICE, AppLocal.getIntString("message.nosave"), eData);
            msg.show(this);
        }
    }
    
    protected final void deleteTicket(TicketInfo ticket) {
        
        try {               
            m_App.lookupDataLogic(SentenceContainer.class).deleteTicket(ticket, m_App.getInventoryLocation());
        } catch (BasicException be) {
        }
    }    
}