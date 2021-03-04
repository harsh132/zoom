package zoom;
public class Car {
    public int rent = -1;
    public int capacity = -1;
    public int bootspace = 0;
    public String model = null;
    public String city = null;

    public Car(int c_rent, int c_capacity, int c_bootspace, String c_model, String c_city) {
        rent = c_rent;
        capacity = c_capacity;
        bootspace = c_bootspace;
        model = c_model;
        city = c_city;
    }
}
