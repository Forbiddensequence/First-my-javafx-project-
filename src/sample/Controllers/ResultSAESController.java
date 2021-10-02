package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.AES.SAES;

import java.io.IOException;

public class ResultSAESController {


    @FXML
    private Button confirmresultoutput;

    @FXML
    private TextArea textoutputfield;

    @FXML
    private Button confirmback;

    @FXML
    void initialize() {

        confirmback.setOnAction(event->{
            confirmback.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
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


        confirmresultoutput.setOnAction(event->{
            SAES.SAES();
            System.out.println("Фега прога по итогу работает получается");
            for(int i=0;i<SAES.resultDocument.length;i++){
                textoutputfield.appendText(SAES.resultDocument[i]+"\n");
            }
            textoutputfield.setOpacity(1.0);
        });

    }
}
