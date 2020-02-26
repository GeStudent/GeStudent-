/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Menu;
import edu.gestudent.entities.MenuMeal;
import edu.gestudent.entities.meal;
import edu.gestudent.entities.teacher;
import edu.gestudent.services.MenuCrud;
import edu.gestudent.services.MenuMealCrud;
import edu.gestudent.utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class FrontRestaurantController implements Initializable {

    private Button btnsinscrire;
    @FXML
    private TableView<Menu> tabmenu;
    @FXML
    private TableColumn<Menu, String> nom1;
    @FXML
    private TableColumn<Menu, String> description;

    MenuCrud mn = new MenuCrud();
    MenuMealCrud mnc = new MenuMealCrud();
    public ObservableList<Menu> data = FXCollections.observableArrayList();
    public ObservableList<meal> data1 = FXCollections.observableArrayList();
    public ObservableList<MenuMeal> data2 = FXCollections.observableArrayList();
    @FXML
    private TextField searchTF;
    @FXML
    private TableView<MenuMeal> tabmenumeal;
    @FXML
    private TableColumn<MenuMeal, String> namemenu;
    @FXML
    private TableColumn<MenuMeal, String> namemeal;
    @FXML
    private TableColumn<MenuMeal, String> typee;
    @FXML
    private TableColumn<MenuMeal, String> desriptionn;
  
    @FXML
    private Button select;
    private Connection con;

    @FXML
    PieChart piechart;
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList<String>();
    ArrayList< Integer> c = new ArrayList<Integer>();

    String idMenu = "0";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data2.addAll(mnc.afficherMenuMeal(""));

        this.tabmenumeal.setEditable(true);
        this.namemenu.setCellValueFactory(new PropertyValueFactory<>("namemenu"));
        this.namemeal.setCellValueFactory(new PropertyValueFactory<>("namemeal"));
        this.typee.setCellValueFactory(new PropertyValueFactory<>("typemenumeal"));
        this.desriptionn.setCellValueFactory(new PropertyValueFactory<>("description"));

        this.tabmenumeal.setItems(data2);
        this.tabmenu.setEditable(true);
        this.nom1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        data.addAll(mn.afficherMenu());
        this.tabmenu.setItems(data);

        tabmenu.setRowFactory(tv -> {
            TableRow<Menu> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = tabmenu.getSelectionModel().getSelectedItems().get(0);
                idMenu = selectedItems.toString().split(",")[1].substring(0);
                System.out.println("idmenu:" + idMenu);

                data2.clear();
               data2.addAll(mnc.afficherMenuMeal(idMenu));
                this.tabmenumeal.setItems(data2);
//                dataAffectation.clear();
//                dataAffectation.addAll(t.rechercheprof(Integer.parseInt(idteacher)));
//                this.tc.setItems(dataAffectation);
            });
            return row;
        });

        loadData();
        piechart.setData(piechartdata);

    }

    private void depsinscrie(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("inscriRest.fxml"));
        Parent root = loader.load();
        btnsinscrire.getScene().setRoot(root);
    }

    private void stat(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("statrest.fxml"));
        Parent root = loader.load();
        btnsinscrire.getScene().setRoot(root);
    }

    @FXML
    public void changeNom1CellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Menu MenuSelected = tabmenu.getSelectionModel().getSelectedItem();
        MenuSelected.setName(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeDesciptionCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Menu MenuSelected = tabmenu.getSelectionModel().getSelectedItem();
        MenuSelected.setDescription(edittedCell.getNewValue().toString());
    }

    public void changeNameMenuCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        MenuMeal MenuMealSelected = tabmenumeal.getSelectionModel().getSelectedItem();
        MenuMealSelected.setNamemenu(edittedCell.getNewValue().toString());
    }

    public void changeNameMealCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        MenuMeal MenuMealSelected = tabmenumeal.getSelectionModel().getSelectedItem();
        MenuMealSelected.setNamemeal(edittedCell.getNewValue().toString());
    }

    public void changetypemenumealCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        MenuMeal MenuMealSelected = tabmenumeal.getSelectionModel().getSelectedItem();
        MenuMealSelected.setTypemenumeal(edittedCell.getNewValue().toString());
    }

    public void changeDesciptionmnCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        MenuMeal MenuMealSelected = tabmenumeal.getSelectionModel().getSelectedItem();
        MenuMealSelected.setDescription(edittedCell.getNewValue().toString());
    }




    @FXML
    private void filter1s(KeyEvent event) {
        data.clear();
        data.addAll(mn.afficherMenu().stream().filter((art)
                -> art.getName().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getDescription().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));

    }

    private void loadData() {

        String query = "select * From meal ORDER BY rating desc";

        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("name"), rs.getInt("rating")));
                p.add(rs.getString("name"));
                c.add(rs.getInt("rating"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
