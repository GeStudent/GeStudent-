/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import doryan.windowsnotificationapi.fr.Notification;
import edu.gestudent.entities.Club;
import edu.gestudent.entities.Evenement;
import edu.gestudent.entities.EventClient;
import edu.gestudent.services.ServiceClub;
import edu.gestudent.services.ServiceEvenement;
import edu.gestudent.services.ServiceEventClub;
import edu.gestudent.utils.DataBase;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
 * @author Asus
 */
public class DashbordFrontClubController implements Initializable {

  ServiceEvenement Se = new ServiceEvenement();
    ServiceClub Sc = new ServiceClub();
    ServiceEventClub Ec = new ServiceEventClub();

    @FXML
    private TextField txtid_club;
      @FXML
    private ComboBox<Integer> comboetat;
          public ObservableList<Integer> etatt = FXCollections.observableArrayList(0,1);
    @FXML
    private TextField txtnb_place;
    @FXML
    private TextField txtdescription;
    @FXML
    PieChart piechart;
    Connection con;
        ObservableList< PieChart.Data> piechartdata;

    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    @FXML
    private Button AddButton;
    public ObservableList<EventClient> data1 = FXCollections.observableArrayList();

    @FXML
    private Button EditButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button SearchButton;
    @FXML
    private Button EmailButton;
    @FXML
    private TextField txtnom;
    @FXML
    private Button ReturnButton;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> date;
    @FXML
    private TableColumn<Evenement, String> place;

    @FXML
    private TableColumn<Evenement, Integer> id_club;
    @FXML
    private TableColumn<Evenement, Integer> nb_place;

    public ObservableList<Evenement> data = FXCollections.observableArrayList();

    public ObservableList<Club> data2 = FXCollections.observableArrayList();

    @FXML
    private DatePicker datetxt;
    @FXML
    private TextField txtplace;
    @FXML
    private TableView<Evenement> evenementtv;
    @FXML
    private TextField txtid_event;
    @FXML
    private TableColumn<Club, String> email;
    @FXML
    private TableColumn<?, ?> id_event;
    @FXML
    private TextField txtid_club1;
    @FXML
    private TextField txtid_president;
    @FXML
    private TextField txtdescription1;
    @FXML
    private TextField txtnumero;
    @FXML
    private TextField txtemail;
    @FXML
    private Button AddButton1;
    @FXML
    private Button EditButton1;
    @FXML
    private Button DeleteButton1;
    @FXML
    private Button SearchButton1;
    @FXML
    private Button EmailButton1;
    @FXML
    private TextField txtnom1;
    @FXML
    private TableView<Club> clubtv;
    @FXML
    private TableColumn<Club, Integer> id_club1;
    @FXML
    private TableColumn<Club, String> nom1;
    @FXML
    private TableColumn<Club, String> date1;

    @FXML
    private TableColumn<Club, String> numero;

    @FXML
    private TableColumn<Club, String> description1;

    @FXML
    private TableColumn<Club, Integer> etat;

    @FXML
    private TableColumn<Club, String> id_president;
   
    @FXML
    private DatePicker datetxt1;
    @FXML
    private Button ReserverButton;
    @FXML
    private Button ReserverButton1;
    ServiceEvenement ser = new ServiceEvenement();
    ServiceEventClub ces = new ServiceEventClub();

    @FXML
    private TableColumn<EventClient, String> nameevent;
    @FXML
    private TableColumn<EventClient, String> datereservationevent;
    int id = 1;
    @FXML
    private TableView<EventClient> eventclienttv;

    public int getTxtid_club() {
        return Integer.parseInt(txtid_club.getText());
    }

    public int getTxtid_event() {
        return Integer.parseInt(txtid_event.getText());
    }

    public int getTxtnb_place() {
        return Integer.parseInt(txtnb_place.getText());
    }

    public int getTxtnumero() {
        return Integer.parseInt(txtnumero.getText());
    }

   /* public int getTxtetat() {
        return Integer.parseInt(comboetat.getText());
    }*/

    public int getTxtid_president() {
        return Integer.parseInt(txtid_president.getText());
    }

    //club
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(Se.readAll());
        this.nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.place.setCellValueFactory(new PropertyValueFactory<>("place"));
        this.id_club.setCellValueFactory(new PropertyValueFactory<>("id_club"));
        this.nb_place.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
 comboetat.setItems(etatt);
        this.evenementtv.setItems(data);
        //this for edit
        this.evenementtv.setEditable(true);
        this.id_club.setCellFactory(TextFieldTableCell.<Evenement, Integer>forTableColumn(new IntegerStringConverter()));

        this.date.setCellFactory(TextFieldTableCell.forTableColumn());
        // TODO
        data2.addAll(Sc.readAll());
        this.id_club1.setCellValueFactory(new PropertyValueFactory<>("id_club"));
        this.nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.date1.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        this.id_president.setCellValueFactory(new PropertyValueFactory<>("id_president"));
        this.clubtv.setItems(data2);

