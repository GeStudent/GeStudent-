/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import java.net.MalformedURLException;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author ASUS
 */
public class UploadLivreServices {
      public String upload(String Path) {
        String fileNameInServer = "";

        FileUploader fu = new FileUploader("localhost/GeStudent/web/Uploads/Library");

        try {
            //Upload
            
            fileNameInServer = fu.upload(Path);
        } catch (MalformedURLException ex) {
            System.out.println("mal:"+ex.getMessage());
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(fileNameInServer);

        return fileNameInServer;

    }

    public static void main(String[] args) {
        UploadLivreServices us = new UploadLivreServices();
        System.out.println(us.upload("C:/Users/ASUS/Desktop/pipo.jpg"));

    }
    
}
