package digital.OCR;

import java.util.ArrayList;
import org.opencv.core.Mat;

/**
 *
 * @author Sayanthini
 */
public class ParagraphAnalaze {

    public ArrayList<Mat> VerticleProjection(Mat image) {
        int VProjection[] = new int[image.cols()];
        int verticle_black_run = 0;
        int row = image.rows(), col = image.cols();

        for (int c = 0; c < col; c++) {
            verticle_black_run = 0;
            for (int r = 0; r < row; r++) {
                if (image.get(r, c)[0] == 0.0) {
                    verticle_black_run = verticle_black_run + 1;
                }
            }
            VProjection[c] = verticle_black_run;

        }
        return this.readingOrder(this.histoMima(VProjection), image);
    }

    public ArrayList<Integer> histoMima(int[] vProjection) {
        ArrayList<Integer> histo_minima = new ArrayList<>();
        boolean start = false;
        for (int i = 1; i < vProjection.length; i++) {
            if (vProjection[i] != 0 && !start) {
                start = true;
                histo_minima.add(i - 1);
            }
            if (vProjection[i] < 5 && start && vProjection[i - 1] != 0) {
                histo_minima.add(i - 1);
            }
        }
        return histo_minima;
    }

    public ArrayList<Mat> readingOrder(ArrayList<Integer> histo_minima, Mat image) {
        ArrayList<Mat> Regions = new ArrayList<>();

        CreateMat mat = new CreateMat();
        int colSize;
        for (int i = 0; i < histo_minima.size(); i++) {
            if (i == histo_minima.size() - 1) {
                colSize = image.cols() - histo_minima.get(i);

            } else {
                colSize = histo_minima.get(i + 1) - histo_minima.get(i);
            }
            if (colSize < 10) {
                continue;
            }
            Mat element = mat.getMat(image.rows(), colSize);

            int r_row = element.rows();
            int r_col = element.cols();
            for (int r = 0; r < r_row; r++) {
                for (int c = 0; c < r_col; c++) {
                    element.put(r, c, image.get(r, c + histo_minima.get(i))[0]);

                }

            }
            Regions.add(element);

        }
        return Regions;
    }
}
