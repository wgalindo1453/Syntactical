package javafxapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private double xOffset =0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/login.fxml"));
        primaryStage.setTitle("SYNTACTICAL");

       primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

       root.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           }
       });
       root.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               primaryStage.setX(event.getScreenX() - xOffset);
               primaryStage.setY(event.getScreenY() - yOffset);

           }
       });


        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
