/*
 * JGPanelGradient.java
 *
 * Created on 22 de mayo de 2005, 1:01
 */

package net.adrianromero.beans;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;
import javax.swing.Timer;

public class JPanelCurves extends JPanel /* implements ActionListener */ {
    
//    private Timer animation;
    
    private Color gradientStart1 = new Color(128, 128, 200);
    private Color gradientEnd1 = new Color(28, 28, 200);
    
    private RenderingHints hints;
    private int counter = 0;
    
    public JPanelCurves() {
        initComponents();
        
        hints = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
//        animation = new Timer(50, this);
    }
    
//    public void start() {
//        animation.start(); 
//    }    
//    public void stop() {
//        animation.stop(); 
//    }
//    
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }
        
//    public void paintComponent(Graphics g) {
//            int width = getWidth();
//            int height = getHeight();
//
//            Graphics2D g2 = (Graphics2D) g;
//            Paint oldPainter = g2.getPaint();
//
//            Color gradientStart = Color.WHITE;
//            Color gradientEnd = Color.LIGHT_GRAY;
//
//            // Paint painter = new GradientPaint(width - 200, 0, gradientStart, width, 0, gradientEnd);
//            Paint painter = new GradientPaint(0, 0, Color.CYAN, width, 0, Color.BLUE);
//            g2.setPaint(painter);
//            g2.fill(g2.getClip());
//
//            g2.setPaint(oldPainter);
//    }
    
    public void paintComponent(Graphics g) {
            // counter++;

            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHints(hints);

            int width = getWidth();
            int height = getHeight();
            
            Paint oldPainter = g2.getPaint();

            g2.setPaint(new GradientPaint(0, 0, gradientStart1, 0, height / 2, gradientEnd1));
            g2.fillRect(0, 0, width, height / 2);            
            g2.setPaint(new GradientPaint(0, height / 2, gradientEnd1, 0, height, gradientStart1));
            g2.fillRect(0, height / 2, width, height);            

            g2.translate(0, -30);
            drawCurve(g2,
                            20.0, -10.0, 20.0, -10.0,
                            (width >> 1) - 40.0, 10.0,
                            0.0, -5.0,
                            (width >> 1) + 40, 1.0,
                            0.0, 5.0,
                            50, 5, false);
            g2.translate(0, 30);

            g2.translate(0, height - 60);
            drawCurve(g2,
                            30.0, -15.0, 50.0, 15.0,
                            (width >> 1) - 40.0, 1.0,
                            15.0, -25.0,
                            (width >> 1), (1 >> 1),
                            0.0, 25.0,
                            15, 6, false);
            g2.translate(0, -height + 60);

            drawCurve(g2,
                            height - 35.0, -5.0, height - 50.0, 10.0,
                            (width >> 1) - 40.0, 1.0,
                            height - 35.0, -25.0,
                            (width >> 1), (1 >> 1),
                            height - 20.0, 25.0,
                            25, 4, true);
            
            g2.setPaint(oldPainter); 
    }

    private void drawCurve(Graphics2D g2, 
                    double y1, double y1_offset,
                    double y2, double y2_offset,
                    double cx1, double cx1_offset,
                    double cy1, double cy1_offset,
                    double cx2, double cx2_offset,
                    double cy2, double cy2_offset,
                    int thickness,
                    int speed,
                    boolean invert) {

            int width = getWidth();
            int height = getHeight();

            double offset = Math.sin(counter / (speed * ((double)Math.PI)));
            double start_x = 0.0f;
            double start_y = y1 + (double) (offset * y1_offset);
            double end_x = width;
            double end_y = y2 + (double) (offset * y2_offset);
            double ctrl1_x = (double) offset * cx1_offset + cx1;
            double ctrl1_y = cy1 + (double) (offset * cy1_offset);
            double ctrl2_x = (double) (offset * cx2_offset) + cx2;
            double ctrl2_y = (double) (offset * cy2_offset) + cy2;

            CubicCurve2D curve = new CubicCurve2D.Double(start_x, start_y, ctrl1_x, ctrl1_y, ctrl2_x, ctrl2_y, end_x, end_y);

            GeneralPath path = new GeneralPath(curve);
            path.lineTo(width, height);
            path.lineTo(0, height);
            path.closePath();

            Area thickCurve = new Area((Shape) path.clone());
            AffineTransform translation = AffineTransform.getTranslateInstance(0, thickness);
            path.transform(translation);
            thickCurve.subtract(new Area(path));

            Color start = new Color(255, 255, 255, 200);
            Color end = new Color(255, 255, 255, 0);

            Rectangle bounds = thickCurve.getBounds();
            GradientPaint painter = new GradientPaint(0, curve.getBounds().y,
                            invert ? end : start,
                            0, bounds.y + bounds.height,
                            invert ? start : end);
            
            g2.setPaint(painter);
            g2.fill(thickCurve);
    }
      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

    }
    // </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
