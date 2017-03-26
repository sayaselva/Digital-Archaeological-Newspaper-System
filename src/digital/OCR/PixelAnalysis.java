package digital.OCR;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sayanthini
 */
public class PixelAnalysis {

    public List<Integer> getROI(int RLSArray[], int threshold) {
        List<Integer> l1 = new ArrayList<Integer>();

        int firstIndex = 0;
        int lastIndex = 0;
        int current_height = 0;
        int previous_height = 0;
        int pIndex = 0;
        int totalFoundHeight = 0;
        int count = 0;

        boolean start_line_detector = false;

        for (int i = 1; i < RLSArray.length; i++) {
            if (RLSArray[i] != 0 && !start_line_detector) {
                firstIndex = i;
                start_line_detector = true;
                pIndex = firstIndex;
            }
            if (start_line_detector && RLSArray[i] == 0 && RLSArray[i - 1] != 0) {
                previous_height = current_height;
                current_height = i - pIndex;

                count++;
                System.out.println("found blocks " + count);
                System.out.println("current height : " + current_height + " " + "i " + i + " previous" + previous_height);

                if (previous_height == 0 || (!(current_height < previous_height / 2) && !(current_height > 3 * previous_height / 2))) {
                    lastIndex = i;//current_height;

                    System.out.println("same type block found!!" + lastIndex);
                    //break;
                } else {
                   // if(previous_height==0)
                    //lastIndex=i;
                    lastIndex = i - current_height;

                    System.out.println("found lastIndex: current he : " + current_height);
                    break;

                }
                totalFoundHeight = current_height + totalFoundHeight;
                pIndex = i;
            }

        }
        l1.add(firstIndex);
        l1.add(lastIndex);

        return l1;
    }
}
