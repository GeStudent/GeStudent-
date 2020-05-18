/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.facebook.User;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.SplashForm;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesUsers;

/**
 *
 * @author Ayadi
 */
public class Login extends Form {

    Form current;
    private static user User;

    public Login(Resources theme) {

        current = this; //Récupérsation de l'interface(Form) en cours
        setTitle("GeStudent");
        
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());

        setLayout(new FlowLayout(Component.CENTER, Component.CENTER));
        ImageViewer imageName = new ImageViewer(theme.getImage("gestudent.png"));
        Container cnt = new Container(BoxLayout.y());

        TextField username = new TextField(null, "username");
        TextField password = new TextField(null, "password");

        password.setConstraint(TextField.PASSWORD);

        Button login = new Button("login");

        cnt.add(imageName);
        cnt.add(username);
        cnt.add(password);
        cnt.add(login);
        add(cnt);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                {

                    User = ServicesUsers.getInstance().Login(username.getText(), password.getText());
                    if (User != null) {

                        username.setText("");
                        password.setText("");
                        Session.start(User);

                        if (User.getRoles().equals("student")) {
                            new student(current, theme).show();
                        } else {
                            new teacher(current, theme).show();

                        }
                    } else {
                        Dialog.show("Alert", "Invalid password or username", "OK", null);
                    }

                }

            }
        });

    }

}