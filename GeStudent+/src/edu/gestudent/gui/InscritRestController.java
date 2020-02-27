/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import edu.gestudent.entities.Session;
import edu.gestudent.entities.inscriRest;
import edu.gestudent.services.inscriRestCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class InscritRestController implements Initializable {
    int iduser = Session.getCurrentSession();

    @FXML
    private ComboBox<String> duration;
    @FXML
    private TextField amount;
    @FXML
    private TextField identifiant;
    ObservableList<String> durationCombo = FXCollections.observableArrayList("par mois", "par semestre", "par an");
    @FXML
    private Button valider;
    public ObservableList<inscriRest> data = FXCollections.observableArrayList();

    inscriRestCrud in = new inscriRestCrud();

    public int getIdentifiant() {
        return Integer.parseInt(identifiant.getText());
    }

    public double getAmount() {
        return Double.parseDouble(amount.getText());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        duration.setItems(durationCombo);
        double val = 3.00;
      //  data.addAll(in.afficherinscri());

//       String str = Double.toString(val);
//        str = Double.toString(val);
//        
//       amount.setText(str);
    }

//    @FXML
//    private void login(ActionEvent event) {
//        String inscrit = identifiant.getText();
//        inscriRestCrud irc = new inscriRestCrud();
//
//        if (irc.InscriInterface(inscrit)) {
//            System.out.println("Loggin  incorrect(s)");
//        }
//
//        if (irc.inscriRestCrud(inscrit)) {     
//
//            System.out.println("Vous êtes connecté !");
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageStudentRest.fxml"));                //Acces interface .fxml
//            try {
//                Parent root = loader.load();
//                PageStudentRestController lc = loader.getController();					     //Acces controller 
//                identifiant.getScene().setRoot(root);							     //Ay fx:id mawjoud fel interface l jdida	
//
//            } catch (IOException ex) {
//                Logger.getLogger(InscriRestController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("inscriRest.fxml"));
//        try {
//            Parent root = loader.load();
//            InscriRestController lc = loader.getController();						//Acces controller 
//            identifiant.getScene().setRoot(root);									//Ay fx:id mawjoud fel interface l jdida
//
//        } catch (IOException ex) {
//            Logger.getLogger(InscriRestController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void addinscri(ActionEvent event) {
        inscriRest l;
        l = new inscriRest(iduser, duration.getValue(), getAmount());
        in.ajouterinscri(l);
        Alert succAddMealAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddMealAlert.setTitle("Add inscri");
        succAddMealAlert.setHeaderText("Results:");
        succAddMealAlert.setContentText("inscri added successfully!");
        succAddMealAlert.showAndWait();

////        data.clear();
        //data.addAll(in.afficherinscri());
    }

    @FXML
    public double calculAA() {

        double montant = 0.0;
        double prix = 1.00;
        switch (duration.getValue()) {
            case "par an":
                System.out.println("hhhhhhhhhhhhhh");
                montant = prix * 1000;
                break;
            case "par semestre":
                montant = prix * 500;
                break;
            case "par mois":
                montant = prix * 100;
                break;
            default:
                System.out.println("select duration");
                break;

        }
        amount.setText(Double.toString(montant));
        return montant;
    }
}
