package digital.OCR;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Sayanthini
 */
public class Histogram {

    public BufferedImage create(File img) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Highgui.imread(img.getPath());
        Mat src = new Mat(image.height(), image.width(), CvType.CV_8UC1);
        Imgproc.cvtColor(image, src, Imgproc.COLOR_RGB2GRAY);

        double no_of_pixel[] = new double[256];
        for (int i = 0; i < 256; i++) {
            no_of_pixel[i] = 0;

        }
        for (int row = 0; row < src.rows(); row++) {
            for (int col = 0; col < src.cols(); col++) {
                double[] value = src.get(row, col);
                double x = value[0];
                int y = (int) x;
                no_of_pixel[y] = no_of_pixel[y] + 1;

            }
        }
        double max = 0;
        for (int i = 0; i < 256; i++) {

            if (max < no_of_pixel[i]) {
                max = no_of_pixel[i];
            }
        }

        //generate histogram for low contrast Image
        Mat histImage = new Mat(190, 300, CvType.CV_8UC1);
        double normalizedHisto1[] = new double[256];
        for (int i = 0; i < 256; i++) {
            normalizedHisto1[i] = Math.round((no_of_pixel[i] / max) * histImage.rows());
        }
        // draw lines in histoImage for low contrast
        for (int i = 0; i < 256; i++) {
            Point p1 = new Point(i, histImage.rows());
            Point p2 = new Point(i, histImage.rows() - normalizedHisto1[i]);
            Core.line(histImage, p1, p2, new Scalar(255), 1, 8, 0);
        }

        MatOfByte bytemat = new MatOfByte();
        Highgui.imencode(".jpg", histImage, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage finalhistoimage = ImageIO.read(in);

        return finalhistoimage;
    }
}
