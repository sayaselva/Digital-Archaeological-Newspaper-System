package digital.keyword_extraction;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TokenTrainingModel {

    public static void main(String[] args) {
        Charset charset = Charset.forName("UTF-16");
        ObjectStream<String> lineStream = null;
        try {
            lineStream = new PlainTextByLineStream(
                    new FileInputStream("ta-sent.train"), charset);
        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        }
        ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);

        TokenizerModel model = null;

        try {
            model = TokenizerME.train("ta", sampleStream, true, TrainingParameters.defaultParams());
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
            modelOut = new BufferedOutputStream(new FileOutputStream("ta-token.model"));
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
