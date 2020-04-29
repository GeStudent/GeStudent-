/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import edu.gestudent.services.UploadServices;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
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
import edu.gestudent.charts.ClubPieChart;
import edu.gestudent.entities.Club;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesClub;

/**
 *
 * @author Ayadi
 */
public class Clubgui extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    String FilenameInserver = "";
    user User = Session.getCurrentSession();
    UploadServices uploadservices = new UploadServices();

    public Clubgui(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("Club");
        currentForm.setLayout(BoxLayout.y());
        for (Club c : ServicesClub.getInstance().getAllClubs()) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label nomClub = new Label(c.getNom());
            Label emailClub = new Label(c.getEmail());
            Label tel = new Label(String.valueOf(c.getTel()));
            Label Date = new Label(c.getDate());
            Button ListMembers = new Button("List Members");
            InfoContainer.add(nomClub);
            InfoContainer.add(emailClub);
            InfoContainer.add(tel);
            InfoContainer.add(Date);
            InfoContainer.add(ListMembers);
            Container Container = new Container(BoxLayout.x());
            placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url = "http://localhost/Images/uploads/" + c.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));
            Container.add(img);
            Container.add(InfoContainer);
            currentForm.add(Container);
            img.addPointerReleasedListener(ev -> {
                ClubDetail(c, theme).show();
            });
            ListMembers.addActionListener(ev -> {
                ListMembersClub(c, theme).show();
            });
        }
        currentForm.getToolbar().addCommandToOverflowMenu("Add Club", null, ev -> {
            if (ServicesClub.getInstance().isClubLeader(User)) {
                Dialog.show("ERROR", "You can't create a Club , you are already a Club leader", new Command("OK"));
            } else {
                AddClub(theme).show();
            }
        });
        currentForm.getToolbar().addCommandToOverflowMenu("Stat Club", null, ev -> {
            StatClub(theme).show();
        });
        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }

    public Form AddClub(Resources theme) {

        Form AddClub = new Form("ADD", BoxLayout.y());

        Label ClubName = new Label("Name");
        Label Email = new Label("Email");
        Button testImage = new Button("Browse Images");
        Label phone = new Label("phone");
        Label Message = new Label("Message");

        TextField ClubNameField = new TextField(null, "Name");
        TextField EmailField = new TextField(null, "Email");
        TextField ImageField = new TextField(null, "Image");
        TextField phoneField = new TextField(null, "phone");
        TextField MessageField = new TextField(null, "Message");
        Button Save = new Button("Save");

        Save.addActionListener(ev -> {

            if ((ClubNameField.getText().length() == 0) || (EmailField.getText().length() == 0) || FilenameInserver.equals("")
                    || phoneField.getText().length() == 0 || MessageField.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Club c = new Club();
                    c.setEmail(EmailField.getText());
                    c.setNom(ClubNameField.getText());
                    c.setDescription(MessageField.getText());
                    c.setImage(FilenameInserver);
                    c.setId_president(User.getId());
                    c.setTel(Integer.parseInt(phoneField.getText()));

                    if (ServicesClub.getInstance().AddClub(c)) {
                        Dialog.show("Success", "Club Added", new Command("OK"));
                        new Clubgui(student.current, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "phone must be a number", new Command("OK"));
                }

            }

        });

        testImage.addActionListener(e -> {
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
        AddClub.addAll(ClubName, ClubNameField, Email, EmailField, testImage, phone, phoneField, Message, MessageField, Save);

        AddClub.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return AddClub;
    }

    public Form ClubDetail(Club c, Resources theme) {

        Form ClubDetail = new Form(c.getNom(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/Images/uploads/" + c.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label ClubName = new Label("Name:");
        Label Email = new Label("Email:");
        Label phone = new Label("phone:");
        Label Description = new Label("Description:");

        TextField DescriptionField = new TextField(null, "Description");

        SpanLabel Message = new SpanLabel("Created AT: " + c.getDate() + "\n" + "Members: " + c.getNombreplace());

        TextField ClubNameField = new TextField(null, "Name");

        ClubNameField.setText(c.getNom());

        TextField EmailField = new TextField(null, "Email");
        EmailField.setText(c.getEmail());
        TextField phoneField = new TextField(null, "phone");
        phoneField.setText(String.valueOf(c.getTel()));
        DescriptionField.setSingleLineTextArea(false);
        DescriptionField.setRows(4);
        DescriptionField.setColumns(20);
        DescriptionField.setText(c.getDescription());

        Container Container = new Container(new FlowLayout());
        Container.addAll(ClubName, ClubNameField, Email, EmailField, phone, phoneField, Description, DescriptionField, Message);
        ClubDetail.add(img);
        ClubDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        ClubDetail.add(ButtonsContainer);
        ClubDetail.revalidate();
        Delete.addActionListener(ev -> {
            if (c.getId_president() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! You must be club leader to edit or delete", new Command("OK"));
            } else {
                String result = ServicesClub.getInstance().DeleteClub(c);
                if (!result.equals("Error")) {
                    Dialog.show("Success", result, new Command("OK"));
                    new Clubgui(student.current, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });

        Edit.addActionListener(ev -> {
            if (c.getId_president() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! You must be club leader to edit or delete", new Command("OK"));
            } else {
                c.setEmail(EmailField.getText());
                c.setNom(ClubNameField.getText());
                c.setTel(Integer.parseInt(phoneField.getText()));
                c.setDescription(DescriptionField.getText());
                if (ServicesClub.getInstance().EditClub(c)) {
                    Dialog.show("Success", "Club Edited", new Command("OK"));
                    ClubDetail.revalidate();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });

        ClubDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return ClubDetail;
    }

    public Form ListMembersClub(Club c, Resources theme) {

        Form ListMembers = new Form(c.getNom(), BoxLayout.y());

        for (user u : ServicesClub.getInstance().getAllMembers(c)) {
            Container InfoContainer = new Container(BoxLayout.y());
            Label name = new Label(u.getFirstname() + " " + u.getLastname());
            Label email = new Label(u.getEmail());
            Label post = new Label(u.getPostClub());
            InfoContainer.add(name);
            InfoContainer.add(email);
            InfoContainer.add(post);
            Container Container = new Container(BoxLayout.x());

            placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url = "http://localhost/Images/uploads/" + u.getImage();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);
            int width = (int) (imgurl.getWidth() / 1.5);
            int Height = (int) (imgurl.getHeight() / 1.5);
            ImageViewer img = new ImageViewer(imgurl.scaled(width, Height));
            Container.add(img);
            Container.add(InfoContainer);
            ListMembers.add(Container);

        }

        ListMembers.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return ListMembers;
    }

    public Form StatClub(Resources theme) {

        ClubPieChart a = new ClubPieChart();
        Form stats_Form = a.execute();
        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return stats_Form;
    }

}
