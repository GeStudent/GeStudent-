/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.services.CryptServices;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesTeacher;
import edu.gestudent.services.ServicesUsers;
import edu.gestudent.services.UploadServices;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class QrcodeController implements Initializable {

    @FXML
    private JFXTextField txtQrCode;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private JFXButton uploadbutton;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXPasswordField txtconformation;

    ServiceStudent ServiceStudent = new ServiceStudent();
    ServicesTeacher ServiceTeacher = new ServicesTeacher();
    ServicesUsers ServiceUsers = new ServicesUsers();

    UploadServices uploadservices = new UploadServices();
    String FilenameInserver = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void uploadimage(ActionEvent event) {

        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            txtimage.setText(imageFile);
            FilenameInserver = uploadservices.upload(txtimage.getText());

        }

    }

    @FXML
    private void createAcount(ActionEvent event) {

        if ("".equals(txtQrCode.getText()) || "".equals(txtimage.getText()) || "".equals(txtpassword.getText()) || "".equals(txtusername.getText())) {

            AlertMaker.showErrorMessage("Failed", "Please Fill out ALL");
            return;
        }
        if (!txtpassword.getText().equals(txtconformation.getText()) || txtpassword.getText().equals("")) {

            AlertMaker.showErrorMessage("Failed", "Password not matched");

            return;

        }

        if (ServiceUsers.QrcodeisUsed(txtQrCode.getText()) == 1) {
            AlertMaker.showwarningMessage("Failed", "Qr code is aleardy used");

            return;
        }

        if (ServiceUsers.ajouterAccount(txtusername.getText(), FilenameInserver, CryptServices.encrypt(txtpassword.getText(), CryptServices.getSecretKey()), txtQrCode.getText())) {
            Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);

            AlertMaker.showSimpleAlert("GeStudent", "Account created successfully!");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));
                Scene scene = new Scene(root, 350, 350);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException ex) {
                ex.getMessage();
            }

        } else {

            AlertMaker.showErrorMessage("Failed", "User name is aleardy used");
            return;

        }

    }

}
