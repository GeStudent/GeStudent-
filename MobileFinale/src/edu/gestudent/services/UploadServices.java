/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.gestudent.entities.Session;

import java.net.MalformedURLException;

import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Ayadi
 */
public class UploadServices {

    public String uploadImage(String Path) {
        String fileNameInServer = "";

        FileUploader fu = new FileUploader("localhost/images");

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

        UploadServices us = new UploadServices();
        System.out.println(us.uploadImage("C:/Users/ASUS/Desktop/pipo.jpg"));

    }
}
