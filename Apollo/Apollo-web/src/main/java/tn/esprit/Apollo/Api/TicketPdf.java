package tn.esprit.Apollo.Api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import tn.esprit.Apollo.persistence.Ticket;

public class TicketPdf {
	 public static void generatePdf(Ticket ticket){
		 
	try {
        OutputStream file = new FileOutputStream(new File("d:/Ticket.pdf"));
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        String block = "                       ";
        Paragraph smaline = new Paragraph("\n",new Font(Font.FontFamily.TIMES_ROMAN, 4,Font.BOLD));
        String decalline = "\n "+block+block+block;
        
        Font tmr10 = new Font(Font.FontFamily.TIMES_ROMAN, 10,Font.BOLD);
        Font tmr12 = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
        Font tmr7 = new Font(Font.FontFamily.TIMES_ROMAN, 7);
//      Paragraph tim = new Paragraph(new Date().toString(),new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD));
//      String ch = new Date().toString();
//      String dat = ch.substring(0, 11);
//      String year = ch.substring(30, 34);
//      String time = ch.substring(11, 29);
      //Wed Oct 25 13:12:52 GMT+01:00 2017;
        
//        String m = t.getTitle().substring(5, 7);
//        String d = t.getTitle().substring(8, 10);
//        String h = t.getTitle().substring(11, 19);
        
//      System.out.println(t.getId()+"|"+t.getTitle()+"|"+t.getEvent().getTitle()+"|"+t.getEvent().getGallery().getLocation().getAddress()+
//  			"|sd|"+t.getEvent().getStartDate()+"|day|"+t.getEvent().getStartDate().getDay()+"|timezone|"+t.getEvent().getStartDate().getTimezoneOffset()
//  			+"|m|"+t.getEvent().getStartDate().getMonth());
        
//        Ticket t = new Ticket(1,"2017-10-31 18:00:00.0","evenement de tresqd qùmqsd dqsdqsd",12f);

        

        String name = ticket.getEvent().getTitle().toUpperCase();
//        x.substring(0, 4) + "." + x.substring(4, x.length());
//        System.out.println(name.length());
        
        document.open();
        document.add(smaline);
        document.add(smaline);
        	if (name.length() <= 20)
        	{
        		String nm = name.substring(0, name.length())+decalline+decalline;
                document.add(new Paragraph(block+block+block+nm+"\n ",tmr12));
        	}
        	else if (name.length() <= 40)
        	{
        		String nm = name.substring(0,20)+decalline+name.substring(20,name.length())+decalline;
                document.add(new Paragraph(block+block+block+nm+"\n ",tmr12));
        	}
        	else if (name.length() <= 60)
        	{
        		String nm = name.substring(0, 20)+decalline+name.substring(20,40)+decalline+name.substring(40,name.length());
                document.add(new Paragraph(block+block+block+nm+"\n ",tmr12));
        	}
        	else
        	{
        		String nm = name.substring(0, 20)+decalline+name.substring(20,40)+decalline+name.substring(40,59);
                document.add(new Paragraph(block+block+block+nm+"\n ",tmr12));

        	}
        	
        	
        
//        System.out.println(name);
//        name.charAt(18) = "\n";
        

         String y = ticket.getEvent().getStartDate().toString().substring(0, 11);
         String h = ticket.getEvent().getStartDate().toString().substring(11, 16);
         
//       System.out.println(y);
         String[] adress = new String[4];
       	adress = ticket.getEvent().getGallery().getLocation().getAddress().split(",");
//       System.out.println(s[2]+" "+s[3]);
//        document.add(new Paragraph(block+block+block+nm+" \n ",new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD)));
        Paragraph tim = new Paragraph(block+block+block+block+"          "+y+block+"                 Unique QR Identifier ",tmr10);
        Paragraph adr = new Paragraph(block+block+block+"          "+adress[0]+adress[1]+decalline+
        						"          "+adress[2]+decalline+"          "+adress[3],tmr10);
        document.add(adr);
        document.add(tim);
        
        String type=ticket.getTitle();
        if (type.equals("VIP"))
        	type="V.I.P";
        else
        	type="Normal";
        
        document.add(smaline);
        Paragraph tp = new Paragraph(decalline+block+type+block+ticket.getPrice()+" DT",tmr10);
        document.add(tp);
        
        Paragraph info = new Paragraph("\n        Ticket N°"+ticket.getId()+".  "+" Order Date "+ticket.getOrderDate().toString().substring(0, 19)+", Ordered by "+ticket.getUser().getFirstname()+" "+ticket.getUser().getLastname(),tmr10);
        document.add(info);
        
        Paragraph notice = new Paragraph("         "+"Notice : "+ticket.getNote(),tmr7);

        
        com.itextpdf.text.Image back = com.itextpdf.text.Image.getInstance("d:/tik2.png");
        back.setAbsolutePosition(15, 550);
        back.scalePercent(70);
        
        com.itextpdf.text.Image map = com.itextpdf.text.Image.getInstance("d:/map.png");
        map.setAbsolutePosition(52, 620);
        map.scalePercent(23);
        
        com.itextpdf.text.Image qr = com.itextpdf.text.Image.getInstance("d:/qrcode-01.png");
        qr.scalePercent(33);
        qr.setAbsolutePosition(420, 660);
        
        com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("d:/Apollo.png");
        logo.scalePercent(11);
        logo.setAbsolutePosition(425, 582);
        //15-10
        document.add(back);
        document.add(map);
        document.add(qr);
        document.add(logo);
       
        
        
        document.add(notice);
        document.addAuthor("ATOM");
        document.addCreationDate();
        document.addCreator("Apollo");
        document.addTitle("Ticket PDF");
        document.close();
        file.close();

    }catch (Exception e) {
        e.printStackTrace();
    }
	 }
}