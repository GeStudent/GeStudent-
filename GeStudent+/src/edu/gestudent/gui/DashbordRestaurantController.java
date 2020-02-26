/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.Menu;
import edu.gestudent.entities.MenuMeal;
import edu.gestudent.entities.meal;
import edu.gestudent.services.Mealcrud;
import edu.gestudent.services.MenuCrud;
import edu.gestudent.services.MenuMealCrud;
import edu.gestudent.services.UploadServices;
import edu.gestudent.utils.DataBase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordRestaurantController implements Initializable {

//    @FXML
//    private Button AjoutMealButton;
//    @FXML
//    private Button AjoutMealButton5;
//    @FXML
//    private Button AjoutMealButton4;
//    @FXML
//    private TableView<?> tabmeal;
//    @FXML
//    private TableColumn<?, ?> nom;
//    @FXML
//    private TableColumn<?, ?> image;
//    @FXML
//    private TableColumn<?, ?> type;
//    @FXML
//    private TableColumn<?, ?> rating;
//    @FXML
//    private JFXTextField searhTF;
//    @FXML
//    private JFXTextField txtname;
//    @FXML
//    private JFXTextField txtimage;
//    @FXML
//    private JFXComboBox<?> txttype;
//    @FXML
//    private JFXComboBox<?> txtrating;
//    @FXML
//    private Button AjoutMealButton1;
//    @FXML
//    private Button AjoutMealButton51;
//    @FXML
//    private Button AjoutMealButton41;
//    @FXML
//    private Button ReturnButton1;
//    @FXML
//    private TableView<?> tabmenu;
//    @FXML
//    private TableColumn<?, ?> nom1;
//    @FXML
//    private TableColumn<?, ?> description;
//    @FXML
//    private JFXTextField searchTF;
//    @FXML
//    private JFXTextField txtname1;
//    @FXML
//    private JFXTextField txtdescription;
//    @FXML
//    private Button AjoutMealButton11;
//    @FXML
//    private Button AjoutMealButton511;
//    @FXML
//    private TableView<?> tabmenumeal;
//    @FXML
//    private TableColumn<?, ?> namemenu;
//    @FXML
//    private TableColumn<?, ?> namemeal;
//    @FXML
//    private TableColumn<?, ?> typee;
//    @FXML
//    private TableColumn<?, ?> desriptionn;
//    @FXML
//    private JFXTextField filterField11;
//    @FXML
//    private JFXComboBox<?> cb_meal;
//    @FXML
//    private JFXComboBox<?> cb_menu;
    Connection con;
    Statement st;
//    Menu Menuu=new Menu();
    Mealcrud mc = new Mealcrud();
    MenuCrud mn = new MenuCrud();
    MenuMealCrud mnc = new MenuMealCrud();

    UploadServices uploadservices = new UploadServices();

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtimage;
    @FXML
    public ObservableList<meal> data = FXCollections.observableArrayList();
    public ObservableList<Menu> data1 = FXCollections.observableArrayList();
    public ObservableList<MenuMeal> data2 = FXCollections.observableArrayList();

    @FXML
    private TableColumn<meal, String> nom;
    @FXML
    private TableColumn<meal, String> type;

    @FXML
    private TableColumn<meal, Integer> rating;
    @FXML
    private Button AjoutMealButton;
    @FXML
    private ComboBox<String> txttype;
    ObservableList<String> typeCombo = FXCollections.observableArrayList("petit dej", "plat principale", "dessert");
    @FXML
    private ComboBox<Integer> txtrating;
    ObservableList<Integer> ratingCombo = FXCollections.observableArrayList(1, 2, 3, 4, 5);

    @FXML
    private Button AjoutMealButton4;
    @FXML
    private Button AjoutMealButton5;
    @FXML
    private TableView<meal> tabmeal;

    private TextField filterField;
    @FXML
    private TableView<Menu> tabmenu;
    @FXML
    private TableColumn<Menu, String> nom1;
    @FXML
    private Button ReturnButton1;

    private TextField filterField1;

    @FXML
    private TextField txtname1;
    @FXML
    private TableColumn<Menu, String> description;

    @FXML
    private Button AjoutMealButton41;
    @FXML
    private Button AjoutMealButton51;
    @FXML
    private Button AjoutMealButton1;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField filterField11;
    @FXML
    private Button AjoutMealButton511;
    @FXML
    private Button AjoutMealButton11;
    @FXML
    private ComboBox<String> cb_meal;
    @FXML
    private ComboBox<String> cb_menu;
    @FXML
    private TableView<MenuMeal> tabmenumeal;
    @FXML
    private TextField searhTF;
    @FXML
    private TextField searchTF;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    @FXML
    private TableColumn<MenuMeal, String> namemenu;
    @FXML
    private TableColumn<MenuMeal, String> namemeal;
    @FXML
    private TableColumn<MenuMeal, String> desriptionn;
    @FXML
    private TableColumn<MenuMeal, String> typee;
    @FXML
    private ImageView imageMeal;

    public int getTxtrating2() {
        return Integer.parseInt(searhTF.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txttype.setItems(typeCombo);
        txtrating.setItems(ratingCombo);

        //loadData();
        //listMenuMeal();
        //piechart.setData(piechartdata);
        refreshComboBoxMenu();
        refreshComboBoxMeal();
        String menu = cb_menu.getValue();
        data1.addAll(mn.afficherMenu());
        data.addAll(mc.afficherMeal());
        //System.out.println(mnc.afficherMenuMeal("riz"));
        data2.addAll(mnc.afficherMenuMeal(""));

        this.nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        this.nom1.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));

        this.tabmeal.setItems(data);
        
          tabmeal.setRowFactory(tv -> {
            TableRow<meal> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = tabmeal.getSelectionModel().getSelectedItems().get(0);
                String Id_meal = selectedItems.toString().split(",")[0].substring(0);
                System.out.println(Id_meal);
                String imagename = mc.getImageMeal(Integer.parseInt(Id_meal));
                Image image = new Image("http://localhost/images/uploads/" + imagename);
                imageMeal.setImage(image);
            });
            return row;
        });
        
        this.tabmenu.setItems(data1);

        this.tabmenumeal.setItems(data2);

        this.tabmeal.setEditable(true);
        this.nom.setCellFactory(TextFieldTableCell.forTableColumn());
        this.rating.setCellFactory(TextFieldTableCell.<meal, Integer>forTableColumn(new IntegerStringConverter()));
        this.type.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tabmenu.setEditable(true);
        this.nom1.setCellFactory(TextFieldTableCell.forTableColumn());
        this.description.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tabmenumeal.setEditable(true);
        this.namemenu.setCellValueFactory(new PropertyValueFactory<>("namemenu"));
        this.namemeal.setCellValueFactory(new PropertyValueFactory<>("namemeal"));
        this.typee.setCellValueFactory(new PropertyValueFactory<>("typemenumeal"));
        this.desriptionn.setCellValueFactory(new PropertyValueFactory<>("description"));
