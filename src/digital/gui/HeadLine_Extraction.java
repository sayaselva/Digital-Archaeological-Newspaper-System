package digital.gui;

import digital.OCR.FEextract;
import digital.OCR.HeadLine;
import digital.OCR.Histogram;
import digital.OCR.ImageRemover;
import digital.OCR.ImageUpload;
import digital.OCR.ODT;
import digital.OCR.ParagraphAnalaze;
import digital.OCR.ROI;
import digital.OCR.RegionAnalysis;
import digital.OCR.RunLength;
import digital.OCR.testttt;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.MORPH_RECT;
import static org.opencv.imgproc.Imgproc.erode;
import static org.opencv.imgproc.Imgproc.getStructuringElement;

/**
 *
 * @author Sayanthini
 */
public class HeadLine_Extraction extends javax.swing.JFrame {

    FEextract feature;
    File folder;
    Histogram his;
    String newSelectedItem;
    ImageUpload ocr;
    ArrayList<Mat> re = new ArrayList<>();
    int next = 0;
    ROI test = new ROI();

    public HeadLine_Extraction() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_select_folder = new javax.swing.JButton();
        pnl_availableFile = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        imgPane = new javax.swing.JLabel();

        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setAutoscrolls(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Select"));

        btn_select_folder.setText("Select Folder/ File");
        btn_select_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_select_folderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_select_folder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_select_folder)
        );

        pnl_availableFile.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Availabe Files", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        pnl_availableFile.setViewportView(jList1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Head Line"));

        jLabel4.setMaximumSize(new java.awt.Dimension(174, 487));
        jScrollPane2.setViewportView(jLabel4);

        jScrollPane3.setViewportView(jScrollPane2);

        jButton4.setText("Extract");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Cancel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_availableFile, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_availableFile, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(146, 146, 146))
        );

        imgPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Article", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jScrollPane1.setViewportView(imgPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_select_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_select_folderActionPerformed
        feature = new FEextract();

        folder = feature.SelectFolder(this);
        File[] listOfFiles = folder.listFiles();
        DefaultListModel listModel = new DefaultListModel();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {

                String extension = "";

                int i = listOfFile.getName().lastIndexOf('.');
                int p = Math.max(listOfFile.getName().lastIndexOf('/'), listOfFile.getName().lastIndexOf('\\'));

                if (i > p) {
                    extension = listOfFile.getName().substring(i + 1);
                }
                if (extension.equalsIgnoreCase("jpg")) {
                    listModel.addElement(listOfFile.getName());
                }
                //System.out.println("File " + listOfFile.getName());
            } else if (listOfFile.isDirectory()) {
                System.out.println("Directory " + listOfFile.getName());
            }
        }
        this.jList1.setModel(listModel);

    }//GEN-LAST:event_btn_select_folderActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged

        String listValue = this.jList1.getSelectedValue().toString();
        String folderPath = folder.getPath();
        System.out.println("Selected folder path::" + folderPath + "\n selcted Item:: " + listValue);

        newSelectedItem = folderPath + "\\" + listValue;
        File newSelection = new File(newSelectedItem);
       
        his = new Histogram();

        try {
            BufferedImage newImg = ImageIO.read(newSelection);

            Image displayimg = newImg.getScaledInstance(this.imgPane.getWidth(), this.imgPane.getHeight(), BufferedImage.SCALE_SMOOTH);
            this.imgPane.setIcon(new ImageIcon(displayimg));

        } catch (IOException ex) {
            Logger.getLogger(HeadLine_Extraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        next = 0;
        RunLength rl = new RunLength();
        ODT convertBinary = new ODT();
        Mat bin = convertBinary.getDynamicThreshold(newSelectedItem);
        this.imgPane.setIcon(new ImageIcon(feature.matToBuffered(bin)));
        Mat temp = bin.clone();

        Mat headlineh = rl.HSmooth(bin);
        Mat headlinev = rl.VSmooth(bin);            //Smooth the input image vertically

        Mat head = rl.logicalAnd(headlinev, headlineh);
        Highgui.imwrite("Runlen.jpg", head);
        Highgui.imwrite("binry.jpg", bin);
        int[] retn = test.HorizontalProjection(head);

       // Image displayRLS = feature.matToBuffered(head).getScaledInstance(this.lblRLS.getWidth(), this.lblRLS.getHeight(), BufferedImage.SCALE_SMOOTH);

        BufferedImage headlineLl;
        HeadLine hl = new HeadLine();
        re = test.getRegion(temp, retn);
        headlineLl = feature.matToBuffered(re.get(0));

        // get head line and convert it into Buffered Image
        Image dimg = headlineLl.getScaledInstance(this.jLabel4.getWidth(), this.jLabel4.getHeight(), BufferedImage.SCALE_SMOOTH);   // Fit headline image into label size to display
        this.jLabel4.setIcon(new ImageIcon(dimg));

       // this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(test.getRegion(temp, retn).get(0))));

        ocr = new ImageUpload();

        digital.segmentation.Histogram histoObj = new digital.segmentation.Histogram();
        histoObj.HorizontalHisto(head);
        
        JOptionPane.showMessageDialog(null, "Extraction Completed!");
    }//GEN-LAST:event_jButton4ActionPerformed

    public void setImage(BufferedImage article) {
        ImageIcon ii = new ImageIcon(article);
        this.imgPane.setIcon(ii);
        setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_select_folder;
    private javax.swing.JLabel imgPane;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane pnl_availableFile;
    // End of variables declaration//GEN-END:variables
}
