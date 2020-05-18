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
import com.codename1.ui.events.ActionListener;
import java.util.List;
import edu.gestudent.entities.Livre;
import edu.gestudent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicesLivres {
     public ArrayList<Livre> Livres;
    public  String  result="";
    public static ServicesLivres instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesLivres() {
         req = new ConnectionRequest();
    }

    public static ServicesLivres getInstance() {
        if (instance == null) {
            instance = new ServicesLivres();
        }
        return instance;
    }
    public ArrayList<Livre> parseLivres(String jsonText){
        try {
            Livres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Livre l = new Livre();
                float id_livre = Float.parseFloat(obj.get("id_livre").toString());
                  l.setId_livre((int) id_livre);
                  l.setName(obj.get("name").toString());
                  l.setImage(obj.get("image").toString());
                  l.setAuthor(obj.get("author").toString());
                  l.setUrl(obj.get("url").toString());
                  l.setCategorie(obj.get("categorie").toString());
                  float quantite = Float.parseFloat(obj.get("quantite").toString());
                  l.setQuantite((int)quantite);

                 
                 
                Livres.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
       
        return Livres;
    }
     public ArrayList<Livre> getAllLivres(){
        String url = Statics.BASE_URL+"ListLivreMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Livres = parseLivres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
                return Livres;
    }
     public boolean AddLivre(Livre l) {
        String url = Statics.BASE_URL + "AddLivreMobile/?name=" + l.getName() + "&image=" + l.getImage() + "&author="+ l.getAuthor() + "&url=" + l.getUrl() + "&categorie=" + l.getCategorie() + "&quantite=" + l.getQuantite(); //création de l'URL
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
     public String DeleteLivre(Livre l) {
          String url = Statics.BASE_URL + "deleteLivreMobile/?id_livre=" + l.getId_livre();
       
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
                   result =(String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
     public boolean EditClub(Livre l) {
        String url = Statics.BASE_URL + "EditLivreMobile/?id_livre="+l.getId_livre()+"&name=" + l.getName() + "&author=" + l.getAuthor() + "&url=" + l.getUrl() + "&categorie=" +l.getCategorie()+ "&quantite="+l.getQuantite(); //création de l'URL
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
}
