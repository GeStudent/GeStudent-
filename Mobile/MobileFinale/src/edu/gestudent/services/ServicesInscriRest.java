/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import edu.gestudent.entities.inscriRest;
import edu.gestudent.entities.user;
import edu.gestudent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServicesInscriRest {

  //  private static StructuredContent hashtable;

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    
    public ArrayList<inscriRest> inscri;
    public ArrayList<user> userMembers;
  
    public static ServicesInscriRest instance = null;
    public boolean resultOK = false;
    public String result = "";
    
    private ConnectionRequest req;
     private ConnectionRequest req1;

    private ServicesInscriRest() {
        req = new ConnectionRequest();
        req1=new ConnectionRequest();
    }

    public static ServicesInscriRest getInstance() {
        if (instance == null) {
            instance = new ServicesInscriRest();
        }
        return instance;
    }

    public boolean AddInscri(inscriRest c) {
        String url = "http://localhost/GeStudent/web/app_dev.php/Back/InscrireMobile";
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
        String id_user = String.valueOf(c.getId_user());
        String amount = String.valueOf(c.getAmount());
        
        HashMap hashtable = new HashMap();
        hashtable.put("id_user", id_user);
        hashtable.put("amount", amount);
        hashtable.put("duration", c.getDuration());

        final String payload = Result.fromContent(hashtable).toString();
       System.out.println(payload);

        req.setRequestBody(payload);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               // String response = req.getResponseErrorMessage();
                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("errors");
                    System.out.println(req.getResponseCode());
                    resultOK = req.getResponseCode() == 200;
               
                    if (resultOK == false) {
                        Dialog.show("ERROR", result, new Command("OK"));
                    } else {
                        Dialog.show("MESSAGE", "Registration was successfully completed", new Command("OK"));
                    }

                } catch (IOException ex) {
                    ex.getMessage();
                }
              
               // String data = req.getResponseContentType();

                //  byte[] data = req.getResponseData();
                //System.out.println(data);
                // Dialog.show("ERROR", data[0], new Command("OK")); 
                //System.out.println(resultOK);                
//Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        //pour envoyer le web service
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean EditInscri(inscriRest c) {
 
        String url = Statics.BASE_URL + "Back/EditInscriMobile/?id=" + c.getId_user() + "&duration=" + c.getDuration() + "&amount=" + c.getAmount(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //recuperer l'erreur
                 String response = req.getResponseErrorMessage();
                resultOK= true;
                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("title");
                    String message = (String) tasksListJson.get("message");
                    
                    System.out.println(result);
                    if (result == "succes") //resultOK = req.getResponseCode() == 200;
                    {
                        Dialog.show("MESSAGE", message, new Command("OK"));
                    }
                    else {
                      Dialog.show("Message", message, new Command("OK"));

                    }
                   

                } catch (IOException ex) {
                    ex.getMessage();
                }
              

                String data = req.getResponseContentType();

              
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    

    public String Deleteinscri(inscriRest c) {
        String url = Statics.BASE_URL + "Back/deleteInscriMobile";
        req.setPost(true);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        //System.out.println(url);

        HashMap hashtabledel = new HashMap();
        hashtabledel.put("id_user", c.getId_user());
        System.out.println(c.getId_user());

        final String payload = Result.fromContent(hashtabledel).toString();

        req.setRequestBody(payload);
        System.out.println(payload);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               // String response = req.getResponseErrorMessage();
                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("title");
                    String message = (String) tasksListJson.get("message");
                    
                    System.out.println(result);
                    if (result == "erreur") {
                        String erreur = (String) tasksListJson.get("errors");
                        Dialog.show("ERROR", erreur, new Command("OK"));
                    } else if (result == "succes") //resultOK = req.getResponseCode() == 200;
                    {
                        Dialog.show("MESSAGE", message, new Command("OK"));
                    }
                    else {
                      Dialog.show("MESSAGE", message, new Command("OK"));

                    }
                    //else {Dialog.show("ERROR", "erreur non indentifiee ", new Command("OK"));}

                } catch (IOException ex) {
                    ex.getMessage();
                }
                //System.out.println(response);

             //   String data = req.getResponseContentType();

                
                req.removeResponseListener(this); //Supprimer cet actionListener
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}