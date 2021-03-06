package digital.gui;

//import digital.OCR.FEextract;
import digital.OCR.FEextract;
import digital.OCR.ImageUpload;
import digital.keyword_extraction.Interface;
import digital.segmentation.Enhancement;
import digital.segmentation.SpaceSegment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.opencv.core.Mat;

/**
 *
 * @author Sayanthini
 */
public class MainGUI extends javax.swing.JFrame {

    private final ImageUpload image = new ImageUpload();
    BufferedImage imge = null;
    private Mat EnhancedMatImg = null;
    Enhancement enhancement = new Enhancement();
    private ArrayList<Mat> imgSet;
    private ArrayList<Mat> verticalimgSet;
    int i = 0;
    FEextract f1 = new FEextract();

    public MainGUI() throws IOException {
        initComponents();
        this.pack();
        this.getArtical.setText("Get Articles");
        this.verticalSegmentScroll.setBorder(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        imgScrlPane = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lblimage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        getArtical = new javax.swing.JButton();
        verticalSegmentScroll = new javax.swing.JScrollPane();
        verticalPane = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        verticalPrevious = new javax.swing.JButton();
        verticalNext = new javax.swing.JButton();
        medianfilterbutton = new javax.swing.JButton();
        binarybutton = new javax.swing.JButton();
        histogrambutton = new javax.swing.JButton();
        grayscalebutton = new javax.swing.JButton();
        detectlinebutton = new javax.swing.JButton();
        vhistogrambutton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Digital Archive");
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1000, 475));

        imgScrlPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Horizontal Segments"));
        jScrollPane1.setViewportView(imgScrlPane);

        lblimage.setBorder(javax.swing.BorderFactory.createTitledBorder("Original"));
        jScrollPane3.setViewportView(lblimage);

