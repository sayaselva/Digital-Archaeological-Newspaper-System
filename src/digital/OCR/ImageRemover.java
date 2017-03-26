package digital.OCR;

/**
 *
 * @author Sayanthini
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_64F;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.*;
import org.opencv.core.Point;

public class ImageRemover {

    final int MAX_LABELS = 8000000;
    int next_label = 1;

    public int[] compactLabeling(int[] image, Dimension d, boolean zeroAsBg) {
        //label first
        int[] label = labeling(image, d, zeroAsBg);
        int[] stat = new int[next_label + 1];
        for (int i = 0; i < image.length; i++) {
            if (label[i] > next_label) {
                System.err.println("bigger label than next_label found!");
            }
            stat[label[i]]++;
        }

        stat[0] = 0;
        // label 0 will be mapped to 0
        // whether 0 is background or not
        int j = 1;
        for (int i = 1; i < stat.length; i++) {
            if (stat[i] != 0) {
                stat[i] = j++;
            }
        }

        System.out.println("From " + next_label + " to " + (j - 1) + " regions");
        next_label = j - 1;
        for (int i = 0; i < image.length; i++) {
            label[i] = stat[label[i]];
        }
        return label;
    }

    public int getMaxLabel() {
        return next_label;
    }

    public int[] labeling(int[] image, Dimension d, boolean zeroAsBg) {
        int w = d.width, h = d.height;
        int[] rst = new int[w * h];
        int[] parent = new int[MAX_LABELS];
        int[] labels = new int[MAX_LABELS];
        // region label starts from 1;
        // this is required as union-find data structure
        int next_region = 1;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (image[y * w + x] == 0 && zeroAsBg) {
                    continue;
                }
                int k = 0;
                boolean connected = false;
                // if connected to the left
                if (x > 0 && image[y * w + x - 1] == image[y * w + x]) {
                    k = rst[y * w + x - 1];
                    connected = true;
                }
                // if connected to the up
                if (y > 0 && image[(y - 1) * w + x] == image[y * w + x]
                        && (connected = false || image[(y - 1) * w + x] < k)) {
                    k = rst[(y - 1) * w + x];
                    connected = true;
                }
                if (!connected) {
                    k = next_region;
                    next_region++;
                }

                if (k >= MAX_LABELS) {
                    System.err.println("maximum number of labels reached. "
                            + "increase MAX_LABELS and recompile.");
                    System.exit(1);
                }
                rst[y * w + x] = k;
                // if connected, but with different label, then do union
                if (x > 0 && image[y * w + x - 1] == image[y * w + x] && rst[y * w + x - 1] != k) {
                    uf_union(k, rst[y * w + x - 1], parent);
                }
                if (y > 0 && image[(y - 1) * w + x] == image[y * w + x] && rst[(y - 1) * w + x] != k) {
                    uf_union(k, rst[(y - 1) * w + x], parent);
                }
            }
        }

        // Begin the second pass.  Assign the new labels
        // if 0 is reserved for background, then the first available label is 1
        next_label = 1;
        for (int i = 0; i < w * h; i++) {
            if (image[i] != 0 || !zeroAsBg) {
                rst[i] = uf_find(rst[i], parent, labels);
                // The labels are from 1, if label 0 should be considered, then
                // all the label should minus 1
                if (!zeroAsBg) {
                    rst[i]--;
                }
            }
        }
        next_label--;   // next_label records the max label
        if (!zeroAsBg) {
            next_label--;
        }

        System.out.println(next_label + " regions");

        return rst;
    }

    void uf_union(int x, int y, int[] parent) {
        while (parent[x] > 0) {
            x = parent[x];
        }
        while (parent[y] > 0) {
            y = parent[y];
        }
        if (x != y) {
            if (x < y) {
                parent[x] = y;
            } else {
                parent[y] = x;
            }
        }
    }

    int uf_find(int x, int[] parent, int[] label) {
        while (parent[x] > 0) {
            x = parent[x];
        }
        if (label[x] == 0) {
            label[x] = next_label++;
        }
        return label[x];
    }

    public Mat Remove(Mat mat) {
        int[] imageData = new int[mat.rows() * mat.cols()];
        int i = 0;
        for (int r = 0; r < mat.rows(); r++) {
            for (int c = 0; c < mat.cols(); c++) {
                imageData[i] = (int) mat.get(r, c)[0];

                i++;
            }
        }
        i = 0;

        int[] output = this.compactLabeling(imageData, new Dimension(mat.rows(), mat.cols()), false);
        Mat out = new Mat(mat.rows(), mat.cols(), mat.type());

        for (int r = 0; r < mat.rows(); r++) {
            for (int c = 0; c < mat.cols(); c++) {
                if (output[i] > 255) {
                    out.put(r, c, 255);
                } else {
                    out.put(r, c, output[i]);
                }

                i++;
            }

        }
        Imgproc.Canny(out, out, 50, 80);
        Mat fout = new Mat(out.rows(), out.cols(), out.type(), new Scalar(0));
        System.out.println("start");
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Imgproc.findContours(out, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
        System.out.println(contours.size());
        for (int j = 0; j < contours.size(); j++) {
            System.out.println(Imgproc.contourArea(contours.get(j)));

            Rect rect = Imgproc.boundingRect(contours.get(j));
            System.out.println(rect.height);
            if (rect.height > 28) {

                for (int r = rect.y; r < rect.y + rect.height; r++) {
                    for (int c = rect.x; c < rect.x + rect.width; c++) {
                        mat.put(r, c, 255);
                        out.put(r, c, 255);
                    }
                }
            }

        }

        Highgui.imwrite("cany.jpg", out);
        System.out.println("Finnesed");
        return mat;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        ImageRemover ir = new ImageRemover();
        RunLength rl = new RunLength();
        ODT convertBinary = new ODT();
        Mat bin = convertBinary.getDynamicThreshold("35.jpg");
        Mat tem = bin.clone();
        Highgui.imwrite("bin.jpg", tem);
        Mat headlineh = rl.HSmooth(bin);
        Mat headlinev = rl.VSmooth(bin);            //Smooth the input image vertically

        Mat mat = rl.logicalAnd(headlinev, headlineh);

    }
}
