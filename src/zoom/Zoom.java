package zoom;

public class Zoom {

    protected static Database db = new Database();
    protected static User user = new User();
    protected static Menu menu = new Menu(db, user);

    public static void main(String[] args) {
        menu.loginMenu();
        db.finalize();
    }
}
