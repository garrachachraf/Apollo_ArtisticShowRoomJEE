package tn.esprit.Apollo.Api;

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

import tn.esprit.Apollo.persistence.Ticket;

public class QrcodeAndMapGenerator {
    public static void generateQr(Ticket ticket) throws IOException, WriterException{
        String data = ticket.getId()+ticket.toString()+ticket.getId();
    	if (ticket.getTitle().equals("VIP"))
    		{QrVip.QrColored(data);}
    	else {
        System.out.println(data);
        int size = 400;
        BitMatrix bitMatrix = generateMatrix(data, size);
        String imageFormat = "png";
        String outputFileName = "d:/qrcode-01." + imageFormat;
        writeImage(outputFileName, imageFormat, bitMatrix);
    	}
        String latitude = ticket.getEvent().getGallery().getLocation().getLatitude().toString();
        String longitude = ticket.getEvent().getGallery().getLocation().getLongitude().toString();
        System.out.println(data+"|"+latitude+"|"+longitude);
        String myMapboxAccessToken ="pk.eyJ1IjoiZ2FycmFjaCIsImEiOiJjajk1bjdmM2MxemgzMzJwNnNhYmN2bnRqIn0.Q35bIooJvU6SA0soVSaN8w";
        URL imageURL = new URL("https://api.mapbox.com/styles/v1/mapbox/streets-v10/static/"
        		+ "geojson({\"type\"%3A\"Point\"%2C\"coordinates\"%3A["
//        		+ "10.1906704"
        		+ latitude
        		+ "%2C"
        		+ longitude
        		+ ",15"
        		+ "]})/"
        		+ latitude
        		+ ","
        		+ longitude
        		+ ",15/750x750?access_token="
        		+ myMapboxAccessToken
        						);
        RenderedImage img = ImageIO.read(imageURL);
        File outputFile = new File("d:/map.png");
        System.out.println(img.getHeight());
        try {
            ImageIO.write(img, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    private static void writeImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) throws IOException  {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
        fileOutputStream.close();
    }

    private static BitMatrix generateMatrix(String data, int size) throws WriterException{
        BitMatrix bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
        return bitMatrix;
    }
}