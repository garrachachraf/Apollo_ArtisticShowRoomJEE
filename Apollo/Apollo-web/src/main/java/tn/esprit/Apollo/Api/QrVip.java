package tn.esprit.Apollo.Api;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import tn.esprit.Apollo.persistence.Ticket;

public class QrVip {
	public static void QrColored(String data) throws IOException, WriterException{
        int size = 400;
        ByteMatrix matrix = generateMatrix(data);
        String imageFormat = "png";
        String outputFileName = "d:/qrcode-01." + imageFormat;
        writeImage(outputFileName, imageFormat, matrix, size);
        
    }

	 private static void writeImage(String outputFileName, String imageFormat, ByteMatrix matrix, final int size) throws IOException {

	        // Futurs modules
	        Area a = new Area();
//	        Area module = new Area(new Rectangle.Float(0, 0, 1, 1));
	        Area module = new Area(new RoundRectangle2D.Float(0, 0, 0.9f, 0.9f, 1f, 1f));
	        // Deplacement du module
	        AffineTransform at = new AffineTransform(); 
	        int width = matrix.getWidth();
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < width; j++) {
	                if (matrix.get(j, i) == 1) {
	                    // Ajout du module
	                    a.add(module);
	                }
	                at.setToTranslation(1, 0); 
	                module.transform(at);
	            }

	            at.setToTranslation(-width, 1); 
	            module.transform(at);
	        }

	        // Agrandissement de l'Area pour le remplissage de l'image
	        double ratio = size / (double) width;

	        // Quietzone : 4 modules de bordures autour du QR Code (zone vide pour bien identifier le code dans la page imprimee)
	        double adjustment = width / (double) (width + 8);
	        ratio = ratio * adjustment;
	        at.setToTranslation(4, 4); 
	        a.transform(at);

	        // On agrandit le tour a la taille souhaitee.
	        at.setToScale(ratio, ratio); 
	        a.transform(at);
	        
	        // Java 2D
	        BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = (Graphics2D) im.getGraphics();

	        // Modules verts
	        Color col = new Color(0xAF28C8);
	        g.setPaint(col);

	        g.setBackground(new Color(0xFFFFFF));

	        // Fond blanc
	        g.clearRect(0, 0, size, size);

	        // Remplissage des modules
	        g.fill(a); 
	        
	        File f = new File(outputFileName);
	        f.setWritable(true);
	        ImageIO.write(im, imageFormat, f);
	        f.createNewFile();
	    }
	    

    private static ByteMatrix generateMatrix(final String data) throws WriterException {
        QRCode qr = new QRCode();
        Encoder.encode(data, ErrorCorrectionLevel.L, qr);
        ByteMatrix matrix = qr.getMatrix();
        return matrix;
    }
    
    private static BitMatrix generateMatrix(String data, int size) throws WriterException{
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        return bitMatrix;
    }
}