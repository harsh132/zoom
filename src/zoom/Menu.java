/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoom;

import java.io.Console;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author jayeshbhole
 */
public class Menu {
    public static Scanner myObj = new Scanner(System.in);
    
    public static int Userid = -1;
    public static String Name="";
    

    protected static Database db = null;
    
    public Menu(Database databaseObj){
        db = databaseObj;
        
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
        
        int n=0;
        while(true){
            clear();
            System.out.println("1. Register\n2. Login\n3. Exit\n");
            n=intinput("Enter your choice: ");
            if(n==3)System.exit(0);
            else if(n==1){
                clear();
                System.out.println("Register to ZOOM\n\n");
                String name= input("Name: ");
                String email= input("Email: ");
                String password= input("Password: ");

                db.update("INSERT INTO users VALUES (null, '"+ name +"', '"+ email +"','"+ password +"')");
            }
            else if(n==2){
                
                System.out.println("Login to ZOOM\n\n");
                String email= input("Email: ");
                String password= input("Password: ");
                // char[] password = con.readPassword("Enter password : "); 

                ResultSet rs=db.query("SELECT * FROM users WHERE email='"+ email +"' AND password='"+ password +"'");
                
                try
                {
                    if(rs.next())
                    {
                        Userid=rs.getInt("id");
                        Name=rs.getString("name");
                        System.out.println("Login Successful!  Welcome "+Name);
                        return;

                    }
                    else
                    {
                        System.out.println("Incorrect Email or Password");
                    }
                }
                catch (Exception excep) {
                    excep.printStackTrace();
                }
            }
            else if(n==3){
                System.exit(0);
            }
        }
    }

//    Helper Functions
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
    

}