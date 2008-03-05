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

package net.adrianromero.tpv.util;

import java.awt.image.*;
import java.awt.*;

public class ThumbNailBuilder {
    
    private Image m_imgdefault;
    private int m_width;
    private int m_height;
    
    /** Creates a new instance of ThumbNailBuilder */
    
    public ThumbNailBuilder(int width, int height) {
        this(width, height, null);
    }
    public ThumbNailBuilder(int width, int height, Image imgdef) {
        m_width = width;
        m_height = height;
        if (imgdef == null) {
            m_imgdefault = null;
        } else {
            m_imgdefault = getThumbNail(imgdef);
        }       
    }
    
    public Image getThumbNail(Image img) {
   
        if (img == null) {
            return m_imgdefault;
        } else {
            
//            // y ahora hacemos la mascara de thumb
//            MaskFilter filter = new MaskFilter(Color.WHITE);
//            ImageProducer prod = new FilteredImageSource(img.getSource(), filter);
//            img = Toolkit.getDefaultToolkit().createImage(prod);



            BufferedImage thumb = new BufferedImage(m_width, m_height, BufferedImage.TYPE_4BYTE_ABGR);

            Graphics2D g2d = thumb.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            g2d.setColor(new Color(0, 0, 0, 0)); // Transparent
            g2d.fillRect(0, 0, m_width, m_height);
            
            double scalex = (double) m_width / (double) img.getWidth(null);
            double scaley = (double) m_height / (double) img.getHeight(null);
            
            if (scalex < scaley) {
                g2d.drawImage(img, 0,(int) ((m_height - img.getHeight(null) * scalex) / 2.0)
                , m_width, (int) (img.getHeight(null) * scalex),  null);
            } else {
               g2d.drawImage(img, (int) ((m_width - img.getWidth(null) * scaley) / 2.0), 0
               , (int) (img.getWidth(null) * scaley), m_height, null);
            }

            g2d.dispose();
            
            return thumb;
        }    
    }    
}
