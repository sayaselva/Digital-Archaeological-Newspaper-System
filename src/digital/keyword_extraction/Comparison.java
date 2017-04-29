package digital.keyword_extraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sayanthini
 */
public class Comparison {

    public String Compare(String summaryInput) throws IOException {
        ArrayList<String> keywords = new ArrayList();
        String Keywrd = " ";
        //SummarizationMain summain=new SummarizationMain();

        //FileInputStream fstream1 = new FileInputStream("C:\\IPhone\\test.txt");
        //FileInputStream fstream2 = new FileInputStream("C:\\IPhone\\Dic1.txt");
        File file1 = new File("C:\\IPhone\\Dic1.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file1), StandardCharsets.UTF_16));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        String[] stringArr = list.toArray(new String[0]);
        for (String item : stringArr) {
            //System.out.println(item);
        }

        String[] tok = summaryInput.split(" ");
        for (int i = 0; i < tok.length; i++) {
            for (int j = 0; j < stringArr.length; j++) {
                if (tok[i].contains(stringArr[j])) {
                    //System.out.println(tok[i]);
                    if (!keywords.contains(tok[i])) {
                        keywords.add(tok[i]);
                    }
                }
            }
        }

        for (int l = 0; l < keywords.size(); l++) {
            Keywrd = Keywrd + "\n" + keywords.get(l);

        }

        return Keywrd;

    }

}
