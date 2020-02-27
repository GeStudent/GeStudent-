/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.services.EmpruntCrud;
import edu.gestudent.services.LivreCrud;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashbordFronLibraryController implements Initializable {

    LivreCrud lcr = new LivreCrud();
    EmpruntCrud ecr = new EmpruntCrud();
    public ObservableList<Livre> data = FXCollections.observableArrayList();
    public ObservableList<Emprunt> dataemp = FXCollections.observableArrayList();

    @FXML
    private DatePicker txtdateE;
    @FXML
    private TableView<Livre> librarytv;
    @FXML
    private TableColumn<Livre, String> name;
    @FXML
    private TableColumn<Livre, String> image;
    @FXML
    private TableColumn<Livre, String> author;
    @FXML
    private TableColumn<Livre, String> url;
    @FXML
    private TableColumn<Livre, String> categiries;
    @FXML
    private TableColumn<Livre, Integer> quantity;
    @FXML
    private TableView<Emprunt> livreeemprunter;
    @FXML
    private TextField searchTF;
    @FXML
    private Button ReturnB;
    @FXML
    private TableColumn<Emprunt, String> name1;
    @FXML
    private TableColumn<Emprunt, String> date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(lcr.afficherlivre());
        dataemp.addAll(ecr.afficherlivreemprunte(1));

        this.author.setCellValueFactory(new PropertyValueFactory<>("author"));
        this.categiries.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.quantity.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        this.image.setCellValueFactory(new PropertyValueFactory<>("image"));
        this.url.setCellValueFactory(new PropertyValueFactory<>("url"));

        this.name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.date.setCellValueFactory(new PropertyValueFactory<>("date_retour"));

        this.librarytv.setItems(data);
        this.livreeemprunter.setItems(dataemp);

        //this for edit
        this.librarytv.setEditable(true);
        this.author.setCellFactory(TextFieldTableCell.forTableColumn());
        this.categiries.setCellFactory(TextFieldTableCell.forTableColumn());
        this.quantity.setCellFactory(TextFieldTableCell.<Livre, Integer>forTableColumn(new IntegerStringConverter()));
        this.image.setCellFactory(TextFieldTableCell.forTableColumn());
        this.url.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    private void emprunterlivre(ActionEvent event) {
        Livre L = librarytv.getSelectionModel().getSelectedItem();

        Emprunt E = new Emprunt(String.valueOf(txtdateE.getValue()), 1, L.getId_livre());
        if (txtdateE.getValue().isBefore(LocalDate.now())) {
            AlertMaker.showErrorMessage("Date Failed !", "The Date can't be in the Past !");
            return;
        }
        if (ecr.checkLivre(E.getId(), E.getId_livre())) {
            AlertMaker.showErrorMessage("Sorry you can't borrow the book !", "You already have one ,please return it at time !");
            return;
        }
        Alert succemprunterlivreAlert = new Alert(Alert.AlertType.INFORMATION);
        succemprunterlivreAlert.setTitle("Add book");
        succemprunterlivreAlert.setHeaderText("Results:");
        succemprunterlivreAlert.setContentText("Book added successfully!");
        succemprunterlivreAlert.showAndWait();
        ecr.ajouterEmprunt(E);

        dataemp.clear();
        dataemp.addAll(ecr.afficherlivreemprunte(1));
        data.clear();
        data.addAll(lcr.afficherlivre());

    }



    @FXML
    private void retournerlivre(ActionEvent event) {
        if (livreeemprunter.getSelectionModel().getSelectedItem() != null) {
            Alert retournerlivreAlert = new Alert(Alert.AlertType.CONFIRMATION);
            retournerlivreAlert.setTitle("Delete book");
            retournerlivreAlert.setHeaderText(null);

            retournerlivreAlert.setContentText("Do want to return this book ?");
            Optional<ButtonType> optionDeleteBookAlert = retournerlivreAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Emprunt E = livreeemprunter.getSelectionModel().getSelectedItem();
                // System.out.println("ide:"+E.getId_emprunt());
                E.setId_livre(lcr.getidliv(E.getName()));
                // System.out.println(E.toString());
                ecr.supprimeremprunt(E, E.getId_emprunt());
                // System.out.println(lcr.getidliv(E.getName()));

                dataemp.clear();
                dataemp.addAll(ecr.afficherlivreemprunte(1));
                data.clear();
                data.addAll(lcr.afficherlivre());
                //Alert Delete Blog :
                Alert succretournerlivreAlert = new Alert(Alert.AlertType.INFORMATION);
                succretournerlivreAlert.setTitle("Blog");
                succretournerlivreAlert.setHeaderText("Results:");
                succretournerlivreAlert.setContentText("Book is returned!");

                succretournerlivreAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a book");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a book first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }
    }

    @FXML
    private void filter(KeyEvent event) {
        data.clear();
        // System.out.println("heyy yuuu");
        data.addAll(lcr.afficherlivre().stream().filter((art)
                -> art.getName().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getAuthor().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getCategorie().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }

}
