/**
 *
 * @author jayeshbhole
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zoom;

import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

class Database {
    public static Connection conn = null;
    public static Statement stmt = null;
    
    public Database(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (Exception e) {
                System.out.println(e);
            }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/zoom", "admin", "admin");
            System.out.println("Connection is created successfully:");
            stmt = (Statement) conn.createStatement();
        }
        catch (SQLException excep) {
            excep.printStackTrace();
        }
        catch (Exception excep) {
            excep.printStackTrace();
        }
    }
//    Helpers
    public static ResultSet query(String sql){
        try{
            return stmt.executeQuery(sql);
        }
        catch (Exception excep) {
             excep.printStackTrace();
        }
        return null;
    }
    
    public static void update(String sql){
        try{
            stmt.executeUpdate(sql);
        }
        catch (Exception excep) {
             excep.printStackTrace();
        }
    }

    protected void finalize(){
        System.out.println("Disconnected");
        try {
            if (stmt != null)
            conn.close();
        }
        catch (SQLException se) {}
        try {
            if (conn != null)
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        } 
    }
    
//    Queries
    public static void getCars(String city, String capacity){    
        try {
            ResultSet rs = query("SELECT * FROM users WHERE city='"+ city + "AND capacity >= '" + capacity+ "'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            while (rs.next()) {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++){
                    
                    System.out.print(rs.getString(i) + " "); //Print one element of a row
                    
                }
            }   
        } catch (Exception excep) {
                        excep.printStackTrace();
        }
    }
    
    public static void rentCar(String carID){
        
//        update("INSERT INTO orders VALUES (null, 'rent', '"+ rent +"','"+ carID +"'"+uid +"'"+leaser +"'");
    }
}
