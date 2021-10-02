package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.DES.SDES;

import java.io.IOException;

public class ResultSDESController {

    @FXML
    private Button confirmback;

    @FXML
    private Button confirmresultoutput;

    @FXML
    private TextArea textoutputfield;

    @FXML
    void initialize() {

        confirmback.setOnAction(event->{
            confirmback.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
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


        confirmresultoutput.setOnAction(event->{
            SDES.SDES();
            System.out.println("Фега прога по итогу работает получается");
            for(int i=0;i<SDES.resultDocument.length;i++){
                textoutputfield.appendText(SDES.resultDocument[i]+"\n");
            }
            textoutputfield.setOpacity(1.0);
        });

    }
}
