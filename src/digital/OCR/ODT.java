package digital.OCR;

import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_8UC1;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Sayanthini
 */
public class ODT {

    public Mat getDynamicThreshold(String selectedFile) {

        File file = new File(selectedFile);

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat input = Highgui.imread(file.getAbsolutePath());

        Mat image = new Mat(input.rows(), input.cols(), CV_8UC1);

        Imgproc.cvtColor(input, image, Imgproc.COLOR_RGB2GRAY);
        //Imgproc.threshold(input,input,150, 255, Imgproc.THRESH_BINARY);

        Mat kernal = new Mat(9, 9, CvType.CV_32FC1);
        Mat DTImage = new Mat(image.rows(), image.cols(), image.type(), new Scalar(255));
        double LocalThreshold, GlobalThreshold = 150.0;
        double sf, sb, cardF, cardB, data;
        // Adaptive Threshold
        for (int r = 4; r < image.rows() - 4; r++) {
            for (int c = 4; c < image.cols() - 4; c++) {

                sf = 0;
                sb = 0;
                cardF = 1;
                cardB = 1;
                for (int i = r - 4; i < r - 4 + kernal.rows(); i++) {
                    for (int j = c - 4; j < c - 4 + kernal.cols(); j++) {
                        data = image.get(i, j)[0];

                        if (data <= GlobalThreshold) {
                            sf = sf + data;
                            cardF = cardF + 1;
                        } else {
                            sb = sb + data;
                            cardB = cardB + 1;
                        }
                    }
                }
                LocalThreshold = ((sf / cardF) + (sb / cardB)) / 2;

                if (image.get(r, c)[0] <= LocalThreshold) {
                    DTImage.put(r, c, 0);

                } else {
                    DTImage.put(r, c, 255);

                }

            }
        }
        return DTImage;
    }

    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ODT ot = new ODT();

        Mat output = ot.getDynamicThreshold("ori.jpg");
        Highgui.imwrite("dynamic.jpg", output);
        Mat m1 = new Mat((190 - 10), output.cols(), output.type());
        Mat m2 = new Mat((277 - 191), output.cols(), output.type());
        Mat m3 = new Mat((output.rows() - 278), output.cols(), output.type());

        for (int r = 0; r < output.rows(); r++) {
            for (int c = 0; c < output.cols(); c++) {
                if (r < 190 && r >= 10) {
                    m1.put(r - 10, c, output.get(r, c)[0]);
                } else if (r < 277 && r >= 191) {
                    m2.put(r - 191, c, output.get(r, c)[0]);

                } else if (r < output.rows() && r >= 278) {
                    m3.put(r - 278, c, output.get(r, c)[0]);
                }
            }
        }

        Highgui.imwrite("im1.jpg", m1);
        Highgui.imwrite("im2.jpg", m2);
        Highgui.imwrite("im3.jpg", m3);

    }
}
