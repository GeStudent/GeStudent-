/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.sun.javaws.Main;
import edu.gestudent.exceptions.ExceptionUtil;
import edu.gestudent.utils.gestudentAssistantUtil;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author Asus
 */
public class MainAmin extends Application {

  //  private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Behaviour.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("GeStudent+");
        gestudentAssistantUtil.setStageIcon(stage);

        
        new Thread(() -> {
            ExceptionUtil.init();

        }).start();
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
       // LOGGER.log(org.apache.logging.log4j.Level.INFO, "Library Assistant launched on {}", gestudentAssistantUtil.formatDateTimeString(startTime));
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
      //          LOGGER.log(org.apache.logging.log4j.Level.INFO, "Library Assistant is closing on {}. Used for {} ms", gestudentAssistantUtil.formatDateTimeString(startTime), exitTime);
            }
        });
    }
}
