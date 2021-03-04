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

    public Database() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/zoom", "admin", "admin");
            System.out.println("Connection is created successfully: ✅ ");
            stmt = (Statement) conn.createStatement();
        } catch (SQLException excep) {
            excep.printStackTrace();
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    // Helpers
    public ResultSet query(String sql) {
        try {
            return stmt.executeQuery(sql);
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return null;
    }

    public void update(String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    protected void finalize() {
        System.out.println("Disconnected");
        try {
        if (stmt != null)
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Queries
    public void getCars(String city, int capacity) {
        try {
            ResultSet rs = query(
                    "SELECT * FROM cars WHERE city='" + city + "' AND capacity >= '" + capacity + "' AND status = '1'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                // Print one row
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(rs.getString(i) + " "); // Print one element of a row
                }
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    public void rentCar(String carID, User user) {

        try {
            ResultSet rs = query("SELECT * FROM cars WHERE cid='" + carID + "'");
            rs.next();
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO orders VALUES (null,'rent',?,?,?,?,?)");
            stmt1.setInt(1, rs.getInt("rent"));
            stmt1.setString(2, carID);
            stmt1.setInt(3, user.uid);
            stmt1.setInt(4, rs.getInt("owner"));
            stmt1.setString(5, rs.getString("city"));
            stmt1.executeUpdate();
            update("UPDATE cars SET status=0 WHERE cid='" + carID + "'");
        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    public void lease(User user, Car car) {
        try {
            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO cars VALUES (null,?,?,?,?,?,?,?)");
            stmt1.setInt(1, car.rent);
            stmt1.setInt(2, car.capacity);
            stmt1.setInt(3, car.bootspace);
            stmt1.setString(4, car.model);
            stmt1.setInt(5, 1);
            stmt1.setInt(6, user.uid);
            stmt1.setString(7, car.city);
            stmt1.executeUpdate();
        } catch (Exception excep) {
            excep.printStackTrace();
        }

    }

    public boolean history(User user) {
        try {
            ResultSet rs = query("SELECT * FROM orders WHERE renter='" + user.uid + "' OR leaser = '" + user.uid + "'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            if(columnsNumber == 0){
                System.out.print("\t!!!! 🔴 No cars found 🔴 !!!!");
                return false;
            }else{
                System.out.print("📚 📗 Available Cars .: ");
                while (rs.next()) {
                    // Print one row
                    for (int i = 1; i <= columnsNumber; i++) {
                        System.out.print(" 🛒  "+rs.getString(i) + " "); // Print one element of a row
                    }
                    return true;
                }
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return false;
    }
}
