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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txthead = new java.awt.TextField();
        setHeadline = new javax.swing.JButton();
        setSubheadLine = new javax.swing.JButton();
        setTextinBody = new javax.swing.JButton();
        txtbody = new java.awt.TextArea();
        txtsub = new java.awt.TextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        imgPane = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        scrollPane1 = new java.awt.ScrollPane();
        lblRLS = new javax.swing.JLabel();
        btnReImage = new javax.swing.JButton();

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Result", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel1.setFont(new java.awt.Font("Bamini", 1, 12)); // NOI18N
        jLabel1.setText("gpujhd jiyg;G");

        jLabel2.setFont(new java.awt.Font("Bamini", 1, 12)); // NOI18N
        jLabel2.setText("cg jiyg;G");

        jLabel3.setFont(new java.awt.Font("Bamini", 1, 12)); // NOI18N
        jLabel3.setText("nra;jp");

        jButton2.setText("Cancel");
        jSplitPane1.setRightComponent(jButton2);

        jButton1.setText("Save");
        jSplitPane1.setLeftComponent(jButton1);

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

        txthead.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        setHeadline.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        setHeadline.setText("Set");
        setHeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setHeadlineActionPerformed(evt);
            }
        });

        setSubheadLine.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        setSubheadLine.setText("Set");
        setSubheadLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSubheadLineActionPerformed(evt);
            }
        });

        setTextinBody.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        setTextinBody.setText("Set");
        setTextinBody.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTextinBodyActionPerformed(evt);
            }
        });

        txtbody.setFont(new java.awt.Font("Bamini", 0, 12)); // NOI18N

        txtsub.setFont(new java.awt.Font("Bamini", 0, 12)); // NOI18N

        jButton7.setText("Cancel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txthead, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(setHeadline))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtbody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(setSubheadLine, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(setTextinBody, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButton7)))
                        .addGap(262, 262, 262))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(setHeadline)
                            .addComponent(txthead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(setSubheadLine)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(setTextinBody)
                        .addGap(27, 27, 27)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_availableFile)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_availableFile, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        imgPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Article", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jScrollPane1.setViewportView(imgPane);

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText(">>");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        scrollPane1.add(lblRLS);

        btnReImage.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        btnReImage.setText("Remove Image");
        btnReImage.setActionCommand("Remove Graphics");
        btnReImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jButton3)
                .addGap(95, 95, 95)
                .addComponent(btnReImage)
                .addGap(84, 84, 84)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReImage)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
        this.txtbody.setText("");
        this.txthead.setText("");
        this.txtsub.setText("");

        his = new Histogram();

        try {
            BufferedImage newImg = ImageIO.read(newSelection);

            Image displayimg = newImg.getScaledInstance(this.imgPane.getWidth(), this.imgPane.getHeight(), BufferedImage.SCALE_SMOOTH);
            this.imgPane.setIcon(new ImageIcon(displayimg));

        } catch (IOException ex) {
            Logger.getLogger(HeadLine_Extraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jList1ValueChanged

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

        Image displayRLS = feature.matToBuffered(head).getScaledInstance(this.lblRLS.getWidth(), this.lblRLS.getHeight(), BufferedImage.SCALE_SMOOTH);

        BufferedImage headlineLl;
        HeadLine hl = new HeadLine();
        re = test.getRegion(temp, retn);
        headlineLl = feature.matToBuffered(re.get(0));

        // get head line and convert it into Buffered Image
        Image dimg = headlineLl.getScaledInstance(this.jLabel4.getWidth(), this.jLabel4.getHeight(), BufferedImage.SCALE_SMOOTH);   // Fit headline image into label size to display
        this.jLabel4.setIcon(new ImageIcon(dimg));

        this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(test.getRegion(temp, retn).get(0))));

        ocr = new ImageUpload();

        digital.segmentation.Histogram histoObj = new digital.segmentation.Histogram();
        histoObj.HorizontalHisto(head);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (next > 0) {
            --next;
            ImageIcon imgThisImg = new ImageIcon(feature.matToBuffered(re.get(next)));

            this.lblRLS.setIcon(imgThisImg);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (next < re.size() - 1) {
            ++next;
            ImageIcon imgThisImg = new ImageIcon(feature.matToBuffered(re.get(next)));

            this.lblRLS.setIcon(imgThisImg);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void setHeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setHeadlineActionPerformed

        String result = ocr.OCRImage(feature.matToBuffered(re.get(next)));
        this.txthead.setText(result);
        System.out.println("Text ...." + result);
        re.remove(next);
        System.out.println("re size : " + re.size());
        this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(re.get(next))));

    }//GEN-LAST:event_setHeadlineActionPerformed

    private void btnReImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImageActionPerformed

        ImageRemover IR = new ImageRemover();
        re.set(next, IR.Remove(re.get(next)));
        this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(re.get(next))));
    }//GEN-LAST:event_btnReImageActionPerformed

    private void setTextinBodyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTextinBodyActionPerformed

        RegionAnalysis obj1 = new RegionAnalysis();

        ParagraphAnalaze p = new ParagraphAnalaze();
        ArrayList<Mat> para = p.VerticleProjection(obj1.getTextinBody(re));
        re = para;
        String result = "";
        for (Mat bodyPara : re) {
            result = result + ocr.OCRImage(feature.matToBuffered(bodyPara));

        }
        this.txtbody.setText(result);
        this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(re.get(0))));
    }//GEN-LAST:event_setTextinBodyActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        this.txtbody.setText("");
        this.txthead.setText("");
        this.txtsub.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void setSubheadLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setSubheadLineActionPerformed

        String result = ocr.OCRImage(feature.matToBuffered(re.get(next)));
        this.txtsub.setText(result);
        re.remove(next);
        System.out.println("re size : " + re.size());
        this.lblRLS.setIcon(new ImageIcon(feature.matToBuffered(re.get(next))));

    }//GEN-LAST:event_setSubheadLineActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    public void setImage(BufferedImage article) {
        ImageIcon ii = new ImageIcon(article);
        this.imgPane.setIcon(ii);
        setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReImage;
    private javax.swing.JButton btn_select_folder;
    private javax.swing.JLabel imgPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblRLS;
    private javax.swing.JScrollPane pnl_availableFile;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JButton setHeadline;
    private javax.swing.JButton setSubheadLine;
    private javax.swing.JButton setTextinBody;
    private java.awt.TextArea txtbody;
    private java.awt.TextField txthead;
    private java.awt.TextField txtsub;
    // End of variables declaration//GEN-END:variables
}
