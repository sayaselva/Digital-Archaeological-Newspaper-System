package digital.keyword_extraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class TamilReaderWriter {

    public static void main(String[] args) {

        Reader in = null;
        Writer out = null;
        String content = "";
        try {
            in = new BufferedReader(
                    new InputStreamReader(new FileInputStream("tamil-sample.txt"), StandardCharsets.UTF_16));

            StringBuilder buffer = new StringBuilder();
            char[] cbuf = new char[2048];
            while (true) {
                int n = in.read(cbuf);
                if (n < 0) {
                    break;
                }
                buffer.append(cbuf, 0, n);
            }
            content = buffer.toString();
            System.out.println(content);

            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_16));
            out.write(content);
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                out.flush();
                in.close();
                out.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}
