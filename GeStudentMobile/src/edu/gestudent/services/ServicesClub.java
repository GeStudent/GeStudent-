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
import edu.gestudent.entities.Club;
import edu.gestudent.entities.user;
import edu.gestudent.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ayadi
 */
public class ServicesClub {

    public ArrayList<Club> Clubs;
    public ArrayList<user> ClubMembers;
    public ArrayList<user> ClubInvitations;

    public static ServicesClub instance = null;
    public boolean resultOK = false;
    public String result = "";
    private ConnectionRequest req;

    private ServicesClub() {
        req = new ConnectionRequest();
    }

    public static ServicesClub getInstance() {
        if (instance == null) {
            instance = new ServicesClub();
        }
        return instance;
    }

    public boolean AddClub(Club c) {
        String url = Statics.BASE_URL + "Club/AddClubMobile/?nom=" + c.getNom() + "&email=" + c.getEmail() + "&image=" + c.getImage() + "&tel=" + c.getTel() + "&message=" + c.getDescription() + "&owner=" + c.getId_president(); //création de l'URL
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

    public boolean EditClub(Club c) {
        String url = Statics.BASE_URL + "Club/EditClubMobile/?id=" + c.getId() + "&nom=" + c.getNom() + "&email=" + c.getEmail() + "&tel=" + c.getTel() + "&message=" + c.getDescription(); //création de l'URL
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

    public String DeleteClub(Club c) {
        String url = Statics.BASE_URL + "Club/deleteClubMobile/?id=" + c.getId();
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
                    result = (String) tasksListJson.get("body");

                } catch (IOException ex) {
                    ex.getMessage();
                }
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    public ArrayList<Club> parseClubs(String jsonText) {
        try {
            Clubs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Club c = new Club();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setNom(obj.get("nom").toString());
                c.setEmail(obj.get("email").toString());
                c.setImage(obj.get("image").toString());
                String date = obj.get("date").toString();
                String value = date.substring(6, 16);
                c.setDate(value);

                c.setDescription(obj.get("description").toString());
                float idPresident = Float.parseFloat(obj.get("idPresident").toString());
                c.setId_president((int) idPresident);
                float tel = Float.parseFloat(obj.get("tel").toString());
                c.setTel((int) tel);
                float nombrePlace = Float.parseFloat(obj.get("nombrePlace").toString());
                c.setNombreplace((int) nombrePlace);

                Clubs.add(c);
            }

        } catch (IOException ex) {

        }

        return Clubs;
    }

    public ArrayList<Club> getAllClubs() {
        String url = Statics.BASE_URL + "Club/ListClubMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Clubs = parseClubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Clubs;
    }

    public boolean isClubLeader(user User) {
        String url = Statics.BASE_URL + "Club/isClubLeader/" + User.getId(); //création de l'URL
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

    public ArrayList<user> parseMembers(String jsonText) {
        try {
            ClubMembers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                user u = new user();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setPostClub(obj.get("post").toString());
                u.setFirstname(obj.get("firstname").toString());
                u.setLastname(obj.get("lastname").toString());
                u.setImage(obj.get("image").toString());
                u.setEmail(obj.get("email").toString());
                u.setQrCodeClub(obj.get("qrcode").toString());

                ClubMembers.add(u);
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

        return ClubMembers;
    }

    public ArrayList<user> parseInvitaions(String jsonText) {

        try {
            ClubInvitations = new ArrayList<>();
            CharArrayReader charArrayReader = new CharArrayReader(jsonText.toCharArray());
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(charArrayReader);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            if (list != null) {
                for (Map<String, Object> obj : list) {
                    //Création des tâches et récupération de leurs données
                    user u = new user();
                    float id = Float.parseFloat(obj.get("id").toString());
                    u.setId((int) id);
                    u.setPostClub(obj.get("post").toString());
                    u.setFirstname(obj.get("firstname").toString());
                    u.setLastname(obj.get("lastname").toString());
                    u.setImage(obj.get("image").toString());
                    u.setEmail(obj.get("email").toString());

                    ClubInvitations.add(u);
                }
            }

        } catch (IOException ex) {
            ex.getMessage();
        }

        return ClubInvitations;
    }

    public ArrayList<user> getAllMembers(Club c) {
        String url = Statics.BASE_URL + "Club/ClubMembersMobile/" + c.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ClubMembers = parseMembers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ClubMembers;
    }

    public boolean JoinClub(Club c, user u) {
        String url = Statics.BASE_URL + "Club/JoinClubMobile/?idClub=" + c.getId() + "&iduser=" + u.getId(); //création de l'URL
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

    public boolean AccpetInviteClub(Club c, user u) {
        String url = Statics.BASE_URL + "Club/AccpetInviteMobile/?idClub=" + c.getId() + "&id=" + u.getId(); //création de l'URL
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

    public ArrayList<user> getAllInvitations(Club c) {
        String url = Statics.BASE_URL + "Club/ClubInvitationsMobile/?idClub=" + c.getId();
        req.setUrl(url);
        req.setPost(false);
        String name = new String(req.getResponseData());
        if (name.contains("Liste Vide")) 
            return null;
      

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (name.contains("Liste Vide")) 
                     return ;
                ClubInvitations = parseInvitaions(name);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ClubInvitations;
    }
    
        public boolean DeclineInviteClub(Club c, user u) {
        String url = Statics.BASE_URL + "Club/DeleteInviteMobile/?idClub=" + c.getId() + "&id=" + u.getId(); //création de l'URL
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
        
        public boolean PromoteMemberClub(Club c, user u,String post) {
        String url = Statics.BASE_URL + "Club/PromoteMobile/?idClub=" + c.getId() + "&id=" + u.getId()+"&post="+post; //création de l'URL
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
        
        public boolean KickMemberClub(Club c, user u) {
        String url = Statics.BASE_URL + "Club/KickMemberMobile/?idClub=" + c.getId() + "&id=" + u.getId(); //création de l'URL
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
        
        
        public String ClubPost(Club c,user User) {
        String url = Statics.BASE_URL + "Club/ClubuserPostMobile/?idClub="+c.getId()+"&id=" + User.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                String data = new String(req.getResponseData());
                JSONParser j = new JSONParser();
                Map<String, Object> tasksListJson;
                try {
                    tasksListJson = j.parseJSON(new CharArrayReader(data.toCharArray()));
                    result = (String) tasksListJson.get("post");
                    System.out.println(result);
                } catch (IOException ex) {
                    ex.getMessage();
                }

                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

}
