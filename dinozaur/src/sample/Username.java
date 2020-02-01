package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Username implements Initializable {

    public Button submit;
    public TextField username;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File file = new File("src/regF.txt");


        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String line = username.getText();
                if (!line.equalsIgnoreCase("") || line.contains(" ")) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        FileWriter writer = new FileWriter(file);

                        writer.write(line);
                        writer.flush();
                        next();

                        writer.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public void next() {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();

            Parent root = (Parent) fxmlLoader.load();

            stage = new Stage();

            stage.setScene(new Scene(root, 727, 511));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
