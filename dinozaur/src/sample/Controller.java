package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public ImageView ground;
    public ImageView dino;
    public ImageView cactus;
    public boolean allowJump = false;
    public Button btn;



    boolean isActive = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btn.setStyle("-fx-background-color: transparent");




        try {
            ground.setImage(new Image(new FileInputStream("src/sand.png")));
            dino.setImage(new Image(new FileInputStream("src/dino.jpg")));
            cactus.setImage(new Image(new FileInputStream("src/cactus.jpg")));

        } catch (Exception e) {
            e.printStackTrace();
        }

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isActive) {

                    if (dino.getY() == 0.0) {

                        new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {




                                    while (dino.getY() > -175 && isActive) {
                                        dino.setY(dino.getY() - 10);
                                        Thread.sleep(30);
                                    }
                                    Thread.sleep(80);
                                    while (dino.getY() != 0.0 && isActive) {
                                        dino.setY(dino.getY() + 10);
                                        Thread.sleep(30);
                                    }


                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    }).start();
                    }

                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (isActive) {


                    if (cactus.getX() <= -435 && cactus.getX() >= -555) {

                        if ((dino.getY() * -1) < cactus.getFitHeight()) {
                            System.out.println(cactus.getFitHeight());
                            System.out.println("loose");
                            isActive = false;

                            btn.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {


                                    try {


                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

                                        Stage stage = (Stage) ground.getScene().getWindow();
                                        stage.close();

                                        Parent root = (Parent) fxmlLoader.load();

                                        stage = new Stage();

                                        stage.setScene(new Scene(root));
                                        stage.show();
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            });

                            break;

                        }
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        }).start();


        new Thread((new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (isActive) {

                    try {


                        cactus.setX(cactus.getX() - 15);
                        if (cactus.getX() <= -665) {

                            cactus.setX(95);
                        }


                        Thread.sleep(35);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        })).start();

    }


}
