/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.inscriRest;
import edu.gestudent.entities.meal;
import edu.gestudent.entities.menu;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesInscriRest;
import edu.gestudent.services.Servicesmeal;
import edu.gestudent.services.Servicesmenu;

/**
 *
 * @author Ayadi
 */
public class Restaurant extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    user User = Session.getCurrentSession();

    public Restaurant(Form previous, Resources theme) {
        currentForm = this;
        setTitle("Restaurant");
        setLayout(BoxLayout.y());

        currentForm = this;
        currentForm.setTitle("Restaurant");
        currentForm.setLayout(BoxLayout.y());
        Container y = new Container(BoxLayout.y());
        Button menu = new Button("Menu");
        Button meal = new Button("Meal");
        y.add(menu).add(meal);

        meal.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            Form ex = new Form("Meal", new FlowLayout());
            for (meal l : Servicesmeal.getInstance().getAllMeal()) {
                Container InfoContainer = new Container(BoxLayout.y());
                Label nom = new Label(l.getName());
                Label imge = new Label(l.getImage());
                Label type = new Label(String.valueOf(l.getType()));
                Label rating = new Label(String.valueOf(l.getRating()));
                Container xx = new Container(BoxLayout.x());
                Container x = new Container(BoxLayout.x());
                Label name = new Label("NAME:");
                Label TYP = new Label("TYPE:");

                xx.add(name).add(nom);
                x.add(TYP).add(type);
                InfoContainer.add(xx).add(x);

                Container Container = new Container(BoxLayout.y());

                placeHolder = EncodedImage.createFromImage(theme.getImage("bla.jpg"), false);
                String url = "http://localhost/Images/uploads/" + l.getImage();
                ConnectionRequest connection = new ConnectionRequest();
                connection.setUrl(url);
                URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);
                int width = (int) (imgurl.getWidth() * 1.5);
                int Height = (int) (imgurl.getHeight() * 1.5);
                ImageViewer img = new ImageViewer(imgurl.scaled(width, Height));
                Container.add(img);
                Container.add(InfoContainer);
                ex.add(Container);
                img.addPointerReleasedListener(ev -> {

                    MealDetail(l, theme).show();
                });
                ex.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt1) -> {
                    currentForm.showBack();
                });
                ex.show();

            }
        });
        menu.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
              Form ex = new Form("Meal", new FlowLayout());
              // ComboBox cb = new ComboBox();
            for (menu l : Servicesmenu.getInstance().getAllMenu())  {     
//                 cb.addItem(l.getName());
//                  ex.add(cb);
                Container InfoContainer = new Container(BoxLayout.y());
         // cb.addItem(l.getName());
                Label nom = new Label(l.getName());
                Label des = new Label(l.getDescription());
                Container xx = new Container(BoxLayout.x());
                Container x = new Container(BoxLayout.x());
                Label name = new Label("MENU NAME:");
                Label TYP = new Label("descri:");
                xx.add(name).add(nom);
             //   x.add(TYP).add(des);
                InfoContainer.add(xx).add(x);
                Container Container = new Container(BoxLayout.y());
                Container.add(InfoContainer);
                ex.add(Container);
                nom.addPointerReleasedListener(ev -> {
                    MenuDetail(l, theme).show();
                });
                ex.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt1) -> {
                    currentForm.showBack();
                });
                ex.show();

            }
          
        });
        
        
        getToolbar().addCommandToOverflowMenu("Register", null, ev -> {
            inscrire().show();
        });
        getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
        currentForm.add(y);
        currentForm.show();
