package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class startWindowController {

    @FXML
    private Button confirmback;

    @FXML
    private ImageView AES;

    @FXML
    private ImageView DES;

    @FXML
    private Button confirmSAES;

    @FXML
    private Button confirmSDES;


    @FXML
    void initialize() {
        System.out.println("Фега стартовое окно работает");
        confirmSAES.setOnAction(event->{
            confirmSAES.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            System.out.println("Фега переход между режимами работает");
            loader.setLocation(getClass().getResource("/sample/FXMLS/confirmdataSAES.fxml"));
            try{
                loader.load();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setTitle("Я открылся!!!");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });

        confirmSDES.setOnAction(event->{
            confirmSDES.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            System.out.println("Фега переход между режимами работает");
            loader.setLocation(getClass().getResource("/sample/FXMLS/confirmdataSDES.fxml"));
            try{
                loader.load();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setTitle("Я открылся!!!");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });
    }
}
