package digital.OCR;

import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Sayanthini
 */
public class ROI {

    public int[] HorizontalProjection(Mat RLSImage) {
        // org=origninal;
        // Rls = RLSImage;
        int HProjection[] = new int[RLSImage.rows()];
        int horizontal_black_run = 0;
        int row = RLSImage.rows(), col = RLSImage.cols();

        for (int r = 0; r < row; r++) {
            horizontal_black_run = 0;
            for (int c = 0; c < col; c++) {
                if (RLSImage.get(r, c)[0] == 0.0) {
                    horizontal_black_run = horizontal_black_run + 1;
                }
            }
            HProjection[r] = horizontal_black_run;

        }

        return HProjection;
    }

    public ArrayList<Mat> getRegion(Mat image, int[] hProjection) {
        ArrayList<Integer> histo_minima = new ArrayList<>();
        boolean flag = false;
        for (int i = 1; i < hProjection.length; i++) {
            if (hProjection[i] != 0 && !flag) {
                flag = true;
                histo_minima.add(i - 1);
            }
            if (hProjection[i] < 5 && flag && hProjection[i - 1] != 0) {
                histo_minima.add(i - 1);
            }

        }

        for (Integer histo_minima1 : histo_minima) {
            System.out.println(histo_minima1);
        }

        int[] heightOfRegion = new int[histo_minima.size()];
        for (int i = 0; i < histo_minima.size() - 1; i++) {
            if (i == 0) {
                heightOfRegion[i] = histo_minima.get(i);
            } else {
                heightOfRegion[i] = histo_minima.get(i) - histo_minima.get(i - 1);
            }
        }

        ArrayList<Mat> Regions = new ArrayList<>();
        ArrayList<Mat> Gray_Region = new ArrayList<>();
        CreateMat mat = new CreateMat();
        int rowSize;
        for (int i = 0; i < histo_minima.size(); i++) {
            if (i == histo_minima.size() - 1) {
                rowSize = image.rows() - histo_minima.get(i);

            } else {
                rowSize = histo_minima.get(i + 1) - histo_minima.get(i);
            }
            if (rowSize < 10) {
                continue;
            }
            Mat element = mat.getMat(rowSize, image.cols());
            Mat gray_element = mat.getMat(rowSize, image.cols());
            int r_row = element.rows();
            int r_col = element.cols();
            for (int r = 0; r < r_row; r++) {
                for (int c = 0; c < r_col; c++) {
                    element.put(r, c, image.get(r + histo_minima.get(i), c)[0]);

                }

            }
            Regions.add(element);

        }

        System.out.println("in\t Minuma\t height \t rate");

        System.out.println("Reion count with in the ROI: " + Regions.size());
        RegionAnalysis obj = new RegionAnalysis();

        return obj.regionCount(Regions);//Regions; 
    }

}
