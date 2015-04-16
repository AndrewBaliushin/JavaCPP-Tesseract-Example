import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class BasicExample {
	
	/**
	 * 
	 * Recognize text on the picture. 
	 * Modified example. Works with BufferedImage instead link to image file.
	 * 
	 * Original example from JavaCPP.
	 * @see <a href="https://github.com/bytedeco/javacpp-presets/tree/master/tesseract">
	   JavaCPP Presets for Tesseract/a>
	 * 
	 * 
	 * 
	 * @param Not used.
	 */
    public static void main(String[] args){
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        
        File imgPath = new File("test.png");
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        BufferedImage img;
        
		try {
			img = ImageIO.read(imgPath);
			ImageIO.write(img, "png", baos );
		} catch (IOException e) {
			System.err.println("Reading file or writing byte[] failed.");
			e.printStackTrace();
		}
        
        byte[] imageInByte=baos.toByteArray();
        
        PIX image = lept.pixReadMemPng(imageInByte, imageInByte.length);
        
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
    }
}
