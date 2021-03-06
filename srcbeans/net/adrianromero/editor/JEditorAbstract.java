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

package net.adrianromero.editor;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import net.adrianromero.basic.BasicException;


public abstract class JEditorAbstract extends javax.swing.JPanel implements EditorComponent {

    private EditorKeys editorkeys;
    
    private boolean m_bActive;
    private final Border m_borderactive =  new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("TextField.selectionBackground")), new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 4, 1, 4)));
    private final Border m_borderinactive =  new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), new javax.swing.border.EmptyBorder(new java.awt.Insets(1, 4, 1, 4)));
    
    /** Creates new form JPanelNumber */
    public JEditorAbstract() {
        
        initComponents();
        
        editorkeys = null;
        m_bActive = false;
        m_jText.setBorder(m_borderinactive);
    }

    protected abstract int getMode();    
    protected abstract int getAlignment();  
    protected abstract String getEditMode();
    protected abstract String getTextEdit();
    protected abstract String getTextFormat() throws BasicException;
    protected abstract void typeCharInternal(char c);    
    protected abstract void transCharInternal(char c);
    
    public void typeChar(char c) {
        typeCharInternal(c);
        reprintText();
        firePropertyChange("Edition", null, null);
    }
    
    public void transChar(char c) {
        transCharInternal(c);
        reprintText();
        firePropertyChange("Edition", null, null);
    }
    
    public void addEditorKeys(EditorKeys ed) {
        editorkeys = ed;
    }
    public void deactivate() {
        setActive(false);
    }
    public Component getComponent() {
        return this;
    }
    public void activate() {
        if (isEnabled()) {
            editorkeys.setActive(this, getMode());        
            setActive(true);
        }
    }
    
    private void setActive(boolean bValue) {
        m_bActive = bValue;
        m_jText.setBorder(m_bActive ? m_borderactive : m_borderinactive);
        reprintText();
    }
            
    protected void reprintText() {
        
        m_jText.setHorizontalAlignment(getAlignment());
        if (m_bActive) {
            m_jMode.setText(getEditMode());
            m_jText.setText(getTextEdit());
            m_jText.setForeground(javax.swing.UIManager.getDefaults().getColor("Label.foreground"));
        } else {
            m_jMode.setText(null);
            try {
                m_jText.setText(getTextFormat());
                m_jText.setForeground(javax.swing.UIManager.getDefaults().getColor("Label.foreground"));
            } catch (BasicException e) {
                m_jText.setText(getTextEdit());
                m_jText.setForeground(Color.RED);
            }
        }
    }
    
    public void setEnabled(boolean b) {
        
        if (editorkeys != null) {
            editorkeys.setInactive(this);
        }
        m_jText.setBackground(b 
            ? javax.swing.UIManager.getDefaults().getColor("TextField.background")
            : javax.swing.UIManager.getDefaults().getColor("TextField.disabledBackground"));        
        super.setEnabled(b);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        m_jText = new javax.swing.JLabel();
        m_jMode = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        m_jText.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
        m_jText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        m_jText.setMinimumSize(new java.awt.Dimension(100, 25));
        m_jText.setOpaque(true);
        m_jText.setPreferredSize(new java.awt.Dimension(100, 25));
        m_jText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                m_jTextMouseClicked(evt);
            }
        });

        add(m_jText, java.awt.BorderLayout.CENTER);

        m_jMode.setFont(new java.awt.Font("Dialog", 0, 9));
        m_jMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_jMode.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        m_jMode.setPreferredSize(new java.awt.Dimension(32, 0));
        add(m_jMode, java.awt.BorderLayout.EAST);

    }// </editor-fold>//GEN-END:initComponents

    private void m_jTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_m_jTextMouseClicked

        activate();
        
    }//GEN-LAST:event_m_jTextMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel m_jMode;
    private javax.swing.JLabel m_jText;
    // End of variables declaration//GEN-END:variables
    
    

    
}
