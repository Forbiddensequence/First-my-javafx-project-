package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLS/startwindow.fxml"));
        primaryStage.setTitle("Я открылся!!!");
        primaryStage.setScene(new Scene(root, 926,659));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
