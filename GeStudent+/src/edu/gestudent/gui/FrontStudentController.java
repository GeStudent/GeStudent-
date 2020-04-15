/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.CryptServices;
import edu.gestudent.services.ServicesUsers;
import edu.gestudent.services.UploadServices;
import edu.gestudent.services.inscriRestCrud;
import edu.gestudent.utils.gestudentAssistantUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class FrontStudentController implements Initializable {

    @FXML
    private BorderPane DashbordPane;
    @FXML
    private ImageView profileimage;
    @FXML
    private Label txtFirstname;
    @FXML
    private Label txtlastname;
    @FXML
    private AnchorPane PaneAdmin;

    int iduser = Session.getCurrentSession();
    UploadServices uploadservices = new UploadServices();
    ServicesUsers ServiceUser = new ServicesUsers();
    @FXML
    private ImageView newimage;
    @FXML
    private JFXTextField txtusername;
    private JFXTextField txtfirstname;
    @FXML
    private JFXTextField txtlastname1;
    @FXML
    private JFXTextField txtphone;
    @FXML
    private DatePicker txtdate;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private Label txtstatus;
    @FXML
    private JFXPasswordField currentpassword;
    @FXML
    private JFXPasswordField newpassword;
    @FXML
    private JFXPasswordField confirmepassword;
    @FXML
    private JFXTextField txtfirstname1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
System.out.println("idddd"+iduser);
        user u = ServiceUser.findbyid(iduser);
        txtFirstname.setText(u.getFirstname());
        txtlastname.setText(u.getLastname());

        String imagename = ServiceUser.getImage(iduser);
        System.out.println(imagename);
        Image image = new Image("http://localhost/images/uploads/" + imagename);

        profileimage.setImage(image);
        
        

        txtusername.setText(u.getUsername());
        txtfirstname1.setText(u.getFirstname());
        txtlastname1.setText(u.getLastname());
        txtphone.setText(String.valueOf(u.getPhone()));

        // profileimage.setImage(image);
        newimage.setImage(image);

        LocalDate parse = LocalDate.parse(u.getBirthDay());
        parse.getYear();
        parse.getMonthValue();
        parse.getDayOfMonth();
        LocalDate of = LocalDate.of(parse.getYear(), parse.getMonthValue(), parse.getDayOfMonth());
        txtdate.setValue(of);
             if (u.getRoles().contains("STUDENT")) {
            txtstatus.setText("student");
        } else if (u.getRoles().contains("ADMIN")) {
            txtstatus.setText("admin");
        } else {
            txtstatus.setText("teacher");
        }
        
        
        //merlt
    }

    @FXML
    private void logoutAction(ActionEvent event) {

        try {
            Session.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        Stage stage;
        Scene scene = null;
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();

        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("Loginpage.fxml")), 350, 350);
        } catch (IOException ex) {
            Logger.getLogger(FrontStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void FullScreen(ActionEvent event) {
        Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void AboutUsAction(ActionEvent event) {
        gestudentAssistantUtil.loadWindow(getClass().getResource("/edu/gestudent/about/about.fxml"), "About Us", null);

    }

    @FXML
    private void profileAction(ActionEvent event) {

        loadScreen();

    }


    @FXML
    private void ClassAction(ActionEvent event) {
        loadUi("frontEtudiant");
    }

    @FXML
    private void ExamsAction(ActionEvent event) {
        loadUi("DashbordExamsFront");
    }

    @FXML
    private void LibraryAction(ActionEvent event) {
          loadUi("DashbordFronLibrary");
    }


    @FXML
    private void ClubAction(ActionEvent event) {
         loadUi("DashbordFrontClub");
    }

    @FXML
    private void RestaurentAction(ActionEvent event) {
        inscriRestCrud irc = new inscriRestCrud();
        
        if(irc.InscriInterface(iduser)==0)
        {
            System.out.println("inscritRest.fxml");
             loadUi("inscritRest");
        }
        else
        loadUi("FrontRestaurant");
    }

    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FrontStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.setCenter(root);
    }

    private Stage getStage() {
        return (Stage) DashbordPane.getScene().getWindow();
    }

    public void updateImage(Image image) {
        profileimage.setImage(image);
    }
    
       private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FrontStudent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.getScene().setRoot(root);
    }

    @FXML
    private void upload(ActionEvent event) {
          FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            System.out.println("image file : " + imageFile);
            txtimage.setText(imageFile);
        }
    }

    @FXML
    private void updateInformation(ActionEvent event) {
        
         if (!txtimage.getText().equals("")) {

            String oldname = ServiceUser.getImage(iduser);
            uploadservices.delete(oldname);

            String FilenameInserver = uploadservices.upload(txtimage.getText());
            ServiceUser.updateimage(iduser, FilenameInserver);
        }
        String imagename = ServiceUser.getImage(iduser);
        Image image = new Image("http://localhost/images/uploads/" + imagename);
        // profileimage.setImage(image);
        newimage.setImage(image);
        //get image name

        String date = txtdate.getValue().format(DateTimeFormatter.ISO_DATE);
        System.out.println(date);

        ServiceUser.updateInfo(iduser, txtusername.getText(), txtfirstname1.getText(), txtlastname1.getText(), Integer.parseInt(txtphone.getText()), date);

    }

    @FXML
    private void ApplyChanepassword(ActionEvent event) {
        
        if (!newpassword.getText().equals(confirmepassword.getText()) || newpassword.getText().equals("")) {

            AlertMaker.showwarningMessage("Failed Password", "password not matched");
            return;

        }
        if (!ServiceUser.checkUser(ServiceUser.getUsername(iduser), currentpassword.getText())) {
            AlertMaker.showwarningMessage("Failed Password", "Verfiy ur password");
            return;
        }

        if (ServiceUser.updatepassword(iduser, newpassword.getText())) {
            AlertMaker.showSimpleAlert("Password", "Password Update");
        } else {
            AlertMaker.showwarningMessage("Failed Password", "Something went wrong");

        }
    }
    }


