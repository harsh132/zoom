/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoom;

/**
 *
 * @author jayeshbhole
 */
public class Car {
    public static int id = -1;
    public static int rent = -1;
    public static String model = null;
    public static String status = null;
    
    public Car(int cid,int crent, String cmodel, String cstatus){
        id = cid;
        rent = crent;
        model = cmodel;
        status = cstatus;
        
    }
}
