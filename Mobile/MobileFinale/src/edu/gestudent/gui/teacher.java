/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import static edu.gestudent.gui.student.current;

/**
 *
 * @author Ayadi
 */
public class teacher extends Form {

    private EncodedImage placeHolder;
    static Form current;

    user User = Session.getCurrentSession();

    public teacher(Form previous, Resources theme) {
        current = this; //Récupérsation de l'interface(Form) en cours
        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/Images/uploads/" + User.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label name = new Label("Name: " + User.getFirstname() + " " + User.getLastname());
        Label Email = new Label("Email: " + User.getEmail());
        Label gender = new Label("gender: " + User.getGender());
        Label Role = new Label("Status : " + User.getRoles());
        addAll(img, name, Email, gender, Role);

        setTitle("teacher");

        setLayout(BoxLayout.y());

        getToolbar().addCommandToSideMenu("Home", null, ev -> {

        }
        );
        
         getToolbar().addCommandToSideMenu("Library", null, ev -> {
            new LivreTeacher(current, com.codename1.ui.util.Resources.getGlobalResources()).showBack();

        });

        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, ev -> {
            try {
                Session.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
            previous.showBack();

        });

    }

}
