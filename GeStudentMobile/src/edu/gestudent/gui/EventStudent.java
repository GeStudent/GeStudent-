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
import static com.codename1.io.Log.e;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import edu.gestudent.entities.Evenement;
import edu.gestudent.entities.Session;
import static edu.gestudent.entities.Session.User;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServiceEvenement;

/**
 *
 * @author Ayadi
 */
public class EventStudent extends Form {

    static Form currentForm;
    private EncodedImage placeHolder;
    String FilenameInserver = "";
    user User = Session.getCurrentSession();
    Evenement e;
    UploadServices uploadservices = new UploadServices();

    public EventStudent(Form previous, Resources theme) {

        currentForm = this;
        currentForm.setTitle("Event");
        currentForm.setLayout(BoxLayout.y());

        for (Evenement e : ServiceEvenement.getInstance().getAllEvenements()) {
            Container InfoContainer = new Container(BoxLayout.y());

            Label description1 = new Label("About:");
            Label nombreplaces1 = new Label("place remaining :");

            Button ListMembers = new Button("My Events");

            Label nom = new Label(e.getNom());
            Label description = new Label((e.getDescription()));
            Label nombreplaces = new Label(String.valueOf(e.getNombreplaces()));
            Label datedebut = new Label(e.getDatedebut());
            // Label Nombreplace = new Label(String.valueOf(c.getNombreplace()));
            Button information = new Button("More information");
            // Button SendMail=new Button("Send email ");

            //  Button reserver=new Button("Book a place");
            InfoContainer.add((nom));
            InfoContainer.add((information));
            //  InfoContainer.add((SendMail));


            /* InfoContainer.add((description1));

            InfoContainer.add((description));
            InfoContainer.add((nombreplaces1));

            InfoContainer.add((nombreplaces));
            InfoContainer.add((datedebut));
            //InfoContainer.add(Nombreplace);
            InfoContainer.add(reserver);*/
            Container Container = new Container(BoxLayout.x());

            placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url = "http://localhost/GeStudenty/web/img/uploads/" + e.getPhoto();
            ConnectionRequest connection = new ConnectionRequest();
            connection.setUrl(url);
            URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

            ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 2, imgurl.getHeight() * 2));
            Container.add(img);
            Container.add(InfoContainer);
            currentForm.add(Container);

            img.addPointerReleasedListener(ev -> {
                EventDetail(e, theme).show();
            });

            /*           
            ListMembers.addActionListener(ev->{ 
                ListEventMember(e,theme).show();
            });
        }

             */
            information.addPointerReleasedListener(ev -> {
                EventDetail(e, theme).show();
            });

            /*   reserver.addActionListener(ev -> {
            if (ServiceEvenement.getInstance().ParticipateEvent(e, User)) {
                Dialog.show("Success", "Evenement booked", new Command("OK"));
                new EventStudent(student.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });*/
 /* reserver.addActionListener(ev->{ 
                ListMembersClub(c,theme).show();
            });*/
        }

        /*   currentForm.getToolbar().addCommandToOverflowMenu("Add Club", null, ev -> {
            if (ServiceEvenement.getInstance().isClubLeader(User)) {
                Dialog.show("ERROR", "You can't create a Club , you are already a Club leader", new Command("OK"));
            } else {
                AddClub(theme).show();
            }
        });*/
        currentForm.getToolbar().addCommandToOverflowMenu("Add Event", null, ev -> {
            AddEvent(theme).show();
        });

        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

        /* currentForm.getToolbar().addCommandToOverflowMenu("Stat Club", null, ev -> {
                                StatClub(theme).show();


        });*/
        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });

    }

    public Form AddEvent(Resources theme) {

        Form AddEvent = new Form("ADD", BoxLayout.y());
        Label nom = new Label("Name");
        Label description = new Label("Description of the event");
        // Label Image = new Label("Image");
        Button testImage = new Button("Browse Images");
        Label nombreplaces = new Label(" Place number ");
        Label datedebut = new Label("Date");

        TextField nomField = new TextField(null, "Name");
        TextField descriptionField = new TextField(null, "Description of the event");
        TextField photoField = new TextField(null, "Image");
        TextField nombreplacesField = new TextField(null, "place number");
        //  TextField datedebutField  = new TextField(null, "Date");
        Picker Datedebut = new Picker();

        Button Save = new Button("Save");
        Button SendMail = new Button("Send email ");
        //  Button Myparticipation=new Button("My participation ");

        Save.addActionListener(ev -> {

            if ((nomField.getText().length() == 0) || (descriptionField.getText().length() == 0) || FilenameInserver.equals("")
                    || nombreplacesField.getText().length() == 0 || datedebut.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    Evenement e = new Evenement();
                    e.setDescription(descriptionField.getText());
                    e.setNom(nomField.getText());

                    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                    String  date = dateformat.format(Datedebut.getDate());
                    System.out.println("Date:"+date);
                    //jareb taw 
                    e.setDatedebut(date);
                    e.setPhoto(FilenameInserver);
                    e.setIdmanager(User.getId());
                    e.setNombreplaces(Integer.parseInt(nombreplacesField.getText()));

                    if (ServiceEvenement.getInstance().AddEvent(e)) {
                        Dialog.show("Success", "Event Added", new Command("OK"));
                        // new EventStudent(student.current, theme).show();

                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "name must be caracters", new Command("OK"));
                }

            }

        });
