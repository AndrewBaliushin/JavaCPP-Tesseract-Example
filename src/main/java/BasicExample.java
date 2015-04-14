import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

public class BasicExample {
	
	/**
	 * 
	 * Recognize text on the picture.
	 * 
	 * Example from JavaCPP.
	 * @see <a href="https://github.com/bytedeco/javacpp-presets/tree/master/tesseract">
	   JavaCPP Presets for Tesseract/a>
	 * 
	 * @param args [0] -- path to image file. "test.png" used if empty args[0].
	 */
    public static void main(String[] args) {
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        // Open input image with leptonica library
        PIX image = pixRead(args.length > 0 ? args[0] : "test.png");
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
