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

package net.adrianromero.tpv.printer.screen;

import java.awt.*;
import javax.swing.*;
import net.adrianromero.tpv.printer.*;
import net.adrianromero.tpv.forms.AppLocal;

/**
 *
 * @author  Adrian
 */
public class DeviceDisplayPanel extends JPanel implements DeviceDisplay, DeviceDisplayImpl {
    
    private static final int H_GAP = 16;
    private static final int V_GAP = 16;
    
    private String m_sName;

    private int m_iFontHeight;
    private int m_iFontWidth;
   
    private double m_dZoom; // Solo modificable en el constructor.

    private Font m_FontBase;
    
    private DeviceDisplayBase m_displaylines;
    
    /** Creates new form JVisor */
    public DeviceDisplayPanel() {
        this(1.0);
    }
    public DeviceDisplayPanel(double dZoom) {
        initComponents();
        
        m_sName = AppLocal.getIntString("Display.Screen");

        m_dZoom = dZoom;
        
        // inicializo los tamanos
        m_FontBase =  new Font("Monospaced", Font.BOLD, (int)(16 * m_dZoom));
        FontMetrics fm = getFontMetrics(m_FontBase);
        
        m_iFontHeight = fm.getHeight();
        m_iFontWidth = fm.stringWidth(" ");

        m_displaylines = new DeviceDisplayBase(this);
    }
    
    public String getDisplayName() {
        return m_sName;
    }    
    public String getDisplayDescription() {
        return null;
    }        
    public JComponent getDisplayComponent() {
        return this;
    }
    
    public void writeVisor(String sLine1, String sLine2) {
        
        m_displaylines.writeVisor(sLine1, sLine2);
    }

    public void writeTimeVisor(String sLine1) {   
        
        m_displaylines.writeTimeVisor(sLine1);
    }
     
    public void clearVisor() {
        m_displaylines.clearVisor();
    }
    
    public void repaintLines() {
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        
        paintBorder(g);
        
        Graphics2D g2d = (Graphics2D) g;     
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        Insets i = getInsets();
        Dimension size = getSize();
        
        //g.setColor(getBackground());
        g2d.setPaint(new GradientPaint(getWidth() - i.left - i.right - 50, getHeight() - i.top - i.bottom - 50, getBackground()
                                     , getWidth() - i.left - i.right, getHeight() - i.top - i.bottom, new Color(0xf0f0f0), true));
        g2d.fillRect(i.left, i.top, getWidth() - i.left - i.right, getHeight() - i.top - i.bottom);
        
        // AffineTransform oldtrans = g2d.getTransform();
        // g2d.transform(AffineTransform.getScaleInstance(m_dZoom, m_dZoom));
        
        g.setColor(getForeground());
        g.setFont(m_FontBase);

        g.drawString(m_displaylines.getLine1(), (size.width - i.right - i.left - m_iFontWidth * 20) / 2, (size.height - i.top - i.left - m_iFontHeight * 2) / 2  + m_iFontHeight);
        g.drawString(m_displaylines.getLine2(), (size.width - i.right - i.left - m_iFontWidth * 20) / 2, (size.height - i.top - i.left - m_iFontHeight * 2) / 2 + 2 * m_iFontHeight);
              
        
        // g2d.setTransform(oldtrans);
    }

    public Dimension getPreferredSize() {
        Insets ins = getInsets();
        return new Dimension((int) (m_iFontWidth * 20 + 2 * H_GAP) + ins.left + ins.right
                           , (int) (m_iFontHeight * 2 + 2 * V_GAP) + ins.top + ins.bottom);
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(javax.swing.UIManager.getDefaults().getColor("window"));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
    }
    // </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
