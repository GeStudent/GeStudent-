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
 * @author Ayadi
 */
public class UploadEventServices {

    public String uploadEvent(String Path) {
        String fileNameInServer = "";

        FileUploader fu = new FileUploader("localhost/GeStudent/web/img");
        
       try {
            //Upload
            fileNameInServer = fu.upload(Path);
        } catch (MalformedURLException ex) {

        } catch (java.io.IOException ex) {
        }
        System.out.println(fileNameInServer);

        return fileNameInServer;

    }

    public static void main(String[] args) {
        UploadEventServices us = new UploadEventServices();
        System.out.println(us.uploadEvent("C:/Users/user/Desktop/load.png"));

    }
    

}
