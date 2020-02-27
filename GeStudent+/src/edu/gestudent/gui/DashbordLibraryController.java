/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Emprunt;
import edu.gestudent.entities.Livre;
import edu.gestudent.entities.teacher;
import edu.gestudent.services.EmpruntCrud;
import edu.gestudent.services.LivreCrud;
import edu.gestudent.services.UploadServices;
import edu.gestudent.utils.DataBase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordLibraryController implements Initializable {

//    @FXML
//    private JFXTextField txtname;
//    @FXML
//    private JFXTextField txtimage;
//    @FXML
//    private JFXTextField txtauthor;
//    @FXML
//    private JFXTextField txturl;
//    @FXML
//    private JFXComboBox<?> combocategorie;
//    @FXML
//    private JFXTextField txtquantity;
//    @FXML
//    private TableView<?> librarytv;
//    @FXML
//    private TableColumn<?, ?> name;
//    @FXML
//    private TableColumn<?, ?> image;
//    @FXML
//    private TableColumn<?, ?> author;
//    @FXML
//    private TableColumn<?, ?> url;
//    @FXML
//    private TableColumn<?, ?> categiries;
//    @FXML
//    private TableColumn<?, ?> quantity;
//    @FXML
//    private JFXTextField searchTF;
//    @FXML
//    private TableView<?> empruntadmin;
//    @FXML
//    private TableColumn<?, ?> firstnameE;
//    @FXML
//    private TableColumn<?, ?> lastnameE;
//    @FXML
//    private TableColumn<?, ?> booknameE;
//    @FXML
//    private TableColumn<?, ?> dateretourE;
    @FXML
    PieChart piechart;
    Connection con;
    Statement ste;
    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    LivreCrud lcr = new LivreCrud();
    EmpruntCrud ecr = new EmpruntCrud();

    UploadServices uploadservices = new UploadServices();

    @FXML
    private TextField txtname;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtauthor;
    @FXML
    private TextField txturl;
    private TextField categorie;
    @FXML
    private TextField txtquantity;

    @FXML
    private TableColumn<Livre, String> name;
    @FXML
    private TableColumn<Livre, String> author;
    @FXML
    private TableColumn<Livre, String> url;
    @FXML
    private TableColumn<Livre, Integer> quantity;

    public ObservableList<Livre> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Livre> librarytv;
    @FXML
    private TableColumn<Livre, String> categiries;
    @FXML
    private ComboBox<String> combocategorie;
    public ObservableList<String> categorieff = FXCollections.observableArrayList("Comedy", "Drama", "Action", "History", "Thriller", "Romantic", "Biography");
    @FXML
    private TextField searchTF;
    @FXML
    private TableView<Emprunt> empruntadmin;
    @FXML
    private TableColumn<Emprunt, String> firstnameE;
    @FXML
    private TableColumn<Emprunt, String> lastnameE;
    @FXML
    private TableColumn<Emprunt, String> booknameE;
    @FXML
    private TableColumn<Emprunt, String> dateretourE;
    public ObservableList<Emprunt> dataemp = FXCollections.observableArrayList();
    @FXML
    private ImageView bookImage;
    @FXML
    private JFXTextField searchTF2;

    public int getTxtquantity() {
        return Integer.parseInt(txtquantity.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combocategorie.setItems(categorieff);
        data.addAll(lcr.afficherlivre());

        this.author.setCellValueFactory(new PropertyValueFactory<>("author"));
        this.categiries.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.quantity.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        this.url.setCellValueFactory(new PropertyValueFactory<>("url"));
        this.librarytv.setItems(data);

        librarytv.setRowFactory(tv -> {
            TableRow<Livre> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = librarytv.getSelectionModel().getSelectedItems().get(0);
                String Id_Livre = selectedItems.toString().split(",")[0].substring(0);
                System.out.println(Id_Livre);
                String imagename = lcr.getImageLivre(Integer.parseInt(Id_Livre));
                Image image = new Image("http://localhost/images/uploads/" + imagename);
                bookImage.setImage(image);
            });
            return row;
        });

        dataemp.addAll(ecr.afficherempruntadmin());
        this.firstnameE.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.lastnameE.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.booknameE.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.dateretourE.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
        this.empruntadmin.setItems(dataemp);

        //this for edit
        this.librarytv.setEditable(true);
        this.categiries.setCellFactory(TextFieldTableCell.forTableColumn());
        this.quantity.setCellFactory(TextFieldTableCell.<Livre, Integer>forTableColumn(new IntegerStringConverter()));
        this.url.setCellFactory(TextFieldTableCell.forTableColumn());

        // TODO
        //graph
        loadData();
        piechart.setData(piechartdata);
        // TODO
    }

    public void loadData() {

        String query = "select COUNT(*) as count,categorie From Livres GROUP BY categorie"; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {

                piechartdata.add(new PieChart.Data(rs.getString("categorie"), rs.getInt("count")));
                p.add(rs.getString("categorie"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void addBook(ActionEvent event) {

        String FilenameInserver = uploadservices.upload(txtimage.getText());

        Livre l = new Livre(txtname.getText(), FilenameInserver, txtauthor.getText(), txturl.getText(), combocategorie.getValue(), getTxtquantity());
        lcr.ajouterLivre(l);
//        getImageLivre 
        String imagename = lcr.getImageLivre(l.getId_livre());
        Image image = new Image("http://localhost/images/uploads/" + imagename);
        bookImage.setImage(image);
        AlertMaker.showSimpleAlert("Add book", "Book added successfully!");
        data.clear();
        data.addAll(lcr.afficherlivre());
        loadData();
        piechart.setData(piechartdata);

    }

    @FXML
    private void select(ActionEvent event) {
        Livre L = librarytv.getSelectionModel().getSelectedItem();
        txtname.setText(L.getName());
        txtimage.setText(L.getImage());
        txtauthor.setText(L.getAuthor());
        txturl.setText(L.getUrl());
        categorie.setText(L.getCategorie());
        txtquantity.setText(String.valueOf(L.getQuantite()));
    }

    @FXML
    private void DeleteAction(ActionEvent event) {

        if (librarytv.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete book");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure that you want to delete this book ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Livre L = librarytv.getSelectionModel().getSelectedItem();
                data.clear();
                data.addAll(lcr.afficherlivre());

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("Delete Blog");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("Book deleted successfully!");

                succDeleteBookAlert.showAndWait();
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
        loadData();
        piechart.setData(piechartdata);
    }

    @FXML
    private void EditAction(ActionEvent event) {

        if (librarytv.getSelectionModel().getSelectedItem() != null) {

            Livre l = librarytv.getSelectionModel().getSelectedItem();

            lcr.Update(l.getName(), l.getImage(), l.getUrl(), l.getCategorie(), l.getQuantite());
            Alert BookAlert = new Alert(Alert.AlertType.INFORMATION);
            BookAlert.setTitle("edit");
            BookAlert.setHeaderText(null);
            BookAlert.setContentText("The book was successfully edit");
            BookAlert.showAndWait();

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

    public void changeImageCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Livre LivreSelected = librarytv.getSelectionModel().getSelectedItem();
        LivreSelected.setImage(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeCategoriesCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Livre LivreSelected = librarytv.getSelectionModel().getSelectedItem();
        LivreSelected.setCategorie(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeAuthorCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Livre LivreSelected = librarytv.getSelectionModel().getSelectedItem();
        LivreSelected.setAuthor(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeUrlCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Livre LivreSelected = librarytv.getSelectionModel().getSelectedItem();
        LivreSelected.setUrl(edittedCell.getNewValue().toString());
    }

    @FXML
    public void changeQuantityCellEvent(TableColumn.CellEditEvent edittedCell
    ) {
        Livre LivreSelected = librarytv.getSelectionModel().getSelectedItem();
        LivreSelected.setQuantite(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    @FXML
    private void upload(ActionEvent event) {

        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            txtimage.setText(imageFile);
        }
    }

    @FXML
    private void selectemprunt(ActionEvent event) {
        Emprunt E = empruntadmin.getSelectionModel().getSelectedItem();
        System.out.println(E.getId());
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

    @FXML
    private void filter2(KeyEvent event) {
        dataemp.clear();
        // System.out.println("heyy yuuu");
        dataemp.addAll(ecr.afficherempruntadmin().stream().filter((art)
                -> art.getName().toLowerCase().contains(searchTF2.getText().toLowerCase())
                || art.getFirstname().toLowerCase().contains(searchTF2.getText().toLowerCase())
                || art.getLastname().toLowerCase().contains(searchTF2.getText().toLowerCase())
        ).collect(Collectors.toList()));
    }
}