//

    }

    public Form MealDetail(meal l, Resources theme) {

        Form MealDetail = new Form(l.getName(), BoxLayout.y());
        String url = "http://localhost/Images/uploads/" + l.getImage();
        System.out.println(url);
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label Name = new Label("Name:");
        Label type = new Label("Type");
        Label rating = new Label("Rating:");

        TextField NameField = new TextField(null, "Name");
        NameField.setText(l.getName());
        TextField typeField = new TextField(null, "Type");
        typeField.setText(l.getType());
        TextField ratingField = new TextField(null, "Rating");
        ratingField.setText(String.valueOf(l.getRating()));
        Container Container = new Container(new FlowLayout());
        Container.addAll(Name, NameField, type, typeField, rating, ratingField);
        MealDetail.add(img);
        MealDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());
        MealDetail.add(ButtonsContainer);
        MealDetail.revalidate();

        MealDetail.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            currentForm.showBack();
        });
        return MealDetail;
    }

    public Form MenuDetail(menu l, Resources theme) {

        Form MenuDetail = new Form(l.getName(), BoxLayout.y());
        Label Name = new Label("Name:");
        Label des = new Label("Description");
        TextField NameField = new TextField(null, "Name");
        NameField.setText(l.getName());
        TextField desField = new TextField(null, "Description");
        desField.setText(l.getDescription());
        Container Container = new Container(new FlowLayout());
        Container.addAll(Name, NameField, des, desField);
        MenuDetail.add(Container);
        Container ButtonsContainer = new Container(new FlowLayout());
        MenuDetail.add(ButtonsContainer);
        MenuDetail.revalidate();
        MenuDetail.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            currentForm.showBack();
        });
        return MenuDetail;
    }

    public Form inscrire() {

        Form inscrire = new Form("Registration", new FlowLayout(Component.CENTER, Component.CENTER));

        Container x = new Container(BoxLayout.x());
        Container xx = new Container(BoxLayout.x());
        Label duration = new Label("Duration:");
        Label amount = new Label("Amount");
        TextField amountField = new TextField(null, "Amount");

        String[] characters = {"Month", "Semester", "Year"};

        ComboBox durationField = new ComboBox();
        for (int i = 0; i < characters.length; i++) {
            durationField.addItem(characters[i]);
        }
        durationField.addActionListener(l -> {
            switch (durationField.getSelectedItem().toString()) {
                case "Month":
                    System.out.println("Month");

                    amountField.setText(Double.toString(60));

                    break;
                case "Semester":
                    System.out.println("Semester");
                    amountField.setText(Double.toString(140));

                    break;
                case "Year":
                    amountField.setText(Double.toString(250));

                    System.out.println("Year");

                    break;
                default:
                    System.out.println("select duration");
                    break;

            }
        });
        x.add(duration).add(durationField);
        xx.add(amount).add(amountField);
        Container Container = new Container(new FlowLayout());
        Container.addAll(x, xx);

        inscrire.add(Container);
        Container ButtonsContainer = new Container(new FlowLayout());
        Button buttonSave = new Button("Register");
        Button modify = new Button("modify");
        ButtonsContainer.add(buttonSave);
        ButtonsContainer.add(modify);
        inscrire.add(ButtonsContainer);
        inscrire.revalidate();
        inscrire.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            currentForm.showBack();
        });
        inscrire.getToolbar().addCommandToOverflowMenu("Delete", null, ev -> {

//
            inscriRest inscri = new inscriRest();
            inscri.setId_user(User.getId());
            //String deleteInscri = ServicesInscriRest.getInstance().Deleteinscri(inscri);
            // System.out.println(deleteInscri);
            if (inscri.getId_user() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! ", new Command("OK"));
            } else {
                String result = ServicesInscriRest.getInstance().Deleteinscri(inscri);

            }
        });
        buttonSave.addActionListener((evt) -> {
            inscriRest inscri = new inscriRest();
            inscri.setAmount(Double.valueOf(amountField.getText()));
            inscri.setDuration((String) durationField.getSelectedItem());
            inscri.setId_user(User.getId());
            boolean AddInscri = ServicesInscriRest.getInstance().AddInscri(inscri);
            if (AddInscri) {
                currentForm.showBack();
            }
        });
        modify.addActionListener((evt) -> {
            inscriRest inscri = new inscriRest();
            inscri.setAmount(Double.valueOf(amountField.getText()));
            inscri.setDuration((String) durationField.getSelectedItem());
            inscri.setId_user(User.getId());
            boolean editInscri = ServicesInscriRest.getInstance().EditInscri(inscri);
            if (editInscri) {
                currentForm.showBack();
            }
        });

        return inscrire;
    }

}
