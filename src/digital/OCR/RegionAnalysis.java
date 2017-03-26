package digital.OCR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 *
 * @author Sayanthini
 */
public class RegionAnalysis {

    double total_region_black[];
    double arrayTotal = 0;
    double prob[];
    int col = 0;

    public ArrayList<Mat> regionCount(ArrayList<Mat> Regions) {
        total_region_black = new double[Regions.size()];
        prob = new double[Regions.size()];
        int row = 0;

        double pixel_in_regions = 0, black_pixel_in_regions = 0, total_black_pixel = 0, total_pixel = 0;
        System.out.println("Total Region count : " + Regions.size());
        for (int i = 0; i < Regions.size(); i++) {
            row = Regions.get(i).rows();
            col = Regions.get(i).cols();
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (Regions.get(i).get(r, c)[0] == 0) {

                        black_pixel_in_regions++;
                    }
                    pixel_in_regions++;
                }
            }
            total_region_black[i] = black_pixel_in_regions;
            total_black_pixel = total_black_pixel + black_pixel_in_regions;
            black_pixel_in_regions = 0;

            total_pixel = pixel_in_regions + total_pixel;
            pixel_in_regions = 0;

        }
        double pob_Threshold;
        int total_row = 0;
        for (int k = 0; k < Regions.size(); k++) {
            prob[k] = (total_region_black[k] * 100) / (total_pixel * Regions.get(k).rows());
            System.out.println("Region " + k + " prob " + prob[k]);
            total_row = total_row + Regions.get(k).rows();
        }
        pob_Threshold = (total_black_pixel * 100) / (total_row * total_pixel);
        double std = 0;

        System.out.println("AvgThreshold : " + pob_Threshold + " std : ");
        return this.mergeRegions(Regions, prob, pob_Threshold);
    }

    public ArrayList<Mat> mergeRegions(ArrayList<Mat> Regions, double[] prob, double threshold) {
        boolean same = true, loop_entry = false;
        HashMap<Integer, Mat> finalRegions = new HashMap<>();
        ArrayList<Mat> orderd_regions = new ArrayList<>();
        Mat temp;
        ArrayList<Integer> lessRegion = new ArrayList<>();
        ArrayList<Integer> largeRegions = new ArrayList<>();
        for (int i = 0; i < Regions.size(); i++) {
            if (prob[i] < threshold) {
                lessRegion.add(i);
            } else {
                largeRegions.add(i);
            }
        }
        for (int reg : lessRegion) {
            System.out.println("Less : " + reg);
        }
        for (int reg : largeRegions) {
            System.out.println("Large : " + reg);
        }
        temp = new Mat();
        int indic = largeRegions.get(0);
        if (largeRegions.size() > 0) {
            temp = Regions.get(largeRegions.get(0));

            for (int j = 1; j < largeRegions.size(); j++) {
                loop_entry = true;
                if (largeRegions.get(j) - 1 == largeRegions.get(j - 1)) {
                    same = false;
                    temp.push_back(Regions.get(largeRegions.get(j)));

                } else {
                    finalRegions.put(largeRegions.get(j - 1), temp);

                    temp = Regions.get(largeRegions.get(j));
                    same = true;
                }
                indic = largeRegions.get(j);
            }
            if (!same || !loop_entry) {
                finalRegions.put(indic, temp);
            }
        }
        same = true;
        loop_entry = false;
        Mat temp2 = new Mat();
        indic = lessRegion.get(0);
        if (lessRegion.size() > 0) {
            temp2 = Regions.get(lessRegion.get(0));
            for (int j = 1; j < lessRegion.size(); j++) {
                if (lessRegion.get(j) - 1 == lessRegion.get(j - 1)) {
                    same = false;
                    loop_entry = true;
                    temp2.push_back(Regions.get(lessRegion.get(j)));

                } else {
                    finalRegions.put(lessRegion.get(j - 1), temp2);
                    same = true;
                    temp2 = Regions.get(lessRegion.get(j));
                }
                indic = lessRegion.get(j);
            }
            if (!same || !loop_entry) {
                finalRegions.put(indic, temp2);
            }
        }

        Object[] keys = finalRegions.keySet().toArray();
        Arrays.sort(keys);
        for (Object key : keys) {
            orderd_regions.add(finalRegions.get(key));
            System.out.println("key : " + key);
        }
        System.out.println("Total merge region : " + orderd_regions.size());
        int l = 0;

        return orderd_regions;
    }

    public Mat getTextinBody(ArrayList<Mat> list) {
        Mat body = new Mat();
        for (Mat mat : list) {
            body.push_back(mat);
        }
        return body;
    }

}
