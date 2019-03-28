/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author ilichh1
 */
public class ImageUtils {
    
    public static String toBase64(File imagen) {
        byte[] imgBytes;
        try {
            imgBytes = new byte[(int)imagen.length()];
            FileInputStream fileIS = new FileInputStream(imagen);
            fileIS.read(imgBytes); //read file into bytes[]
            fileIS.close();
            return Base64.encodeBase64String(imgBytes);
        } catch (Exception ex) {
            System.out.println("No se pudo convertir la imagen a BASE64");
            System.out.println(ex.getLocalizedMessage());
        }
        return "";
    }
    
    public static BufferedImage fromBase64(String base64File) {
        BufferedImage image = null;
        try {
            byte[] imageByte;
            imageByte = Base64.decodeBase64(base64File);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
        } catch(Exception ex) {
            System.out.println("No se pudo convertir la imagen desde un BASE64");
            System.out.println(ex.getLocalizedMessage());
        }
        
        return image;
    }
    
}
