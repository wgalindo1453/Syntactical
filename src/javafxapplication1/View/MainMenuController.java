package javafxapplication1.View;

import javafxapplication1.Model.DataBaseConnection;

import javafxapplication1.Model.UserInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMenuController extends UserInfo implements Initializable {
    @FXML
    ToggleButton cppButton, pythonButton, javaButton, compButton;
    @FXML
    Label mmLabel;


    private double xOffset =0;
    private double yOffset = 0;



//THE JAVA BUTTON SO WE KNOW LOAD STAGE WORKS HERE
public void buttonHandler(){

    compButton.setOnAction(event -> {
        setDatapad("cpp_pad");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Display3.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);

            }
        });
    });



    javaButton.setOnAction(event -> {

        setDatapad("java_pad");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Display2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);

            }
        });

    });

    pythonButton.setOnAction(event -> {
        setDatapad("python_pad");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Display2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);

            }
        });

    });
    cppButton.setOnAction(event -> {
        setDatapad("cpp_pad");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Display.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);

            }
        });
        System.out.println("calling setDatapad(cpp pad)"); //DEBUGGING


    });







}











    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("THIS GOT CALLED FROM MAIN MENU CONTROLLER");
        mmLabel.setText("Welcome back "+getUser_name()+"!");
        System.out.println();
        buttonHandler();

    }
}
