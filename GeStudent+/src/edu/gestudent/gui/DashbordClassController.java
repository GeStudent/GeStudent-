/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXComboBox;
import edu.gestudent.entities.Student;
import edu.gestudent.entities.classEtudiant;
import edu.gestudent.entities.classe;
import edu.gestudent.entities.cours;
import edu.gestudent.entities.tcc;
import edu.gestudent.entities.teacher;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesTeacher;
import edu.gestudent.services.classCRUD;
import edu.gestudent.services.classEtudiantCRUD;
import edu.gestudent.services.coursCRUD;
import edu.gestudent.services.tccCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import static java.util.logging.Level.parse;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javax.xml.ws.Holder;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordClassController implements Initializable {

    coursCRUD cr = new coursCRUD();
    classCRUD c = new classCRUD();
    tccCRUD t = new tccCRUD();
    classEtudiantCRUD cE = new classEtudiantCRUD();

    ServiceStudent ss = new ServiceStudent();
    ServicesTeacher st = new ServicesTeacher();
    public ObservableList<teacher> Teacherdata = FXCollections.observableArrayList();
    public ObservableList<Student> Studentdata = FXCollections.observableArrayList();

    @FXML
    private TextField nametxt;
    @FXML
    private TextField lessontxt;
    @FXML
    private TextField durationtxt;
    @FXML
    private TableView<cours> tabviewcours;
    @FXML
    private TableColumn<cours, String> name;
    @FXML
    private TableColumn<cours, String> lesson;
    @FXML
    private TableColumn<cours, Integer> duration;
    public ObservableList<cours> data = FXCollections.observableArrayList();
    public ObservableList<classe> dataa = FXCollections.observableArrayList();
    public ObservableList<tcc> dataAffectation = FXCollections.observableArrayList();
    public ObservableList<classEtudiant> dataClassStudent = FXCollections.observableArrayList();

    @FXML
    private TextField namectxt;
    @FXML
    private TableView<classe> tableviewclass;
    @FXML
    private TableColumn<classe, String> nameC;
    @FXML
    private TableView<tcc> tc;
    @FXML
    private TableColumn<tcc, String> namecours;
    @FXML
    private TableColumn<tcc, String> nameprof;
    @FXML
    private TableColumn<tcc, String> nameclass;
    @FXML
    private JFXComboBox<classe> Classcombobox;
    @FXML
    private JFXComboBox<cours> Subjectcombobox;
    @FXML
    private TableView<teacher> teacherTv;
    @FXML
    private TableColumn<teacher, String> firstnameT;
    @FXML
    private TableColumn<teacher, String> lastnameT;
    @FXML
    private TableColumn<teacher, String> emailT;

    String idteacher = "idteacher";
    @FXML
    private JFXComboBox<classe> Classcombobox1;
    @FXML
    private TableView<Student> StudentTv;
    @FXML
    private TableColumn<Student, String> firstname;
    @FXML
    private TableColumn<Student, String> lastname;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableView<classEtudiant> StudentClassTv;
    @FXML
    private TableColumn<classEtudiant, String> classe;
    @FXML
    private TableColumn<classEtudiant, String> etudiant;

    /**
     * Initializes the controller class.
     */
    public int getdurationtxt() {
        return Integer.parseInt(durationtxt.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data.addAll(cr.readAll());

        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.lesson.setCellValueFactory(new PropertyValueFactory<>("lesson"));

        this.duration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        this.tabviewcours.setItems(data);
        //  System.out.println(data.get(1).getlesson());
        try {
            dataa.addAll(c.readAll());
        } catch (SQLException ex) {
            ex.getMessage();
        }

        this.nameC.setCellValueFactory(new PropertyValueFactory<>("nameC"));

        this.tableviewclass.setItems(dataa);

        this.tabviewcours.setEditable(true);

        this.duration.setCellFactory(TextFieldTableCell.<cours, Integer>forTableColumn(new IntegerStringConverter()));

        Classcombobox.setItems(dataa);
        Classcombobox1.setItems(dataa);
        Subjectcombobox.setItems(data);

        Teacherdata.addAll(st.readAllTeachers());

        this.firstnameT.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.lastnameT.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.emailT.setCellValueFactory(new PropertyValueFactory<>("email"));

        this.teacherTv.setItems(Teacherdata);

        //System.out.println(t.rechercheprof(5));
        this.namecours.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.nameclass.setCellValueFactory(new PropertyValueFactory<>("nameC"));
        this.nameprof.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        teacherTv.setRowFactory(tv -> {
            TableRow<teacher> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = teacherTv.getSelectionModel().getSelectedItems().get(0);
                idteacher = selectedItems.toString().split(",")[0].substring(0);
                dataAffectation.clear();
                dataAffectation.addAll(t.rechercheprof(Integer.parseInt(idteacher)));
                this.tc.setItems(dataAffectation);
            });
            return row;
        });
        this.tc.setItems(dataAffectation);

        //for students
        Studentdata.addAll(ss.readAllStudentRegistrated());

        this.firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));

        this.StudentTv.setItems(Studentdata);

        this.etudiant.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.classe.setCellValueFactory(new PropertyValueFactory<>("namecl"));
        this.StudentClassTv.setItems(dataClassStudent);
        //        try {
