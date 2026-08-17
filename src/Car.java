public class Car {

    enum CarType {
        SUV, SED, LUX, HATCH
    }

    private int manufacturingYear;
    private double price;
    private String model;
    private CarType type;

    public Car(int manufacturingYear, double price, String model, CarType type) {
        this.manufacturingYear = manufacturingYear;
        this.price = price;
        this.model = model;
        this.type = type;
    }

    // getters
    public String getModel() {
        return model;
    }

    public double getPrice() {
        return this.price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void print() {// printing the object
        System.out.println("Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: "
                + manufacturingYear);
    }

    public String toString() {// conveting the objetc to string
        return "Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: "
                + manufacturingYear;
    }

    // rising the price of the car
    public double priceRise(double rise) {
        double risedPrice = price * (1 + rise);
        return risedPrice;
    }
}