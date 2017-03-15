package digital.segmentation;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import digital.segmentation.Imshow;
import org.opencv.core.Size;

/**
 *
 * @author Sayanthini
 */
public class Enhancement {

    static double alpha = 2;
    static double beta = 10;

    public Mat enhancedImage(BufferedImage img) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        BufferedImage image = img;

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat original = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        original.put(0, 0, data);

        //convert grayscale
        Mat gray = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
        Imgproc.cvtColor(original, gray, Imgproc.COLOR_RGB2GRAY);
        Highgui.imwrite("gray.jpg", gray);

        //median filter
        Enhancement en = new Enhancement();
        Mat median = en.medianFilter(gray);
        Highgui.imwrite("MedianFiltered.jpg", median);

        //threshold Binary 
        Mat binary = en.makeBinary(median);
        Highgui.imwrite("Binary.jpg", binary);

       
        Mat brighten = new Mat(binary.rows(), binary.cols(), binary.type());
        median.convertTo(brighten, -1, alpha, beta);
        Highgui.imwrite("brighten.jpg", brighten);

        byte[] data1 = new byte[original.rows() * original.cols() * (int) (original.elemSize())];
        brighten.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(original.cols(), original.rows(), BufferedImage.TYPE_BYTE_GRAY);
        image1.getRaster().setDataElements(0, 0, original.cols(), original.rows(), data1);

        // call segmentation function
        Segment segm = new Segment();
        segm.Segmentation(binary);
        
        Histogram histro = new Histogram();
        histro.HorizontalHisto(binary);
        histro.VerticalHisto(binary);

        System.out.println("rows :" + binary.rows() + "  Cols:" + binary.cols());

        return binary;//image1;
    }

    public Mat medianFilter(Mat img) {
        Mat median = new Mat(img.rows(), img.cols(), img.type());
        Imgproc.medianBlur(img, median, 3);

        return median;
    }

    public Mat makeBinary(Mat img) {
        Mat binary = new Mat(img.rows(), img.cols(), img.type());
        Imgproc.threshold(img, binary, 145, 255, Imgproc.THRESH_BINARY);

        return binary;
    }

    public Mat mediainFiltered(Mat img) {
        Mat gr = img;
        Mat kernal = new Mat(3, 3, CvType.CV_32FC1);

        for (int r = 0; r < kernal.rows(); r++) {
            for (int c = 0; c < kernal.cols(); c++) {
                kernal.put(r, c, 1);
            }
        }

        Mat medianFiltered = new Mat(gr.rows(), gr.cols(), gr.type());

        for (int r = 1; r < gr.rows() - 1; r++) {
            for (int c = 1; c < gr.cols() - 1; c++) {

                double array[] = new double[9];
                double median = 0;
                int i = 0;
                for (int kr = -1; kr < kernal.rows() - 1; kr++) {
                    for (int kc = -1; kc < kernal.cols() - 1; kc++) {
                        double value = gr.get(kr + r, kc + c)[0] * kernal.get(kr + 1, kc + 1)[0];

                        array[i++] = value;

                    }
                }

                Arrays.sort(array);
                median = array[4];
                medianFiltered.put(r, c, median);

            }
        }
        return medianFiltered;

    }
    
    public int add(int a , int b){
        return a + b;
    }

}
