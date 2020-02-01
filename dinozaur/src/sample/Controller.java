package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public ImageView ground;
    public ImageView dino;
    public ImageView cactus;
    public Text score;
    public Label lostCase;

    public Button btn;
    public Button settings;



    boolean isActive = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                File file = new File("src/audio.wav");
                try {

                    Clip clip = AudioSystem.getClip();

                    AudioInputStream ais = AudioSystem.getAudioInputStream(file);


                    clip.open(ais);

                    clip.setFramePosition(0);
                    clip.start();




                } catch (Exception e) {
                    e.printStackTrace();
                }

                lostCase.setTextFill(Color.web("#ff0000"));
                lostCase.setFont(new Font(32));
                lostCase.setVisible(false);


                settings.setDisable(true);

                settings.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {


                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsFrame.fxml"));

                            Stage stage = (Stage) settings.getScene().getWindow();


                            Parent root = (Parent) fxmlLoader.load();

                            stage = new Stage();

                            stage.setScene(new Scene(root, 727, 511));
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int Score = Integer.parseInt(score.getText());

                        for (int i = 0; i < 1000 && isActive; i++) {

                            try {
                                Thread.sleep(100);
                                Score++;
                                score.setText(String.valueOf(Score));


                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }

                    }
                }).start();


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
                                    System.out.println("You Lost");
                                    isActive = false;
                                    settings.setDisable(false);
                                    lostCase.setVisible(true);

                                    btn.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            settings.setDisable(true);

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
        }).start();
    }


}
