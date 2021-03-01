/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Harsh
 */
package zoom;
import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.util.Scanner;


public class Zoom {
    public static Scanner myObj = new Scanner(System.in);
    public static Connection conn = null;
    public static Statement stmt = null;
    public static void connect(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (Exception e) {
                System.out.println(e);
            }
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/zoom", "admin", "admin");
//            System.out.println("Connection is created successfully:");
            stmt = (Statement) conn.createStatement();
        }
        catch (SQLException excep) {
            excep.printStackTrace();
        }
        catch (Exception excep) {
            excep.printStackTrace();
        }
    }
    
    public static void disconnect(){
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
    
    public static void clear(){
        for(int i = 0; i < 80*300; i++)
        System.out.print("\b");
    }

    public static String input(String args){
        System.out.print(args);
        String in1 = myObj.nextLine();
        return in1;
    }
    public static int intinput(String args){
        System.out.print(args);
        String in1 = myObj.nextLine();
        return Integer.parseInt(in1);
    }
    
    public static void menu(){
        connect();
        int n=0;
        while(n!=3){
            clear();
            System.out.println(
"zzzzzzzzzzzzzzzzz    ooooooooooo       ooooooooooo       mmmmmmm    mmmmmmm   \n" +
"z:::::::::::::::z  oo:::::::::::oo   oo:::::::::::oo   mm:::::::m  m:::::::mm \n" +
"z::::::::::::::z  o:::::::::::::::o o:::::::::::::::o m::::::::::mm::::::::::m\n" +
"zzzzzzzz::::::z   o:::::ooooo:::::o o:::::ooooo:::::o m::::::::::::::::::::::m\n" +
"      z::::::z    o::::o     o::::o o::::o     o::::o m:::::mmm::::::mmm:::::m\n" +
"     z::::::z     o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n" +
"    z::::::z      o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n" +
"   z::::::z       o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n" +
"  z::::::zzzzzzzz o:::::ooooo:::::o o:::::ooooo:::::o m::::m   m::::m   m::::m\n" +
" z::::::::::::::z o:::::::::::::::o o:::::::::::::::o m::::m   m::::m   m::::m\n" +
"z:::::::::::::::z  oo:::::::::::oo   oo:::::::::::oo  m::::m   m::::m   m::::m\n" +
"zzzzzzzzzzzzzzzzz    ooooooooooo       ooooooooooo    mmmmmm   mmmmmm   mmmmmm");
            System.out.println("\nWelcome to zoom. The car rental platform.\n");
            System.out.println("1. Register\n2. Login\n3. Exit\n");
            n=intinput("Enter your choice: ");
            if(n==3)System.exit(0);
            else if(n==1){
                clear();
                System.out.println("Register to ZOOM\n\n");
                String name= input("Name: ");
                String email= input("Email: ");
                String password= input("Password: ");
                try{
                    stmt.executeUpdate("INSERT INTO users VALUES (null, '"+ name +"', '"+ email +"','"+ password +"')");
                }
                catch (SQLException excep) {
                    excep.printStackTrace();
                }
                catch (Exception excep) {
                     excep.printStackTrace();
                }
            }
            else if(n==2){
                
            }
        }
        

    }
    public static void main(String[] args) {
        menu();
    }
    
}
