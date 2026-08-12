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

    public double getPrice() {
        return this.price;
    }

    public void print() {
        System.out.println("Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: "
                + manufacturingYear);
    }

    public String toString() {
        return "Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: "
                + manufacturingYear;
    }
}