        btnPrevious.setText("<<");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        getArtical.setText("Get Artical");
        getArtical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getArticalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btnPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(getArtical)
                .addGap(63, 63, 63)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext)
                    .addComponent(getArtical)))
        );

        verticalSegmentScroll.setBorder(javax.swing.BorderFactory.createTitledBorder("Artical"));
        verticalSegmentScroll.setViewportView(verticalPane);

        verticalPrevious.setText("<<");
        verticalPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalPreviousActionPerformed(evt);
            }
        });

        verticalNext.setText(">>");
        verticalNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(verticalPrevious)
                .addGap(38, 38, 38)
                .addComponent(verticalNext, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verticalPrevious)
                    .addComponent(verticalNext)))
        );

        medianfilterbutton.setText("median filter");
        medianfilterbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medianfilterbuttonActionPerformed(evt);
            }
        });

        binarybutton.setLabel("binary");
        binarybutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarybuttonActionPerformed(evt);
            }
        });

        histogrambutton.setText("horizontal histo");
        histogrambutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                histogrambuttonActionPerformed(evt);
            }
        });

        grayscalebutton.setText("gray scale");
        grayscalebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grayscalebuttonActionPerformed(evt);
            }
        });

        detectlinebutton.setText("detect line");
        detectlinebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detectlinebuttonActionPerformed(evt);
            }
        });

        vhistogrambutton1.setText("verticle histo");
        vhistogrambutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vhistogrambutton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(medianfilterbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(grayscalebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(95, 95, 95)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(binarybutton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(detectlinebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(histogrambutton)
                                    .addComponent(vhistogrambutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(766, 766, 766)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verticalSegmentScroll)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(79, 79, 79)))))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(histogrambutton)
                                    .addComponent(binarybutton)
                                    .addComponent(grayscalebutton))
                                .addGap(27, 27, 27)))
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medianfilterbutton)
                            .addComponent(detectlinebutton)
                            .addComponent(vhistogrambutton1)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(174, 174, 174)
                .addComponent(verticalSegmentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(verticalSegmentScroll, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(medianfilterbutton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(binarybutton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(histogrambutton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(grayscalebutton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(detectlinebutton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(vhistogrambutton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu3.setText("Upload");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Segment");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setText("Features");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        jMenu7.setText("Summarized");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu5.setText("Search");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1103, 616));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorResized


    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        File img = image.uploadImage(MainGUI.this);
        System.out.println("img " + img);

        try {
            imge = ImageIO.read(img);
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       // BufferedImage bImg = (BufferedImage) imge.getScaledInstance(lblimage.WIDTH, lblimage.HEIGHT, Image.SCALE_SMOOTH);

        lblimage.setIcon(new ImageIcon(imge));
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked

        System.out.println("Segment button clicked");
        EnhancedMatImg = enhancement.enhancedImage(imge);
        System.out.println("Image enhancement");
        System.out.println("output" + EnhancedMatImg);
        SpaceSegment histo = new SpaceSegment();
        imgSet = histo.horizontalSpaceSeg(EnhancedMatImg, imge);

        JOptionPane.showMessageDialog(null, "Segmentation Completed!");
       // imgScrlPane.setIcon(new ImageIcon(f1.matToBuffered(imgSet.get(0))));

    }//GEN-LAST:event_jMenu4MouseClicked

    private void grayscalebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grayscalebuttonActionPerformed

       // ImageIcon f2 = new ImageIcon("gray.jpg");
        imgScrlPane.setIcon(new ImageIcon("gray.jpg"));

    }//GEN-LAST:event_grayscalebuttonActionPerformed

    private void medianfilterbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medianfilterbuttonActionPerformed
        // TODO add your handling code here:
        imgScrlPane.setIcon(new ImageIcon("MedianFiltered.jpg"));
    }//GEN-LAST:event_medianfilterbuttonActionPerformed

    private void verticalNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verticalNextActionPerformed

    private void verticalPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalPreviousActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verticalPreviousActionPerformed


    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void binarybuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarybuttonActionPerformed
        // TODO add your handling code here:
        imgScrlPane.setIcon(new ImageIcon("Binary.jpg"));
    }//GEN-LAST:event_binarybuttonActionPerformed

    private void histogrambuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_histogrambuttonActionPerformed
        // TODO add your handling code here:
        imgScrlPane.setIcon(new ImageIcon("horizontalHistogram.jpg"));
    }//GEN-LAST:event_histogrambuttonActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        HeadLine_Extraction obj = new HeadLine_Extraction();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        // TODO add your handling code here:
   Interface sumaryInteface;
        try {
            sumaryInteface = new Interface();
            sumaryInteface.show();
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenu7MouseClicked

    private void detectlinebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detectlinebuttonActionPerformed
        // TODO add your handling code here:
        imgScrlPane.setIcon(new ImageIcon("detectLine.jpg"));
    }//GEN-LAST:event_detectlinebuttonActionPerformed

    private void vhistogrambutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vhistogrambutton1ActionPerformed
        // TODO add your handling code here:
        imgScrlPane.setIcon(new ImageIcon("verticleHistogram.jpg"));
    }//GEN-LAST:event_vhistogrambutton1ActionPerformed

    private void getArticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getArticalActionPerformed
        //        int j = 0;
        //        ImageIcon icon=(ImageIcon)imgScrlPane.getIcon();
        //        Mat img=f1.BufferedTOmat((BufferedImage)((Image)icon.getImage()));
        //        SpaceSegment histo1 = new SpaceSegment();
        //        verticalimgSet=histo1.verticalSpaceSeg(img);
        //
        //        verticalPane.setIcon(new ImageIcon(f1.matToBuffered(verticalimgSet.get(0))));
    }//GEN-LAST:event_getArticalActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        // TODO add your handling code here:
        NewJFrame2 searchWindow = new NewJFrame2();
        searchWindow.setVisible(true);
    }//GEN-LAST:event_jMenu5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton binarybutton;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton detectlinebutton;
    private javax.swing.JButton getArtical;
    private javax.swing.JButton grayscalebutton;
    private javax.swing.JButton histogrambutton;
    private javax.swing.JLabel imgScrlPane;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblimage;
    private javax.swing.JButton medianfilterbutton;
    private javax.swing.JButton verticalNext;
    private javax.swing.JLabel verticalPane;
    private javax.swing.JButton verticalPrevious;
    private javax.swing.JScrollPane verticalSegmentScroll;
    private javax.swing.JButton vhistogrambutton1;
    // End of variables declaration//GEN-END:variables
}
