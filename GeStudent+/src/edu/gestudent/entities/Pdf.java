/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */

public class Pdf {
      
        //webcam.main(args);  
//    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
//    {
//        Document document=new  Document();
//        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
//        document.open();
//        //Image img = Image.getInstance("photo.png");
//        //Image img2 = Image.getInstance("logo.png");
//        UsersService us=new UsersService();
//        List<User> list=us.getUsers();
//        for(User u:list)
//        {
//        document.add(new Paragraph("Username :"+u.getUsername()));
//        document.add(new Paragraph("email :"+u.getEmail()));
//        document.add(new Paragraph("Account Type :"+u.getAccount_type()));
//           //document.add(img);
//         //document.add(img2);
//        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
//        }//Notification.main(args);
//        document.close();
//        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
//    }
    
        public void GeneratePdfSubject(cours Subject) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(Subject.getName()+".pdf"));
        document.open();
        //Image img = Image.getInstance("photo.png");
        //Image img2 = Image.getInstance("logo.png");

        document.add(new Paragraph("Subject :"+Subject.getName()));
        document.add(new Paragraph("Lesson :"+Subject.getLesson()));
           //document.add(img);
         //document.add(img2);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+Subject.getName()+".pdf");
    }
}
