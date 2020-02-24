/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.jfoenix.controls.JFXSpinner;
import static edu.gestudent.gui.Timer6.state;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TimerController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Spinner<Integer> shour;

    @FXML
    private Spinner<Integer> sminute;

    static int hours;
    static int minute;
    static int seconds;

    public int gets() {
        int hours = (int) shour.getValue();
        int minute = (int) sminute.getValue();
        int gets = hours * 3600 + minute * 60;
        //System.out.printf("\nClient: \n" + getClient);
        return gets;
    }

    static boolean state = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> hourvalue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0);
        SpinnerValueFactory<Integer> minvalue = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        this.shour.setValueFactory(hourvalue);
        this.sminute.setValueFactory(minvalue);

    }

    @FXML
    private void start(ActionEvent event) {
        state = true;

       //Thread t = new Thread() {
       //     public void run() {
//try {
//    shour.commitEdit();
//    sminute.commitEdit();
//} catch ( java.text.ParseException e ) {  }
//      hours= (int) shour.getValue();
//               minute= (int) sminute.getValue();
                    seconds = gets();
                System.out.println("i am in !");

             Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {     
                    if (state == true) {
                        
                           //sleep(1000);
                            
                            seconds--;
                            int day = (int) TimeUnit.SECONDS.toDays(seconds);
                            long Hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
                            long minutes = TimeUnit.SECONDS.toMinutes(seconds)
                                    - (TimeUnit.SECONDS.toHours(seconds) * 60);
                            long second = TimeUnit.SECONDS.toSeconds(seconds)
                                    - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
                            label.setText(Hours + " Hour(s), " + minutes + " Minute(s) and "
                                    + second + " Second(s)");
                            if (seconds == 0) {
                         //       break;
                            }
                    }
                             }),
         new KeyFrame(Duration.seconds(1))
    );
                       clock.setCycleCount(Animation.INDEFINITE);
    clock.play();  
                    }
                

        //   }
      //  };

  

    @FXML
    private void stop(ActionEvent event) {
        state = false;

    }

}
