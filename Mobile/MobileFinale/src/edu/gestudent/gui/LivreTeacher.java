/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.Session;
import static edu.gestudent.entities.smsSender.ACCOUNT_SID;
import static edu.gestudent.entities.smsSender.AUTH_TOKEN;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesEmprunt;
import edu.gestudent.services.ServicesLivres;
import edu.gestudent.services.UploadLivreServices;
import edu.gestudent.services.UploadServices;

/**
 *
 * @author ASUS
 */
public class LivreTeacher extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    user User = Session.getCurrentSession();
    UploadLivreServices uploadservices = new UploadLivreServices();
    String FilenameInserver = "";

    public LivreTeacher(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("Book List");
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

        currentForm.getToolbar().addCommandToOverflowMenu("Add Book", null, ev -> {
            AddLivre(theme).show();
        });
        currentForm.getToolbar().addCommandToOverflowMenu("Borrowed Books", null, ev -> {
            ListEmprunt(theme).show();
        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }

    public Form AddLivre(Resources theme) {

        Form AddLivre = new Form("ADD", BoxLayout.y());

        Label Name = new Label("Name");
        Label Image = new Label("Image");
        Label Author = new Label("Author");
        Label Description = new Label("Description");
        Label Categorie = new Label("Categorie");
        Label Quantite = new Label("Quantite");

        TextField NameField = new TextField(null, " NameField");
        Button ImageField = new Button("ImageField");
        TextField AuthorField = new TextField(null, "AuthorField");
        TextField UrlField = new TextField(null, "DescriptionField");
        // TextField CategorieField = new TextField(null, "CategorieField");
        ComboBox<String> CategorieField = new ComboBox();
        CategorieField.addItem("Action");
        CategorieField.addItem("Comedy");
        CategorieField.addItem("Drama");
        CategorieField.addItem("Art");
        CategorieField.addItem("Thriller");
        TextField QuantiteField = new TextField(null, "QuantiteField");
        Button Save = new Button("Save");

        AddLivre.addAll(Name, NameField, Image, ImageField, Author, AuthorField, Description, UrlField, Categorie, CategorieField, Quantite, QuantiteField, Save);

        Save.addActionListener(ev -> {
            if ((NameField.getText().length() == 0) || (ImageField.getText().length() == 0) || AuthorField.getText().length() == 0
                    || UrlField.getText().length() == 0 || FilenameInserver.equals("") || QuantiteField.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Livre l = new Livre();
                    l.setName(NameField.getText());
                    l.setImage(FilenameInserver);
                    l.setAuthor(AuthorField.getText());
                    l.setUrl(UrlField.getText());
                    l.setCategorie(CategorieField.getSelectedItem());
                    l.setQuantite(Integer.parseInt(QuantiteField.getText()));

                    if (ServicesLivres.getInstance().AddLivre(l)) {
                        Dialog.show("Success", "Book Added", new Command("OK"));
                        new LivreTeacher(LivreTeacher.currentForm, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "quantity must be a number", new Command("OK"));
                }
            }

        });
        ImageField.addActionListener(ev -> {
            Display.getInstance().openGallery(new ActionListener() {

                public void actionPerformed(final ActionEvent evt) {
                    if (evt == null) {
                        ToastBar.Status s = ToastBar.getInstance().createStatus();
                        s.setMessage("User Cancelled Gallery");
                        s.setMessageUIID("Title");
                        Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                        s.setIcon(i);
                        s.setExpires(2000);
                        s.show();
                        return;
                    }
                    String file = (String) evt.getSource();
                    System.out.println("pathhhh:" + file);
                    String path = file.substring(7);
                    System.out.println(path);
                    FilenameInserver = uploadservices.upload(path);
                }

            }, Display.GALLERY_IMAGE);

        });
        AddLivre.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new LivreTeacher(teacher.current, theme).show();
        });

        return AddLivre;
    }

    public Form ListEmprunt(Resources theme) {

        Form ListEmprunt = new Form("Borrowed Books", BoxLayout.y());
        for (Emprunt e : ServicesEmprunt.getInstance().getAllEmprunts()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label nomLivre = new Label(e.getName());
            Label name = new Label(e.getFirstname() + " " + e.getLastname());
            Label dateEmprunt = new Label("Borrow Date: " + e.getD_emprunt());
            Label dateRetour = new Label("Return Date: " + e.getD_retour());
            InfoContainer.add(nomLivre);
            InfoContainer.add(name);
            InfoContainer.add(dateEmprunt);
            InfoContainer.add(dateRetour);

            Container Container = new Container(BoxLayout.x());
            Container Containery = new Container(BoxLayout.y());

            placeHolder = EncodedImage.createFromImage(theme.getImage("bla.jpg"), true);
            String url = "http://localhost/GeStudent/web/Uploads/Library/photo/" + e.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

            ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() / 2, imgurl.getHeight() / 2));
            Image icon = FontImage.createMaterial(FontImage.MATERIAL_SMS, UIManager.getInstance().getComponentStyle("Label"));
            Button sms = new Button(icon);

            Containery.addAll(img, sms);
            Container.add(Containery);
            Container.add(InfoContainer);
            ListEmprunt.add(Container);
            sms.addActionListener(ev -> {

                if (Dialog.show("Confirmation", "Are you Sure ? ", "OK", "CANCEL")) {

                    System.out.println("phone: +216" + e.getPhone());
                    Twilio.setUsername(ACCOUNT_SID);
                    Twilio.setPassword(AUTH_TOKEN);
                    Message mressage = Message
                            .creator(new PhoneNumber("+216" + e.getPhone()), // to
                                    new PhoneNumber("+17575058027"), // from
                                    "Please return the book '" + e.getBookName() + "'! You have passed the dead line of " + e.getD_retour())
                            .create();
                    Dialog.show("Success", "sms sent succesfully", "OK", null);

                    
                }
            });

        }

        ListEmprunt.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new LivreTeacher(teacher.current, theme).show();
        });

        return ListEmprunt;
    }

    public Form LivreDetail(Livre l, Resources theme) {

        Form LivreDetail = new Form(l.getName(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("bla.jpg"), true);
        String url = "http://localhost/GeStudent/web/Uploads/Library/photo/" + l.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label Name = new Label("Name:");
        Label Author = new Label("Author:");
        Label Description = new Label("Description:");
        Label Categorie = new Label("Categorie:");

        Label Quantite = new Label("Quantity:");
        TextField NameField = new TextField(null, "Name");
        NameField.setText(l.getName());
        TextField AuthorField = new TextField(null, "Author");
        AuthorField.setText(l.getAuthor());
        TextField UrlField = new TextField(null, "Description");
        UrlField.setText(l.getUrl());
        ComboBox<String> CategorieField = new ComboBox();
        CategorieField.addItem("Action");
        CategorieField.addItem("Comedy");
        CategorieField.addItem("Drama");
        CategorieField.addItem("Art");
        CategorieField.addItem("Thriller");
        CategorieField.setSelectedItem(l.getCategorie());
        TextField QuantiteField = new TextField(null, "Quantity");
        QuantiteField.setText(String.valueOf(l.getQuantite()));

        Container Container = new Container(BoxLayout.y());
        Container.addAll(Name, NameField, Author, AuthorField, Description, UrlField, Categorie, CategorieField, Quantite, QuantiteField);
        LivreDetail.add(img);
        LivreDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        LivreDetail.add(ButtonsContainer);
        LivreDetail.revalidate();
        Delete.addActionListener(ev -> {

            if (Dialog.show("Confirmation", "Are you Sure ? ", "OK", "CANCEL")) {

                String result = ServicesLivres.getInstance().DeleteLivre(l);
                if (!result.equals("Error")) {
                    Dialog.show("Success", result, new Command("OK"));
                    new LivreTeacher(teacher.current, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } else {

            }

        });

        Edit.addActionListener(ev -> {
            l.setName(NameField.getText());
            l.setAuthor(AuthorField.getText());
            l.setUrl(UrlField.getText());
            l.setCategorie(CategorieField.getSelectedItem());
            l.setQuantite(Integer.parseInt(QuantiteField.getText()));
            if (ServicesLivres.getInstance().EditClub(l)) {
                Dialog.show("Success", "Book Edited", new Command("OK"));
                new LivreTeacher(teacher.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

        LivreDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new LivreTeacher(teacher.current, theme).show();
        });

        return LivreDetail;
    }

}
