package digital.OCR;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint3f;
import org.opencv.core.Point;
import org.opencv.core.Point3;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.HoughLines;
import static org.opencv.imgproc.Imgproc.HoughLinesP;

/**
 *
 * @author Sayanthini
 */
public class RunLength {

    boolean imgFlag = true;
    double rowStd[];
    double mean_array[];
    FEextract feature = new FEextract();
    double std = 15;
    double vsv, hsv, mcl, mtld;

    public Mat HSmooth(Mat original) {

        int row = original.rows();
        int col = original.cols();
        Mat HSmoothImg = original;//new Mat(row, col, CvType.CV_8UC1);
        this.setMcl(HSmoothImg);
        hsv = 0.5 * mcl;
        System.out.println("mcl..:" + mcl);
        mtld = 0.8 * mcl;
        System.out.println("mtld..:" + mtld);
        int zero_count = 0;
        int one_flag = 0;
        int white_count = 0;
        for (int r = 0; r < row; r++) {
            zero_count = 0;
            white_count = 0;

            for (int c = 0; c < col; c++) {
                if (HSmoothImg.get(r, c)[0] == 0.0) {
                    if (one_flag == 1) {
                        if (zero_count < hsv) { // change white_count/col 
                            for (int j = c - zero_count; j < c; j++) {
                                HSmoothImg.put(r, j, 0);
                            }
                        } else {
                            one_flag = 0;
                        }
                        zero_count = 0;
                    }
                    one_flag = 1;
                } else {
                    if (one_flag == 1) {
                        zero_count = zero_count + 1;
                    }
                }

            }

        }
        vsv = this.averageHeightV(HSmoothImg);

        return HSmoothImg;//feature.matToBuffered(HSmoothImg);
    }

    public Mat VSmooth(Mat original) {

        int row = original.rows();
        int col = original.cols();
        Mat VSmoothImg = original;

        std = 3;
        int zero_count = 0;
        int one_flag = 0;
        int white_count = 0;
        for (int c = 0; c < col; c++) {
            zero_count = 0;
            white_count = 0;

            for (int r = 0; r < row; r++) {
                if (VSmoothImg.get(r, c)[0] == 0.0) {
                    if (one_flag == 1) {
                        if (zero_count < (0.4 * mcl)) { //(int j=c-zero_count;j<c;j++
                            for (int j = r - zero_count; j < r; j++) {
                                VSmoothImg.put(j, c, 0);
                            }
                        } else {
                            one_flag = 0;
                        }
                        zero_count = 0;
                    }
                    one_flag = 1;
                } else {
                    if (one_flag == 1) {
                        zero_count = zero_count + 1;
                    }
                }
            }
        }

        return VSmoothImg;//feature.matToBuffered(VSmoothImg);
    }

    public Mat logicalAnd(Mat vimg, Mat himg) {
        Mat reult = new Mat(vimg.rows(), vimg.cols(), CvType.CV_8UC1, Scalar.all(0));

        for (int r = 0; r < reult.rows(); r++) {
            for (int c = 0; c < reult.cols(); c++) {
                double v = vimg.get(r, c)[0];//==255;
                double h = himg.get(r, c)[0];//==255;

                if (v == 255 && h == 255) {
                    reult.put(r, c, 255);
                } else {
                    reult.put(r, c, 0);
                }
            }
        }
        return reult;//feature.matToBuffered(reult);
    }

    public void setMcl(Mat img) {
        int gmhbr1 = 0, gmhbr2 = 0;
        int[] array = new int[img.rows()];
        for (int i = 0; i < img.rows(); i++) {
            array[i] = Core.countNonZero(img.row(i));

        }
        Arrays.sort(array);
        gmhbr1 = array[array.length - 1];
        for (int i = array.length - 2; i > 0; i--) {
            if (gmhbr1 != array[i]) {
                gmhbr2 = array[i];
            }
        }
        //  gmhbr=gmhbr/img.cols();
        System.out.println("maximum gbhr " + gmhbr1 + " : " + gmhbr2);
        mcl = (3.5 * (gmhbr1 - gmhbr2) + 4.0 * (gmhbr1 - gmhbr2)) / gmhbr1;
        // return gmhbr;
    }

    public int averageHeightV(Mat inputMat) {
        int RLSArray[] = new int[inputMat.rows()];
        int height = 0;
        int count = 0;
        int count_Rls_row_black;// = 0;
        for (int r = 0; r < inputMat.rows(); r++) {
            count_Rls_row_black = 0;
            for (int c = 0; c < inputMat.cols(); c++) {
                if (inputMat.get(r, c)[0] == 0.0) {
                    count_Rls_row_black = count_Rls_row_black + 1;
                }
            }
            RLSArray[r] = count_Rls_row_black;
        }
        boolean start_line_detector = false;
        for (int i = 1; i < RLSArray.length; i++) {
            if (RLSArray[i] != 0 && !start_line_detector) {
                start_line_detector = true;

            }
            if (start_line_detector && RLSArray[i] == 0 && RLSArray[i - 1] == 0) {
                height++;
            }
            if (start_line_detector && RLSArray[i] == 0 && RLSArray[i - 1] != 0) {
                count++;
            }
        }

        return height / count;
    }

}
