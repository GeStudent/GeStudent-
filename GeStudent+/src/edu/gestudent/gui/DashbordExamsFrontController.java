/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import edu.gestudent.entities.Behaviour;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.exams;
import edu.gestudent.services.classEtudiantCRUD;

import edu.gestudent.services.examsCRUD;
import edu.gestudent.services.tsaCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashbordExamsFrontController implements Initializable {

    //public ObservableList<Behaviour> listaward = FXCollections.observableArrayList(ts.afficherBehaviourstu(iduser, Integer.parseInt(idstudent)));
    @FXML
    private TableView<exams> examtv;
    @FXML
    private TableColumn<exams, String> nomex;
    @FXML
    private TableColumn<exams, String> duree;
    @FXML
    private TableColumn<exams, String> dateex;
    @FXML
    private TableView<Behaviour> sttv1;
    @FXML
    private TableColumn<Behaviour, String> databeh;
    @FXML
    private TableColumn<Behaviour, Integer> dataaward;
    public ObservableList<exams> data = FXCollections.observableArrayList();
    int idetudiant = Session.getCurrentSession();

    classEtudiantCRUD CE = new classEtudiantCRUD();
    int idclass = CE.getclass(idetudiant);

    examsCRUD exc = new examsCRUD();
        tsaCRUD ts = new tsaCRUD();

    
        public ObservableList<Behaviour> listaward = FXCollections.observableArrayList(ts.afficherBehaviourstu(idetudiant));


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data.addAll(exc.afficherexClass(idclass));

        this.nomex.setCellValueFactory(new PropertyValueFactory<>("nomex"));
        this.dateex.setCellValueFactory(new PropertyValueFactory<>("dateex"));
        this.duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        this.examtv.setItems(data);
//        examtv.setRowFactory(tv -> {
//            TableRow<edu.gestudent.entities.exams> row = new TableRow<>();
//            row.setOnMouseClicked(e -> {
//
//                Object selectedItems = examtv.getSelectionModel().getSelectedItems().get(0);
//                idexam = selectedItems.toString().split(",")[0].substring(0);
//                System.out.println("id sutdent:" + idexam);
////                listeClass.clear();
////                listeClass.addAll(ts.afficherBehaviourstu(iduser, Integer.parseInt(idstudent)));
////                this.sttv1.setItems(listaward);
//            });
//            return row;
//        });
        // TODO
        

          this.dataaward.setCellValueFactory(new PropertyValueFactory<>("award"));
        this.databeh.setCellValueFactory(new PropertyValueFactory<>("nombeh"));
        this.sttv1.setItems(listaward);
    }

}
