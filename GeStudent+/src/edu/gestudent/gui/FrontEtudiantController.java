/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXTextArea;
import edu.gestudent.entities.Email;
import edu.gestudent.entities.Pdf;
import edu.gestudent.entities.Session;
import edu.gestudent.entities.classEtudiant;
import edu.gestudent.entities.cours;
import edu.gestudent.entities.tcc;
import edu.gestudent.services.ServicesUsers;
import edu.gestudent.services.classEtudiantCRUD;
import edu.gestudent.services.coursCRUD;
import edu.gestudent.services.tccCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import static org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.Ansi.Style.bg;

/**
 * FXML Controller class
 *
 * @author Ayadi
 */
public class FrontEtudiantController implements Initializable {

//    @FXML
//    private TableView<?> tabfront;
//    @FXML
//    private TableColumn<?, ?> classe;
//    @FXML
//    private TableColumn<?, ?> cours;
//    @FXML
//    private TableColumn<?, ?> teacher;
//    @FXML
//    private TextField chercher;
//    @FXML
//    private TableView<?> subjects;
//    @FXML
//    private TableColumn<?, ?> name;
    /**
     * Initializes the controller class.
     */
//    classEtudiantCRUD cE = new classEtudiantCRUD();
//    private TableColumn<classEtudiant, String> etudiant;
//    @FXML
//    private TableColumn<classEtudiant, String> classe;
//    public ObservableList<classEtudiant> data = FXCollections.observableArrayList();
//    private TableView<classEtudiant> cee;
//    @FXML
//    private TableView<?> tabfront;
//    @FXML
//    private TableColumn<?, ?> cours;
//    @FXML
//    private TableColumn<?, ?> teacher;
//    @FXML
//    private TextField chercher;
//    @FXML
//    private TableView<?> subjects;
//    @FXML
//    private TableColumn<?, ?> name;
    tccCRUD t = new tccCRUD();
    coursCRUD co = new coursCRUD();
    public ObservableList<tcc> data = FXCollections.observableArrayList();
    public ObservableList<cours> dataco = FXCollections.observableArrayList();
    @FXML
    private TableColumn<tcc, String> classe;
    @FXML
    private TableColumn<tcc, String> cours;
    @FXML
    private TableColumn<tcc, String> teacher;
    @FXML
    private TableView<tcc> tabfront;
    @FXML
    private TextField chercher;
    @FXML
    private TableView<cours> subjects;
    @FXML
    private TableColumn<cours, String> name;
    private TableColumn<cours, String> lesson;
    int idetudiant = Session.getCurrentSession();

    classEtudiantCRUD CE = new classEtudiantCRUD();
    int idclass = CE.getclass(idetudiant);
    @FXML
    private Label Labelclass;
    @FXML
    private JFXTextArea MailBody;
    @FXML
    private Button SendMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dataco.addAll(co.readAll());

        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.subjects.setItems(dataco);

        try {
            data.addAll(t.rechercheclass(idclass));
        } catch (SQLException ex) {
            ex.getMessage();
        }

        // System.out.println(cE.rechercheetudiant((4)));     
        this.teacher.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        this.classe.setCellValueFactory(new PropertyValueFactory<>("nameC"));
        this.cours.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.tabfront.setItems(data);
        if (!data.isEmpty())
        Labelclass.setText(data.get(0).getNameC());
    }

    @FXML
    private void selectionner(ActionEvent event) {

        cours c = subjects.getSelectionModel().getSelectedItem();
        Pdf pdf = new Pdf();
        try {
            pdf.GeneratePdfSubject(c);
        } catch (DocumentException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (InterruptedException ex) {
            ex.getMessage();
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void SendMail(ActionEvent event) {

        tcc c = tabfront.getSelectionModel().getSelectedItem();
        ServicesUsers su = new ServicesUsers();
        System.out.println("id" + c.getidteacher());
        String mailprof = su.getMail(c.getidteacher());

        Email email = new Email();
        HashMap<String, String> message = new HashMap<String, String>();
        System.out.println(su.getName(idetudiant));
        System.out.println(mailprof);
        message.put("Title", "Claim from " + su.getName(idetudiant));
//         message.put("UpdatedAt","");
//        message.put("Description", "hh");
        message.put("Content", MailBody.getText());
        try {
            email.sendEmail(mailprof, "Claim", message);

        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    @FXML
    private void filter1(KeyEvent event) {

        dataco.clear();
        // System.out.println("heyy yuuu");
        dataco.addAll(co.readAll().stream().filter((art)
                -> art.getName().toLowerCase().contains(chercher.getText().toLowerCase())
        //                || Integer.toString(art.getPrixAchat()).equals(searchTF.getText())
        //                || Integer.toString(art.getPrixVente()).equals(searchTF.getText())

        //kahaw naamel recherche keen aala 3 hedhoukom wel be9i ?zeyed  okk
        ).collect(Collectors.toList()));
    }

}
