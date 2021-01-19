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
public class DisplayController extends MainMenuController implements Initializable {


    public WebView webView2;
    private double xOffset = 0;
    private double yOffset = 0;


    @FXML
    Button backButton, exitButton, cmp_Button;
    @FXML
    Button funcsButton, condsButton, saveButton;
    @FXML
    CheckBox cpp_Check, c_Check, java_Check, python_Check;
    @FXML
    WebView webView;
    @FXML
    TextArea syntaxExpArea;


    public void ButtonHandler() {

        funcsButton.setOnAction(event -> {
            WebEngine engine = webView.getEngine();

           


            if (getDatapad() == "cpp_pad") {
                //engine.load( Urlfetch("url_cpp", getUser_id()));
                try {
                    engine.load(Urlfetch("url_cpp", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            if (getDatapad() == "python_pad") {

                try {
                    engine.load(Urlfetch("url_python", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (getDatapad() == "java_pad") {

                try {
                    engine.load(Urlfetch("url_java", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        });


        condsButton.setOnAction(event -> {
            WebEngine engine = webView2.getEngine();

            if (getDatapad() == "cpp_pad") {
                //engine.load( Urlfetch("url_cpp", getUser_id()));
                try {
                    engine.load(Urlfetch("url_cpp", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            if (getDatapad() == "python_pad") {

                try {
                    engine.load(Urlfetch("url_python", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (getDatapad() == "java_pad") {

                try {
                    engine.load(Urlfetch("url_java", getUser_id()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        });


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

        cmp_Button.setOnAction(event -> {

            if (c_Check.isSelected()) {
                System.out.println("Check box C was selected :)");
                getPads().add("c_pad"); //element 0
            }
            if (cpp_Check.isSelected()) {
                System.out.println("Check box C plus plus was Selected");
                getPads().add("cpp_pad"); //element 1
            }
            if (java_Check.isSelected()) {
                System.out.println("Check box Java was selected :)");
                getPads().add("java_pad");//element 2
            }
            if (python_Check.isSelected()) {
                System.out.println("Check box Jaav was selected :)");
                getPads().add("python_pad"); //element 3
            }
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("CmpDisplay.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene MainMenuScene = new Scene(root);//Creating a Scene object and passing in the Parent we just made
            Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });

            Stage finalWindow = window1;
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    finalWindow.setX(event.getScreenX() - xOffset);
                    finalWindow.setY(event.getScreenY() - yOffset);

                }
            });
            window1.setScene(MainMenuScene);
            window1.show();
        });

        exitButton.setOnAction(event -> {
            Platform.exit();
        });

        saveButton.setOnAction(event -> {
            System.out.println(getUser_name());
            System.out.println(getUser_id());
            try {
                saveData(syntaxExpArea.getText(), getDatapad());
                System.out.println("this is the current datapad from saveButton: "+getDatapad());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DISPLAYTABLE();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            syntaxExpArea.setText(LoadData(getUser_id(), getDatapad()));
            System.out.println("INITIALIZE CALLED FROM PYTHON DISPLAY CONTROLLER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ButtonHandler();

    }
}
