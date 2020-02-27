/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.mysql.jdbc.StringUtils;
import edu.gestudent.entities.Behaviour;

import edu.gestudent.entities.exams;
import edu.gestudent.services.behaviourCRUD;
import edu.gestudent.services.examsCRUD;
import edu.gestudent.utils.DataBase;
import edu.gestudent.utils.gestudentAssistantUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DashboardExamsController implements Initializable {
   ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();
    examsCRUD exc = new examsCRUD();
    private StackPane rootPane;
    @FXML
    private TextField txtnom;
    @FXML
    private AnchorPane hamburger;
    private JFXDrawer drawer;
    @FXML
    private DatePicker datetxt;
    @FXML
    private TextField txtduree;
    @FXML
    private TableColumn<exams, String> nomex;
    @FXML
    private TableColumn<exams, String> dateex;
    @FXML
    private TableColumn<exams, String> duree;

    public ObservableList<exams> data = FXCollections.observableArrayList();

    @FXML
    private TableView<exams> examtv;
    @FXML
    private TextField txtduree1;
    @FXML
    PieChart piechart;
    Connection con;
    Statement ste;
  ObservableList < PieChart.Data > piechartdata;
  behaviourCRUD behc = new behaviourCRUD();
  @FXML
    private TextField txtnameaward;
    /**
     * Initializes the controller class.
     */
     static JFrame f;
    behaviourCRUD bhcr = new behaviourCRUD();
        public ObservableList<Behaviour> dataa = FXCollections.observableArrayList();
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


 
    

//     public ObservableList<PieChart.Data> getExamGraphStatistics() {
//        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//                con = DataBase.getInstance().getConnection();
//
//        try {
//            String qu1 = "SELECT COUNT(*) FROM exams";
//                        String qu2 = "SELECT COUNT(*) FROM behaviour";
//                          PreparedStatement pst;
//
//        pst = con.prepareStatement(qu1);
//
//      
//            ResultSet rs = pst.executeQuery(qu1);
//            if (rs.next()) {
//                int count = rs.getInt(1);
//                data.add(new PieChart.Data("Total exams (" + count + ")", count));
//            }
//               pst = con.prepareStatement(qu2);
//                  rs = pst.executeQuery(qu2);
//            if (rs.next()) {
//                int count = rs.getInt(1);
//                data.add(new PieChart.Data("Behaviour in exams (" + count + ")", count));
//            }
//          
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
      //  initGraphs();
        initDrawer();

        piechart.setData(piechartdata);

        data.addAll(exc.afficherex());

        // TODO
        this.nomex.setCellValueFactory(new PropertyValueFactory<>("nomex"));
        this.dateex.setCellValueFactory(new PropertyValueFactory<>("dateex"));
        this.duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        this.examtv.setItems(data);
        //this for edit
        this.examtv.setEditable(true);
//        this.nomex.setCellFactory(TextFieldTableCell.forTableColumn());
        this.dateex.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.duree.setCellFactory(TextFieldTableCell.forTableColumn());
//behavior
   // TODO
      //  data.addAll(bhcr.afficherBehaviour());
        comboaward.setItems(awards);
          dataa.addAll(behc.afficherBehaviour());
    
      
        // TODO
       this.nombeh.setCellValueFactory(new PropertyValueFactory<>("nombeh"));
       this.award.setCellValueFactory(new PropertyValueFactory<>("award"));
      
        this.awardtv.setItems(dataa);
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

        dataa.clear();
        dataa.addAll(behc.afficherBehaviour());
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

                dataa.clear();
                dataa.addAll(behc.afficherBehaviour());

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
    private void initDrawer() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/gestudent/toolbar/Toolbar.fxml"));
//            VBox toolbar = loader.load();
//            drawer.setSidePane(toolbar);
//            ToolbarController controller = loader.getController();
//          //  controller.setBookReturnCallback(this);
//        } catch (IOException ex) {
//       //     Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
//        task.setRate(-1);
//        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
//            drawer.toggle();
//        });
//        drawer.setOnDrawerOpening((event) -> {
//            task.setRate(task.getRate() * -1);
//            task.play();
//            drawer.toFront();
//        });
//        drawer.setOnDrawerClosed((event) -> {
//            drawer.toBack();
//            task.setRate(task.getRate() * -1);
//            task.play();
//        });
    }
