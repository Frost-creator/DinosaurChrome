package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        File file = new File("src/regF.txt");
        if (file.exists()) {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Dino custom");
            Scene scene = new Scene(root, 727, 511);
            this.primaryStage.setScene(scene);
        }else{
            root = FXMLLoader.load(getClass().getResource("Username.fxml"));
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Dino custom");
            Scene scene = new Scene(root, 600, 400);
            this.primaryStage.setScene(scene);
        }



        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
