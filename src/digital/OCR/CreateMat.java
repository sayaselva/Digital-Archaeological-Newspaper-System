package digital.OCR;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author Sayanthini
 */
public class CreateMat {

    private int rows = 0;
    private int cols = 0;

    public Mat getMat(int row, int col) {
        this.cols = col;
        this.rows = row;
        Mat matObj = new Mat(rows, cols, CvType.CV_8UC1);
        return matObj;
    }

}
