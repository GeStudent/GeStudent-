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
import edu.gestudent.entities.meal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Servicesmeal {
    public ArrayList<meal> meal;
    public  String result = "";
    public static Servicesmeal instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Servicesmeal() {
         req = new ConnectionRequest();
    }

    public static Servicesmeal getInstance() {
        if (instance == null) {
            instance = new Servicesmeal();
        }
        return instance;
    }
    public ArrayList<meal> parsemeal(String jsonText){
        try {
            meal = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();
            Map<String,Object> tasksListJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                System.err.println(obj);
                meal l = new meal();
                float id = Float.parseFloat(obj.get("id").toString());
                  l.setId_meal((int)id);
                  l.setImage(obj.get("image").toString());
                  l.setName(obj.get("name").toString());
                  l.setType(obj.get("type").toString());
                  float rating = Float.parseFloat(obj.get("rating").toString());
                  l.setRating((int) rating);
                meal.add(l);
            }  
        } catch (IOException ex) {
            
        }
       
        return meal;
    }
     public ArrayList<meal> getAllMeal(){
        String url = "http://localhost/GeStudent/web/app_dev.php/Back/ListMealMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                meal = parsemeal(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
        return meal;
    }
}

