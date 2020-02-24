/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServicesUsers;
import edu.gestudent.services.UploadServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordProfileController implements Initializable {

    @FXML
    private ImageView newimage;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private JFXTextField txtphone;
    @FXML
    private DatePicker txtdate;
    @FXML
    private JFXTextField txtimage;
    int iduser = Session.getCurrentSession();

    UploadServices uploadservices = new UploadServices();
    ServicesUsers ServiceUser = new ServicesUsers();
    @FXML
    private JFXTextField currentpassword;
    @FXML
    private JFXTextField newpassword;
    @FXML
    private JFXTextField confirmepassword;
    @FXML
    private Label txtstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user u = ServiceUser.findbyid(iduser);

        txtusername.setText(u.getUsername());
        txtfirstname.setText(u.getFirstname());
        txtlastname.setText(u.getLastname());
        txtphone.setText(String.valueOf(u.getPhone()));
        String imagename = ServiceUser.getImage(iduser);
       
        Image image = new Image("http://localhost/images/uploads/" + imagename);
        // profileimage.setImage(image);
        newimage.setImage(image);

        LocalDate parse = LocalDate.parse(u.getBirthDay());
        parse.getYear();
        parse.getMonthValue();
        parse.getDayOfMonth();
        LocalDate of = LocalDate.of(parse.getYear(), parse.getMonthValue(), parse.getDayOfMonth());
        txtdate.setValue(of);
        txtstatus.setText(u.getRoles());
        // TODO
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

            String imagename = ServiceUser.getImage(iduser);
            Image image = new Image("http://localhost/images/uploads/" + imagename);
            // profileimage.setImage(image);
            newimage.setImage(image);
            //get image name

        }
    }

    @FXML
    private void ApplyChanepassword(ActionEvent event) {
    }
    
   

}
