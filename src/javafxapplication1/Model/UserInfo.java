package javafxapplication1.Model;
//import org.h2.jdbc.JdbcPreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class UserInfo extends DataBaseConnection {

    private  static int user_id;
    private  static String user_name;
    private  static String user_pw;
    private  static String Datapad;
   private static List<String> pads = new ArrayList<String>();


    public static List<String> getPads() {
        return pads;
    }

    public static void setPads(List<String> pads) {
        UserInfo.pads = pads;
    }

    public UserInfo() {
    }


    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        UserInfo.user_id = user_id;
    }

    public static String getUser_name() {
        return user_name;
    }

    public static void setUser_name(String user_name) {
        UserInfo.user_name = user_name;
    }

    public static String getUser_pw() {
        return user_pw;
    }

    public static void setUser_pw(String user_pw) {
        UserInfo.user_pw = user_pw;
    }

    public static String getDatapad() {
        return Datapad;
    }

    public static void setDatapad(String datapad) {
        Datapad = datapad;
    }

    public  UserInfo(String user_name, String user_pw) {
        this.user_name = user_name;
        this.user_pw = user_pw;

    }

public static String Urlfetch(String type_url, int user_id) throws SQLException {

    DataBaseConnection connectNow = new DataBaseConnection();
    Connection connectDB = connectNow.getConnection();

    String geturlData = "SELECT url_id."+type_url+" \n" +
            "FROM url_id\n" +
            "WHERE url_id = (\n" +
            "    SELECT users.user_id \n" +
            "    FROM users\n" +
            "    WHERE users.user_id = "+user_id+"\n" +
            ")";
    String data="";
    Statement statement = connectDB.createStatement();
    ResultSet queryResult = statement.executeQuery(geturlData);
    while(queryResult.next()){
        System.out.println("Urlfetch Called with this queryResult: "+queryResult.getString(type_url));
        data = queryResult.getString(type_url);//gets

    
    }

    return data;
}






    //checks the users input(username password) with database returns true if theres a match and false if no match
    public boolean validate(String name, String password) throws SQLException {
        Statement statement = null;
        DataBaseConnection connectNow = new DataBaseConnection();
        try (Connection connectDB = connectNow.getConnection()) {
            String getUserId = "SELECT user_id FROM users WHERE user_name = '" + name + "' AND user_pw = '" + password + "'";
            String verifyLogin = "SELECT Count(1) FROM users WHERE user_name = '" + name + "' AND user_pw = '" + password + "'";
            boolean result = false;
            PreparedStatement st = connectDB.prepareStatement(verifyLogin);
            ResultSet rs = st.executeQuery();
          //  ResultSet queryResult = connectDB.createStatement().executeQuery(verifyLogin);
            //ResultSet queryResult = statement.executeQuery(verifyLogin);


            while (rs.next()) {
                if (rs.getInt(1) == 1) {

                    System.out.println("congrats");
                    setUser_name(name);
                    setUser_pw(password);

                    result = true;
                } else {

                    System.out.println("incorrect");
                    result = false;
                }
            }
                PreparedStatement st1 = connectDB.prepareStatement(getUserId);
            ResultSet rs2 = st1.executeQuery();
                   // ResultSet queryResul2 = statement.executeQuery(getUserId);
            int users_id;
            while (rs2.next()) {
                users_id = rs2.getInt("user_id");

                System.out.println(users_id);
                setUser_id(users_id); //setting the userid to this specific current user at login to use later
             ///   try {
               //     LoadData(users_id);
               // }catch(IOException e){
               //     System.out.println(e);
              //  }
            }
            
            return result;
            
        }

    }


    public static String LoadData(int user_id, String type_pad) throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getpadData = "SELECT scratchpad_id."+type_pad+"\n" +
                "FROM scratchpad_id \n" +
                "WHERE scratchpad_id= \n" +
                "(\n" +
                "    SELECT users.user_id \n" +
                "    FROM users\n" +
                "    WHERE users.user_id="+user_id+"\n" +
                ")";
        String data="";
        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(getpadData);
        while(queryResult.next()){
            System.out.println("LoadData Called with this queryResult: "+queryResult.getString(type_pad));
            data = queryResult.getString(type_pad);//gets

           
        }
connectDB.close();
        return data;

    }


    public static void saveData(String data, String type_pad) throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        Statement statement = connectDB.createStatement();

        String updateSP = "UPDATE scratchpad_id SET "+type_pad+" = '"+data+"' WHERE scratchpad_id = (\n" +
                "    SELECT users.user_id\n" +
                "    FROM users\n" +
                "    WHERE users.user_id="+getUser_id()+"\n" +
                ")";
        System.out.println(getUser_id());

        statement.executeUpdate(updateSP);
        
       
    }

    public static void DISPLAYTABLE() throws SQLException {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        Statement statement = connectDB.createStatement();

        String usersTable = "SELECT * FROM users";
        System.out.println(usersTable);

        ResultSet rs =statement.executeQuery(usersTable);
        while ( rs.next()) {
            int id_col = rs.getInt("user_id");
            String first_name = rs.getString("user_name");
            String password = rs.getString("user_pw");
            int scrathpad_id = rs.getInt("scratchpad_id");
            int url_id = rs.getInt("url_id");

            String p = id_col + " " + first_name + " " + password+" "+ scrathpad_id+ " "+url_id;
            System.out.println(p);
        }
     
    }



    public void addUser(String name, String password) throws SQLException {


        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        Statement statement = connectDB.createStatement();

        String addUser = "INSERT INTO users(user_name,user_pw) VALUES('" + name + "', '" + password + "')";
        String addscratch = "INSERT INTO scratchpad_id(python_pad,cpp_pad,java_pad,c_pad) VALUES(DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
        String addurls="INSERT INTO url_id(url_python,url_cpp,url_java) VALUES(DEFAULT,DEFAULT,DEFAULT)";
        statement.executeUpdate(addUser);
        statement.executeUpdate(addscratch);
        statement.executeUpdate(addurls);
        System.out.println("added all records with user executed");
        
        
    }

}
