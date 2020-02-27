/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Student;
import edu.gestudent.entities.teacher;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesTeacher;
import edu.gestudent.services.ServicesUsers;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class SignUpController implements Initializable {

    ServiceStudent ServiceStudent = new ServiceStudent();
    ServicesTeacher ServiceTeacher = new ServicesTeacher();
    ServicesUsers ServiceUsers = new ServicesUsers();

    @FXML
    private BorderPane AnchorepaneSignup;
    @FXML
    private JFXTextField txtFirstname;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private JFXComboBox<String> combotype;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXComboBox<String> txtgender;
    @FXML
    private DatePicker comboDate;
    @FXML
    private JFXTextField txtphone;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextField txtCity;

    public ObservableList<String> Occupation = FXCollections.observableArrayList("student", "teacher");
    public ObservableList<String> Gender = FXCollections.observableArrayList("male", "female");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        combotype.setItems(Occupation);
        txtgender.setItems(Gender);

    }

    @FXML
    private void SignUpAction(ActionEvent event) {
        loadScreen();
    }

    @FXML
    private void QrCodeAction(ActionEvent event) {
        loadUi("Qrcode");
    }

    @FXML
    private void Signup(ActionEvent event) {

        if (combotype.getSelectionModel().isEmpty() || txtlastname.getText().equals("")
                || txtFirstname.getText().equals("") || txtemail.getText().equals("")
                || txtphone.getText().equals("") || txtCountry.getText().equals("")
                || txtCity.getText().equals("") ) {
            AlertMaker.showErrorMessage("Failed", "Please Fill out ALL");

            return;
        }
        String date = comboDate.getValue().format(DateTimeFormatter.ISO_DATE);

        if ("student".equals(combotype.getValue())) {
            Student s = new Student(txtlastname.getText(), txtFirstname.getText(), txtemail.getText(), date, Integer.parseInt(txtphone.getText()), txtCountry.getText(), txtCity.getText(), txtgender.getValue());
            ServiceStudent.ajouterStudent(s);
            AlertMaker.showSimpleAlert("GeStudent", "Account student created successfully! "
                    + "Please wait for confirmation email");

        } else if ("teacher".equals(combotype.getValue())) {
            teacher t = new teacher(txtlastname.getText(), txtFirstname.getText(), txtemail.getText(), date, Integer.parseInt(txtphone.getText()), txtCountry.getText(), txtCity.getText(), txtgender.getValue());
            ServiceTeacher.ajouterTeacher(t);
            AlertMaker.showSimpleAlert("GeStudent", "Account created successfully! "
                    + "Please wait for confirmation email");
        }
    }

    private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorepaneSignup.setCenter(root);
    }

    private void loadScreen() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorepaneSignup.getScene().setRoot(root);
    }

    @FXML
    private void SignIn(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
            Scene scene = new Scene(root, 350, 350);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
