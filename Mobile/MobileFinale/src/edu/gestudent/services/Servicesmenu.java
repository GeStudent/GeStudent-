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
import edu.gestudent.entities.menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Servicesmenu {
    public ArrayList<menu> menu;
    public  String result = "";
    public static Servicesmenu instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Servicesmenu() {
         req = new ConnectionRequest();
    }

    public static Servicesmenu getInstance() {
        if (instance == null) {
            instance = new Servicesmenu();
        }
        return instance;
    }
    public ArrayList<menu> parseMenu(String jsonText){
        try {
            menu = new ArrayList<>();
            JSONParser jsonParser = new JSONParser();
            Map<String,Object> tasksListJson = jsonParser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                System.err.println(obj);
                menu l = new menu();
                float id = Float.parseFloat(obj.get("id").toString());
                  l.setId_menu((int)id);
                  l.setName(obj.get("name").toString());
                  l.setDescription(obj.get("description").toString());
                menu.add(l);
            }  
        } catch (IOException ex) {
            
        }
       
        return menu;
    }
     public ArrayList<menu> getAllMenu(){
        String url = "http://localhost/GeStudent/web/app_dev.php/Back/ListMenuMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                menu = parseMenu(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);     
        return menu;
    }
}

