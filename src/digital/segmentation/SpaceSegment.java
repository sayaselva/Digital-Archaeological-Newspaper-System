package digital.segmentation;

import digital.segmentation.Imshow;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Sayanthini
 */
public class SpaceSegment {

    public ArrayList<Mat> horizontalSpaceSeg(Mat enhan, BufferedImage origi) {
        Mat enhance = enhan;
        BufferedImage image = origi;

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat orginal = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        orginal.put(0, 0, data);

        Highgui.imwrite("orginal.jpg", orginal);
        Histogram histo = new Histogram();
        int horizontal_Wcou[] = histo.HorizontalHisto(enhance);

        final ArrayList<Integer> deteted_line = new ArrayList<Integer>();
        ArrayList<Mat> histoSegm = new ArrayList<Mat>();
        int cou = 0;
        boolean flag = true;

        int row = enhance.rows();
        final int col = enhance.cols();

        for (int i = 0; i < row && flag; i++) {
            if (i == 0) {
                deteted_line.add(i);
            } else {
                if (horizontal_Wcou[i] == col) {
                    cou++;

                    if (cou > row / 52) {
                        deteted_line.add(i);
                        Core.line(enhance, new Point(0, i), new Point(col, i), new Scalar(0), 1);
                        cou = 0;
                    }
                }
            }
        }

        Highgui.imwrite("detectLine.jpg", enhance);

        class histoseg {

            public Mat createMat(int row, int col) {
                Mat histoSegment = new Mat(row, col, CvType.CV_8UC1);

                return histoSegment;
            }
        }

        for (int i = 0; i < deteted_line.size() - 1; i++) {
            histoseg seg = new histoseg();
            histoSegm.add(seg.createMat(deteted_line.get(i + 1) - deteted_line.get(i), col));
        }

        int lastRow = deteted_line.get(0);
        for (int i = 0; i < histoSegm.size(); i++) {
            for (int r = lastRow; r < deteted_line.get(i + 1); r++) {
                for (int c = 0; c < col; c++) {
                    double original_value = orginal.get(r, c)[0];
                    histoSegm.get(i).put(r - lastRow, c, original_value);
                }
            }
            lastRow = deteted_line.get(i + 1);//lastRow+histoSegm.get(i).rows();
            //System.out.println("lastrow "+lastRow);
        }

        //Highgui.imwrite("histoSegment.jpg", histoSegm.get(2));
        System.out.println("************no of horizontal seg*************************" + histoSegm.size());

        return histoSegm;
    }

    public ArrayList<Mat> verticalSpaceSeg(Mat horiHistoSegm) {
        Enhancement en = new Enhancement();
        Mat median = en.medianFilter(horiHistoSegm);
        Mat histoSegm = en.makeBinary(median);

        final ArrayList<Integer> VerticalDeteted_line = new ArrayList<Integer>();
        ArrayList<Mat> vertiHistoSegm = new ArrayList<Mat>();
        Histogram histo1 = new Histogram();
        int vertical_Wcou[] = histo1.VerticalHisto(histoSegm);
        int rows = histoSegm.rows();
        int colm = histoSegm.cols();
        int cou = 0;

        for (int i = 0; i < colm; i++) {
            if (i == 0) {
                VerticalDeteted_line.add(i);
            } else {
                if (vertical_Wcou[i] == rows) {
                    cou++;

                    if (cou > colm / 52) {
                        VerticalDeteted_line.add(i);
                        Core.line(histoSegm, new Point(i, 0), new Point(i, rows), new Scalar(0), 1);
                        cou = 0;
                    }
                }
            }
        }

        Highgui.imwrite("VerDetectLine.jpg", histoSegm);

        class histoseg {

            public Mat createMat(int row, int col) {
                Mat histoSegment = new Mat(row, col, CvType.CV_8UC1);

                return histoSegment;
            }
        }

        for (int i = 0; i < VerticalDeteted_line.size() - 1; i++) {
            histoseg seg1 = new histoseg();
            vertiHistoSegm.add(seg1.createMat(rows, VerticalDeteted_line.get(i + 1) - VerticalDeteted_line.get(i)));
        }
        int lastColumn = VerticalDeteted_line.get(0);
        for (int i = 0; i < vertiHistoSegm.size(); i++) {
            for (int r = 0; r < rows; r++) {
                for (int c = lastColumn; c < VerticalDeteted_line.get(i + 1); c++) {
                    double original_value = horiHistoSegm.get(r, c)[0];
                    vertiHistoSegm.get(i).put(r, c - lastColumn, original_value);
                }
            }
            lastColumn = VerticalDeteted_line.get(i + 1);//lastRow+histoSegm.get(i).rows();
            //System.out.println("lastColumn "+lastColumn);
        }
        //Highgui.imwrite("VertihistoSegment.jpg", vertiHistoSegm.get(1));
        System.out.println("************no of vertical seg*************************" + vertiHistoSegm.size());

        return vertiHistoSegm;
    }

}
