package digital.OCR;

import digital.gui.MainGUI;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.opencv.core.CvType.CV_32FC1;
import static org.opencv.core.CvType.CV_8UC1;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;

/**
 *
 * @author Sayanthini
 */
public class FEextract {

    private File selFolder;

    public void makeBinary(String img) {
        Mat image = Highgui.imread(img);
        Mat gr = new Mat(image.rows(), image.cols(), CV_8UC1);
        Mat binaryMat = new Mat(gr.rows(), gr.cols(), CV_8UC1);
        Imgproc.threshold(gr, binaryMat, 50, 255, THRESH_BINARY);
    }

    public BufferedImage matToBuffered(Mat img) {
        byte[] data1 = new byte[img.rows() * img.cols() * (int) (img.elemSize())];
        img.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(img.cols(), img.rows(), BufferedImage.TYPE_BYTE_GRAY);
        image1.getRaster().setDataElements(0, 0, img.cols(), img.rows(), data1);

        return image1;
    }

    public Mat BufferedTOmat(BufferedImage img) {
        BufferedImage image = img;
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CV_8UC1);
        mat.put(0, 0, data);
        return mat;
    }

}
