/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;

/**
 *
 * @author Ayadi
 */
public class student extends Form {

    static Form current;
    user User=Session.getCurrentSession();
    public student(Form previous, Resources theme) {
        current = this; //Récupérsation de l'interface(Form) en cours

        setTitle("Student");
        setLayout(BoxLayout.y());

        getToolbar().addCommandToSideMenu("Home", null, ev -> {

        }
        );
        getToolbar().addCommandToSideMenu("Club", null, ev -> {
            new Clubgui(current, theme).show();

        });
        
        getToolbar().addCommandToSideMenu("Second", null, ev -> {
            new second(current, theme).showBack();

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
