package digital.OCR;

import digital.gui.MainGUI;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author Sayanthini
 */
public class ImageUpload {

   
    private File selFile;

    public File uploadImage(MainGUI obj) {
        final JFileChooser jfc;
        jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
        File f = new File(System.getProperty("user.dir"));
        jfc.setFileFilter(filter);
        jfc.setCurrentDirectory(f);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showOpenDialog(obj);
        selFile = jfc.getSelectedFile();
        
        System.out.println("selected file " + selFile);
        BufferedImage img = null;
        try {
            img = ImageIO.read(selFile);

        } catch (IOException e) {
           // e.printStackTrace();
        }
        return selFile;
    }



}
