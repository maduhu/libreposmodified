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

package net.adrianromero.tpv.printer.ticket;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import net.adrianromero.tpv.printer.DevicePrinter;
import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.java2d.Java2DCanvasProvider;

public class PrintItemBarcode implements PrintItem {
    
    private AbstractBarcodeBean m_barcode;
    private String m_sCode;
    
    private int m_iWidth;
    private int m_iHeight;
    
    /** Creates a new instance of PrinterItemBarcode */
    public PrintItemBarcode(String sType, String code) {
        
        m_sCode = code;
        
        if (DevicePrinter.BARCODE_EAN13.equals(sType)) {
            m_barcode = new EAN13Bean();
        } else if (DevicePrinter.BARCODE_CODE128.equals(sType)) {
            m_barcode = new Code128Bean();
        } else {
            m_barcode = null;
        }
        
        if (m_barcode != null) {
            m_barcode.setModuleWidth(1.0); 
            m_barcode.setBarHeight(40.0);
            m_barcode.setFontSize(10.0);
            m_barcode.setQuietZone(10.0);
            m_barcode.doQuietZone(true);  
            
            BarcodeDimension dim = m_barcode.calcDimensions(m_sCode);
            m_iWidth = (int) dim.getWidth(0);
            m_iHeight = (int) dim.getHeight(0);
        }
    }
    
    public void draw(Graphics2D g, int x, int y, int width) {

        if (m_barcode != null) {
            Graphics2D g2d = (Graphics2D) g;

            AffineTransform oldt = g2d.getTransform();

            g2d.translate(x - 10 + (width - m_iWidth) / 2, y + 10);      
            
            try {
                m_barcode.generateBarcode(new Java2DCanvasProvider(g2d, 0), m_sCode);
            } catch (IllegalArgumentException e) {
                g2d.drawRect(0, 0, m_iWidth, m_iHeight);
                g2d.drawLine(0, 0, m_iWidth, m_iHeight);
                g2d.drawLine(m_iWidth, 0, 0, m_iHeight);
            }

            g2d.setTransform(oldt);
        }
    }
    
    public int getHeight() {
        return m_iHeight + 20;
    }    
}
