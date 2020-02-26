/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class Email {

    public static void sendEmail(String address, String subject, HashMap<String, String> message) throws Exception {

        String from = "amin.bencheikh@esprit.tn";
        String pass = "197382465";
        String[] to = {address};
        String host = "smtp.gmail.com";

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(prop);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));

        InternetAddress[] toaddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            toaddress[i] = new InternetAddress(to[i]);
        }
        for (int i = 0; i < toaddress.length; i++) {
            msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
        }
        //
        /*
            String content = new String(Files.readAllBytes(Paths.get("/fixit/Views/BlogPost.html")), "UTF-8");
            Path pathToFile = Paths.get("/fixit/Views/BlogPost.html");
             System.out.println("Path is : "+ pathToFile);
            message = content;
         */

        String messageBody = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<style>\n"
                + "* {\n"
                + "  box-sizing: border-box;\n"
                + "}\n"
                + "\n"
                + "/* Add a gray background color with some padding */\n"
                + "body {\n"
                + "  font-family: Arial;\n"
                + "  padding: 20px;\n"
                + "  background: #f1f1f1;\n"
                + "}\n"
                + "\n"
                + "/* Header/Blog Title */\n"
                + ".header {\n"
                + "  padding: 30px;\n"
                + "  font-size: 40px;\n"
                + "  text-align: center;\n"
                + "  background: white;\n"
                + "}\n"
                + "\n"
                + "/* Create two unequal columns that floats next to each other */\n"
                + "/* Left column */\n"
                + ".leftcolumn {   \n"
                + "  float: left;\n"
                + "  width: 75%;\n"
                + "}\n"
                + "\n"
                + "/* Right column */\n"
                + ".rightcolumn {\n"
                + "  float: left;\n"
                + "  width: 25%;\n"
                + "  padding-left: 20px;\n"
                + "}\n"
                + "\n"
                + "/* Fake image */\n"
                + ".fakeimg {\n"
                + "  background-color: #aaa;\n"
                + "  width: 100%;\n"
                + "  padding: 20px;\n"
                + "}\n"
                + "\n"
                + "/* Add a card effect for articles */\n"
                + ".card {\n"
                + "   background-color: white;\n"
                + "   padding: 20px;\n"
                + "   margin-top: 20px;\n"
                + "}\n"
                + "\n"
                + "/* Clear floats after the columns */\n"
                + ".row:after {\n"
                + "  content: \"\";\n"
                + "  display: table;\n"
                + "  clear: both;\n"
                + "}\n"
                + "\n"
                + "/* Footer */\n"
                + ".footer {\n"
                + "  padding: 20px;\n"
                + "  text-align: center;\n"
                + "  background: #ddd;\n"
                + "  margin-top: 20px;\n"
                + "}\n"
                + "\n"
                + "/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */\n"
                + "@media screen and (max-width: 800px) {\n"
                + "  .leftcolumn, .rightcolumn {   \n"
                + "    width: 100%;\n"
                + "    padding: 0;\n"
                + "  }\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"header\">\n"
                + "  <h2>FIXIT</h2>\n"
                + "</div>\n"
                + "\n"
                + "<div class=\"row\">\n"
                + "  <div class=\"\">\n"
                + "    <div class=\"card\">\n"
                + "      <h2>" + message.get("Title") + "</h2>\n"
                + "      <h5>" + message.get("CreatedAt") + "</h5>\n"
                + "      \n"
                + "      <p>" + message.get("Description") + "</p>\n"
                + "      <p>" + message.get("Content") + "</p>\n"
                + "    </div>\n"
                + "    \n"
                + "  </div>\n"
                + "  \n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        //
        msg.setSubject(subject);
        msg.setContent(messageBody, "test/html ; charset=utf-8");
        msg.setText(messageBody, null, "html");
        msg.saveChanges();
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();

    }
}
