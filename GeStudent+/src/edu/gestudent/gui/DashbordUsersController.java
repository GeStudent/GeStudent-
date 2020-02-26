/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.gestudent.entities.Student;
import edu.gestudent.entities.teacher;
import edu.gestudent.services.ServiceStudent;
import edu.gestudent.services.ServicesTeacher;
import edu.gestudent.services.ServicesUsers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.ws.Holder;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class DashbordUsersController implements Initializable {

    ServicesUsers su = new ServicesUsers();
    ServiceStudent ss = new ServiceStudent();
    ServicesTeacher st = new ServicesTeacher();

    public ObservableList<Student> Studentdata = FXCollections.observableArrayList();
    public ObservableList<teacher> Teacherdata = FXCollections.observableArrayList();

    @FXML
    private TableView<Student> StudentTv;
    @FXML
    private TableColumn<Student, String> firstname;
    @FXML
    private TableColumn<Student, String> lastname;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> birthday;
    @FXML
    private TableColumn<Student, String> phone;
    @FXML
    private TableColumn<Student, String> pays;
    @FXML
    private TableColumn<Student, String> gender;

    public static Holder<String> holdId = new Holder<String>();
    @FXML
    private TableView<teacher> teacherTv;
    @FXML
    private TableColumn<teacher, String> firstnameT;
    @FXML
    private TableColumn<teacher, String> lastnameT;
    @FXML
    private TableColumn<teacher, String> emailT;
    @FXML
    private TableColumn<teacher, String> birthdayT;
    @FXML
    private TableColumn<teacher, String> phoneT;
    @FXML
    private TableColumn<teacher, String> paysT;
    @FXML
    private TableColumn<teacher, String> genderT;
    @FXML
    private JFXButton Registered;
    @FXML
    private JFXButton Unregistered;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Studentdata.addAll(ss.readAllStudentRegistrated());

        this.firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.email.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.birthday.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        this.phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        this.gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        this.StudentTv.setItems(Studentdata);

        StudentTv.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = StudentTv.getSelectionModel().getSelectedItems().get(0);
                String data1 = selectedItems.toString().split(",")[0].substring(0);
                String data2 = selectedItems.toString().split(",")[0].substring(1);
                System.out.println(data1);
                holdId.value = data1;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(DashbordUsersController.this.getClass().getResource("UserDetaills.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 400, 550);
                    Stage stage = new Stage();
                    //stage.setTitle(data1);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            });
            return row;
        });
        Teacherdata.addAll(st.readAllRegistritedTeachers());

        this.firstnameT.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.lastnameT.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.emailT.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.birthdayT.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        this.phoneT.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.paysT.setCellValueFactory(new PropertyValueFactory<>("pays"));
        this.genderT.setCellValueFactory(new PropertyValueFactory<>("gender"));

        this.teacherTv.setItems(Teacherdata);

        teacherTv.setRowFactory(tv -> {
            TableRow<teacher> row = new TableRow<>();
            row.setOnMouseClicked(e -> {

                Object selectedItems = teacherTv.getSelectionModel().getSelectedItems().get(0);
                String data1 = selectedItems.toString().split(",")[0].substring(0);
                String data2 = selectedItems.toString().split(",")[0].substring(1);
                System.out.println(data1);
                holdId.value = data1;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(DashbordUsersController.this.getClass().getResource("UserDetaills.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 400, 550);
                    Stage stage = new Stage();
                    //stage.setTitle(data1);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();

                }
            });
            return row;
        });

        // TODO
    }

    @FXML
    private void RegisteredStudent(ActionEvent event) {

        Studentdata.clear();
        Studentdata.addAll(ss.readAllStudentRegistrated());
        this.StudentTv.setItems(Studentdata);
    }

    @FXML
    private void UnRegisteredstudent(ActionEvent event) {

        Studentdata.clear();
        Studentdata.addAll(ss.readAllStudentNotRegistrated());
        this.StudentTv.setItems(Studentdata);
    }

    @FXML
    private void RegisteredTeacher(ActionEvent event) {

        Teacherdata.clear();
        Teacherdata.addAll(st.readAllRegistritedTeachers());
        this.teacherTv.setItems(Teacherdata);

    }

    @FXML
    private void UnRegisteredteacher(ActionEvent event) {
        Teacherdata.clear();
        Teacherdata.addAll(st.readAllNotRegistritedTeachers());
        this.teacherTv.setItems(Teacherdata);
    }

}
