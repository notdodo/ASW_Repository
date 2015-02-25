package asw.async;

import asw.imp.Applet1;
import asw1029.utils.Base64Util;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Load the image selected by applet
 */
public class ImageLoader extends Thread {

    private final File fileSelected;
    private final Applet1 context;

    /**
     * class costructor
     *
     * @param c Applet1 applet
     * @param f file selected
     */
    public ImageLoader(Applet1 c, File f) {
        this.fileSelected = f;
        this.context = c;
    }

    @Override
    public void run() {
        String encodedImage = "";
        try {
            encodedImage = Base64Util.encode(fileSelected);
            this.context.imageLoaded(true, encodedImage);
        } catch (Exception ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, "Error on loading image", ex);
            this.context.imageLoaded(false, null);
        }
    }

}
