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
    public boolean getCars(String city, int capacity) {
        try {
            ResultSet rs = query("SELECT `cid`,`model`,`capacity`,`bootspace`,`rent` FROM cars WHERE city='" + city
                    + "' AND capacity >= " + capacity + " AND status = 1");

            if (!rs.next()) {
                System.out.print("\t!!!! 🔴 No cars found 🔴 !!!!");
                return false;
            } else {
                System.out.print("📚 📗 Available Cars .: \n\n");
                System.out.format("%5s | %35s | %10s | %10s | %10s\n", "🚙 ID", "MODEL", "💺 CAPACITY", "BOOTSPACE",
                        "💰 RENT");
                System.out
                        .println("------+-------------------------------------+------------+------------+------------");

                do {
                    System.out.format("%5s | %35s | %10s | %10s | %10s\n", rs.getInt("cid"), rs.getString("model"),
                            rs.getInt("capacity"), rs.getInt("bootspace") == 1 ? 'Y' : 'N', rs.getInt("rent"));
                } while (rs.next());
                return true;
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return false;
    }

    public void rentCar(int carID, User user) {

        try {
            ResultSet rs = query("SELECT * FROM cars WHERE cid=" + carID);
            if(rs.next())
            {
                PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO orders VALUES (null,'rent',?,?,?,?,?)");
                stmt1.setInt(1, rs.getInt("rent"));
                stmt1.setInt(2, carID);
                stmt1.setInt(3, user.uid);
                stmt1.setInt(4, rs.getInt("owner"));
                stmt1.setString(5, rs.getString("city"));
                stmt1.executeUpdate(); // Order Table Update
                update("UPDATE cars SET status=0 WHERE cid='" + carID + "'");
            }
            else
            {
                System.out.println("\nEnter a correct Car ID");
            }
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
            ResultSet rs = query("SELECT * FROM orders o JOIN cars c ON o.car_id=c.cid WHERE o.renter=" + user.uid
                    + " OR o.leaser = " + user.uid);

            if (!rs.next()) {
                System.out.print("\t!!!! 🔴 No orders found 🔴 !!!!");
                return false;
            } else {
                System.out.print("📚 📗 Your Orders .:\n\n");
                System.out.format("%10s | %35s | %10s | %20s\n", "Order ID", "🚗 Model", "💰 Expense", "🌇 City");
                System.out.println(
                        "-----------+-------------------------------------+------------+-------------------------");
                do {
                    System.out.format("%10s | %35s | %10s | %20s\n", rs.getInt("oid"), rs.getString("model"),
                            rs.getInt("expense") * ((rs.getInt("renter") == user.uid) ? -1 : 1), rs.getString("city"));
                } while (rs.next());
                return true;
            }
        } catch (Exception excep) {
            excep.printStackTrace();
        }
        return false;
    }
}
