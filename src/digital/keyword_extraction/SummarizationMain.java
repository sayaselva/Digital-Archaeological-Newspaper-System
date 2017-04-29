package digital.keyword_extraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;

public class SummarizationMain {

    public String Summain(File fill) throws IOException {

        System.out.print("start method");
        String Displaysum = " ";
        String content = "";

        SummaryTool instance = new SummaryTool();
               // instance.intersection(String[] sent1,String[] sent2);

              //  File fil1=filRead.Filepath();
        Reader in = null;
        try {
            System.out.print("try...");
            int intValueOfChar;
            //File file1 = new File(obj1.);
            in = new BufferedReader(new InputStreamReader(new FileInputStream(fill), StandardCharsets.UTF_16));
            while ((intValueOfChar = in.read()) != -1) {
                content += (char) intValueOfChar;
            }
            System.out.print("complet while");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("finsh contsn writng method");
        System.out.println(content);
        String[] paragraphs = instance.splitToParagraphs(content);
        StringBuilder summary = new StringBuilder();

        for (String p : paragraphs) {
            String bestSent = instance.getBestsentenceFromParagraph(p);
            if (bestSent != null && bestSent.length() > 0) {
                summary.append(bestSent);
            }
            Displaysum = Displaysum + summary;
        }
        Writer out = null;
        try {
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_16));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Writing file");
            out.write(summary.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(summary);

        return Displaysum.toString();

    }

}
