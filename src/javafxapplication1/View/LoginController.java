package javafxapplication1.View;


import javafxapplication1.Model.DataBaseConnection;

import javafxapplication1.Model.UserInfo;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.sql.ConnectionPoolDataSource;
import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.fxml.FXMLLoader.getDefaultClassLoader;
import static javafx.fxml.FXMLLoader.load;
import java.sql.*;

public class LoginController extends UserInfo implements Initializable {
    private double xOffset =0;
    private double yOffset = 0;
    @FXML
    Label loginLabel;
    @FXML
    Button loginButton, regButton;
    @FXML
    TextField userName;
    @FXML
    PasswordField passWord;


    int user_ident;

    public int getUser_ident() {
        return user_ident;
    }

    public void setUser_ident(int user_ident) {
        this.user_ident = user_ident;
    }

    public void onClick_btn_regButton() throws SQLException {

        //UserInfo ui = new UserInfo(userName.getText(), passWord.getText());

       if (validate(userName.getText(), passWord.getText())== true){ //already set userId
            loginLabel.setText("Sorry account already exist");


        }else {
          addUser(userName.getText(), passWord.getText());
           loginLabel.setText("Registration Successful");


       }

    }



    public void onClick_btn_Login(ActionEvent event) throws IOException, SQLException {
        DISPLAYTABLE();



        //UserInfo ui = new UserInfo(userName.getText(), passWord.getText());
        if(!validate(userName.getText(), passWord.getText()))
        {
            loginLabel.setText("Please Register no Record found!");

        }else{
              setUser_ident(getUser_id());

              System.out.println("User_ident = "+ getUser_ident());
        //adding everything in FXML into this Parent object and calling it MainMenuParent
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Scene MainMenuScene = new Scene(root);//Creating a Scene object and passing in the Parent we just made

            
        //This line gets the Stage information
        //action event when you say get Source doesnt know what is returned, so we make it a node type object
        // then because its a node we can get the scene and get the window then cast it as Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

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


    }
    }
    public void onClick_btn_Exit(ActionEvent e){
        Platform.exit();
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
