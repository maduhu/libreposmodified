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

package net.adrianromero.tpv.inventory;

import java.awt.Component;
import javax.swing.*;

import net.adrianromero.tpv.forms.AppLocal;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;
import net.adrianromero.format.Formats;
import net.adrianromero.basic.BasicException;
import net.adrianromero.data.gui.ComboBoxValModel;
import net.adrianromero.data.gui.JMessageDialog;
import net.adrianromero.data.gui.MessageInf;
import net.adrianromero.data.loader.SentenceExec;
import net.adrianromero.data.loader.SentenceList;
import net.adrianromero.data.user.EditorRecord;
import net.adrianromero.data.user.DirtyManager;
import net.adrianromero.tpv.forms.AppView;
import net.adrianromero.tpv.forms.SentenceContainer;

/**
 *
 * @author  adrian
 */
public class CategoriesEditor extends JPanel implements EditorRecord {
       
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    
    private SentenceExec m_sentadd;
    private SentenceExec m_sentdel;
    
    private Object m_oId;
    
    /** Creates new form JPanelCategories */
    public CategoriesEditor(AppView app, DirtyManager dirty) {
        initComponents();
             
        // El modelo de categorias
        m_sentcat = app.lookupDataLogic(SentenceContainer.class).getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();
        
        m_sentadd = app.lookupDataLogic(SentenceContainer.class).getCatalogCategoryAdd();
        m_sentdel = app.lookupDataLogic(SentenceContainer.class).getCatalogCategoryDel();
        
        m_jName.getDocument().addDocumentListener(dirty);
        m_jCategory.addActionListener(dirty);
        m_jImage.addPropertyChangeListener("image", dirty);
        
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
        
        List a = m_sentcat.list();
        a.add(0, null); // The null item
        m_CategoryModel = new ComboBoxValModel(a);
        m_jCategory.setModel(m_CategoryModel);
    }
    
    public void writeValueEOF() {
        m_oId = null;
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        m_jImage.setImage(null);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
    }
    public void writeValueInsert() {
        m_oId = null;
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        m_jImage.setImage(null);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
    }
    public void writeValueDelete(Object value) {
        Object[] cat = (Object[]) value;
        m_oId = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jImage.setEnabled(false);
        m_jCatalogDelete.setEnabled(false);
        m_jCatalogAdd.setEnabled(false);
    }    
    public void writeValueEdit(Object value) {
        Object[] cat = (Object[]) value;
        m_oId = cat[0];
        m_jName.setText(Formats.STRING.formatValue(cat[1]));
        m_CategoryModel.setSelectedKey(cat[2]);
        m_jImage.setImage((BufferedImage) cat[3]);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jImage.setEnabled(true);
        m_jCatalogDelete.setEnabled(true);
        m_jCatalogAdd.setEnabled(true);
    }

    public Object createValue() throws BasicException {
        
        Object[] cat = new Object[4];

        cat[0] = m_oId == null ? UUID.randomUUID().toString() : m_oId;
        cat[1] = m_jName.getText();
        cat[2] = m_CategoryModel.getSelectedKey();
        cat[3] = m_jImage.getImage();
        return cat;
    }    
    
    public Component getComponent() {
        return this;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        m_jImage = new net.adrianromero.data.gui.JImageEditor();
        m_jCatalogAdd = new javax.swing.JButton();
        m_jCatalogDelete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();

        setLayout(null);

        jLabel2.setText(AppLocal.getIntString("Label.Name"));
        add(jLabel2);
        jLabel2.setBounds(20, 20, 80, 15);

        add(m_jName);
        m_jName.setBounds(100, 20, 180, 19);

        jLabel3.setText(AppLocal.getIntString("label.image"));
        add(jLabel3);
        jLabel3.setBounds(20, 80, 80, 15);

        m_jImage.setMaxDimensions(new java.awt.Dimension(32, 32));
        add(m_jImage);
        m_jImage.setBounds(100, 80, 240, 180);

        m_jCatalogAdd.setText(AppLocal.getIntString("button.catalogadd"));
        m_jCatalogAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogAddActionPerformed(evt);
            }
        });

        add(m_jCatalogAdd);
        m_jCatalogAdd.setBounds(370, 20, 170, 25);

        m_jCatalogDelete.setText(AppLocal.getIntString("button.catalogdel"));
        m_jCatalogDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jCatalogDeleteActionPerformed(evt);
            }
        });

        add(m_jCatalogDelete);
        m_jCatalogDelete.setBounds(370, 50, 170, 25);

        jLabel5.setText(AppLocal.getIntString("label.prodcategory"));
        add(jLabel5);
        jLabel5.setBounds(20, 50, 90, 15);

        add(m_jCategory);
        m_jCategory.setBounds(100, 50, 180, 20);

    }// </editor-fold>//GEN-END:initComponents

    private void m_jCatalogDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogDeleteActionPerformed

        try {
            m_sentdel.exec(m_oId);
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }
        
    }//GEN-LAST:event_m_jCatalogDeleteActionPerformed

    private void m_jCatalogAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jCatalogAddActionPerformed

        try {
            Object param = m_oId;
            m_sentdel.exec(param); // primero borramos
            m_sentadd.exec(param); // y luego insertamos lo que queda
        } catch (BasicException e) {
            JMessageDialog.showMessage(this, new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotexecute"), e));
        }

    }//GEN-LAST:event_m_jCatalogAddActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton m_jCatalogAdd;
    private javax.swing.JButton m_jCatalogDelete;
    private javax.swing.JComboBox m_jCategory;
    private net.adrianromero.data.gui.JImageEditor m_jImage;
    private javax.swing.JTextField m_jName;
    // End of variables declaration//GEN-END:variables
    
}
