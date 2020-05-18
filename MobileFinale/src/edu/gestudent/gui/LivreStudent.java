/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import edu.gestudent.charts.LivrePieChart;
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesEmprunt;
import edu.gestudent.services.ServicesLivres;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class LivreStudent extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    user User = Session.getCurrentSession();

    String d_emprunt = "";
    String d_retour = "";
   
    
    



    public LivreStudent(Form previous, Resources theme) {
        currentForm = this;
        currentForm.setTitle("Book");

        currentForm.setLayout(BoxLayout.y());

        for (Livre l : ServicesLivres.getInstance().getAllLivres()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label nomLivre = new Label("Name: " + l.getName());
            Label authorLivre = new Label("Author: " + String.valueOf(l.getAuthor()));
            Label urlLivre = new Label("Description: " + l.getUrl());
            Label categorieLivre = new Label("Categorie: " + l.getCategorie());
            Label quantiteLivre = new Label("Quantity: " + String.valueOf(l.getQuantite()));
            InfoContainer.add(nomLivre);
            InfoContainer.add(authorLivre);
            InfoContainer.add(urlLivre);
            InfoContainer.add(categorieLivre);
            InfoContainer.add(quantiteLivre);
            Container Container = new Container(BoxLayout.x());

            placeHolder = EncodedImage.createFromImage(theme.getImage("bla.jpg"), true);
            String url = "http://localhost/GeStudent/web/Uploads/Library/photo/" + l.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);
            int width = (int) (imgurl.getWidth() / 1.5);
            int Height = (int) (imgurl.getHeight() / 1.5);
            ImageViewer img = new ImageViewer(imgurl.scaled(width, Height));
            Container.add(img);
            Container.add(InfoContainer);
            currentForm.add(Container);

            img.addPointerReleasedListener(ev -> {
                LivreDetail(l, theme).show();
            });
        }

        currentForm.getToolbar().addCommandToOverflowMenu("BOOK STAT", null, ev -> {
            StatLivre(theme).show();
        });
        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }

    public Form AddLivre() {

        Form AddLivre = new Form("ADD", new FlowLayout(Component.CENTER, Component.CENTER));
        AddLivre.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            currentForm.showBack();
        });

        return AddLivre;
    }

    public Form LivreDetail(Livre l, Resources theme) {

        Form LivreDetail = new Form(l.getName(), BoxLayout.y());
        Emprunt e = new Emprunt();
        e.setId_user(User.getId());
        e.setId_livre(l.getId_livre());

        placeHolder = EncodedImage.createFromImage(theme.getImage("bla.jpg"), true);
        String url = "http://localhost/GeStudent/web/Uploads/Library/photo/" + l.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        SpanLabel Description = new SpanLabel("Name: " + l.getName() + "\n" + "Author: " + l.getAuthor() + "\n" + "Descriptions: " + l.getUrl() + "\n" + "Quantite: " + l.getQuantite() + "\n");

        Label DateEmprunt = new Label("Borrow Date:");
        Label DateRetour = new Label("Return Date:");
        Picker DateE = new Picker();
        Picker Dater = new Picker();
        Button Borrow = new Button("Borrow");

        LivreDetail.add(img);
        LivreDetail.add(Description);
        LivreDetail.addAll(DateEmprunt, DateE, DateRetour, Dater, Borrow);

        LivreDetail.revalidate();
        Borrow.addActionListener(ev -> {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

            d_emprunt = dateformat.format(DateE.getDate());
            d_retour = dateformat.format(Dater.getDate());

            if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "ANNULER")) {
                Date date = new Date();

                e.setD_emprunt(d_emprunt);
                e.setD_retour(d_retour);
                if (ServicesEmprunt.getInstance().isBorrowed(e)) {
                    Dialog.show("Error", "You already borrowed this book ! ", "OK", null);

                } else if (l.getQuantite() <= 0) {
                    Dialog.show("Error", "We don't have enough books", "OK", null);

                } else if (Dater.getDate().before(DateE.getDate()) || DateE.getDate().before(date)) {

                    Dialog.show("Error", "The date is invalid", "OK", null);

                } else {

                    if (ServicesEmprunt.getInstance().Emprunter(e)) {
                        Dialog.show("Information", "The book is borrowed ", "OK", null);
                        new LivreStudent(student.current, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            } else {

            }

        });
        if (ServicesEmprunt.getInstance().isBorrowed(e)) {
            Button returnB = new Button("Return the book");
            LivreDetail.add(returnB);
            returnB.addActionListener(ev -> {
                if (ServicesEmprunt.getInstance().ReturnBook(e)) {
                    Dialog.show("Information", "The book is returned ", "OK", null);
                    new LivreStudent(student.current, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }

            });

        }

        LivreDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new LivreStudent(student.current, theme).show();
        });

        return LivreDetail;
    }

    public Form StatLivre(Resources theme) {

        LivrePieChart a = new LivrePieChart();
        Form stats_Form = a.execute();
        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new LivreStudent(student.current, theme).show();
        });

        return stats_Form;
    }

}
