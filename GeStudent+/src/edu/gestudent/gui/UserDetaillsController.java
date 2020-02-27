/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import edu.gestudent.entities.Email;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.Pdf;
import edu.gestudent.entities.Student;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesUsers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class UserDetaillsController extends DashbordUsersController implements Initializable {

    @FXML
    private ImageView DetailImage;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label LastnameLabel;
    @FXML
    private Label EmailLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label CountryLabel;
    @FXML
    private Label StateLabel;
    @FXML
    private Label GenderLabel;
    @FXML
    private Label Rolelabel;

    ServicesUsers se = new ServicesUsers();

    String userid = holdId.value;
    int i = Integer.parseInt(userid);

    user u = se.findUsertbyid(i);

    @FXML
    private Button Delete;
    @FXML
    private Button Qrcode;
    @FXML
    private JFXButton Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        readonly();
    }

    public void readonly() {
        List<Student> arr = new ArrayList<>();
        u = se.findUsertbyid(i);
        System.out.println(u);
        CountryLabel.setText(u.getPays());
        EmailLabel.setText(u.getEmail());
        GenderLabel.setText(u.getGender());
        LastnameLabel.setText(u.getLastname());
        Rolelabel.setText(u.getRoles());
        StateLabel.setText(u.getAdress());
        firstnameLabel.setText(u.getFirstname());
        phoneLabel.setText(String.valueOf(u.getPhone()));
        dateLabel.setText(u.getBirthDay());
        Image image = new Image("http://localhost/images/uploads/" + u.getImage());

        DetailImage.setImage(image);
        if (se.QrcodeisUsed(se.getQRcode(u.getEmail())) == 1) {
            Qrcode.setVisible(false);
            Supprimer.setVisible(false);

        } else {
            Delete.setVisible(false);
        }

    }

    @FXML
    private void print(ActionEvent event) {

        Pdf pdf = new Pdf();

        try {
            pdf.GeneratePdf(u);
        } catch (DocumentException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (InterruptedException ex) {
            ex.getMessage();
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void Qrcode(ActionEvent event) {

        Email email = new Email();
        HashMap<String, String> message = new HashMap<String, String>();
        message.put("Title", "Confirmation account ");
        message.put("Content", "Congratulations, you have been approved! Please use the code below for you account " + se.getQRcode(u.getEmail()));

        try {
            email.sendEmail(u.getEmail(), "GeStudent", message);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {

        Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteBookAlert.setTitle("Delete ");
        deleteBookAlert.setHeaderText(null);
        deleteBookAlert.setContentText("Are you sure that you want to delete  ?");
        Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
        if (optionDeleteBookAlert.get() == ButtonType.OK) {

            //Alert Delete Blog :
            Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
            succDeleteBookAlert.setTitle("Delete Blog");
            succDeleteBookAlert.setHeaderText("Results:");
            succDeleteBookAlert.setContentText("Book deleted successfully!");
            se.delete(se.getQRcode(u.getEmail()));

            succDeleteBookAlert.showAndWait();
        } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

        }

    }

}
