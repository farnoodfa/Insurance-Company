import java.io.*;
import java.util.*;

abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy>, Serializable {
    private static final String pattern = "[A-Z][a-z]{2,} [A-Z][a-z]{2,}";
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate)
            throws PolicyException, PolicyHolderNameException {
        if (id < 300000 || id > 399999) {
            Random rand = new Random();
            int generatedID = 300000 + rand.nextInt(100000); // Generates 300000 - 399999

            this.id = generatedID;
            throw new PolicyException(generatedID);
        } else {
            this.id = id;
        }

        if (!policyHolderName.matches(pattern)) {
            throw new PolicyHolderNameException(policyHolderName);
        }
        this.policyHolderName = policyHolderName;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    // getters
    public int getID() {
        return id;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public String getCarModel() {
        return car.getModel();
    }

    public Car getCar() {
        return car;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // setters
    public void setPolicyHolderName(String newName) throws PolicyHolderNameException {
        if (!newName.matches(pattern)) {
            throw new PolicyHolderNameException(newName);
        }
        policyHolderName = newName;
    }

    public void setCarModel(String model) {
        car.setModel(model);
    }

    public void print() {// printing policy
        System.out.println("Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims);
        car.print();
    }

    public void print(double flatRate) {
        print();
        System.out.printf("Premium Payment: $%.2f%n", calcPayment(flatRate));
    }

    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies) {
            System.out.println(policy.toString());
        }
    }

    // Overloaded version to include flatRate and premium calculation
    public static void printPolicies(ArrayList<InsurancePolicy> policies, double flatRate) {
        for (InsurancePolicy policy : policies) {
            policy.print(flatRate);
        }
    }

    public String toString() {
        return "Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims
                + "\n" + car;
    }

    public abstract double calcPayment(double flatRate); // super method for calculating the payment

    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, double flatRate) { // calculating all
        double tatalPayments = 0.0;
        for (InsurancePolicy policy : policies) {
            tatalPayments += policy.calcPayment(flatRate);
        }
        return tatalPayments;
    }

    public void carPriceRise(double risePercent) {// calling priceRise in Car
        car.priceRise(risePercent);
    }

    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) { // rising all
        if (policies == null) {
            return;
        }
        for (InsurancePolicy insurancePolicy : policies) {
            insurancePolicy.carPriceRise(risePercent);
        }
    }

    // filtering by model
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String model) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            if (insurancePolicy.car.getModel().toLowerCase().contains(model.toLowerCase())) {
                filteredPolicies.add(insurancePolicy);
            }
        }
        return filteredPolicies;
    }

    // Filters and returns a list of policies expired by the given cutoff date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
        if (policies == null || date == null) {
            return expiredPolicies;
        }
        for (InsurancePolicy policy : policies) {
            if (policy.getExpiryDate() != null && date.isExpired(policy.getExpiryDate())) {
                expiredPolicies.add(policy);
            }
        }
        return expiredPolicies;
    }

    // -------------------------------------------lab4
    // copy constructor
    public InsurancePolicy(InsurancePolicy other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InsurancePolicy object.");
        }
        this.policyHolderName = other.policyHolderName;
        this.id = other.id;
        this.car = new Car(other.car);
        this.numberOfClaims = other.numberOfClaims;
        this.expiryDate = new MyDate(other.expiryDate);
    }

    // lab4
    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        InsurancePolicy cloned = (InsurancePolicy) super.clone();
        cloned.car = car.clone();
        cloned.expiryDate = expiryDate.clone();
        return cloned;
    }

    // lab4
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies)
            throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.add(insurancePolicy.clone());
        }
        return copied;
    }

    // lab4
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.add(insurancePolicy);
        }
        return copied;
    }

    // lab4
    @Override
    public int compareTo(InsurancePolicy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    // ----------------------------lab5

    public static ArrayList< InsurancePolicy> filterByCarModel(HashMap<Integer, InsurancePolicy> policies,
            String carModel) {
        ArrayList< InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy policy : policies.values()) {
            if (policy.car.getModel().contains(carModel)) {
                filteredPolicies.add(policy.getID(), policy);
            }
        }
        return filteredPolicies;
    }

        public static ArrayList<InsurancePolicy> filterByExpiryDate(HashMap<Integer, InsurancePolicy> policies,
            MyDate date) {
        ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
        if (policies == null || date == null) {
            return expiredPolicies;
        }
        for (InsurancePolicy policy : policies.values()) {
            if (date.isExpired(policy.getExpiryDate())) {
                expiredPolicies.add(policy);
            }
        }
        return expiredPolicies;
    }

    public static ArrayList<InsurancePolicy> deepCopy(HashMap<Integer, InsurancePolicy> policies)
            throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.add(insurancePolicy.clone());
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(HashMap<Integer, InsurancePolicy> policies)
            throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();

        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.put(insurancePolicy.id, insurancePolicy.clone());
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(ArrayList<InsurancePolicy> policies)
            throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();

        for (InsurancePolicy insurancePolicy : policies) {
            copied.put(insurancePolicy.id, insurancePolicy.clone());
        }
        return copied;
    }

    public static ArrayList<InsurancePolicy> shallowCopy(HashMap<Integer, InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.add(insurancePolicy);
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(HashMap<Integer, InsurancePolicy> policies) {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.put(insurancePolicy.id, insurancePolicy);
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(ArrayList<InsurancePolicy> policies) {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.put(insurancePolicy.id, insurancePolicy);
        }
        return copied;
    }

    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies.values()) {
            System.out.println(policy.toString());
        }
    }

    // Overloaded version to include flatRate and premium calculation
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies, double flatRate) {
        for (InsurancePolicy policy : policies.values()) {
            policy.print();
            System.out.println("Premium Payment: $" + policy.calcPayment(flatRate));
        }
    }

    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, int flatRate) {
        double tatalPayments = 0.0;
        for (InsurancePolicy policy : policies.values()) {
            tatalPayments += policy.calcPayment(flatRate);
        }
        return tatalPayments;
    }

    public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy> policies, double risePercent) {
        if (policies == null) {
            return;
        }
        for (InsurancePolicy insurancePolicy : policies.values()) {
            insurancePolicy.carPriceRise(risePercent);
        }
    }

    // --------------------------------------------------lab6

    public static HashMap<Integer, InsurancePolicy> load(String fileName)
            throws IOException, ClassNotFoundException {
        try (FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream inputSt = new ObjectInputStream(file)) {
            return (HashMap<Integer, InsurancePolicy>) inputSt.readObject();
        }
    }

    public static Boolean save(HashMap<Integer, InsurancePolicy> policies, String fileName)
            throws IOException {
        try (FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream outputSt = new ObjectOutputStream(file)) {
            outputSt.writeObject(policies);
        }
        return true;
    }

    // public static HashMap<Integer, InsurancePolicy> load(String fileName)
    // throws IOException, ClassNotFoundException {
    // HashMap<Integer, InsurancePolicy> policies = new HashMap<>();
    // try (FileInputStream file = new FileInputStream(fileName);
    // ObjectInputStream inputSt = new ObjectInputStream(file)) {
    // try {
    // while (true) {
    // InsurancePolicy policy = (InsurancePolicy) inputSt.readObject();
    // policies.put(policy.getID(), policy);
    // }
    // } catch (EOFException e) {
    // System.out.println("End of file reached: no more policies to load.");
    // }
    // }
    // return policies;
    // }

    // public static Boolean save(HashMap<Integer, InsurancePolicy> policies, String
    // fileName)
    // throws IOException {
    // try (FileOutputStream file = new FileOutputStream(fileName);
    // ObjectOutputStream outputSt = new ObjectOutputStream(file)) {
    // for (InsurancePolicy policy : policies.values()) {
    // outputSt.writeObject(policy);
    // }
    // }
    // return true;
    // }

    public String toDelimitedString() {
        return policyHolderName + "," + id + "," + numberOfClaims + "," + expiryDate.toDelimitedString() + ","
                + car.toDelimitedString();
    }

    public static HashMap<Integer, InsurancePolicy> loadTextFile(String fileName)
            throws IOException, PolicyException, PolicyHolderNameException {
        HashMap<Integer, InsurancePolicy> policies = new HashMap<Integer, InsurancePolicy>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line = in.readLine();
            while (line != null) {
                String[] field = line.trim().split(",");
                InsurancePolicy policy = loadOnePolicy(field, 0);
                policies.put(policy.getID(), policy);
                line = in.readLine();
            }
        }
        return policies;
    }

    public static InsurancePolicy loadOnePolicy(String[] field, int counter)
            throws PolicyException, PolicyHolderNameException {
        String type = field[counter++];
        String policyHolderName = field[counter++];
        int id = Integer.parseInt(field[counter++]);
        int numberOfClaims = Integer.parseInt(field[counter++]);

        // Expiry Date fields
        int expYear = Integer.parseInt(field[counter++]);
        int expMonth = Integer.parseInt(field[counter++]);
        int expDay = Integer.parseInt(field[counter++]);
        MyDate expiryDate = new MyDate(expYear, expMonth, expDay);

        // Car fields
        int carYear = Integer.parseInt(field[counter++]);
        double carPrice = Double.parseDouble(field[counter++]);
        String carModel = field[counter++];
        Car.CarType carType = Car.CarType.valueOf(field[counter++]);
        Car car = new Car(carYear, carPrice, carModel, carType);

        switch (type) {
            case "CP":
                int driverAge = Integer.parseInt(field[counter++]);
                int level = Integer.parseInt(field[counter++]);
                return new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge, level);
            case "TP":
                String comments = field[counter++];
                return new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
            default:
                throw new IllegalArgumentException("Unknown policy type: " + type);
        }
    }

    public static Boolean saveTextFile(HashMap<Integer, InsurancePolicy> policies, String fileName)
            throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (InsurancePolicy policy : policies.values()) {
                bw.write(policy.toDelimitedString());
                bw.newLine();
            }
        }
        return true;
    }
}