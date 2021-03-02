/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Harsh
 */
package zoom;

public class Zoom {

    protected static Database db = new Database();
    protected static User user = new User();
    protected static Menu menu = new Menu(db,user);
    
       //        db.finalize();        
    }
    
}
