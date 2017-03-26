package digital.OCR;

import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_32FC1;
import static org.opencv.core.CvType.CV_8UC1;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class testttt {

    FEextract fea = new FEextract();

    public BufferedImage ht(String it) {

        Mat img = Highgui.imread(it);

        // generate gray scale and blur
        Mat gray = new Mat(img.rows(), img.cols(), CvType.CV_8UC1);
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(gray, gray, new Size(3, 3));

        // detect the edges
        Mat edges = new Mat();
        int lowThreshold = 50;
        int ratio = 4;
        Imgproc.Canny(gray, edges, lowThreshold, lowThreshold * ratio);

        Mat lines = new Mat();
        Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 50, 100, 10);

        for (int i = 0; i < lines.cols(); i++) {
            double[] val = lines.get(0, i);
            Core.line(gray, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(0), 2);
        }

        return fea.matToBuffered(edges);
    }

    public Mat medianFilter(Mat noise) {
        Mat noisefree = new Mat(noise.rows(), noise.cols(), CV_8UC1);

        Mat kernal = new Mat(3, 3, CV_32FC1);

        for (int r = 0; r < kernal.rows(); r++) {
            for (int c = 0; c < kernal.cols(); c++) {
                kernal.put(r, c, 1);
            }
        }

        double mean = 0;

        for (int r = 1; r < noise.rows() - 1; r++) {
            for (int c = 1; c < noise.cols() - 1; c++) {
                double sum = 0;
                double array[] = new double[9];
                double median = 0;
                int i = 0;
                for (int kr = -1; kr < kernal.rows() - 1; kr++) {
                    for (int kc = -1; kc < kernal.cols() - 1; kc++) {
                        double value = noise.get(kr + r, kc + c)[0] * kernal.get(kr + 1, kc + 1)[0];

                        sum = sum + value;
                    }
                }

                for (int j = 0; j < 8; j++) {
                    if (array[j + 1] < array[j]) {
                        double temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }

                mean = sum / 9.0;
                noisefree.put(r, c, mean);
            }
        }

        return noisefree;

    }

    public void Histrogram(Mat img1) {

        Mat enhance = img1;

        Mat verticleHisto = new Mat(enhance.rows(), enhance.cols(), CvType.CV_8UC1, new Scalar(255));
        Mat horizontalHisto = new Mat(enhance.cols(), enhance.cols(), CvType.CV_8UC1, new Scalar(255));

        int row = enhance.rows();
        int col = enhance.cols();
        int count = 0;

        for (int r = 0; r < row; r++) {
            count = 0;
            for (int c = 0; c < col; c++) {
                double current = enhance.get(r, c)[0];

                if (current == 255.0) {
                    count++;
                }
            }

            Core.line(horizontalHisto, new Point(0, r), new Point(col - count + 1, r), new Scalar(0), 1);
        }

        Highgui.imwrite("HorizontalHisto.jpg", horizontalHisto);
        int count1 = 0;
        for (int c = 0; c < col; c++) {
            count1 = 0;
            for (int r = 0; r < row; r++) {
                double current1 = enhance.get(r, c)[0];

                if (current1 == 255.0) {
                    count1++;
                }
            }

            Core.line(verticleHisto, new Point(c, row - (count1 + 1)), new Point(c, row), new Scalar(0), 1);
        }
        Highgui.imwrite("verticalHisto.jpg", verticleHisto);

    }
}
