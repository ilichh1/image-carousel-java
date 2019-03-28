/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ilichh1
 */
public class Imagenes extends javax.swing.JFrame {
    private DataBaseImage currentImage = new DataBaseImage();
    private int imagenActualIndice = 0;
    private static final ArrayList<DataBaseImage> IMAGENES = new ArrayList<>();
    
    /**
     * Creates new form Imagenes
     */
    public Imagenes() {
        initComponents();
        getAllImages();
    }
    
    private void nextImage() {
        if (imagenActualIndice == IMAGENES.size() - 1) {
            imagenActualIndice = 0;
        } else imagenActualIndice++;
        updateImage();
    }
    
    private void prevImage() {
        if (imagenActualIndice == 0) {
            imagenActualIndice = IMAGENES.size() - 1;
        } else imagenActualIndice--;
        updateImage();
    }
    
    private void updateImage() {
        currentImage = IMAGENES.get(imagenActualIndice);
        convertCurrentImage();
    }
    
    private void updateImage(int index) {
        currentImage = IMAGENES.get(index);
        convertCurrentImage();
    }
    
    public void getAllImages() {
        String sqlSelect = "SELECT * FROM imagen;";
        
        ResultSet rs = database.BaseDeDatos.executeSqlAndGetResultSet(sqlSelect);
        if (rs == null) return;
        
        try {
            while (rs.next()) {
                int id = rs.getInt("idImagen");
                String nombre = rs.getString("nombre");
                String datos = rs.getString("datos");
                IMAGENES.add(new DataBaseImage(id, nombre, datos));
                // System.out.println(id+","+nombre+","+","+datos);
            }
            updateImage();
        } catch (SQLException ex) {
            System.out.println("NO SE PUEDO ITERAR EL ResultSet");
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    private void convertCurrentImage() {
        BufferedImage img = utilerias.ImageUtils.fromBase64(currentImage.getDatos());
        imagenLabel.setText(null);
        imagenLabel.setIcon(new ImageIcon(img));
        imagenNombreLabel.setText(currentImage.getNombre());
    }
    
    private void insertImagen() {
        String imgName = currentImage.getNombre();
        String imgData = currentImage.getDatos();
        String insertSQL = "INSERT INTO imagen (nombre, datos) VALUES ('"+ imgName +"','"+ imgData +"');";
        
        if (database.BaseDeDatos.executeSQL(insertSQL))
            System.out.println("Se guardo la imagen exitosamente.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agregarBTN = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        imagenLabel = new javax.swing.JLabel();
        imagenNombreLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        agregarBTN.setBackground(new java.awt.Color(0, 255, 255));
        agregarBTN.setText("Agregar");
        agregarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBTNActionPerformed(evt);
            }
        });

        prevButton.setText("ANTERIOR");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        nextButton.setText("SIGUIENTE");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        imagenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagenLabel.setText("IMAGEN");
        jScrollPane1.setViewportView(imagenLabel);

        imagenNombreLabel.setText("NOMBRE DE LA IMAGEN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(agregarBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagenNombreLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(imagenNombreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarBTN)
                    .addComponent(prevButton)
                    .addComponent(nextButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBTNActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        
        int option = fileChooser.showOpenDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            String nombre = fileChooser.getSelectedFile().getName();
            String datos = utilerias.ImageUtils.toBase64(fileChooser.getSelectedFile());
            
            DataBaseImage nuevaImagen = new DataBaseImage(-1, nombre, datos);
            IMAGENES.add(nuevaImagen);
            updateImage(IMAGENES.size()-1);
        }
    }//GEN-LAST:event_agregarBTNActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        nextImage();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        prevImage();
    }//GEN-LAST:event_prevButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBTN;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel imagenNombreLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    // End of variables declaration//GEN-END:variables
}
