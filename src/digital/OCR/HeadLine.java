package digital.OCR;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static org.opencv.core.CvType.CV_8UC1;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Sayanthini
 */
public class HeadLine {

    FEextract feature1 = new FEextract();

    public List<Mat> getHeadLine(Mat RLSImage, String original) {
        int row = RLSImage.rows();
        int col = RLSImage.cols();
        Mat originalImage = Highgui.imread(original);

        Mat headline = null;

        int RLSArray[] = new int[RLSImage.rows()];
        int count_Rls_row_black;// = 0;
        for (int r = 0; r < row; r++) {
            count_Rls_row_black = 0;
            for (int c = 0; c < col; c++) {
                if (RLSImage.get(r, c)[0] == 0.0) {
                    count_Rls_row_black = count_Rls_row_black + 1;
                }
            }
            RLSArray[r] = count_Rls_row_black;
        }

        int firstIndex = 0;
        int lastIndex = 0;

        // get ROI coordinates
        List<Integer> list, list2;
        PixelAnalysis pa = new PixelAnalysis();
        list = pa.getROI(RLSArray, 30);
        firstIndex = list.get(0);
        lastIndex = list.get(1);
        System.out.println("first : " + firstIndex + " last : " + lastIndex);
        int temLast = lastIndex;

        // firstIndex=14;
        // lastIndex=135;
        Mat head = new Mat((lastIndex - firstIndex), col, CV_8UC1, new Scalar(255));

        for (int r = 0; r < head.rows(); r++) {
            for (int c = 0; c < col; c++) {
                double value = originalImage.get(firstIndex + r, c)[0];
                head.put(r, c, value);
            }
        }

        int RLSArray2[] = new int[head.rows()];
        System.arraycopy(RLSArray, 0, RLSArray2, 0, head.rows());

        list2 = pa.getROI(RLSArray2, 20);

        firstIndex = list2.get(0);
        lastIndex = temLast;

        System.out.println("sub heading first and last index!! " + firstIndex + " : " + lastIndex);
        Mat subHead = new Mat((lastIndex - firstIndex), col, CV_8UC1);

        for (int r = 0; r < subHead.rows(); r++) {
            for (int c = 0; c < col; c++) {
                double value = originalImage.get(firstIndex + r, c)[0];
                subHead.put(r, c, value);

            }
        }
        List<Mat> headingSet = new ArrayList<Mat>();
        headingSet.add(subHead);

        return headingSet;
    }

    public Mat getSubHeading(Mat headline) {
        Mat subHeading = new Mat(headline.rows(), headline.cols(), headline.type());

        return subHeading;
    }
}
