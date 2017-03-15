package digital.segmentation;

import digital.segmentation.Imshow;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Sayanthini
 */
public class Segment {

    public void Segmentation(Mat img1) {

        Mat seg = img1;

        //Find the maximum height and width of input image  
        int row = seg.rows();
        int col = seg.cols();

        // declare an array horizontal_line[]...element size=height of the input image 
        int horizontal_line[] = new int[row];

        // initilize horizontal_line[] array element by zero
        for (int i = 0; i < row; i++) {
            horizontal_line[i] = 0;
        }
        // declare an array verticle_line[]...element size=width of the input image 
        int verticle_line[] = new int[col];

        // initilize verticle_line[] array element by zero
        for (int i = 0; i < col; i++) {
            verticle_line[i] = 0;
        }

        // define a threshold value, in order to identify whether a horizontal white line exists
        int threshold_row = 3 * col / 4;
        // define a threshold value, in order to identify whether a verticle white line exists
        int threshold_col = row / 4;

     // identify whether a horizontal white line is exits....
        //count width of white line
        //If (counting width of white line > original_width of image/2)...set as horizontal white line else not.
        for (int r = 0; r < row; r++) {
            int count = 0;
            for (int c = 0; c < col; c++) {
                double current = seg.get(r, c)[0];

                if (current == 255.0) {
                    count++;

                } else if (count > threshold_row) {
                    horizontal_line[r] = 1;
                    count = 0;
                }
            }
        }

        for (int i = 0; i < 50; i++) {
            System.out.println(i + " " + horizontal_line[i]);
        }

    // identify whether a verticle white line is exits....
        //count height of white line
        //If (counting height of white line > original_height of image/4)...set as verticle white line else not.
        for (int c = 0; c < col; c++) {
            int count1 = 0;
            for (int r = 0; r < row; r++) {
                double current1 = seg.get(r, c)[0];

                if (current1 == 255.0) {
                    count1++;
                } else if (count1 > threshold_col) {
                    verticle_line[c] = 1;
                    count1 = 0;
                }
            }
        }

        System.out.println("coloum \n");
        for (int i = 0; i < 50; i++) {
            System.out.println(i + " " + verticle_line[i]);
        }

        //define top left corner point(index1,index2)
        int index1 = 0;
        int index2 = 0;
        //define bottom right corner point (index3,index4)
        int index3 = 0;
        int index4 = 0;

        // identify index1
        boolean flag = true;
        for (int i = 0; i < row && flag; i++) {

            if (horizontal_line[i] == 0) {
                index1 = i;
                flag = false;
            }
        }

        // identify index2
        flag = true;
        for (int i = 0; i < col && flag; i++) {

            if (verticle_line[i] == 0) {
                index2 = i;
                flag = false;
            }
        }

        // identify index3
        flag = false;//initiaze as false for following
        int VHthreshold = 10;
        double VcurrentPixel = 0;
        int vHeight = 0;
        int co = 0;

        for (int i = index1; i < row; i++) {
            //VcurrentPixel=seg.get(r, c)[0];
            VcurrentPixel = horizontal_line[i];
            if (VcurrentPixel == 0) {
                if (flag == true) {
                    if (vHeight > VHthreshold) {
                        index3 = i;
                        co++;

                        //index4=c;
                        if (co == 1) {
                            break;
                        }
                    }
                } else {
                    flag = true;
                }
            } else {
                if (flag == true) {
                    vHeight = vHeight + 1;
                }
            }
        }

        // identify index4
        flag = false;//initiaze as false for following
        int HHthreshold = 30;
        double HcurrentPixel = 0;
        int HHeight = 0;
        int count = 0;
       //for(int r=index1;r<row;r++){
        //for(int c=index2;c<col;c++){
        for (int i = index2; i < col; i++) {
            HcurrentPixel = verticle_line[i];
            if (HcurrentPixel == 0) {
                if (flag == true) {
                    if (HHeight > HHthreshold) {
                        //index3=r;
                        index4 = i;
                        count++;
                        if (count == 2) {
                            break;
                        }
                    }
                } else {
                    flag = true;
                }
            } else {
                if (flag == true) {
                    HHeight = HHeight + 1;
                }
            }

        }

        System.out.println(" Index1:" + index1 + " " + "index2:" + index2 + " " + "index3:" + index3 + " " + "index4:" + index4);

        // declare a mat structure to copy the 1st segment from input image
        Mat article = new Mat(index3 - index1, index4 - index2, CvType.CV_8UC1);

        for (int r = index1; r < index3; r++) {
            for (int c = index2; c < index4; c++) {
                double original_value = seg.get(r, c)[0];
                article.put(r - index1, c - index2, original_value);
            }
        }

        Highgui.imwrite("segement1.jpg", article);

        Mat resiz_article = new Mat();
        Imgproc.resize(article, resiz_article, new Size(600, 200));

        Imshow im = new Imshow("Segment1");
        im.showImage(resiz_article);

        // declare a mat structure to copy the 1st segment from input image
        Mat article1 = new Mat(row - index3, index4 - index2, CvType.CV_8UC1);

        for (int r = index3; r < row; r++) {
            for (int c = index2; c < index4; c++) {
                double original_value1 = seg.get(r, c)[0];
                article1.put(r - index3, c - index2, original_value1);
            }
        }

        Highgui.imwrite("segement2.jpg", article1);

        Mat resiz_article1 = new Mat();
        Imgproc.resize(article1, resiz_article1, new Size(600, 200));

        Imshow im1 = new Imshow("Segment2");
        im1.showImage(resiz_article1);

    }

}
