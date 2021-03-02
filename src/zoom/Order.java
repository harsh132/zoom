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
public class Order {
    public static int oid = -1;
    public static String type = null;
    public static int expense = -1;
    public static int cid = -1;
    public static int leaser = -1;
    public static int renter = -1;
    public static boolean bootspace = false;
    public static String city = null;

    // Constructor
    public Order(int orderID, String oType, int oexpense, int oCid, int oLeaser, int oRenter, boolean oBoot, String oCity){
        oid = orderID;
        type = oType;
        cid = oCid;
        leaser = oLeaser;
        renter = oRenter;
        bootspace = oBoot;
        city = oCity;
    }

}
