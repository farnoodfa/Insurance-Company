public class Car implements Cloneable {

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
        System.out.println("Car type: " + type + " |Price: " + price + " |Model: " + model + " |Manufacturing Year: "
                + manufacturingYear);
    }

    public String toString() {// conveting the objetc to string
        return "Car type: " + type + " |Price: " + price + " |Model: " + model + " |Manufacturing Year: "
                + manufacturingYear;
    }

    // rising the price of the car
    public void priceRise(double risePercent) {
        this.price = this.price * (1 + risePercent);
    }

    // lab4
    // copy constructor
    public Car(Car other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null Car object.");
        }
        this.manufacturingYear = other.manufacturingYear;
        this.price = other.price;
        this.model = other.model;
        this.type = other.type;
    }

    // lab4
    @Override
    public Car clone() throws CloneNotSupportedException {
        return (Car) super.clone();
    }
}