//         SendMail.addActionListener(ev -> {
//            if (ServiceEvenement.getInstance().AcceptEvent(e, User)) {
//                Dialog.show("Success", "An email has been sent", new Command("OK"));
//                new EventStudent(student.current, theme).show();
//            } else {
//                Dialog.show("ERROR", "Server error", new Command("OK"));
//            }
//        });  
        /*    Myparticipation.addActionListener(ev -> {
            if (ServiceEvenement.getInstance().MyParticipation(User)) {
                new EventStudent(student.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });  */

        testImage.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {

                public void actionPerformed(final ActionEvent evt) {
                    //if a user cancels the gallery the evt will be null
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

//                    try {
//                        Image img = Image.createImage(file);
//                        AddClub.add(new Label(img));
//                        if (true) {
//                            return;
//                        }
//                    } catch (Exception ex) {
//                        Log.e(ex);
//                    }
//
//                    AddClub.revalidate();
                }
            }, Display.GALLERY_IMAGE);

        });
        AddEvent.addAll(nom, nomField, description, descriptionField, testImage, nombreplaces, nombreplacesField, Datedebut, datedebut, Save, SendMail);

        AddEvent.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new EventStudent(student.current, theme).show();
        });

        return AddEvent;
    }

    public Form EventDetail(Evenement e, Resources theme) {

        Form EventDetail = new Form(e.getNom(), BoxLayout.y());

        placeHolder = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/GeStudenty/web/img/uploads/" + e.getPhoto();
        ConnectionRequest connection = new ConnectionRequest();
        connection.setUrl(url);
        URLImage imgurl = URLImage.createToStorage(placeHolder, url, url);

        ImageViewer img = new ImageViewer(imgurl.scaled(imgurl.getWidth() * 2, imgurl.getHeight() * 2));

        Label nom = new Label("Name:");
        Label description = new Label("About:");
        Label nombreplaces = new Label("place remaining :");
        //  Label datedebut = new Label("will take place AT:");

        //   Label datedebut = new Label(" Date:");
        //   SpanLabel Message = new SpanLabel("Created AT: " + c.getDate() + "\n" + "Members: " + c.getNombreplace());
        SpanLabel Message = new SpanLabel("will take place AT: " + e.getDatedebut() + "\n" + "Place number: " + e.getNombreplaces());

        TextField nomField = new TextField(null, "Name");
        //   SpanLabel Message = new SpanLabel("This event will take place  AT: " + e.getDatedebut() +  );

        nomField.setText(e.getNom());

        TextField descriptionField = new TextField(null, "Description of the event");
        TextField nombreplacesField = new TextField(null, "Place number");
        nombreplacesField.setText(String.valueOf(e.getNombreplaces()));
        // TextField datedebutField = new TextField(null, "Will take place at");

        // Picker datedebut1 = new Picker();
        descriptionField.setSingleLineTextArea(false);
        descriptionField.setRows(4);
        descriptionField.setColumns(20);
        descriptionField.setText(e.getDescription());
        // Label datedebut1  = new Label(e.getDatedebut());

        Container Container = new Container(new FlowLayout());
        Container.addAll(nom, nomField, description, descriptionField, nombreplaces, nombreplacesField, Message);
        EventDetail.add(img);
        EventDetail.add(Container);

        Container ButtonsContainer = new Container(new FlowLayout());

        Button Delete = new Button("Delete");
        Button Edit = new Button("Edit");
        Button reserver = new Button("Book a place");
        // Button SendMail=new Button("mail ");

        ButtonsContainer.addAll(Edit, Delete, reserver);

        EventDetail.add(ButtonsContainer);
        EventDetail.revalidate();
        Delete.addActionListener(ev -> {
            if (e.getIdmanager() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! You must be the manager of this event to edit or delete", new Command("OK"));
            } else {
                String result = ServiceEvenement.getInstance().DeleteEvenement(e);
                if (!result.equals("Error")) {
                    Dialog.show("Success", result, new Command("OK"));
                    new EventStudent(student.current, theme).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });

        Edit.addActionListener(ev -> {
            if (e.getIdmanager() != User.getId()) {
                Dialog.show("ERROR", "Permission denied! You must be the manager of this event to edit or delete", new Command("OK"));
            } else {
                e.setDescription(descriptionField.getText());
                e.setNom(nomField.getText());
                e.setNombreplaces(Integer.parseInt(nombreplacesField.getText()));
                if (ServiceEvenement.getInstance().EditEvenement(e)) {
                    Dialog.show("Success", "Event Edited", new Command("OK"));
                    EventDetail.revalidate();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });

        reserver.addActionListener(ev -> {
            if (ServiceEvenement.getInstance().ParticipateEvent(e, User)) {
                Dialog.show("Success", "Evenement booked", new Command("OK"));
                new EventStudent(student.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

//        cancel.addActionListener(ev -> {
//            if (ServiceEvenement.getInstance().QuitEvent(e, User)) {
//                Dialog.show("Success", "Reservation has been canceled", new Command("OK"));
//                new EventStudent(student.current, theme).show();
//            } else {
//                Dialog.show("ERROR", "Server error", new Command("OK"));
//            }
//        });
        /*   Delete.addActionListener(ev -> {
            String result = ServiceEvenement.getInstance().DeleteEvenement(e);
            if (!result.equals("Error")) {
                Dialog.show("Success", result, new Command("OK"));
                new EventStudent(student.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }

        });

        Edit.addActionListener(ev -> {
            e.setNom(nomField.getText());
            e.setNombreplaces(Integer.parseInt(nombreplacesField.getText()));
            if (ServiceEvenement.getInstance().EditEvenement(e)) {
                Dialog.show("Success", "Evenement Edited", new Command("OK"));
                new EventStudent(student.current, theme).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });
       
       
       
         */
        EventDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new EventStudent(student.current, theme).show();
        });

        return EventDetail;
    }

    /*  public Form StatClub(Resources theme) {

      //  Form AddClub = new Form("ADD", BoxLayout.y());
                ClubPieChart a = new ClubPieChart();
                        Form stats_Form =a.execute();
                        SpanLabel test_SpanLabel = new SpanLabel("Hiiii");
                        //stats_Form.add(test_SpanLabel);
                        Class cls = ClubPieChart.class;
                        
//                        ServiceStats serviceStats = new ServiceStats();
//                        double [] xValues = serviceStats.getValues();
//                        System.out.println("DEBUG X  : "+xValues[0]);
//                        double [] yValues = serviceStats.getYValues();
//                        System.out.println("DEBUG Y  : "+yValues[0]);
//                                
                        
                        
             //           stats_Form.show();

        stats_Form.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new Clubgui(student.current, theme).show();
        });

        return stats_Form;
    }
     */
}