//        

        // TODO
    }

    //    private void listMenuMeal() {
//
//        this.namemenu.setCellValueFactory(new PropertyValueFactory<>("namemenu"));
//        this.namemeal.setCellValueFactory(new PropertyValueFactory<>("namemeal"));
//        this.type.setCellValueFactory(new PropertyValueFactory<>("type"));
//        this.description.setCellValueFactory(new PropertyValueFactory<>("desription"));
//
//        tabmenumeal.setItems(data2);
//
//    }
    @FXML
    private void addMeal(ActionEvent event) {

        String FilenameInserver = uploadservices.upload(txtimage.getText());

        meal l;
        l = new meal(FilenameInserver, txtname.getText(), txttype.getValue(), txtrating.getValue());
        mc.ajouterMeal(l);
        Alert succAddMealAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddMealAlert.setTitle("Add meal");
        succAddMealAlert.setHeaderText("Results:");
        succAddMealAlert.setContentText("meal added successfully!");
        succAddMealAlert.showAndWait();

        data.clear();
        data.addAll(mc.afficherMeal());
        refreshComboBoxMeal();

    }

    @FXML
    private void addMenu(ActionEvent event) {

        Menu m;
        m = new Menu(txtname1.getText(), txtdescription.getText());
        mn.ajouterMenu(m);
        Alert succAddMealAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddMealAlert.setTitle("Add Menu");
        succAddMealAlert.setHeaderText("Results:");
        succAddMealAlert.setContentText("Menu added successfully!");
        succAddMealAlert.showAndWait();

        data1.clear();
        data1.addAll(mn.afficherMenu());
        refreshComboBoxMenu();

    }

    @FXML
    private void addMenuMeal(ActionEvent event) {

        String meal = cb_meal.getValue();
        String menu = cb_menu.getValue();
        //System.out.println(meal + " " + menu);

        int id_menu = mn.getIdMenu(menu);
        int id_meal = mc.getIdMeal(meal);
        MenuMeal mm = new MenuMeal(id_menu, id_meal);
        Alert succAddMealAlert = new Alert(Alert.AlertType.INFORMATION);
        succAddMealAlert.setTitle("Add Menu");
        succAddMealAlert.setHeaderText("Results:");
        succAddMealAlert.setContentText("Menu added successfully!");
        succAddMealAlert.showAndWait();
        mnc.ajouterMenuMeal(mm);
        data2.clear();
        data2.addAll(mnc.afficherMenuMeal(menu));

    }

    @FXML
    private void ReturnAction(ActionEvent event) {

//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
//            Parent root = loader.load();
//            HomepageController spc = loader.getController();
//            ReturnButton.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(LibrarypageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //private void select(ActionEvent event) {
//        meal M = tabmeal.getSelectionModel().getSelectedItem();
//        txtname.setText(M.getName());
//        txtimage.setText(M.getImage());
//        txttype.setText(M.getType());
//        txtrating.setText(M.getRating());
    //}
    @FXML
    private void DeleteAction(ActionEvent event) {

        if (tabmeal.getSelectionModel().getSelectedItem() != null) {
            Alert deleteMealAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteMealAlert.setTitle("Delete Partner");
            deleteMealAlert.setHeaderText(null);
            deleteMealAlert.setContentText("Are you sure want to delete this meal ?");
            Optional<ButtonType> optionDeleteMealAlert = deleteMealAlert.showAndWait();
            if (optionDeleteMealAlert.get() == ButtonType.OK) {
                meal M = tabmeal.getSelectionModel().getSelectedItem();
                try {
                    mc.delete(M);
                } catch (SQLException ex) {
                    ex.getMessage();
                }
                data.clear();
                data.addAll(mc.afficherMeal());

                //Alert Delete Blog :
                Alert succDeleteMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMealAlert.setTitle("Delete Blog");
                succDeleteMealAlert.setHeaderText("Results:");
                succDeleteMealAlert.setContentText("Meal deleted successfully!");

                succDeleteMealAlert.showAndWait();
            } else if (optionDeleteMealAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a meal");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select meal first!");
            selectMealAlert.showAndWait();
            //Alert Select Book !

        }
        refreshComboBoxMeal();
    }

    @FXML
    private void DeleteMenu(ActionEvent event) {

        if (tabmenu.getSelectionModel().getSelectedItem() != null) {
            Alert deleteMenuAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteMenuAlert.setTitle("Delete Partner");
            deleteMenuAlert.setHeaderText(null);
            deleteMenuAlert.setContentText("Are you sure want to delete this menu ?");
            Optional<ButtonType> optionDeleteMenuAlert = deleteMenuAlert.showAndWait();
            if (optionDeleteMenuAlert.get() == ButtonType.OK) {
                Menu M = tabmenu.getSelectionModel().getSelectedItem();
                try {
                    mn.delete(M);

                } catch (SQLException ex) {
                    ex.getMessage();
                }
                data1.clear();
                data1.addAll(mn.afficherMenu());

                //Alert Delete Blog :
                Alert succDeleteMenuAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMenuAlert.setTitle("Delete Blog");
                succDeleteMenuAlert.setHeaderText("Results:");
                succDeleteMenuAlert.setContentText("Menu deleted successfully!");

                succDeleteMenuAlert.showAndWait();
            } else if (optionDeleteMenuAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectMenuAlert = new Alert(Alert.AlertType.WARNING);
            selectMenuAlert.setTitle("Select a meal");
            selectMenuAlert.setHeaderText(null);
            selectMenuAlert.setContentText("You need to select menu first!");
            selectMenuAlert.showAndWait();
            //Alert Select Book !

        }
        refreshComboBoxMenu();
    }

    @FXML
    private void DeleteMenuMeal(ActionEvent event) {

        if (tabmenumeal.getSelectionModel().getSelectedItem() != null) {
            Alert deleteMenuMealAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteMenuMealAlert.setTitle("Delete Partner");
            deleteMenuMealAlert.setHeaderText(null);
            deleteMenuMealAlert.setContentText("Are you sure want to delete this menu ?");
            Optional<ButtonType> optionDeleteMenuAlert = deleteMenuMealAlert.showAndWait();
            if (optionDeleteMenuAlert.get() == ButtonType.OK) {
                MenuMeal Ml = tabmenumeal.getSelectionModel().getSelectedItem();
                try {
                    mnc.deleteMenuMeal(Ml);

                } catch (SQLException ex) {
                    ex.getMessage();
                }
                data2.clear();
                data2.addAll(mnc.afficherMenuMeal(cb_menu.getValue()));

                //Alert Delete Blog :
                Alert succDeleteMenuMealAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteMenuMealAlert.setTitle("Delete Blog");
                succDeleteMenuMealAlert.setHeaderText("Results:");
                succDeleteMenuMealAlert.setContentText("Menu deleted successfully!");

                succDeleteMenuMealAlert.showAndWait();
            } else if (optionDeleteMenuAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectMenuAlert = new Alert(Alert.AlertType.WARNING);
            selectMenuAlert.setTitle("Select a meal");
            selectMenuAlert.setHeaderText(null);
            selectMenuAlert.setContentText("You need to select menu first!");
            selectMenuAlert.showAndWait();
            //Alert Select Book !

        }
    }

    @FXML
    /*private void enregistrerEV(ActionEvent event) throws SQLException, IOException {
         event ev=new event(nomEv.getText());
                  String p = prixEv.getText();

 double prix =Double.valueOf(p);
 sr.modifierEvent(ev, nomEv.getText(), id_Datev.getText(),prix, descEv.getText(),  id_typev.getText(),lieuEv.getText());
      FXMLLoader loader = new FXMLLoader(getClass().getResource("liste_event.fxml"));
           Parent root = loader.load();
           nomEv.getScene().setRoot(root); 
    } */

    private void EditAction(ActionEvent event) {

        if (tabmeal.getSelectionModel().getSelectedItem() != null) {

            meal l = tabmeal.getSelectionModel().getSelectedItem();

            mc.Update(l.getName(), l.getImage(), l.getType(), l.getRating());
            Alert MealAlert = new Alert(Alert.AlertType.INFORMATION);
            MealAlert.setTitle("edit");
            MealAlert.setHeaderText(null);
            MealAlert.setContentText("Meal was succfuly edit");
            MealAlert.showAndWait();

        } else {
            //Alert Select Meal :
            Alert selectMealAlert = new Alert(Alert.AlertType.WARNING);
            selectMealAlert.setTitle("Select a meal");
            selectMealAlert.setHeaderText(null);
            selectMealAlert.setContentText("You need to select meal first!");
            selectMealAlert.showAndWait();
            //Alert Select Meal !
        }
        refreshComboBoxMeal();
    }

    @FXML
    private void EditMenu(ActionEvent event) throws SQLException {

        if (tabmenu.getSelectionModel().getSelectedItem() != null) {

            Menu M = tabmenu.getSelectionModel().getSelectedItem();
            mn.Update(M.getName(), M.getDescription());
            Alert MenuAlert = new Alert(Alert.AlertType.INFORMATION);
            MenuAlert.setTitle("edit");
            MenuAlert.setHeaderText(null);
            MenuAlert.setContentText("Menu was succfuly edit");
            MenuAlert.showAndWait();

        } else {
            //Alert Select Menu :
            Alert selectMenuAlert = new Alert(Alert.AlertType.WARNING);
            selectMenuAlert.setTitle("Select a menu");
            selectMenuAlert.setHeaderText(null);
            selectMenuAlert.setContentText("You need to select menu first!");
            selectMenuAlert.showAndWait();
            //Alert Select Menu !
        }
        refreshComboBoxMenu();
    }

    private void rechercheMenu(ActionEvent event) {
        data1.clear();
        data1.addAll(mn.Rechercherrrr(filterField1.getText()));
        this.nom1.setCellFactory(TextFieldTableCell.forTableColumn());
        this.description.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tabmenu.setItems(data1);
    }



    @FXML
    public void changeNomCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        meal MealSelected = tabmeal.getSelectionModel().getSelectedItem();
        MealSelected.setName(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changetypeCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        meal MealSelected = tabmeal.getSelectionModel().getSelectedItem();
        MealSelected.setType(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeRatingCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        meal MealSelected = tabmeal.getSelectionModel().getSelectedItem();
        MealSelected.setRating(Integer.parseInt(edittedCell.getNewValue().toString()));
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
    private void filter(ActionEvent event) {

        data.clear();

        data.addAll(mc.afficherMeal().stream().filter((art)
                -> art.getName().toLowerCase().contains(searhTF.getText().toLowerCase())
                || art.getType().toLowerCase().contains(searhTF.getText().toLowerCase())
        //|| art.getRating().contains(getTxtrating2())
        //                || Integer.toString(art.getPrixAchat()).equals(searchTF.getText())
        //                || Integer.toString(art.getPrixVente()).equals(searchTF.getText())

        ).collect(Collectors.toList()));

    }

    @FXML
    private void filter1(ActionEvent event) {
        data1.clear();
        data1.addAll(mn.afficherMenu().stream().filter((art)
                -> art.getName().toLowerCase().contains(searchTF.getText().toLowerCase())
                || art.getDescription().toLowerCase().contains(searchTF.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }

    @FXML
    private void filter2(ActionEvent event) {

        data2.clear();

        data2.addAll(mnc.afficherMenuMeal(cb_menu.getValue()).stream().filter((art)
                -> art.getNamemeal().toLowerCase().contains(filterField11.getText().toLowerCase())
                || art.getNamemenu().toLowerCase().contains(filterField11.getText().toLowerCase())
                || art.getTypemenumeal().toLowerCase().contains(filterField11.getText().toLowerCase())
                || art.getDescription().toLowerCase().contains(filterField11.getText().toLowerCase())
        ).collect(Collectors.toList()));

    }

    @FXML
//    private void loadData() {
//
//        String query = "select * From meal ORDER BY rating asc";
//
//        piechartdata = FXCollections.observableArrayList();
//
//        con = DataBase.getInstance().getConnection();
//
//        try {
//
//            ResultSet rs = con.createStatement().executeQuery(query);
//            while (rs.next()) {
//
//                piechartdata.add(new PieChart.Data(rs.getString("name"), rs.getInt("id_meal")));
//                p.add(rs.getString("name"));
//                c.add(rs.getInt("id_meal"));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    private void select(ActionEvent event) {
        data2.clear();
        data2.addAll(mnc.afficherMenuMeal(cb_menu.getValue()));
        this.tabmenumeal.setItems(data2);
    }

    public void refreshComboBoxMenu() {
        try {
            ObservableList<String> Menu_Listes = FXCollections.observableArrayList(mn.readAllMenu());
            cb_menu.setItems(Menu_Listes);
            String mu = cb_menu.getValue();
            System.out.println(mu);
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    public void refreshComboBoxMeal() {

        try {
            ObservableList<String> Meal_Listes = FXCollections.observableArrayList(mc.readAllMeals());
            cb_meal.setItems(Meal_Listes);
            String me = cb_meal.getValue();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void uploadImagemenu(ActionEvent event) {

        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            txtimage.setText(imageFile);
        }
    }
}
