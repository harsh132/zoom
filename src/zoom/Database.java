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

    public ResultSet query(String sql){
        try{
            return stmt.executeQuery(sql);
        }
        catch (Exception excep) {
             excep.printStackTrace();
        }
        return null;
    }
    
    public void update(String sql){
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
}
