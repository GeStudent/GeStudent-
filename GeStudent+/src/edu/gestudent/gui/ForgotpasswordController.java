/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.gestudent.entities.Session;
import static edu.gestudent.entities.SmsSender.ACCOUNT_SID;
//import static edu.gestudent.entities.SmsSender.API_PHONE;
import static edu.gestudent.entities.SmsSender.AUTH_TOKEN;
import edu.gestudent.entities.user;
import edu.gestudent.services.BCrypt;
import edu.gestudent.services.CryptServices;
import edu.gestudent.services.ServicesUsers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class ForgotpasswordController implements Initializable {

    @FXML
    private JFXTabPane resetTab;
    @FXML
    private Tab fyuTab;
    @FXML
    private JFXTextField forgotPwdLabel;
    @FXML
    private Text promptLabel;
    @FXML
    private JFXButton forgotBtnLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Tab verifTab;
    @FXML
    private JFXTextField verifCodeField;
    @FXML
    private JFXButton veriftCodeBtn;
    @FXML
    private Label errorLabelCode;
    @FXML
    private Tab resetPTab;
    @FXML
    private AnchorPane dialogContainer;
    @FXML
    private JFXPasswordField newPassField;
    @FXML
    private JFXButton resetPassBtn;
    @FXML
    private Label errorPwdLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        verifTab.setDisable(true);
        resetPTab.setDisable(true);
    }

    @FXML
    private void nextCliecked(ActionEvent event) {
        user u = new user();
        String pin = "";
        ServicesUsers us = new ServicesUsers();

        u.setUsername(forgotPwdLabel.getText());
        if (us.findUser(u) == 1) {

            String phnNum = us.getPhoneByUser(u.getUsername());
            System.out.println(phnNum);

            phnNum = "29025104";
            Session.generatePIN();

            Twilio.setUsername(ACCOUNT_SID);
            Twilio.setPassword(AUTH_TOKEN);
            Message mressage = Message
                    .creator(new PhoneNumber("+216" + phnNum), // to
                            new PhoneNumber("+12036939921"), // from
                            "Your code is : " + Session.getPin())
                    .create();

            /*     SmsSender.SendSMS(phnNum,
                    API_PHONE,
                    "Your code is :11111 " );*/
            fyuTab.setDisable(true);
            verifTab.setDisable(false);
        } else {
            errorLabel.setText("Please verify your username!");
        }
    }

    @FXML
    public void returnHomeClickedAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent signUpPage = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
        Scene scene = new Scene(signUpPage);
        stage = (Stage) forgotBtnLabel.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void verifyClicked(ActionEvent event) {
        if (!Session.getPin().equals(verifCodeField.getText())) {
            errorLabelCode.setText("Code not correct! please try again");
        } else {
            resetTab.getSelectionModel().select(2);
            verifTab.setDisable(true);
            resetPTab.setDisable(false);
        }
    }

    @FXML
    private void resetClicked(ActionEvent event) {
        if (newPassField.getText().isEmpty()) {
            errorPwdLabel.setText("Please verify your new password!");
        } else {
            user u = new user();
            ServicesUsers us = new ServicesUsers();
            u.setUsername(forgotPwdLabel.getText());
            String hashed2 = BCrypt.hashpw(newPassField.getText(), BCrypt.gensalt(13));
            hashed2 = "$2y$" + hashed2.substring(4);
            u.setPassword(hashed2);
            if (us.changePwd(u) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Password reset successful!");
                alert.showAndWait();

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
                    Scene scene = new Scene(root, 350, 350);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException ex) {
                    ex.getMessage();
                }

                //   returnHomeClickedAction();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Query Result:");
                alert.setContentText("Error! please try again");
                alert.showAndWait();
            }
        }
    }

//    @FXML
//    private JFXTabPane resetTab;
//    @FXML
//    private Tab fyuTab;
//    @FXML
//    private JFXTextField forgotPwdLabel;
//    @FXML
//    private Text promptLabel;
//    @FXML
//    private JFXButton forgotBtnLabel;
//    @FXML
//    private Label errorLabel;
//    @FXML
//    private Tab verifTab;
//    @FXML
//    private JFXTextField verifCodeField;
//    @FXML
//    private JFXButton veriftCodeBtn;
//    @FXML
//    private Label errorLabelCode;
//    @FXML
//    private Tab resetPTab;
//    @FXML
//    private AnchorPane dialogContainer;
//    @FXML
//    private JFXPasswordField newPassField;
//    @FXML
//    private JFXButton resetPassBtn;
//    @FXML
//    private Label errorPwdLabel;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//
//    @FXML
//    private void nextCliecked(ActionEvent event) {
//    }
//
//    @FXML
//    private void returnHomeClickedAction(ActionEvent event) {
//    }
//
//    @FXML
//    private void verifyClicked(ActionEvent event) {
//    }
//
//    @FXML
//    private void resetClicked(ActionEvent event) {
//    }
}
