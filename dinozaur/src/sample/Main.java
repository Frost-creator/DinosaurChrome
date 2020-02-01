package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.Scanner;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        File file = new File("src/regF.txt");
        if (file.exists()) {
            try {


                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                primaryStage.setTitle("Dino custom");
                Scene scene = new Scene(root, 727, 511);
                primaryStage.setScene(scene);
            } catch (Exception e) {

            }
        } else {
            root = FXMLLoader.load(getClass().getResource("Username.fxml"));
            primaryStage.setTitle("Dino custom");
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setScene(scene);
        }


        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
