/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;

/**
 *
 * @author Ayadi
 */
public class InscriRestGui extends Form {

//    static Form currentForm;
//    private EncodedImage placeHolder;
//    String FilenameInserver = "";
//    user User = Session.getCurrentSession();
//    UploadServices uploadservices = new UploadServices();
//
//    public inscriRestGui(Form previous, Resources theme) {
//
//        currentForm = this;
//        currentForm.setTitle("inscription au restaurant");
//        currentForm.setLayout(BoxLayout.y());
//        currentForm.getToolbar().addCommandToOverflowMenu("s'inscrire", null, ev -> {
//            if (ServicesInscriRest.getInstance().isInscri(User)) {
//                Dialog.show("ERROR", "inscri annulee", new Command("OK"));
//            } else {
//              //  Addinscri(theme).show();
//            }
//        });
//        
//            currentForm.getToolbar().addCommandToOverflowMenu("Stat Club", null, ev -> {
//                                StatClub(theme).show();
//
//
//        });
//
//        currentForm.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
//            previous.showBack();
//        });
//
//    }
//
//    public Form Addinscri(Resources theme) {
//
//        Form Addinscri = new Form("ADD", BoxLayout.y());
//        Label duration = new Label("duration");
//        Label amount = new Label("amount");
//        ComboBox durationField = new ComboBox();
//        durationField.addItem("MOIS");
//        durationField.addItem("SEMESTRE");
//        durationField.addItem("ANNEE");
//       // TextField durationField = new TextField(null, "duration");
//        TextField amountField = new TextField(null, "amount");
//        Button Save = new Button("s'inscrire");
//
//        Save.addActionListener(ev -> {
//
//            if ((durationField.getSelectedItem().equals(null)) || (amountField.getText().length() == 0)) {
//                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
//            } else {
//                try {
//                    inscriRest c = new inscriRest();  
//                    c.setId_user(User.getId());
//                    c.setDuration((String) durationField.getSelectedItem());
//                    c.setAmount(Integer.parseInt(amountField.getText()));
//
//                    if (ServicesInscriRest.getInstance().AddInscri(c)) {
//                        Dialog.show("Success", "Vous etes inscri", new Command("OK"));
//                        //new inscriRestGui(student.current, theme).show();
//                    } else {
//                        Dialog.show("ERROR", "Server error", new Command("OK"));
//                    }
//                } catch (NumberFormatException e) {
//                    Dialog.show("ERROR", "amount must be a number", new Command("OK"));
//                }
//
//            }
//
//        });
//        Addinscri.addAll(duration, durationField, amount, amountField, Save);
//        return Addinscri;
//    }
//
//    public Form inscriDetail(inscriRest c, Resources theme) {
//
//        Form inscriDetail = new Form("inscription", BoxLayout.y());
//        Label duration = new Label("duration:");
//        Label amount = new Label("amount:"); 
////        TextField durationField = new TextField(null, "duration");
// ComboBox durationField = new ComboBox();
//        durationField.addItem("MOIS");
//        durationField.addItem("SEMESTRE");
//        durationField.addItem("ANNEE");
//        TextField amountField = new TextField(null, "amount");
//        amountField.setText(String.valueOf(c.getAmount()));
//        Container Container = new Container(new FlowLayout());
//        Container.addAll(duration, durationField, amount, amountField);
//        inscriDetail.add(Container);
//
//        Container ButtonsContainer = new Container(new FlowLayout());
//
//        Button Delete = new Button("Delete");
//        Button Edit = new Button("Edit");
//        ButtonsContainer.addAll(Edit, Delete);
//
//        inscriDetail.add(ButtonsContainer);
//        inscriDetail.revalidate();
//        Delete.addActionListener(ev -> {
//              if (c.getId_user() != User.getId()) {
//                Dialog.show("ERROR", "Permission denied! You must be club leader to edit or delete", new Command("OK"));
//            } else {
//            String result = ServicesInscriRest.getInstance().Deleteinscri(c);
//            if (!result.equals("Error")) {
//                Dialog.show("Success", result, new Command("OK"));
//               // new Clubgui(student.current, theme).show();
//            } else {
//                Dialog.show("ERROR", "Server error", new Command("OK"));
//            }
//              }
//        });
//
//        Edit.addActionListener(ev -> {
//            if (c.getId_user() != User.getId()) {
//                Dialog.show("ERROR", "Permission denied! You must be club leader to edit or delete", new Command("OK"));
//            } else {
//                c.setDuration((String) durationField.getSelectedItem());
//                c.setAmount(Double.parseDouble(amountField.getText()));
//                if (ServicesInscriRest.getInstance().EditInscri(c)) {
//                    Dialog.show("Success", "inscri Edited", new Command("OK"));
//                    inscriDetail.revalidate();
//                } else {
//                    Dialog.show("ERROR", "Server error", new Command("OK"));
//                }
//            }
//        });
//
////        inscriDetail.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
////            new inscriRestGui(student.current, theme).show();
////        });
//
//        return inscriDetail;
//    }
//    
     


}
