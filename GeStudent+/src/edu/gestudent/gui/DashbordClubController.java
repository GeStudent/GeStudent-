/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Club;
import edu.gestudent.services.ServiceClub;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordClubController implements Initializable {

//    @FXML
//    private Button AddButton;
//    @FXML
//    private Button EditButton;
//    @FXML
//    private Button DeleteButton;
//    @FXML
//    private Button EmailButton;
//    @FXML
//    private TableView<?> clubtv;
//    @FXML
//    private TableColumn<?, ?> nom;
//    @FXML
//    private TableColumn<?, ?> date;
//    @FXML
//    private TableColumn<?, ?> email;
//    @FXML
//    private TableColumn<?, ?> numero;
//    @FXML
//    private TableColumn<?, ?> description;
//    @FXML
//    private TableColumn<?, ?> etat;
//    @FXML
//    private TableColumn<?, ?> id_president;
//    @FXML
//    private JFXTextField txtnumero;
//    @FXML
//    private JFXTextField txtnom;
//    @FXML
//    private JFXTextField txtdescription;
//    @FXML
//    private DatePicker datetxt;
//    @FXML
//    private JFXTextField txtetat;
//    @FXML
//    private JFXTextField txtid_president;
//    @FXML
//    private JFXTextField txtemail;

    /**
     * Initializes the controller class.
     */
    ServiceClub Sc = new ServiceClub();

    @FXML
    private TextField txtid_club;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtnumero;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtetat;
    @FXML
    private TextField txtid_president;

 
   
    @FXML
    private TableColumn<Club, String> nom;
    @FXML
    private TableColumn<Club, String> date;
    @FXML
    private TableColumn<Club, String> email;

    @FXML
    private TableColumn<Club, String> numero;

    @FXML
    private TableColumn<Club, String> description;

    @FXML
    private TableColumn<Club, Integer> etat;
    
            
    @FXML
    private TableColumn<Club, String> id_president;
    public ObservableList<Club> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Club> clubtv;
    @FXML
    private Label club;
    @FXML
    private Button AddButton;
    @FXML
    private Button EditButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button SearchButton;
    @FXML
    private Button EmailButton;
    @FXML
    private DatePicker datetxt;

    public int getTxtnumero() {
        return Integer.parseInt(txtnumero.getText());
    }

    public int getTxtetat() {
        return Integer.parseInt(txtetat.getText());
    }

    public int getTxtid_president() {
        return Integer.parseInt(txtid_president.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(Sc.readAll());
        this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        this.id_president.setCellValueFactory(new PropertyValueFactory<>("id_president"));
        this.clubtv.setItems(data);

        //this for edit
        this.clubtv.setEditable(true);
        this.etat.setCellFactory(TextFieldTableCell.<Club, Integer>forTableColumn(new IntegerStringConverter()));

        // TODO
    }

    @FXML
    private void AddClub(ActionEvent event) throws SQLException {
        String date = datetxt.getValue().format(DateTimeFormatter.ISO_DATE);
        //System.out.println(date); chbik
        Club c;
        c = new Club(txtnom.getText(), date, txtemail.getText(), getTxtnumero(), txtdescription.getText(), getTxtetat(), getTxtid_president());
        Sc.ajouterClub(c);
        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Club");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Club addeeed successfully!");
        succAddBookAlert.showAndWait();

        data.clear();
        data.addAll(Sc.readAll());
    }


    @FXML
    private void EditClub(ActionEvent event) {
        
         if (clubtv.getSelectionModel().getSelectedItem() != null) {

            Club l = clubtv.getSelectionModel().getSelectedItem();

            Sc.Update(l.getEtat(), l.getNom());
            Alert clubAlert = new Alert(Alert.AlertType.INFORMATION);
            clubAlert.setTitle("edit");
            clubAlert.setHeaderText(null);
            clubAlert.setContentText("club was succfuly edit");
            clubAlert.showAndWait();

        } else {
            //Alert Select BOOK :
            Alert clubAlert = new Alert(Alert.AlertType.WARNING);
            clubAlert.setTitle("Select a CLub");
            clubAlert.setHeaderText(null);
            clubAlert.setContentText("You need to select club first!");
            clubAlert.showAndWait();
            //Alert Select Book !
        }
    }

    @FXML
    private void DeleteClub(ActionEvent event) {
        if (clubtv.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete Partner");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure want to delete this Club ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Club c = clubtv.getSelectionModel().getSelectedItem();
                Sc.supprimer(c);
                data.clear();
                data.addAll(Sc.readAll());
                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("Delete Club");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("Club deleted successfully!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a book");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select book first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }
    }
    @FXML
  public void changeEtatCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Club c = clubtv.getSelectionModel().getSelectedItem();
        c.setEtat(Integer.parseInt(edittedCell.getNewValue().toString()));
    }
    @FXML
    private void SearchClub(ActionEvent event) {
    }

    @FXML
    private void EmailClub(ActionEvent event) {
    }
    
}
