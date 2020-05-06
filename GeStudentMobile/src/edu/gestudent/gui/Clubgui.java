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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
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
    static String ClubPost = "";

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
                    FilenameInserver = uploadservices.uploadImage(path);
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
        Button Join = new Button("Join");

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
        ClubPost = ServicesClub.getInstance().ClubPost(c, User);
        if (ClubPost.equals("null")) {
            ClubDetail.add(Join);
        }
        ClubDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        ButtonsContainer.addAll(Edit, Delete);

        ClubDetail.add(ButtonsContainer);
        ClubDetail.revalidate();
        Delete.addActionListener(ev -> {

            if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
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
            } else {

            }

        });

        Edit.addActionListener(ev -> {
                    ClubPost = ServicesClub.getInstance().ClubPost(c, User);
            if (ClubPost.equals("leader") || ClubPost.equals("co_leader") || ClubPost.equals("treasurer") || ClubPost.equals("secretary")) {
           
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
            
            } else {
                Dialog.show("ERROR", "Permission denied! You must be club leader to edit or delete", new Command("OK"));

            }
        });
        Join.addActionListener(ev -> {
            String reqPosst = ServicesClub.getInstance().ClubPost(c, User);

            if (reqPosst.equals("null")) {
                if (Dialog.show("Confirmation", "Do you want to join " + c.getNom() + " ? ", "OK", "Cancel")) {
                    if (ServicesClub.getInstance().JoinClub(c, User)) {
                        ClubPost = ServicesClub.getInstance().ClubPost(c, User);
                        Dialog.show("Success", "You should get a reply Soon by the admins !", new Command("OK"));

                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            } else {
                Dialog.show("ERROR", "you have already sent request !\n"
                        + "you should get a reply soon!!", new Command("OK"));

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

            img.addPointerReleasedListener(ev -> {
                StudentProfile(u, c, theme).show();
            });

        }
        ListMembers.getToolbar().addCommandToOverflowMenu("Requestes", null, ev -> {
            ServicesClub.getInstance().getAllInvitations(c);
            if (c.getId_president() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! You must be club leader ", new Command("OK"));
            } else if (ServicesClub.getInstance().getAllInvitations(c) == null) {

                Dialog.show("ERROR", "NO INVITATIONS ", new Command("OK"));

            } else {
                Invitations(c, theme).show();
            }
        });

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

    public Form Invitations(Club c, Resources theme) {

        Form ListInvitations = new Form(c.getNom(), BoxLayout.y());

        for (user u : ServicesClub.getInstance().getAllInvitations(c)) {
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
            int width = (int) (imgurl.getWidth() / 2);
            int Height = (int) (imgurl.getHeight() / 2);
            ImageViewer img = new ImageViewer(imgurl.scaled(width, Height));
            Container.add(img);
            Container.add(InfoContainer);
            Image icon = FontImage.createMaterial(FontImage.MATERIAL_CHECK, UIManager.getInstance().getComponentStyle("Label"));
            Button accpet = new Button(icon);
            Image icon2 = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, UIManager.getInstance().getComponentStyle("Label"));
            Button remove = new Button(icon2);
            Container ButtonContainer = new Container(BoxLayout.y());
            ButtonContainer.addAll(accpet, remove);
            Container.add(ButtonContainer);
            ListInvitations.add(Container);
            accpet.addActionListener(ev -> {
                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().AccpetInviteClub(c, u)) {
                        Dialog.show("Success", "Invitation Accpted", new Command("OK"));
                        ListMembersClub(c, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            });
            remove.addActionListener(ev -> {
                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().DeclineInviteClub(c, u)) {
                        Dialog.show("Success", "Invitation Deleted", new Command("OK"));
                        ListMembersClub(c, theme).show();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }
            });

        }
        ListInvitations.getToolbar().addCommandToOverflowMenu("Members", null, ev -> {
            ListMembersClub(c, theme).show();
        });

        ListInvitations.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return ListInvitations;

    }

    public Form StudentProfile(user u, Club c, Resources theme) {

        Form StudentProfile = new Form(u.getFirstname().toUpperCase(), BoxLayout.y());

        StudentProfile.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            ListMembersClub(c, theme).show();
        });
        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/Images/uploads/" + u.getImage();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 1, imgurl.getHeight() * 1));

        Label Name = new Label("Name: " + u.getFirstname().toUpperCase() + " " + u.getLastname().toUpperCase());
        Label Email = new Label("Email: " + u.getEmail());
        Label Club = new Label("Club:" + c.getNom());
        Label Post = new Label("Post:" + u.getPostClub());

        String url2 = "http://localhost/Images/uploads/" + u.getQrCodeClub();
        System.out.println(url);
        System.out.println(url2);
        ConnectionRequest connection2 = new ConnectionRequest();
        connection2.setUrl(url2);
        URLImage imgurl2 = URLImage.createToStorage(placeHolder, url2, url2);

        ImageViewer img2 = new ImageViewer(imgurl2.scaled(imgurl2.getWidth() * 1, imgurl2.getHeight() * 1));

        TextField tt = new TextField();
        tt.setEditable(false);
        tt.setEnabled(false);
        tt.setVisible(false);

        Button promote = new Button("promote");
        Button Kick = new Button("Kick");
        Container ButtonCnt = new Container(BoxLayout.x());
        ButtonCnt.addAll(promote, Kick);
        Button co_leader = new Button("co_leader");
        Button treasurer = new Button("treasurer");
        Button secretary = new Button("secretary");
        Button member = new Button("member");
        Dialog d = new Dialog("Promote");
        d.setLayout(BoxLayout.y());
        d.addAll(co_leader, treasurer, secretary, member);
        promote.addActionListener(ev -> {

            ClubPost = ServicesClub.getInstance().ClubPost(c, User);
            System.out.println("ConnectPost:" + ClubPost);
            if (ClubPost.equals("leader") || ClubPost.equals("co_leader") || ClubPost.equals("treasurer") || ClubPost.equals("secretary")) {
                d.showPopupDialog(promote);
            } else {
                Dialog.show("ERROR", "Permission denied! You must be club Adminstrator ", new Command("OK"));
            }

        });
        String MemberPost = ServicesClub.getInstance().ClubPost(c, u);

        co_leader.addActionListener(evt -> {
            if (MemberPost.equals("leader")) {
                Dialog.show("ERROR", "Permission denied! You can't do this action on club leader ", new Command("OK"));
            } else if (ClubPost.equals("leader") || MemberPost.equals("member")) {

                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().PromoteMemberClub(c, u, "co_leader")) {
                        Dialog.show("Success", "Action Completed", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            } else {
                Dialog.show("ERROR", "Permission denied! You must be a club Leader  ", new Command("OK"));
            }

        });
        treasurer.addActionListener(evt -> {

            if (MemberPost.equals("leader")) {
                Dialog.show("ERROR", "Permission denied! You can't do this action on club leader ", new Command("OK"));
            } else if (ClubPost.equals("leader") || MemberPost.equals("member")) {
                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().PromoteMemberClub(c, u, "treasurer")) {
                        Dialog.show("Success", "Action Completed", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }
            } else {
                Dialog.show("ERROR", "Permission denied! You must be a club Leader  ", new Command("OK"));
            }

        });

        secretary.addActionListener(evt -> {
            if (MemberPost.equals("leader")) {
                Dialog.show("ERROR", "Permission denied! You can't do this action on club leader ", new Command("OK"));
            } else if (ClubPost.equals("leader") || MemberPost.equals("member")) {
                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().PromoteMemberClub(c, u, "secretary")) {
                        Dialog.show("Success", "Action Completed", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }
            } else {
                Dialog.show("ERROR", "Permission denied! You must be a club Leader  ", new Command("OK"));
            }
        });

        member.addActionListener(evt -> {

            if (MemberPost.equals("leader")) {
                Dialog.show("ERROR", "Permission denied! You can't do this action on club leader ", new Command("OK"));
            } else if (ClubPost.equals("leader") || MemberPost.equals("member")) {
                if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                    if (ServicesClub.getInstance().PromoteMemberClub(c, u, "member")) {
                        Dialog.show("Success", "Action Completed", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }
            } else {
                Dialog.show("ERROR", "Permission denied! You must be a club Leader  ", new Command("OK"));
            }

        });
        Kick.addActionListener(ev -> {
            if (ClubPost.equals("leader") || ClubPost.equals("co_leader") || ClubPost.equals("treasurer") || ClubPost.equals("secretary")) {

                if (MemberPost.equals("leader")) {
                    Dialog.show("ERROR", "Permission denied! You can't do this action on club leader ", new Command("OK"));
                } else if (ClubPost.equals("leader") || MemberPost.equals("member")) {

                    if (Dialog.show("Confirmation", "Are u Sure ? ", "OK", "CANCEL")) {
                        if (ServicesClub.getInstance().KickMemberClub(c, u)) {
                            Dialog.show("Success", "Member Kicked", new Command("OK"));
                            ListMembersClub(c, theme).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }

                    }
                } else {
                    Dialog.show("ERROR", "Permission denied! You must be a club Leader  ", new Command("OK"));
                }

            } else {
                Dialog.show("ERROR", "Permission denied! You must be club Adminstrator ", new Command("OK"));
            }

        });

        StudentProfile.addAll(img, ButtonCnt, Name, Email, Club, Post, tt, img2);

        return StudentProfile;
    }

}
