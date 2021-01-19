package javafxapplication1.View;
//-Xmx1024m

import javafxapplication1.Model.UserInfo;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CmpDisplayController extends UserInfo implements Initializable {


    private double xOffset =0;
    private double yOffset = 0;
//<---SELECTION OBJECT

    MainMenuController object;


    @FXML
    Button backButton, exitButton;
    @FXML
    Button  saveButton;
    @FXML
    WebView webView;
    @FXML
    TextArea  originalTextArea, newTextArea, newTextArea2, newTextArea3;



    public void ButtonHandler() {

        backButton.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene MainMenuScene = new Scene(root);//Creating a Scene object and passing in the Parent we just made
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
                    window.setX(event.getScreenX() - xOffset);
                    window.setY(event.getScreenY() - yOffset);

                }
            });
            window.setScene(MainMenuScene);
            window.show();

        });

        exitButton.setOnAction(event -> {
            Platform.exit();
        });

        saveButton.setOnAction(event -> {
            System.out.println(getUser_name()); //debug
            System.out.println(getUser_id()); //debug
            try {
                saveData(originalTextArea.getText(), "cpp_pad");
                saveData(newTextArea.getText(), "c_pad");
                saveData(newTextArea2.getText(), "java_pad");
                saveData(newTextArea3.getText(), "python_pad");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });


            WebEngine engine = webView.getEngine();
            engine.load("https://www.onlinegdb.com/online_c_compiler");


    }


    public void loadPads() throws SQLException {


        for (String temp : getPads()) {
            if (temp == "cpp_pad") {
                originalTextArea.setText(LoadData(getUser_id(), "cpp_pad"));
            }

            if (temp == "c_pad") {
                try {
                    newTextArea.setText((LoadData(getUser_id(), "c_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (temp == "java_pad") {
                try {
                    newTextArea2.setText((LoadData(getUser_id(), "java_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (temp == "python_pad") {
                try {
                    newTextArea3.setText((LoadData(getUser_id(), "python_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
        getPads().clear();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadPads();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ButtonHandler();

    }


}

