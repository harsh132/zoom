/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoom;

//import java.io.Console;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author jayeshbhole
 */

public class Menu {
    public static Scanner myObj = new Scanner(System.in);

    protected static Database db = null;
    protected static User user = null;

    public Menu(Database databaseObj, User userobj) {
        db = databaseObj;
        user = userobj;
        greet();
    }

    public void loginMenu() {
        int n = 0;
        while (true) {
            String inpName = null;
            String inpEmail = null;
            String inpPassword = null;

            clear();
            System.out.println("\n1. Register\n2. Login\n3. Exit\n");
            n = intinput("Enter your choice: ");
            clear();

            switch (n) {
                case 1:
                    System.out.println("\n\nRegister to ZOOM\n\n");
                    inpName = input("Name: ");
                    inpEmail = input("Email: ");
                    inpPassword = input("Password: ");

                    db.update("INSERT INTO users VALUES (null, '" + inpName + "', '" + inpEmail + "','" + inpPassword
                            + "')");
                    break;

                case 2:
                    System.out.println("\n\nLogin to ZOOM\n");
                    inpEmail = input("Email: ");
                    inpPassword = input("Password: ");
                    // char[] inpPassword = con.readPassword("Enter password : ");

                    ResultSet rs = db.query(
                            "SELECT * FROM users WHERE email='" + inpEmail + "' AND password='" + inpPassword + "'");

                    try {
                        if (rs.next()) {
                            user.uid = rs.getInt("id");
                            user.name = rs.getString("name");
                            user.email = rs.getString("email");
                            System.out.println("Login Successful!  Welcome " + user.name);
                            dashboard();
                        } else {
                            System.out.println("Incorrect Email or Password");
                        }
                    } catch (Exception excep) {
                        excep.printStackTrace();
                    }
                    break;

                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    public void dashboard() {
        int n = 0;
        while (true) {

            System.out.println("\n\n1. Rent a car\n2. Lease your car\n3. Order history\n4. Logout\n");
            n = intinput("Enter your choice: ");
            clear();
            switch (n) {
                case 1:
                    String city = input("\tEnter your location .: ");
                    int capacity = intinput("\tEnter the desired capacity .: ");
                    db.getCars(city, capacity);

                    String carID = input("\tEnter the ID of the car you want to rent .: ");
                    db.rentCar(carID, user);

                    break;

                case 2:
                    int rent = intinput("\tEnter the rent .: ");
                    capacity = intinput("\tEnter the capacity(number of seats) .: ");
                    int bootspace = intinput("\t Does the car have trunk/bootspace. (Y/N).: ");
                    String model = input("\tEnter the model of the car.: ");
                    String c_city = input("\tEnter the location of the car.: ");

                    Car car = new Car(rent, capacity, bootspace, model, c_city);
                    db.lease(user, car);

                    break;

                case 3:
                    db.history(user);
                    break;

                case 4:

                    return;
            }
        }
    }

    // Helper Functions
    public static void clear() {
        for (int i = 0; i < 80 * 300; i++)
            System.out.print("\b");
    }

    public static String input(String args) {
        System.out.print(args);
        String in1 = myObj.nextLine();
        return in1;
    }

    public static int intinput(String args) {
        System.out.print(args);
        String in1 = myObj.nextLine();
        return Integer.parseInt(in1);
    }

    private static void greet() {
        System.out.println("\t\tzzzzzzzzzzzzzzzzz    ooooooooooo       ooooooooooo       mmmmmmm    mmmmmmm   \n"
                + "\t\tz:::::::::::::::z  oo:::::::::::oo   oo:::::::::::oo   mm:::::::m  m:::::::mm \n"
                + "\t\tz::::::::::::::z  o:::::::::::::::o o:::::::::::::::o m::::::::::mm::::::::::m\n"
                + "\t\tzzzzzzzz::::::z   o:::::ooooo:::::o o:::::ooooo:::::o m::::::::::::::::::::::m\n"
                + "\t\t      z::::::z    o::::o     o::::o o::::o     o::::o m:::::mmm::::::mmm:::::m\n"
                + "\t\t     z::::::z     o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n"
                + "\t\t    z::::::z      o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n"
                + "\t\t   z::::::z       o::::o     o::::o o::::o     o::::o m::::m   m::::m   m::::m\n"
                + "\t\t  z::::::zzzzzzzz o:::::ooooo:::::o o:::::ooooo:::::o m::::m   m::::m   m::::m\n"
                + "\t\t z::::::::::::::z o:::::::::::::::o o:::::::::::::::o m::::m   m::::m   m::::m\n"
                + "\t\tz:::::::::::::::z  oo:::::::::::oo   oo:::::::::::oo  m::::m   m::::m   m::::m\n"
                + "\t\tzzzzzzzzzzzzzzzzz    ooooooooooo       ooooooooooo    mmmmmm   mmmmmm   mmmmmm");

        System.out.println("\n\tWelcome to zoom. The car rental platform. 🚗  🚙  🏎️\n");
    }

}