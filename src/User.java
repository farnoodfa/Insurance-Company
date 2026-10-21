import java.io.*;
import java.util.*;

public class User implements Cloneable, Comparable<User>, Serializable {
    private static int count = 1000;

    private String name;
    private Address address;
    private int userID;
    // private ArrayList<InsurancePolicy> policies;
    private HashMap<Integer, InsurancePolicy> policies;

    // Auto-generates userID incrementally (overload)

    // ============ Assignment2 changes(handles duplication)
    public User(String name, Address address) {
        this(name, getNextUserID(), address);
    }

    // manual ID assignments still work
    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new HashMap<>();
        // keep count ahead of any manually assigned ID
        if (userID >= count) {
            count = userID;
        }
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public String getCity() {
        return address.getCity();
    }

    public HashMap<Integer, InsurancePolicy> getPolicies() {
        return policies;
    }

    public static int getCount() {
        return count;
    }

    // setters
    public void setAddress(Address address) {
        this.address = address;
    }

    // public InsurancePolicy findPolicy(int policyID) {
    // for (InsurancePolicy policy : policies) {
    // if (policy.getID() == policyID) {
    // return policy;
    // }
    // }
    // return null;
    // }

    // public boolean addPolicy(InsurancePolicy policy) {
    // if (policy != null && findPolicy(policy.getID()) == null) {
    // policies.add(policy);
    // return true;
    // }
    // return false;
    // }

    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
    if (this.policies == null) {
    return null;
    }
    return InsurancePolicy.filterByCarModel(this.policies, carModel);
    }

    // // Filters policies expired by the given date
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
    if (this.policies == null) {
    return null;
    }
    return InsurancePolicy.filterByExpiryDate(this.policies, date);
    }

    // // Remove a policy by policyID
    // public boolean removePolicy(int policyID) {
    // InsurancePolicy policy = findPolicy(policyID);
    // if (policy != null) {
    // policies.remove(policy);
    // return true;
    // }
    // return false;
    // }

    // // Populate distinct car models across this user's policies
    // public ArrayList<String> populateDistinctCarModels() {
    // ArrayList<String> models = new ArrayList<String>();
    // for (InsurancePolicy policy : policies) {
    // boolean found = false;
    // for (String model : models) {
    // if (policy.getCarModel().equals(model)) {
    // found = true;
    // break;
    // }
    // }
    // if (!found)
    // models.add(policy.getCarModel());
    // }
    // return models;
    // }

    // // Count how many policies this user has for a given car model
    // public int getTotalCountForCarModel(String carModel) {
    // if (policies == null || carModel == null)
    // return 0;
    // int count = 0;
    // for (InsurancePolicy policy : policies) {
    // if (policy.car.getModel().equalsIgnoreCase(carModel)) {
    // count++;
    // }
    // }
    // return count;
    // }

    // public double getTotalPaymentForCarModel(String carModel, int flatRate) {
    // if (policies == null || carModel == null)
    // return 0;
    // double total = 0.0;
    // for (InsurancePolicy policy : policies) {
    // if (policy.car.getModel().equalsIgnoreCase(carModel)) {
    // total += policy.calcPayment(flatRate);
    // }
    // }
    // return total;
    // }

    public void setCity(String city) {
        address.setCity(city);
    }

    public InsurancePolicy findPolicy(int policyID) {
        return policies.get(policyID);
    }

    public boolean addPolicy(InsurancePolicy policy) {
        if (findPolicy(policy.getID()) == null) {
            policies.put(policy.getID(), policy);
            return true;
        } else
            return false;
    }

    public void print() {
        System.out.println(this);
    }

    public String toString() {
        String result = "User: " + name + " | Address: " + address + " | ID: " + userID
                + "\n======= Policies: ========\n";

        if (policies == null || policies.isEmpty()) {
            result += "No policies found.\n";
        } else {
            for (InsurancePolicy policy : policies.values()) {
                result += "Policy " + (policy.id) + ":\n" + policy.toString() + "\n";
            }
        }
        return result;
    }

    public void printPolicies(int flatRate) {
        if (policies == null || policies.isEmpty()) {
            System.out.println("You have no Policies!");
            return;
        }
        InsurancePolicy.printPolicies(policies, flatRate);
    }

    public double calcTotalPremiums(int flatRate) {
        if (policies == null) {
            return 0;
        }
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    public void carPriceRiseAll(double risePercent) {
        if (policies == null) {
            return;
        }
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, String comments) throws PolicyException, PolicyHolderNameException {
        ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        return addPolicy(policy);
    }

    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, int driverAge, int level) throws PolicyException, PolicyHolderNameException {
        ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate,
                driverAge, level);
        return addPolicy(policy);
    }

    public static int getNextUserID() {
        return ++count;
    }

    // Count per model for a list of car models
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for (String model : carModels) {
            counts.add(getTotalCountForCarModel(model));
        }
        return counts;
    }

    // Total payment per model for a list of car models
    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels, int flatRate) {
        ArrayList<Double> payments = new ArrayList<Double>();
        for (String model : carModels) {
            payments.add(getTotalPaymentForCarModel(model, flatRate));
        }
        return payments;
    }

    // Report per car model for this user
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts,
            ArrayList<Double> premiumPayments) {
        System.out.println("==========================================================================");
        System.out.printf("%-30s %-30s %-25s%n", "Car Model", "Total Premium Payment", "Average Premium Payment");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < counts.size(); i++) {
            String model = carModels.get(i);
            int count = counts.get(i);
            double total = premiumPayments.get(i);
            double average = (count > 0) ? total / count : 0.0;
            System.out.printf("%-30s $%,-29.2f $%,.2f%n", model, total, average);
        }
        System.out.println("==========================================================================");
    }

    // -------------------------------------------lab4
    // copy constructor
    public User(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot copy from a null User object.");
        }
        this.name = user.name;
        this.userID = user.userID;
        this.address = new Address(user.address);
        this.policies = new HashMap<>();
        for (InsurancePolicy policy : user.policies.values()) {
            this.policies.put(policy.getID(), policy instanceof ThirdPartyPolicy
                    ? new ThirdPartyPolicy((ThirdPartyPolicy) policy)
                    : new ComprehensivePolicy((ComprehensivePolicy) policy));
        }
    }

    // lab4
    public User clone() throws CloneNotSupportedException {
        User cloned = (User) super.clone();
        cloned.address = address.clone();
        cloned.policies = InsurancePolicy.deepCopyHashMap(policies);
        return cloned;
    }

    // lab4
    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        ArrayList<User> shallowCopy = new ArrayList<>();
        for (User user : users) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    // lab4
    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : users) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    // lab4
    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
        return InsurancePolicy.deepCopy(policies);
    }

    // lab4
    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        return InsurancePolicy.shallowCopy(policies);
    }

    // lab4
    @Override
    public int compareTo(User other) {
        return this.address.compareTo(other.address);
    }

    // lab4
    // public int compareTo1(User other) {
    //     double total = InsurancePolicy.calcTotalPayments(this.policies, 20);
    //     double otherTotal = InsurancePolicy.calcTotalPayments(other.policies, 20);
    //     // return total - OtherTotal
    //     if (total < otherTotal) {
    //         return -1;
    //     }
    //     if (total > otherTotal) {
    //         return 1;
    //     }
    //     return 0;
    // }

    // lab4
    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
        ArrayList<InsurancePolicy> sorted = InsurancePolicy.shallowCopy(policies);
        Collections.sort(sorted);
        return sorted;
    }

    // -------------------------------------------lab5

    // Remove a policy by policyID
    public boolean removePolicy(int policyID) {
        return policies.remove(policyID) != null;
    }

    // Populate distinct car models across this user's policies
    public ArrayList<String> populateDistinctCarModels() {
        HashMap<String, Boolean> models = new HashMap<>();

        if (policies == null) {
            return new ArrayList<>();
        }

        for (InsurancePolicy policy : policies.values()) {
            if (policy.getCarModel() != null) {
                models.put(policy.getCarModel(), true);
            }
        }
        return new ArrayList<>(models.keySet());
    }

    // Count how many policies this user has for a given car model
    public int getTotalCountForCarModel(String carModel) {
        if (policies == null || carModel == null)
            return 0;
        int count = 0;
        for (InsurancePolicy policy : policies.values()) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                count++;
            }
        }
        return count;
    }

    public double getTotalPaymentForCarModel(String carModel, int flatRate) {
        if (policies == null || carModel == null)
            return 0;
        double total = 0.0;
        for (InsurancePolicy policy : policies.values()) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                total += policy.calcPayment(flatRate);
            }
        }
        return total;
    }

    public static HashMap<Integer, User> deepCopyHashMap(HashMap<Integer, User> users)
            throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users.values()) {
            deepCopy.put(user.userID, user.clone());
        }
        return deepCopy;
    }

    public static HashMap<Integer, User> deepCopyHashMap(ArrayList<User> users) throws CloneNotSupportedException {
        HashMap<Integer, User> deepCopy = new HashMap<>();
        for (User user : users) {
            deepCopy.put(user.userID, user.clone());
        }
        return deepCopy;
    }

    public static ArrayList<User> deepCopy(HashMap<Integer, User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : users.values()) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    public HashMap<Integer, InsurancePolicy> deepCopyPoliciesHashMap() throws CloneNotSupportedException {
        return InsurancePolicy.deepCopyHashMap(policies);
    }

    public HashMap<Integer, InsurancePolicy> shallowCopyPoliciesHashMap() {
        return InsurancePolicy.shallowCopyHashMap(policies);
    }

    public static HashMap<Integer, User> shallowCopyHashMap(HashMap<Integer, User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<>();
        for (User user : users.values()) {
            shallowCopy.put(user.getUserID(), user);
        }
        return shallowCopy;
    }

    public static ArrayList<User> shallowCopy(HashMap<Integer, User> users) {
        ArrayList<User> shallowCopy = new ArrayList<>();
        for (User user : users.values()) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    public static HashMap<Integer, User> shallowCopyHashMap(ArrayList<User> users) {
        HashMap<Integer, User> shallowCopy = new HashMap<>();
        for (User user : users) {
            shallowCopy.put(user.getUserID(), user);
        }
        return shallowCopy;
    }

    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> totalCount = new HashMap<>();

        for (InsurancePolicy policy : policies.values()) {
            String model = policy.getCarModel();
            Integer count = totalCount.get(model);

            if (count == null) {
                totalCount.put(model, 1);
            } else {
                totalCount.put(model, count + 1);
            }
        }

        return totalCount;
    }

    public HashMap<String, Double> getTotalPremiumPerCarModel(int flatRate) {
        HashMap<String, Double> total = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            String model = policy.getCarModel();
            double policyPayment = policy.calcPayment(flatRate);
            if (total.get(model) == null) {
                total.put(model, policyPayment);
            } else {
                total.put(model, total.get(model) + policyPayment);
            }

        }
        return total;
    }

    public HashMap<String, Double> getTotalPremiumPerCarModel() {
        return getTotalPremiumPerCarModel(20); // Uses default flatRate when none is passed
    }

    public void reportPaymentsPerCarModel(HashMap<String, Integer> counts, HashMap<String, Double> premiums) {
        System.out.println("==========================================================================");
        System.out.printf("%-30s %-30s %-25s%n", "Car Model", "Total Premium Payment", "Average Premium Payment");
        System.out.println("--------------------------------------------------------------------------");

        if (counts == null) {
            System.out.println("==========================================================================");
            return;
        }
        if (premiums == null) {
            System.out.println("==========================================================================");
            return;
        }
        for (String model : counts.keySet()) {
            int count = counts.get(model);

            Double totalObj = premiums.get(model);
            double total = 0.0;
            if (totalObj != null) {
                total = totalObj;
            }
            double average = 0.0;
            if (count > 0) {
                average = total / count;
            }
            System.out.printf("%-30s $%,-29.2f $%,.2f%n", model, total, average);
        }
        System.out.println("==========================================================================");
    }

    // automatically generates the hashmaps and prints
    public void reportPaymentsPerCarModel(int flatRate) {
        HashMap<String, Integer> counts = getTotalCountPerCarModel();
        HashMap<String, Double> premiums = getTotalPremiumPerCarModel(flatRate);
        reportPaymentsPerCarModel(counts, premiums);
    }

    public void reportPremiumCarModel(HashMap<String, Integer> counts, HashMap<String, Double> totals) {
        reportPaymentsPerCarModel(counts, totals);
    }

    // -----------------------------------------------------------------------
    // lab6

    // public static HashMap<Integer, User> load(String fileName) throws
    // IOException, ClassNotFoundException {
    // try (FileInputStream file = new FileInputStream(fileName);
    // ObjectInputStream inputSt = new ObjectInputStream(file)) {
    // HashMap<Integer, User> users = (HashMap<Integer, User>) inputSt.readObject();
    // return users;
    // }
    // }

    public static Boolean save(HashMap<Integer, User> users, String fileName) throws IOException {
        try (FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream obj = new ObjectOutputStream(file)) {
            obj.writeObject(users);
        }
        return true;
    }

    // public static HashMap<Integer, User> load(String fileName) throws
    // IOException, ClassNotFoundException {
    // HashMap<Integer, User> users = new HashMap<>();
    // try (FileInputStream file = new FileInputStream(fileName);
    // ObjectInputStream inputSt = new ObjectInputStream(file)) {
    // try {
    // while (true) {
    // User user = (User) inputSt.readObject();
    // users.put(user.getUserID(), user);
    // }
    // } catch (EOFException e) {

    // }
    // System.out.println("End of file reached: no more users to load.");
    // }
    // return users;
    // }

    // public static Boolean save(HashMap<Integer, User> users, String fileName)
    // throws IOException {
    // try (FileOutputStream file = new FileOutputStream(fileName);
    // ObjectOutputStream obj = new ObjectOutputStream(file)) {
    // for (User user : users.values()) {
    // obj.writeObject(user);
    // }
    // }
    // return true;
    // }

    public String toDelimitedString() {
        String result = name + "," + userID + "," + address.toDelimitedString() + "," + policies.size();
        for (InsurancePolicy policy : policies.values()) {
            result += "," + policy.toDelimitedString();
        }
        return result;
    }

    public static HashMap<Integer, User> loadTextFile(String fileName) throws IOException, PolicyException, PolicyHolderNameException {
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line = in.readLine();
            while (line != null) {
                String[] field = line.trim().split(",");
                User user = loadOneUser(field, 0);
                users.put(user.getUserID(), user);
                line = in.readLine();
            }
        }
        return users;
    }

    public static User loadOneUser(String[] field, int counter) throws PolicyException, PolicyHolderNameException {
        String name = field[counter++];
        int userID = Integer.parseInt(field[counter++]);
        int streetNum = Integer.parseInt(field[counter++]);
        String street = field[counter++];
        String suburb = field[counter++];
        String city = field[counter++];
        Address address = new Address(streetNum, street, suburb, city);

        int numPolicies = Integer.parseInt(field[counter++]);
        User user = new User(name, userID, address);

        for (int i = 0; i < numPolicies; i++) {
            String type = field[counter];
            user.addPolicy(InsurancePolicy.loadOnePolicy(field, counter));
            // move past this policy's fields (13 for CP, 12 for TP)
            counter += type.equals("CP") ? 13 : 12;
        }
        return user;
    }

    public static Boolean saveTextFile(HashMap<Integer, User> users, String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : users.values()) {
                bw.write(user.toDelimitedString());
                bw.newLine();
            }
        }
        return true;
    }

    // ===================================== Assingment2

    // duplicated IDs fixed
    public static HashMap<Integer, User> load(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream inputSt = new ObjectInputStream(file)) {
            HashMap<Integer, User> users = (HashMap<Integer, User>) inputSt.readObject();

            for (User user : users.values()) {
                if (user.getUserID() > count) {
                    count = user.getUserID();
                }
            }
            return users;
        }
    }
}
