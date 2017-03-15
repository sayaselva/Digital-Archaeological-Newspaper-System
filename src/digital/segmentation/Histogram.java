package digital.segmentation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Sayanthini
 */
public class Histogram {

    public int[] HorizontalHisto(Mat img) {
        Mat enhance = img;
        Mat horizontalHisto = new Mat(enhance.cols(), enhance.cols(), CvType.CV_8UC1, new Scalar(255));

        int row = enhance.rows();
        final int col = enhance.cols();

        int horizontal_Wcount[] = new int[row];

        for (int i = 0; i < row; i++) {
            horizontal_Wcount[i] = 0;
        }

        int count = 0;
        for (int r = 0; r < row; r++) {
            count = 0;
            for (int c = 0; c < col; c++) {
                double current = enhance.get(r, c)[0];

                if (current == 255.0) {
                    count++;

                }

            }
            horizontal_Wcount[r] = count;
            Core.line(horizontalHisto, new Point(0, r), new Point(col - count + 1, r), new Scalar(0), 1);
        }
        Highgui.imwrite("horizontalHistogram.jpg", horizontalHisto);

        return horizontal_Wcount;
    }

    public int[] VerticalHisto(Mat img) {
        Mat enhance = img;
        Mat verticleHisto = new Mat(enhance.rows(), enhance.cols(), CvType.CV_8UC1, new Scalar(255));

        int row = enhance.rows();
        final int col = enhance.cols();

        int verticle_Wcount[] = new int[col];

        for (int i = 0; i < col; i++) {
            verticle_Wcount[i] = 0;
        }
        int count1 = 0;
        for (int c = 0; c < col; c++) {
            count1 = 0;
            for (int r = 0; r < row; r++) {
                double current1 = enhance.get(r, c)[0];

                if (current1 == 255.0) {
                    count1++;
                }

            }
            verticle_Wcount[c] = count1;
            Core.line(verticleHisto, new Point(c, row - (count1 + 1)), new Point(c, row), new Scalar(0), 1);
        }

        Highgui.imwrite("verticleHistogram.jpg", verticleHisto);

        return verticle_Wcount;
    }
}
