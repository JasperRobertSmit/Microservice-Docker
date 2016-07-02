package App;
/**
 * Created by Jasper on 30-6-2016.
 */
//STEP 1. Import required packages
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:8889/newchatdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";


    public Integer getId(String username, String password){
        Connection conn = null;
        Statement stmt = null;
        Integer returnedId = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT uid FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                returnedId  = rs.getInt("uid");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

        return returnedId;
    }

    public Integer checkCredentials(String username, String password){
        Connection conn = null;
        Statement stmt = null;
        Integer uid = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);


            String sql;
            sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "' ";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                uid = rs.getInt("uid");
            }

            rs.close();
            stmt.close();
            conn.close();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
//            result = true;
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            se.printStackTrace(pw);
//            return sw.toString(); // stack trace as a string
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return uid;
    }

    public boolean insertMessage(Integer uid, Integer roomnumber, String message){
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "INSERT INTO messages (uid, roomnumber, message) VALUES ('"+ uid +"', '" + roomnumber + "', '" + message + "')";
            stmt.executeUpdate(sql);

            result = true;

            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return result;
    }

    public boolean joinRoom(Integer roomnumber, Integer uid){
        Connection conn = null;
        Statement stmt = null;

        boolean result = false;

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT roomnumber FROM chatroom WHERE roomnumber ='" + roomnumber + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.isBeforeFirst()){
                //Niets gevonden
                result = false;
            }else{
                //We voegen alleen een nieuwe record toe als de opgegeven roomnummer al bestaat
                sql = "INSERT INTO chatroom (uid, roomnumber) VALUES ('" + uid + "', '" + roomnumber + "')";
                stmt.executeUpdate(sql);
                result = true;
            }


            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return result;
    }

    public ArrayList<Integer> getChatroomList(Integer uid){
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Integer> roomnumbers = new ArrayList<>();

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT DISTINCT roomnumber FROM chatroom WHERE uid = '" + uid + "'";
            stmt.executeQuery(sql);

//            sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                roomnumbers.add(rs.getInt("roomnumber"));
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return roomnumbers;
    }

    public ArrayList<Map<String,String>> retrieveChat(Integer roomnumber){
        Connection conn = null;
        Statement stmt = null;

        ArrayList<Map<String, String>> resultCollection = new ArrayList<>();


        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * " +
                    "FROM messages " +
                    "INNER JOIN users ON users.uid = messages.uid " +
                    "WHERE roomnumber = '" + roomnumber + "' " +
                    "ORDER BY dateadded";
            stmt.executeQuery(sql);

//            sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                Map<String, String> result = new HashMap<>();
                result.put("Message", rs.getString("message"));
                result.put("Username", rs.getString("username"));

                resultCollection.add(result);
            }




            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return resultCollection;

    }


}//end Database