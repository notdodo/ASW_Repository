package asw1029.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Utility for encode and decode the images
 */
public class Base64Util {

    /**
     * Encode the image
     *
     * @param img image to encode
     * @return Encoded image
     */
    public static String encode(File img) {
        String encodedImage = "";
        try {
            
            BufferedImage imgb = ImageIO.read(img);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imgb, "png", baos);
            baos.flush();
            encodedImage = Base64.encode(baos.toByteArray());
            baos.close();
        } catch (Exception ex) {
            Logger.getLogger(Base64Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedImage;
    }

    /**
     * Decode the image
     *
     * @param img image to decode
     * @return Decoed image
     */
    public static BufferedImage decode(String img) {
        BufferedImage result = null;
        try {
            byte[] bytes = Base64.decode(img);
            result = ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException ex) {
            Logger.getLogger(Base64Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