        data1.addAll(Ec.afficherEvenementReserve(1));
        this.nameevent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.datereservationevent.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
        this.eventclienttv.setItems(data1);
        //this for edit
        this.clubtv.setEditable(true);
        this.etat.setCellFactory(TextFieldTableCell.<Club, Integer>forTableColumn(new IntegerStringConverter()));
        // TODO
        loadData();
        piechart.setData(piechartdata);
    }

    @FXML
    private void Add1(ActionEvent event) {
        String date = datetxt.getValue().format(DateTimeFormatter.ISO_DATE);
        Evenement e;
        e = new Evenement(getTxtid_event(), txtnom.getText(), txtdescription.getText(), date, txtplace.getText(), getTxtid_club(), getTxtnb_place());
  if(datetxt.getValue().isBefore(LocalDate.now())){
        AlertMaker.showErrorMessage("Date Failed","The date can't be in the past");
        return;
  }
        Se.ajouter(e);
        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Evenement");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Evenement addeeed successfully!");
        succAddBookAlert.showAndWait();
        data.clear();
        data.addAll(Se.readAll());
    }

 
//    private void ReturnAction(ActionEvent event) {
//        try {
//            Notification.sendNotification("reseveeee", TrayIcon.MessageType.INFO);
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
//                Parent root = loader.load();
//                HomepageController spc = loader.getController();
//                ReturnButton.getScene().setRoot(root);
//            } catch (IOException ex) {
//                Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (AWTException ex) {
//            Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void Edit1(ActionEvent event) {

        if (evenementtv.getSelectionModel().getSelectedItem() != null) {

            Evenement e = evenementtv.getSelectionModel().getSelectedItem();
            //Se.Update(e.getNom(), e.getDate());
            Se.Update(e.getDate(), e.getNom());

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
    private void Delete1(ActionEvent event) {
        if (evenementtv.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete Partner");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure want to delete this evenement ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                try {
                    Evenement e = evenementtv.getSelectionModel().getSelectedItem();
                    Se.supprimer(e);
                    data.clear();
                    data.addAll(Se.readAll());
                    //Alert Delete Blog :
                    Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                    succDeleteBookAlert.setTitle("Delete Evenement");
                    succDeleteBookAlert.setHeaderText("Results:");
                    succDeleteBookAlert.setContentText("evenement deleted successfully!");

                    succDeleteBookAlert.showAndWait();
                } catch (SQLException ex) {
                    Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a evenement");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select evenement first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }
    }

    @FXML
    private void Search1(ActionEvent event) {
    }

    @FXML
    private void Email1(ActionEvent event) {
    }

    @FXML
    private void AddClub(ActionEvent event) {
        String date = datetxt1.getValue().format(DateTimeFormatter.ISO_DATE);
        //System.out.println(date); chbik
        Club c;
        c = new Club(txtnom1.getText(), date, txtemail.getText(), getTxtnumero(), txtdescription1.getText(), comboetat.getValue() , getTxtid_president());
      if(datetxt1.getValue().isBefore(LocalDate.now())){
        AlertMaker.showErrorMessage("Date Failed","The date can't be in the past");
        return;
  }
        Sc.ajouterClub(c);
        Alert succAddBookAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBookAlert.setTitle("Add Club");
        succAddBookAlert.setHeaderText("Results:");
        succAddBookAlert.setContentText("Club addeeed successfully!");
        succAddBookAlert.showAndWait();

        data2.clear();
        data2.addAll(Sc.readAll());
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
                data2.clear();
                data2.addAll(Sc.readAll());
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
    private void SearchClub(ActionEvent event) {
    }



    @FXML
    private void Return1(ActionEvent event) {
    }

    @FXML
    public void changeDateCellEvent(TableColumn.CellEditEvent edittedCell) {
        Evenement e = evenementtv.getSelectionModel().getSelectedItem();
        e.setDate(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeEtatCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Club c = clubtv.getSelectionModel().getSelectedItem();
        c.setEtat(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    @FXML
    private void Reserver(ActionEvent event) {

        Evenement e = evenementtv.getSelectionModel().getSelectedItem();

        EventClient reservation = new EventClient(1, ces.getidEvent(e.getNom()));
        reservation.setNom(e.getNom());
        Alert succemprunterlivreAlert = new Alert(Alert.AlertType.INFORMATION);
        succemprunterlivreAlert.setTitle("reservation");
        succemprunterlivreAlert.setHeaderText("Results:");
        succemprunterlivreAlert.setContentText("Event reserved successfully!");
        succemprunterlivreAlert.showAndWait();
        // ecr.ajouterEmprunt(E);
        Ec.ajouterEventClub(reservation);
        data1.clear();
        data1.addAll(ces.afficherEvenementReserve(1));
        data.clear();
        data.addAll(ser.readAll());
        try {
            Notification.sendNotification("reseveeee", TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DashbordFrontClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Annuler(ActionEvent event) {
       EventClient reservation = eventclienttv.getSelectionModel().getSelectedItem();

        //EventClient reservation  = new EventClient(1,ces.getidEvent(e.getNom()));
        //reservation.setNom(e.getNom());
        // ecr.ajouterEmprunt(E);
        Ec.supprimerEventClub(reservation);
        data1.clear();
        data1.addAll(ces.afficherEvenementReserve(1));
        data.clear();
        data.addAll(ser.readAll());
        Alert succemprunterlivreAlert = new Alert(Alert.AlertType.INFORMATION);
        succemprunterlivreAlert.setTitle("reservation");
        succemprunterlivreAlert.setHeaderText("Results:");
        succemprunterlivreAlert.setContentText("Event reserved successfully!");
        succemprunterlivreAlert.showAndWait();
    }

    public void loadData() {

        String query = "select COUNT(*) as count,nom From Evenement GROUP BY nom"; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("nom"), rs.getInt("count")));
                p.add(rs.getString("nom"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void EmailClub(ActionEvent event) {
    }
}
