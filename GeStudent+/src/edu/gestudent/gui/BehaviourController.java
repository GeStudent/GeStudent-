/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;


import edu.gestudent.entities.Behaviour;
import edu.gestudent.entities.exams;
import edu.gestudent.services.behaviourCRUD;
import edu.gestudent.services.examsCRUD;
import edu.gestudent.utils.gestudentAssistantUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BehaviourController implements Initializable {
    
   behaviourCRUD behc = new behaviourCRUD();
  @FXML
    private TextField txtnameaward;
    /**
     * Initializes the controller class.
     */
     static JFrame f;
    behaviourCRUD bhcr = new behaviourCRUD();
        public ObservableList<Behaviour> data = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> comboaward;

    public ObservableList<Integer> awards = FXCollections.observableArrayList(-2,-1,1,2);
    @FXML
    private TableView<Behaviour> sttv;
    @FXML
    private TableView<Behaviour> awardtv;
    @FXML
    private TableColumn<Behaviour, String> nombeh;
    @FXML
    private TableColumn<Behaviour, Integer> award;
    @FXML
    private Button Timer;
    @FXML
    private TableView<?> sttv1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  data.addAll(bhcr.afficherBehaviour());
        comboaward.setItems(awards);
          data.addAll(behc.afficherBehaviour());
    
      
        // TODO
       this.nombeh.setCellValueFactory(new PropertyValueFactory<>("nombeh"));
       this.award.setCellValueFactory(new PropertyValueFactory<>("award"));
      
        this.awardtv.setItems(data);
    }

    @FXML
    private void Timer(ActionEvent event) throws IOException {
  
             gestudentAssistantUtil.loadWindow(getClass().getResource("Timer.fxml"), "Timer", null);

    }

    @FXML
    private void addaward(ActionEvent event) {
                Behaviour b;
                        b = new Behaviour(comboaward.getValue(),txtnameaward.getText());
        behc.ajouterbeh(b);
        Alert succAddBehAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddBehAlert.setTitle("Add Award");
        succAddBehAlert.setHeaderText("Results:");
        succAddBehAlert.setContentText("Award added successfully!");
        succAddBehAlert.showAndWait();

        data.clear();
        data.addAll(behc.afficherBehaviour());
    }

    @FXML
    private void deleteaward(ActionEvent event) {
           if (awardtv.getSelectionModel().getSelectedItem() != null) {
            Alert deletebehAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deletebehAlert.setTitle("Delete Partner");
            deletebehAlert.setHeaderText(null);
            deletebehAlert.setContentText("Are you sure want to delete this Award ?");
            Optional<ButtonType> optionDeletebehAlert = deletebehAlert.showAndWait();
            if (optionDeletebehAlert.get() == ButtonType.OK) {
                Behaviour b = awardtv.getSelectionModel().getSelectedItem();
                try {
                    behc.delete(b);
                } catch (SQLException ex) {
                    Logger.getLogger(ExamenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                data.clear();
                data.addAll(behc.afficherBehaviour());

                //Alert Delete Blog :
                Alert succDeleteExamAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteExamAlert.setTitle("Delete Exam");
                succDeleteExamAlert.setHeaderText("Results:");
                succDeleteExamAlert.setContentText("Exam deleted successfully!");

                succDeleteExamAlert.showAndWait();
            } else if (optionDeletebehAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select Exam :
            Alert selectBehAlert = new Alert(Alert.AlertType.WARNING);
            selectBehAlert.setTitle("Select a Award");
            selectBehAlert.setHeaderText(null);
            selectBehAlert.setContentText("You need to select Award first!");
            selectBehAlert.showAndWait();
            //Alert Select Exam !

        }
    }

    @FXML
    private void Msg(ActionEvent event) {
        chat_server cs= new chat_server();
         cs.setVisible(true);

        
    }
    
}
