/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Harsh
 */
package zoom;

import java.io.*;

public class Zoom {
    public static int Userid = -1;
    public static String Name="";
    protected static Database db = new Database();
    
    protected static Menu menu = new Menu(db);
    
    
    public static void main(String[] args) {
       //        db.finalize();        
    }
    
}
