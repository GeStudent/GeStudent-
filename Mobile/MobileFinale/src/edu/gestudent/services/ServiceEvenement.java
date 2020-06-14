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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import edu.gestudent.entities.Evenement;
import edu.gestudent.entities.user;
import edu.gestudent.utils.Statics;
import java.util.Date;
import java.util.List;


/**
 *
 * @author user
 */
public class ServiceEvenement {
    
    
    
    
    
    
    public ArrayList<Evenement> Evenements;
    public  String  result="";
        public ArrayList<user> EventMembers;

    public static ServiceEvenement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenement() {
         req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
    
    
    
    
    
    
    
    
  public ArrayList<Evenement> parseEvenements(String jsonText){
        try {
            Evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                  e.setId((int)id);
                  e.setNom(obj.get("nom").toString());
                  e.setPhoto(obj.get("photo").toString());
                  e.setDescription(obj.get("description").toString());
                  e.setDatedebut(obj.get("datedebut").toString());
                  e.setDecision(obj.get("decision").toString());


                  float nombreplaces = Float.parseFloat(obj.get("nombreplaces").toString());
                  e.setNombreplaces((int)nombreplaces);
                  float etat = Float.parseFloat(obj.get("etat").toString());
                     float idmanager = Float.parseFloat(obj.get("idmanager").toString());
                  e.setIdmanager((int)idmanager);


                  e.setEtat((int)etat);

                 
                Evenements.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return Evenements;
    }  
    
    
    
    
    
    
    
    
    public ArrayList<Evenement> getAllEvenements(){
        String url = Statics.BASE_URL+"ListEventMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenements;
    }
    
    
    
    public boolean AddEvent(Evenement e) {
        String url = Statics.BASE_URL + "AddEventMobile/?nom=" + e.getNom() + "&description=" + e.getDescription() + "&photo=" + e.getPhoto() + "&datedebut=" + e.getDatedebut() + "&nombreplaces=" + e.getNombreplaces() + "&etat=" + e.getEtat() + "&decision=" + e.getDecision()+ "&idmanager=" + e.getIdmanager(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String DeleteEvenement(Evenement e) {
          String url = Statics.BASE_URL + "deleteEventMobile/?id=" + e.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
                   
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    String data = new String(req.getResponseData());
                    JSONParser j = new JSONParser();
                    Map<String, Object> tasksListJson;
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                   result=(String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
   
    
    
    
    
    
    
    
    
    
    public boolean EditEvenement(Evenement e) {
        String url = Statics.BASE_URL + "EditEventMobile/?id="+e.getId()+"&nom=" + e.getNom() + "&etat=" + e.getEtat() + "&nombreplaces=" + e.getNombreplaces(); //création de l'URL
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    
     public boolean ParticipateEvent(Evenement e, user u) {
        String url = Statics.BASE_URL + "ParticipateEventMobile/?id=" + e.getId()+ "&idUser=" + u.getId(); //création de l'URL
         System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
      
//   public boolean AcceptEvent(Evenement e, user u) {
//        String url = Statics.BASE_URL + "AcceptEventMobile/?id=" + e.getId()+ "&idUser=" + u.getId(); //création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }  
//      public boolean QuitEvent(Evenement e, user u) {
//        String url = Statics.BASE_URL + "QuitEventMobile/?id=" + e.getId()+ "&idUser=" + u.getId(); //création de l'URL
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
      
      
      
//       public ArrayList<user> parseEvents(String jsonText) {
//        try {
//            EventMembers = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//
//            for (Map<String, Object> obj : list) {
//                //Création des tâches et récupération de leurs données
//                user u = new user();
//                float id = Float.parseFloat(obj.get("id").toString());
//                u.setId((int) id);
//                u.setFirstname(obj.get("post").toString());
//                u.setFirstname(obj.get("firstname").toString());
//                u.setLastname(obj.get("lastname").toString());
//                u.setImage(obj.get("image").toString());
//                u.setEmail(obj.get("email").toString());
//              
//
//                EventMembers.add(u);
//            }
//
//        } catch (IOException ex) {
//            ex.getMessage();
//        }
//
//        return EventMembers;
//    }
       
//         public ArrayList<user> getAllMembers(Evenement e) {
//        String url = Statics.BASE_URL + "Evenement/MyParticipationMobile/"+e.getId();
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                EventMembers = parseEvents(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return EventMembers;
//    }
          
      
     
}
