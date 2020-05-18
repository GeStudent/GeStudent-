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
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicesEmprunt {

    public ArrayList<Emprunt> Emprunts;
    public ArrayList<Emprunt> StatLivres;
    public String result = "";
    public static ServicesEmprunt instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicesEmprunt() {
        req = new ConnectionRequest();
    }

    public static ServicesEmprunt getInstance() {
        if (instance == null) {
            instance = new ServicesEmprunt();
        }
        return instance;
    }

    public boolean Emprunter(Emprunt e) {
        String url = Statics.BASE_URL + "EmpruntLivreMobile/?iduser=" + e.getId_user() + "&idlivre=" + e.getId_livre() + "&Dateretour=" + e.getD_retour() + "&Dateemprunt=" + e.getD_emprunt(); //création de l'URL
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

    public boolean ReturnBook(Emprunt e) {
        String url = Statics.BASE_URL + "RetournerLivreMobile/?iduser=" + e.getId_user() + "&idlivre=" + e.getId_livre(); //création de l'URL
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

    public boolean isBorrowed(Emprunt e) {
        String url = Statics.BASE_URL + "isBorowed/?iduser=" + e.getId_user() + "&idlivre=" + e.getId_livre(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String data = new String(req.getResponseData());
                JSONParser j = new JSONParser();
                Map<String, Object> tasksListJson;
                try {
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("boolean");
                    System.out.println(result);
                } catch (IOException ex) {
                    ex.getMessage();
                }

                resultOK = result.equals("true");
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Emprunt> parseStat(String jsonText) {
        try {
            StatLivres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Emprunt e = new Emprunt();

                e.setName(obj.get("name").toString());
                float quantite = Float.parseFloat(obj.get("nbr").toString());
                e.setNombre((int) quantite);
                StatLivres.add(e);
            }

        } catch (IOException ex) {

        }

        return StatLivres;
    }

    public ArrayList<Emprunt> getAllStat() {
        String url = Statics.BASE_URL + "StatLivreMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                StatLivres = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return StatLivres;
    }

    public ArrayList<Emprunt> parseEmprunt(String jsonText) {
        try {
            Emprunts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Emprunt e = new Emprunt();

                e.setName(obj.get("titrelivre").toString());
                e.setImage(obj.get("image").toString());
                e.setBookName(obj.get("titrelivre").toString());
                float phoneF = Float.parseFloat(obj.get("phone").toString().substring(0,9));
                String tel =obj.get("phone").toString().substring(0,9);
                String phonenumber="";
                for (int i=0;i<tel.length();i++)
                {
                    if (tel.charAt(i)!='.')
                        phonenumber=phonenumber+tel.charAt(i);
                        
                }
                   int phone = Integer.parseInt(phonenumber);
                   e.setPhone(phone);
                System.out.println("phone:"+phone);
                e.setFirstname(obj.get("firstname").toString());
                e.setLastname(obj.get("lastname").toString());
                e.setD_emprunt(obj.get("dateEmprunt").toString().substring(6, 16));
                e.setD_retour(obj.get("dateRetour").toString().substring(6, 16));

                Emprunts.add(e);
            }

        } catch (IOException ex) {

        }

        return Emprunts;
    }

    public ArrayList<Emprunt> getAllEmprunts() {
        String url = Statics.BASE_URL + "LivreEmprunterMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Emprunts = parseEmprunt(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Emprunts;
    }

}
