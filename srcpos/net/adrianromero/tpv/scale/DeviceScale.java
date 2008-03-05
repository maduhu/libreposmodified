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

package net.adrianromero.tpv.scale;

import net.adrianromero.tpv.forms.AppViewConnection;
import net.adrianromero.tpv.util.StringParser;

public class DeviceScale {
    
    private Scale m_scale;
    
    /** Creates a new instance of DeviceScale */
    public DeviceScale(AppViewConnection app) {
        StringParser sd = new StringParser(app.getProperty("machine.scale"));
        String sScaleType = sd.nextToken(':');
        String sScaleParam1 = sd.nextToken(',');
        String sScaleParam2 = sd.nextToken(',');
        
        if ("dialog1".equals(sScaleType)) {
            m_scale = new ScaleComm(sScaleParam1);
        } else if ("samsungesp".equals(sScaleType)) {
            m_scale = new ScaleSamsungEsp(sScaleParam1);            
        } else if ("fake".equals(sScaleType)) { // de mentirijillas para depurar
            m_scale = new ScaleFake();            
        } else {
            m_scale = null;
        }
    }
    
    public boolean existsScale() {
        return m_scale != null;
    }
    
    public double readWeight() throws ScaleException {
        if (m_scale == null) {
            throw new ScaleException();
        } else {
            return m_scale.readWeight();
        }
    }    
}
