/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.InetAddress;
import java.sql.ResultSet;
/**
 *
 * @author AQUAVIRUS
 */
public class LoginModel {
    private String baseURL;
    private static Connection connection;
    private static Statement statement;
    private boolean connected;
    private final String dbName = "Blocks";
    private static String databaseURL;
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/electricitydb";
    private static String username;
    private static String password;
    
    
    //private enum Status { CONNECTED ( 1 ), INVALID ( 2 ), UNKNOWN ( 3 ) };
    public enum Status {
        CONNECTED  (3),  //calls constructor with value 3
        INVALID(2),  //calls constructor with value 2
        UNKNOWN   (1)   //calls constructor with value 1
        ; // semicolon needed when fields / methods follow


        private final int levelCode;

        Status(int statusCode) {
            this.levelCode = statusCode;
        }

        public int getStatusCode() {
            return this.levelCode;
        }

    }
    Status status = Status.UNKNOWN;
    int connectionStatus;
    
    public LoginModel()
    {
        initVariables();
    }
    
    private void initVariables()
    {
        //baseURL = "jdbc:derby://localhost:1527/" +dbName + ";";
        //connected = false;
    }
    
    public void createConnection( String username, String password ) throws SQLException
    {   LoginModel.username = username;
        LoginModel.password = password;
        initSQLServer();
    }
    
    private void initSQLServer() throws SQLException{
        try
        {
            Class.forName(DRIVER).newInstance();
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
        try
        {
            connection = DriverManager.getConnection( DATABASE_URL, username, password );
            status = Status.CONNECTED;
            System.out.println( "connected" );
        }
        catch( SQLException ex )
        {
            //INVALID PASSWORD
            //derby: 08004 SQL: 28000
            if (ex.getSQLState().equals("08004") || ex.getSQLState().equals("28000") )
             {
                 System.err.println("SQLException: " + ex.getMessage());
                 status = Status.INVALID;
             }
            else
            {
                status = Status.UNKNOWN;
                ex.printStackTrace();
            }
            throw new SQLException();
        }
        
    }
    
    public int getErrorCode()
    {
        return status.getStatusCode();
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
    
}