//            dataClassStudent.addAll(cE.rechercheclassetudiant((0)));
//        } catch (SQLException ex) {
//            ex.getMessage();
//        }
        // System.out.println(cE.rechercheetudiant((4)));

        StudentClassTv.setRowFactory(tv -> {
            TableRow<classEtudiant> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = StudentClassTv.getSelectionModel().getSelectedItems().get(0);
                String idstudent = selectedItems.toString().split(",")[0].substring(0);

                try {
                    dataClassStudent.clear();
                    dataClassStudent.addAll(cE.rechercheetudiant(Integer.parseInt(idstudent)));
                    System.out.println(idstudent);
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            });
            return row;
        });
        // TODO

    }

    @FXML
    private void Addcours(ActionEvent event) throws SQLException {
        cours c = new cours(nametxt.getText(), lessontxt.getText(), getdurationtxt());
        if (cr.ajouter(c)) {
            Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
            succDeleteBookAlert.setTitle("Delete Blog");
            succDeleteBookAlert.setHeaderText("Results:");
            succDeleteBookAlert.setContentText("cours deleted successfully!");
            succDeleteBookAlert.showAndWait();
        } else {
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a cours");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a cours first!");
            selectBookAlert.showAndWait();
        }
        data.clear();
        data.addAll(cr.readAll());
    }

    @FXML
    private void addclass(ActionEvent event) throws SQLException {
        classe cl = new classe(namectxt.getText());
        c.ajouter(cl);
        dataa.clear();
        dataa.addAll(c.readAll());

    }

    @FXML
    private void selectionner(ActionEvent event) {

        cours c = tabviewcours.getSelectionModel().getSelectedItem();
        nametxt.setText(c.getName());
        lessontxt.setText(c.getLesson());
        durationtxt.setText(String.valueOf(c.getDuration()));

    }

    @FXML
    private void deletecours(ActionEvent event) throws SQLException {

        if (tabviewcours.getSelectionModel().getSelectedItem() != null) {
            Alert deleteBookAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteBookAlert.setTitle("Delete a cours");
            deleteBookAlert.setHeaderText(null);
            deleteBookAlert.setContentText("Are you sure that you want to delete this cours?");
            Optional<ButtonType> optionDeleteBookAlert = deleteBookAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {

                cours c = tabviewcours.getSelectionModel().getSelectedItem();
                cr.supprimercour(c.getIdcour());
                data.clear();
                data.addAll(cr.readAll());

                //Alert Delete Blog :
                Alert succDeleteBookAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteBookAlert.setTitle("Delete Blog");
                succDeleteBookAlert.setHeaderText("Results:");
                succDeleteBookAlert.setContentText("cours deleted successfully!");

                succDeleteBookAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select BOOK :
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Select a cours");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("You need to select a cours first!");
            selectBookAlert.showAndWait();
            //Alert Select Book !

        }

    }

    @FXML
    private void editcours(ActionEvent event) throws SQLException {

        if (tabviewcours.getSelectionModel().getSelectedItem() != null) {

            cours c = tabviewcours.getSelectionModel().getSelectedItem();

            cr.modifiercours(c.getDuration(), c.getIdcour());
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

    @FXML
    private void changelessonCellEvent(TableColumn.CellEditEvent edittedCell) {
        /* cours coursSelected = tabviewcours.getSelectionModel().getSelectedItem();
        coursSelected.setDuration(Integer.parseInt(edittedCell.getNewValue().toString()));
        // enti tbadel ken duration ? normalement naatih l id iwal i ibadel el duration oui duration kahaw ok*/

    }

    @FXML
    private void changeIdurationCellEvent(TableColumn.CellEditEvent edittedCell) {
        cours coursSelected = tabviewcours.getSelectionModel().getSelectedItem();
        coursSelected.setDuration(Integer.parseInt(edittedCell.getNewValue().toString()));
    }

    @FXML
    private void Affecter(ActionEvent event) {

        System.out.println("idd" + idteacher);
        System.out.println("comboboc: " + Classcombobox.getValue().getIdclass());
        try {

            tcc te = new tcc(Integer.parseInt(idteacher), Classcombobox.getValue().getIdclass(), Subjectcombobox.getValue().getIdcour());
            t.ajouter(te);
            dataAffectation.clear();
            dataAffectation.addAll(t.rechercheprof(Integer.parseInt(idteacher)));
            this.tc.setItems(dataAffectation);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void selectClass(ActionEvent event) {
        int idclass = Classcombobox1.getValue().getIdclass();

        try {
            dataClassStudent.clear();
            dataClassStudent.addAll(cE.rechercheclassetudiant((idclass)));
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
