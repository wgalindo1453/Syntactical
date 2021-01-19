package javafxapplication1.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {



private String databasePassword = "";
private String databaseName = "";
   private String databaseUser ="";
    public Connection databaseLink;

    public Connection getConnection(){

       // String url ="jdbc:h2:file:C:/Users/rellik/Destop/destopdatabase/test;";
       String url ="jdbc:h2:~/test";
     //  String url ="jdbc:h2:mem:testdb;IFEXISTS=TRUE";

        try{
            Class.forName("org.h2.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Connected");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
