/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordRestaurantController implements Initializable {

    @FXML
    private Button AjoutMealButton;
    @FXML
    private Button AjoutMealButton5;
    @FXML
    private Button AjoutMealButton4;
    @FXML
    private TableView<?> tabmeal;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> rating;
    @FXML
    private JFXTextField searhTF;
    @FXML
    private JFXTextField txtname;
    @FXML
    private JFXTextField txtimage;
    @FXML
    private JFXComboBox<?> txttype;
    @FXML
    private JFXComboBox<?> txtrating;
    @FXML
    private Button AjoutMealButton1;
    @FXML
    private Button AjoutMealButton51;
    @FXML
    private Button AjoutMealButton41;
    @FXML
    private Button ReturnButton1;
    @FXML
    private TableView<?> tabmenu;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private JFXTextField searchTF;
    @FXML
    private JFXTextField txtname1;
    @FXML
    private JFXTextField txtdescription;
    @FXML
    private Button AjoutMealButton11;
    @FXML
    private Button AjoutMealButton511;
    @FXML
    private TableView<?> tabmenumeal;
    @FXML
    private TableColumn<?, ?> namemenu;
    @FXML
    private TableColumn<?, ?> namemeal;
    @FXML
    private TableColumn<?, ?> typee;
    @FXML
    private TableColumn<?, ?> desriptionn;
    @FXML
    private JFXTextField filterField11;
    @FXML
    private JFXComboBox<?> cb_meal;
    @FXML
    private JFXComboBox<?> cb_menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addMeal(ActionEvent event) {
    }

    @FXML
    private void DeleteAction(ActionEvent event) {
    }

    @FXML
    private void EditAction(ActionEvent event) {
    }

    @FXML
    private void changeNomCellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void changeImageCellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void changetypeCellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void changeRatingCellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void addMenu(ActionEvent event) {
    }

    @FXML
    private void DeleteMenu(ActionEvent event) {
    }

    @FXML
    private void EditMenu(ActionEvent event) {
    }

    @FXML
    private void ReturnAction(ActionEvent event) {
    }

    @FXML
    private void changeNom1CellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void changeDesciptionCellEvent(TableColumn.CellEditEvent<S, T> event) {
    }

    @FXML
    private void addMenuMeal(ActionEvent event) {
    }

    @FXML
    private void DeleteMenuMeal(ActionEvent event) {
    }

    @FXML
    private void select(ActionEvent event) {
    }
    
}