//    public void initGraphs(){
//            piechart = new PieChart(getExamGraphStatistics());
// ExamInfoContainer.getChildren().add(piechart);
//}

//    public int getnomq() throws SQLException {
//        int q = 0;
//        con = DataBase.getInstance().getConnection();
//        String requete4 = "select COUNT(*) as count from exams GROUP BY nomex";
//        PreparedStatement pst;
//  ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
//        pst = con.prepareStatement(requete4);
//
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            exams e = new exams();
//         //   if (rs.getString("nomex").equals(nomex)) {
//                int count = rs.getInt("count");
//                String nomex =rs.getString("nomex");
//              //  data.add(new PieChart.Data("Total exams (" + count + ")", count));
//         //   }
//      
//
//        }
//     //   return count;
//    }

    public void loadData() {

        String query = "select COUNT(*) as count ,nomex from exams GROUP BY nomex"; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

        con = DataBase.getInstance().getConnection();

        try {

            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                exams e = new exams();
                //	int result = Integer.parseInt(e.getDuree());
                piechartdata.add(new PieChart.Data(rs.getString("nomex"),rs.getInt("count") ));
                p.add(rs.getString("nomex"));
                c.add(rs.getInt("count"));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void addex(ActionEvent event) {
        
        Alert alertl = new Alert(Alert.AlertType.ERROR);
        if (datetxt.getValue().isBefore(LocalDate.now())) {
//            alertl.setTitle("Date Failed");
//            alertl.setContentText("The Date can't be in the Past !!");
//            alertl.showAndWait();
            //     JFXButton btn = new JFXButton("Okay!");
            AlertMaker.showErrorMessage("Date Failed!", "The Date can't be in the Past !!");

            return;
        }
        System.out.println(datetxt.getValue().isBefore(LocalDate.now()));
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        if (txtduree.getText().matches("(.*):(.*)") == false) {
//                alert2.setTitle("Duree Failed");
//               alert2.setContentText("The Time must be like 00:00 !!");
//                alert2.showAndWait();
            AlertMaker.showErrorMessage("Duree Failed!", "The Time must be like 00:00 !!");
            return;
        }
        System.out.println(txtduree.getText().matches("(.*):(.*)"));
        String date = datetxt.getValue().format(DateTimeFormatter.ISO_DATE);
        String numberAsString = txtduree + "Heurs";

        // String duree= txtduree.getText().concat(txtduree+"Heurs");
        //System.out.println(date);
        exams e;
        e = new exams(txtnom.getText(), date, txtduree.getText());
        exc.ajoutex(e);
        Alert succAddExamAlert = new Alert(Alert.AlertType.INFORMATION);
//        succAddExamAlert.setTitle("Add Exam");
//        succAddExamAlert.setHeaderText("Results:");
//        succAddExamAlert.setContentText("Exam added successfully!");
//        succAddExamAlert.showAndWait();
        AlertMaker.showSimpleAlert("Add Exam", "Exam added successfully!");

        data.clear();
        data.addAll(exc.afficherex());
    }

    @FXML
    private void editex(ActionEvent event) {
        if (examtv.getSelectionModel().getSelectedItem() != null) {

            exams e = examtv.getSelectionModel().getSelectedItem();

            try {
                exc.updateex(e.getIdexa(), e.getDateex());
            } catch (SQLException ex) {
                Logger.getLogger(ExamenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alert ExamAlert = new Alert(Alert.AlertType.INFORMATION);
//            ExamAlert.setTitle("edit");
//            ExamAlert.setHeaderText(null);
//            ExamAlert.setContentText("Exam was succfuly edit");
//            ExamAlert.showAndWait();
        AlertMaker.showSimpleAlert("edit", "Exam was succfuly edit!");


        } else {
            //Alert Select exam :
            Alert selectExamAlert = new Alert(Alert.AlertType.WARNING);
//            selectExamAlert.setTitle("Select a Exam");
//            selectExamAlert.setHeaderText(null);
//            selectExamAlert.setContentText("You need to select Exam first!");
//            selectExamAlert.showAndWait();
        AlertMaker.showwarningMessage("Select a Exam", "You need to select Exam first!");

            //Alert Select Exam !
        }
    }

    @FXML
    private void removeex(ActionEvent event) {
        if (examtv.getSelectionModel().getSelectedItem() != null) {
            Alert deleteExamAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteExamAlert.setTitle("Delete Partner");
            deleteExamAlert.setHeaderText(null);
            deleteExamAlert.setContentText("Are you sure want to delete this Exam ?");
            Optional<ButtonType> optionDeleteExamAlert = deleteExamAlert.showAndWait();
            if (optionDeleteExamAlert.get() == ButtonType.OK) {
                exams e = examtv.getSelectionModel().getSelectedItem();
                try {
                    exc.delete(e);
                } catch (SQLException ex) {
                    Logger.getLogger(ExamenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                data.clear();
                data.addAll(exc.afficherex());

                //Alert Delete Blog :
//                Alert succDeleteExamAlert = new Alert(Alert.AlertType.INFORMATION);
//                succDeleteExamAlert.setTitle("Delete Exam");
//                succDeleteExamAlert.setHeaderText("Results:");
//                succDeleteExamAlert.setContentText("Exam deleted successfully!");
//
//                succDeleteExamAlert.showAndWait();
        AlertMaker.showSimpleAlert("Delete Exam", "Exam deleted successfully!");

            } else if (optionDeleteExamAlert.get() == ButtonType.CANCEL) {

            }

        } else {

            //Alert Select Exam :
//            Alert selectExamAlert = new Alert(Alert.AlertType.WARNING);
//            selectExamAlert.setTitle("Select a Exam");
//            selectExamAlert.setHeaderText(null);
//            selectExamAlert.setContentText("You need to select Exam first!");
//            selectExamAlert.showAndWait();
        AlertMaker.showwarningMessage("Select a Exam", "You need to select Exam first!");

            //Alert Select Exam !

        }
    }

    @FXML
    private void displayex(ActionEvent event) {
        data.clear();
        data.addAll(exc.RechercheReclamation(txtduree1.getText()));
        this.nomex.setCellValueFactory(new PropertyValueFactory<>("nomex"));
        this.dateex.setCellValueFactory(new PropertyValueFactory<>("dateex"));
        this.duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        this.examtv.setItems(data);
    }
//      @FXML
//    public void changeNameCellEvent(CellEditEvent edittedCell
//    ) {
//        exams ExamSelected = examtv.getSelectionModel().getSelectedItem();
//        ExamSelected.setNomex(edittedCell.getNewValue().toString());
//  }

    @FXML
    public void changeDateCellEvent(CellEditEvent edittedCell
    ) {
        exams ExamSelected = examtv.getSelectionModel().getSelectedItem();
        ExamSelected.setDateex(edittedCell.getNewValue().toString());
    }
//      @FXML
//     public void changeTimeCellEvent(CellEditEvent edittedCell
//    ) {
//        exams ExamSelected = examtv.getSelectionModel().getSelectedItem();
//        ExamSelected.setDuree(Integer.parseInt(edittedCell.getNewValue().toString()));
//    }
//
//    @FXML
//    private void changeNameCellEvent(CellEditEvent edittedCell) {
//    }
//
//    @FXML
//    private void changeDateCellEvent(CellEditEvent edittedCell) {
//    }
//
//    @FXML
//    private void changeTimeCellEvent(CellEditEvent edittedCell) {
//    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void handleMenuFullScreen(ActionEvent event) {
        Stage stage = getStage();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void handleAboutMenu(ActionEvent event) {
                gestudentAssistantUtil.loadWindow(getClass().getResource("/edu/gestudent/about/about.fxml"), "About Us", null);
    }

}
