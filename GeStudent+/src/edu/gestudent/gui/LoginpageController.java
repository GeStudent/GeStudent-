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
import edu.gestudent.services.ServicesUsers;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class LoginpageController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LoginAction(ActionEvent event) {

        String role = "";
        int count;
        ServicesUsers us = new ServicesUsers();
        user u = new user();
        u.setUsername(txtusername.getText());
        u.setPassword(txtpassword.getText());
        System.out.println(txtpassword.getText());
        System.out.println(u);
        count = us.login(u);

        if (count == 1) {
            role = us.getRole(txtusername.getText());
            System.out.println(role);
            if ("student".equals(role)) {
                try {
                    System.out.println("user id is : " + u.getUsername());
                    Session.start(u.getId());
                    System.out.println(Session.getCurrentSession());

                    System.out.println("Role from login! : student" + u.getId());

                    Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                    Scene scene = new Scene(root, 1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();

                } catch (IOException ex) {
                    Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if ("teacher".equals(role)) {
                try {
                    System.out.println("user id is : " + u.getUsername());
                    Session.start(u.getId());
                    System.out.println(Session.getCurrentSession());

                    System.out.println("Role from login! : teacher" + u.getId());
                    Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                    Scene scene = new Scene(root, 1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ("admin".equals(role)) {
                try {
                    System.out.println("user id is : admin" + u.getId());
                    Session.start(u.getId());
                    System.out.println(Session.getCurrentSession());

                    Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                    Scene scene = new Scene(root, 1100, 650);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginpageController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {

            txtusername.getStyleClass().add("wrong-credentials");
            txtpassword.getStyleClass().add("wrong-credentials");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not login");
            alert.setContentText("please check your credentials!");
            alert.showAndWait();

        }

    }

}
