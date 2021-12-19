package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.AES.SAES;
import sample.DES.SDES;
import sample.Main;
import sample.Verifier;

import java.io.*;
import java.util.Arrays;

public class ResultSDESController {

    @FXML
    private Button confirmback;

    @FXML
    private Button confirmresultoutput;

    @FXML
    private TextArea textoutputfield;

    @FXML
    void initialize() {
        Main.CurrentButtonScene=getButt();
        confirmback.setOnAction(event->{
            if(Verifier.isButtonHopeActive) {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/confirmdataSDES.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }
            else {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root, 926, 659));
                stage.setResizable(false);
                stage.show();
            }
        });


        confirmresultoutput.setOnAction(event->{
            if(Verifier.isButtonHopeActive) {
                FileWriter writer=null;
                FileReader reader =null;
                try {
                    SDES.SDES();
                    System.out.println("Фега прога по итогу работает получается");
                    writer = new FileWriter("ResSDES.txt", false);
                    System.out.println(SDES.resultDocument.length);
                    for (int i = 0; i < SDES.resultDocument.length; i++) {
                        textoutputfield.appendText(SDES.resultDocument[i] + "\n");
                        writer.write(Verifier.encrypt(SDES.resultDocument[i])+ "\n");
                    }
                    textoutputfield.setOpacity(1.0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                confirmback.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/FXMLS/startwindow.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Я открылся!!!");
                stage.setScene(new Scene(root, 926, 659));
                stage.setResizable(false);
                stage.show();
            }
        });

    }

    public Button getButt(){
        return confirmback;
    }
}
