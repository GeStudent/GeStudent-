/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesUsers;
import edu.gestudent.services.UploadServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private BorderPane DashbordPane;
    @FXML
    private AnchorPane PaneAdmin;
    @FXML
    private ImageView profileimage;
    @FXML
    private Label txtFirstname;
    @FXML
    private Label txtlastname;

    int iduser = Session.getCurrentSession();
    UploadServices uploadservices = new UploadServices();
    ServicesUsers ServiceUser = new ServicesUsers();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user u = ServiceUser.findbyid(iduser);
        txtFirstname.setText(u.getFirstname());
        txtlastname.setText(u.getLastname());

        String imagename = ServiceUser.getImage(iduser);
        System.out.println(imagename);
        Image image = new Image("http://localhost/images/uploads/" + imagename);

        profileimage.setImage(image);
        // TODO
       loadUi("DashbordProfile");
    }

    public void updateImage(Image image) {
        profileimage.setImage(image);
    }

    @FXML
    private void profileAction(ActionEvent event) {
        // DashbordPane.setCenter(null);

        // loadUi("DashbordAdmin");
        loadScreen();
        loadUi("DashbordProfile");

    }

    @FXML
    private void UsersAction(ActionEvent event) {
        loadUi("DashbordUsers");

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
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void ClassAction(ActionEvent event) {
        loadUi("DashbordClass");

    }

    @FXML
    private void ExamsAction(ActionEvent event) {
        loadUi("DashbordExams");
    }

    @FXML
    private void LibraryAction(ActionEvent event) {
        loadUi("DashbordLibrary");
    }

    @FXML
    private void ClubAction(ActionEvent event) {
        loadUi("DashbordClub");
    }

    @FXML
    private void RestaurentAction(ActionEvent event) {
        loadUi("DashbordRestaurant");
    }

    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.setCenter(root);
    }

    private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DashbordPane.getScene().setRoot(root);
    }

}
