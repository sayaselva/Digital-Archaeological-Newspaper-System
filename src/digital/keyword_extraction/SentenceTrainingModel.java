package digital.keyword_extraction;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class SentenceTrainingModel {

    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-16");
        ObjectStream<String> lineStream = null;
        try {
            lineStream = new PlainTextByLineStream(
                    new FileInputStream("C:\\Users\\mmuzzami.ORADEV\\Desktop\\NLP\\ta-sent.train"), charset);
        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        }
        ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream);

        SentenceModel model = null;

        try {
            model = SentenceDetectorME.train("ta", sampleStream, true, null, TrainingParameters.defaultParams());
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                sampleStream.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        OutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream("ta-sent.model"));
            model.serialize(modelOut);
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (modelOut != null) {
                try {
                    modelOut.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

}
