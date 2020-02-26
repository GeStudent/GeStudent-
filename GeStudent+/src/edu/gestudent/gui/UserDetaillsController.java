/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import edu.gestudent.entities.Student;
import edu.gestudent.entities.user;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesUsers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private Button Delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        readonly();
    }

    public void readonly() {
        int i = Integer.parseInt(userid);
        List<Student> arr = new ArrayList<>();
        user u= se.findUsertbyid(i);
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
        if (1==1)
            Delete.setVisible(false);
            
        

    }

}
