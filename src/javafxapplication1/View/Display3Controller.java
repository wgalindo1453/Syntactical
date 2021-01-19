package javafxapplication1.View;
//CPP DISPLAY

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//CPLUS CPLUS VIEW
public class Display3Controller extends MainMenuController implements Initializable {


    public WebView webView2;
    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    Button backButton, exitButton, saveButton;
    @FXML
    TextArea textArea1, textArea2, textArea3, textArea4;
    public void loadPads1() throws SQLException {



                textArea1.setText(LoadData(getUser_id(), "cpp_pad"));



                try {
                    textArea2.setText((LoadData(getUser_id(), "c_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();
                }



                try {
                   textArea3.setText((LoadData(getUser_id(), "java_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                try {
                    textArea4.setText((LoadData(getUser_id(), "python_pad")));//we need datapad from database
                } catch (SQLException e) {
                    e.printStackTrace();

                }
        getPads().clear();
            }




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
            System.out.println(getUser_name());
            System.out.println(getUser_id());
            try {
                saveData(textArea1.getText(), getDatapad());
                saveData(textArea1.getText(), getDatapad());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadPads1();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            textArea1.setText(LoadData(getUser_id(), getDatapad()));
            System.out.println("INITIALIZE CALLED FROM PYTHON DISPLAY CONTROLLER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ButtonHandler();

    }
}