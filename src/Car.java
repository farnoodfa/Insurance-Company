public class Car {

    enum CarType {
        SUV, SED, LUX, HATCH
    }

    protected int manufacturingYear;
    protected double price;
    protected String model;
    protected CarType type;

    public Car(int manufacturingYear, double price, String model, CarType type) {
        this.manufacturingYear = manufacturingYear;
        this.price = price;
        this.model = model;
        this.type = type;
    }

    public void print() {
        System.out.println("Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: "  + manufacturingYear);
    }

    public String toString() {
        return "Car type: " + type + " Price: " + price + " Model: " + model + " Manufacturanig Year: " + manufacturingYear;
    }
}