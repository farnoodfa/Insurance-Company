import java.io.*;
import java.util.*;

public class InsuranceCompany implements Cloneable, Serializable {
    private String name;
    // private ArrayList<User> users;
    private HashMap<Integer, User> users;
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany(String name, String adminUserName, String adminPassword, int flatRate) {
        if (flatRate < 1) {
            throw new IllegalArgumentException("Flat rate must be a positive integer.");
        }
        this.name = name;
        this.users = new HashMap<Integer, User>();
        // this.users = new ArrayList<User>();
        this.adminUsername = adminUserName;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    // public ArrayList<User> getUsers() {
    // return users;
    // }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public int getFlatRate() {
        return flatRate;
    }


        // // Finds a user by userID, returns null if not found
    // public User findUser(int userID) {
    // // for (User user : users) {
    // // if (user.getUserID() == userID) {
    // // return user;
    // // }
    // // }
    // // return null;
    // return users.get(userID);
    // }

    // // Adds a user if the user object is not null and userID is unique
    // public boolean addUser(User user) {
    // // if (user != null && findUser(user.getUserID()) == null) {
    // // users.add(user);
    // // return true;
    // // }
    // // return false;
    // if (user != null && findUser(user.getUserID()) == null) {
    // users.put(user.getUserID(), user);
    // return true;
    // } else
    // return false;
    // }

     // // Prints all users and all of their policies with calculated premiums
    // public void print() {
    // // for (User user : users) {
    // // System.out.println(
    // // "User: " + user.getName() + " | ID: " + user.getUserID() + " | Address: "
    // +
    // // user.getAddress());
    // // user.printPolicies(flatRate);
    // // System.out.println("-----------------------");
    // // }
    // for (User user : users.values()) {

    // System.out.println(
    // "User: " + user.getName() + " | ID: " + user.getUserID() + " | Address: " +
    // user.getAddress());
    // user.printPolicies(flatRate);
    // System.out.println("-----------------------");
    // }
    // }

    // // Converts the company details, all users, and their policies to String
    // public String toString() {
    // String result = "===== Insurance Company: " + name + "\nUsers:\n ======";
    // // for (User user : users) {
    // // result += user.toString() + "\n";
    // // }

    // for (User user : users.values()) {
    // result += user.toString() + "\n";
    // }
    // return result;
    // }

     // // Calculates total premium payments across all users in the company
    // public double calcTotalPayments() {
    // double total = 0.0;
    // // for (User user : users) {
    // // total += user.calcTotalPremiums(flatRate);
    // // }
    // for (User user : users.values()) {
    // total += user.calcTotalPremiums(flatRate);
    // }
    // return total;
    // }

     // // Increases car prices by risePercent across all users in the company
    // public void carPriceRise(double risePercent) {
    // // for (User user : users) {
    // // user.carPriceRiseAll(risePercent);
    // // }

    // for (User user : users.values()) {
    // user.carPriceRiseAll(risePercent);
    // }
    // }

    // // Returns a consolidated list of all policies held across all users
    // public ArrayList<InsurancePolicy> allPolicies() {
    // ArrayList<InsurancePolicy> allPolicies = new ArrayList<InsurancePolicy>();
    // // for (User user : users) {
    // // if (user.getPolicies() != null) {
    // // for (InsurancePolicy policy : user.getPolicies()) {
    // // allPolicies.add(policy);
    // // }
    // // }
    // // }

    // for (User user : users.values()) {
    // if (user.getPolicies() != null) {
    // for (InsurancePolicy policy : user.getPolicies()) {
    // allPolicies.add(policy);
    // }
    // }
    // }
    // return allPolicies;
    // }

    // // Filters policies by car model across all users in the company
    // public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
    // ArrayList<InsurancePolicy> filteredList = new ArrayList<InsurancePolicy>();
    // for (User user : users) {
    // ArrayList<InsurancePolicy> userMatchedPolicies =
    // user.filterByCarModel(carModel);
    // if (userMatchedPolicies != null) {
    // for (InsurancePolicy policy : userMatchedPolicies) {
    // filteredList.add(policy);
    // }
    // }
    // }
    // return filteredList;
    // }

    // // Overloaded method to filter policies by car model for a specific user
    // public ArrayList<InsurancePolicy> filterByCarModel(int userID, String
    // carModel) {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByCarModel(carModel);
    // }
    // return new ArrayList<InsurancePolicy>();
    // }

    // // Filters policies by expiry date for a specific user
    // public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date)
    // {
    // User user = findUser(userID);
    // if (user != null) {
    // return user.filterByExpiryDate(date);
    // }
    // return new ArrayList<InsurancePolicy>();

    // }

    // // Filters policies expired by the given date across all users in the company
    // public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
    // ArrayList<InsurancePolicy> filtered = new ArrayList<>();
    // for (User user : users) {
    // ArrayList<InsurancePolicy> expired = user.filterByExpiryDate(date);
    // if (expired != null) {
    // for (InsurancePolicy policy : expired) {
    // filtered.add(policy);
    // }
    // }
    // }
    // return filtered;
    // }

    // // Goes through all users and populates a list of distinct city names
    // public ArrayList<String> populateDistinctCityNames() {
    // ArrayList<String> cities = new ArrayList<String>();
    // for (User user : users) {
    // boolean found = false;
    // for (String city : cities) {
    // if (user.getCity().equals(city)) {
    // found = true;
    // break;
    // }
    // }
    // if (!found)
    // cities.add(user.getCity());
    // }
    // return cities;
    // }

    // // Returns the total premium payment for the given city across all users
    // public double getTotalPaymentForCity(String city) {
    // double total = 0.0;
    // for (User user : users) {
    // if (user != null && user.getAddress() != null) {
    // if (city.equalsIgnoreCase(user.getAddress().getCity())) {
    // total += user.calcTotalPremiums(flatRate);
    // }
    // }
    // }
    // return total;
    // }

        // // Remove a user by userID
    // public boolean removeUser(int userID) {
    // User user = findUser(userID);
    // if (user != null) {
    // users.remove(user);
    // return true;
    // }
    // return false;
    // }

    // Validates admin credentials against stored details
    public boolean validateAdmin(String username, String password) {
        return this.adminUsername.equals(username) && this.adminPassword.equals(password);
    }

    // Remove a user by userID
    public boolean removeUser(int userID) {
        return users.remove(userID) != null;
    }

    // Populate distinct car models across ALL users
    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> allModels = new ArrayList<String>();
        for (User user : users.values()) {
            ArrayList<String> userModels = user.populateDistinctCarModels();
            for (String userModel : userModels) {
                boolean found = false;
                for (String model : allModels) {
                    if (model.equals(userModel)) {
                        found = true;
                        break;
                    }
                }
                if (!found)
                    allModels.add(userModel);
            }
        }
        return allModels;
    }

    // Total count per car model across all users
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> totals = new ArrayList<Integer>();
        if (carModels == null)
            return totals;
        for (String model : carModels) {
            int count = 0;
            for (User user : users.values()) {
                if (user != null) {
                    count += user.getTotalCountForCarModel(model);
                }
            }
            totals.add(count);
        }
        return totals;
    }

    // Overloaded addUser creating a User object and reusing addUser(User user)
    public boolean addUser(String name, int userID, Address address) {
        User user = new User(name, userID, address);
        return addUser(user);
    }

    // Finds user by userID and adds the policy to the user's policy list
    public boolean addPolicy(int userID, InsurancePolicy policy) {
        User user = findUser(userID);
        if (user != null) {
            return user.addPolicy(policy);
        }
        return false;
    }

    // Finds and returns a specific policy for a given user
    public InsurancePolicy findPolicy(int userID, int policyID) {
        User user = findUser(userID);
        if (user != null) {
            return user.findPolicy(policyID);
        }
        return null;
    }

    // Prints the user information and all policies for the given userID
    public void printPolicies(int userID) {
        User user = findUser(userID);
        if (user != null) {
            user.print();
        }
    }

    // Creates and adds a Third-Party policy to the specified user
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, String comments) throws PolicyException {
        User user = findUser(userID);
        if (user != null) {
            return user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return false;
    }

    // Creates and adds a Comprehensive policy to the specified user
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, int driverAge, int level) throws PolicyException {
        User user = findUser(userID);
        if (user != null) {
            return user.createComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate, driverAge,
                    level);
        }
        return false;
    }

    // Calculates total premium payments for a specific user
    public double calcTotalPayments(int userID) {
        User user = findUser(userID);
        if (user != null) {
            return user.calcTotalPremiums(flatRate);
        }
        return 0.0;
    }

    // Aggregates total premium payments for each city in the list in matching order
    public ArrayList<Double> getTotalPaymentPerCity(ArrayList<String> cities) {
        ArrayList<Double> payments = new ArrayList<Double>();
        for (String city : cities) {
            payments.add(getTotalPaymentForCity(city));
        }
        return payments;
    }

    // Displays the formatted summary report of payments per city
    public void reportPaymentPerCity(ArrayList<String> cities, ArrayList<Double> payments) {
        System.out.println("=================================================");
        System.out.printf("%-20s %-25s%n", "City Name", "Total Premium Payment");
        System.out.println("-------------------------------------------------");

        if (cities != null && payments != null) {
            for (int i = 0; i < cities.size(); i++) {
                System.out.printf("%-20s $%,.2f%n", cities.get(i), payments.get(i));
            }
        }
        System.out.println("=================================================");
    }

    // Remove a policy from a specific user
    public boolean removePolicy(int userID, int policyID) {
        User user = findUser(userID);
        if (user != null) {
            return user.removePolicy(policyID);
        }
        return false;
    }

    // Change admin password (requires old password confirmation)
    public boolean changeAdminPassword(String oldPassword, String newPassword) {
        if (oldPassword == null || newPassword == null)
            return false;
        if (this.adminPassword.equals(oldPassword)) {
            this.adminPassword = newPassword;
            return true;
        }
        return false;
    }

    // Prints all users and all of their policies with calculated premiums
    public void print() {
        for (User user : users.values()) {

            System.out.println(
                    "User: " + user.getName() + " | ID: " + user.getUserID() + " | Address: " + user.getAddress());
            user.printPolicies(flatRate);
            System.out.println("-----------------------");
        }
    }

    // Converts the company details, all users, and their policies to String
    public String toString() {
        String result = "===== Insurance Company: " + name + "\nUsers:\n ======";

        for (User user : users.values()) {
            result += user.toString() + "\n";
        }
        return result;
    }

    // Calculates total premium payments across all users in the company
    public double calcTotalPayments() {
        double total = 0.0;
        for (User user : users.values()) {
            total += user.calcTotalPremiums(flatRate);
        }
        return total;
    }

    // Increases car prices by risePercent across all users in the company
    public void carPriceRise(double risePercent) {
        for (User user : users.values()) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // Returns a consolidated list of all policies held across all users
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> allPolicies = new ArrayList<InsurancePolicy>();
        for (User user : users.values()) {
            if (user.getPolicies() != null) {
                for (InsurancePolicy policy : user.getPolicies().values()) {
                    allPolicies.add(policy);
                }
            }
        }
        return allPolicies;
    }

    // Company-wide report per car model
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts,
            ArrayList<Double> premiumPayments) {
        System.out.println("==========================================================================");
        System.out.printf("%-30s %-30s %-25s%n", "Car Model", "Total Premium Payment", "Average Premium Payment");
        System.out.println("--------------------------------------------------------------------------");
        if (carModels == null || counts == null || premiumPayments == null)
            return;
        int size = Math.min(carModels.size(), Math.min(counts.size(), premiumPayments.size()));
        for (int i = 0; i < size; i++) {
            String model = carModels.get(i);
            int count = counts.get(i);
            double total = premiumPayments.get(i);
            double average = (count > 0) ? total / count : 0.0;
            System.out.printf("%-30s $%,-29.2f $%,.2f%n", model, total, average);
        }
        System.out.println("==========================================================================");
    }

    // lab4
    // copy constructor
    public InsuranceCompany(InsuranceCompany company) {
        this.adminPassword = company.adminPassword;
        this.adminUsername = company.adminUsername;
        this.name = company.name;
        this.flatRate = company.flatRate;
        this.users = new HashMap<>();
        for (User user : company.users.values()) {
            users.put(user.getUserID(), new User(user));
        }
    }

    // lab4
    public InsuranceCompany clone() throws CloneNotSupportedException {
        InsuranceCompany cloned = (InsuranceCompany) super.clone();
        cloned.users = User.deepCopyHashMap(users);
        return cloned;
    }

    // lab4
    public ArrayList<User> deepCopyUsers() throws CloneNotSupportedException {
        return User.deepCopy(users);
    }

    // lab4
    public ArrayList<User> shallowCopyUsers() {
        return User.shallowCopy(users);
    }

    public ArrayList<User> sortUsers() {
        ArrayList<User> shallowCopy = User.shallowCopy(users);
        Collections.sort(shallowCopy);
        return shallowCopy;
    }

    // ------------------------------------------------lab5

    // Finds a user by userID, returns null if not found
    public User findUser(int userID) {
        return users.get(userID);
    }

    // Adds a user if the user object is not null and userID is unique
    public boolean addUser(User user) {
        if (user != null && findUser(user.getUserID()) == null) {
            users.put(user.getUserID(), user);
            return true;
        } else
            return false;
    }

    public HashMap<Integer, User> deepCopyUsersHashMap() throws CloneNotSupportedException {
        return User.deepCopyHashMap(users);
    }

    public HashMap<Integer, User> shallowCopyUsersHashMap() {
        return User.shallowCopyHashMap(users);
    }

    // Filters policies by car model across all users in the company
    public HashMap<Integer, InsurancePolicy> filterByCarModel(String carModel) {
        HashMap<Integer, InsurancePolicy> filteredMap = new HashMap<>();

        for (User user : users.values()) {
            HashMap<Integer, InsurancePolicy> userMatchedPolicies = user.filterByCarModel(carModel);
            if (userMatchedPolicies != null) {
                filteredMap.putAll(userMatchedPolicies);
            }
        }

        return filteredMap;
    }

    // Overloaded method to filter policies by car model for a specific user
    public HashMap<Integer, InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByCarModel(carModel);
        }
        return new HashMap<>();
    }

    // Filters policies by expiry date for a specific user
    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return new HashMap<>();

    }

    // Filters policies expired by the given date across all users in the company
    public HashMap<Integer, InsurancePolicy> filterByExpiryDate(MyDate date) {
        HashMap<Integer, InsurancePolicy> filtered = new HashMap<>();

        for (User user : users.values()) {
            if (user != null) {
                HashMap<Integer, InsurancePolicy> expired = user.filterByExpiryDate(date);
                if (expired != null) {
                    filtered.putAll(expired);
                }
            }
        }
        return filtered;
    }

    // Goes through all users and populates a list of distinct city names
    public ArrayList<String> populateDistinctCityNames() {
        ArrayList<String> cities = new ArrayList<String>();
        for (User user : users.values()) {
            boolean found = false;
            for (String city : cities) {
                if (user.getCity().equals(city)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                cities.add(user.getCity());
        }
        return cities;
    }

    // Returns the total premium payment for the given city across all users
    public double getTotalPaymentForCity(String city) {
        double total = 0.0;
        for (User user : users.values()) {
            if (user != null && user.getAddress() != null) {
                if (city.equalsIgnoreCase(user.getAddress().getCity())) {
                    total += user.calcTotalPremiums(flatRate);
                }
            }
        }
        return total;
    }

    // Total payment per car model across all users
    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels) {
        ArrayList<Double> totals = new ArrayList<Double>();
        if (carModels == null)
            return totals;
        for (String model : carModels) {
            double total = 0.0;
            for (User user : users.values()) {
                if (user != null) {
                    total += user.getTotalPaymentForCarModel(model, this.flatRate);
                }
            }
            totals.add(total);
        }
        return totals;
    }

    public HashMap<String, Double> getTotalPremiumPerCity() {
        HashMap<String, Double> total = new HashMap<>();

        for (User user : users.values()) {

            String city = user.getCity();
            Double userPremium = user.calcTotalPremiums(this.flatRate);

            if (total.get(city) == null) {
                total.put(city, userPremium);
            } else {
                total.put(city, total.get(city) + userPremium);
            }

        }

        return total;
    }

    public HashMap<String, Integer> getTotalCountPerCarModel() {
        HashMap<String, Integer> total = new HashMap<>();

        for (User user : users.values()) {
            HashMap<String, Integer> userCounts = user.getTotalCountPerCarModel();
            if (userCounts != null) {
                for (String model : userCounts.keySet()) {
                    int count = userCounts.get(model);

                    if (total.get(model) == null) {
                        total.put(model, count);
                    } else {
                        total.put(model, total.get(model) + count);
                    }
                }
            }
        }

        return total;
    }

    public HashMap<String, Double> getTotalPremiumPerCarModel() {
        HashMap<String, Double> total = new HashMap<>();
        for (User user : users.values()) {
            HashMap<String, Double> userTotal = user.getTotalPremiumPerCarModel(this.flatRate);
            if (userTotal != null) {
                for (String model : userTotal.keySet()) {
                    double totalPremium = userTotal.get(model);
                    if (total.get(model) == null) {
                        total.put(model, totalPremium);
                    } else {
                        total.put(model, total.get(model) + totalPremium);
                    }

                }
            }
        }
        return total;
    }

    // Prints city report directly from the aggregated HashMap
    public void reportPaymentPerCity(HashMap<String, Double> cityPremiums) {
        System.out.println("=================================================");
        System.out.printf("%-20s %-25s%n", "City Name", "Total Premium Payment");
        System.out.println("-------------------------------------------------");

        if (cityPremiums == null) {
            System.out.println("=================================================");
            return;
        }
        for (String city : cityPremiums.keySet()) {
            Double totalObj = cityPremiums.get(city);
            double total = 0.0;
            if (totalObj != null) {
                total = totalObj;
            }

            System.out.printf("%-20s $%,.2f%n", city, total);
        }
        System.out.println("=================================================");
    }

    // automatically aggregates and prints
    public void reportPaymentPerCity() {
        HashMap<String, Double> cityPremiums = getTotalPremiumPerCity();
        reportPaymentPerCity(cityPremiums);
    }

    // Prints company-wide car model report from the two aggregated HashMaps
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

    // Zero-parameter overload that automatically aggregates and prints
    public void reportPaymentsPerCarModel() {
        HashMap<String, Integer> counts = getTotalCountPerCarModel();
        HashMap<String, Double> premiums = getTotalPremiumPerCarModel();
        reportPaymentsPerCarModel(counts, premiums);
    }

    // ---------------------------------------------------------------lab6

    public InsuranceCompany() {
        this.adminPassword = "";
        this.adminUsername = "";
        this.flatRate = 100;
        this.name = "";
        this.users = new HashMap<>();
    }

    public Boolean load(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream file = new FileInputStream(fileName);
                ObjectInputStream obj = new ObjectInputStream(file)) {
            InsuranceCompany loaded = (InsuranceCompany) obj.readObject();
            this.name = loaded.name;
            this.adminUsername = loaded.adminUsername;
            this.adminPassword = loaded.adminPassword;
            this.flatRate = loaded.flatRate;
            this.users = loaded.users;
        }
        return true;
    }

    public Boolean save(String fileName) throws IOException {
        try (FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream obj = new ObjectOutputStream(file)) {
            obj.writeObject(this);
        }
        return true;
    }

    // public Boolean load(String fileName) throws IOException, ClassNotFoundException {
    //     try (FileInputStream file = new FileInputStream(fileName);
    //             ObjectInputStream obj = new ObjectInputStream(file)) {
    //         // company fields first, in the same order they were saved
    //         this.name = (String) obj.readObject();
    //         this.adminUsername = (String) obj.readObject();
    //         this.adminPassword = (String) obj.readObject();
    //         this.flatRate = obj.readInt();
    //         this.users = new HashMap<>();
    //         // then the users until the end of the file   
    //         try {
    //             while (true) {
    //                 User user = (User) obj.readObject();
    //                 this.users.put(user.getUserID(), user);
    //             }
    //         } catch (EOFException e) {
    //             System.out.println("End of file reached: no more users to load.");
    //         }
    //     }
    //     return true;
    // }

    // public Boolean save(String fileName) throws IOException {
    //     try (FileOutputStream file = new FileOutputStream(fileName);
    //             ObjectOutputStream obj = new ObjectOutputStream(file)) {
    //         obj.writeObject(name);
    //         obj.writeObject(adminUsername);
    //         obj.writeObject(adminPassword);
    //         obj.writeInt(flatRate);
    //         for (User user : users.values()) {
    //             obj.writeObject(user);
    //         }
    //     }
    //     return true;
    // }

    public String toDelimitedString() {
        String result = name + "," + adminUsername + "," + adminPassword + "," + flatRate + "," + users.size();
        for (User user : users.values()) {
            result += "\n" + user.toDelimitedString();
        }
        return result;
    }

    public Boolean saveTextFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(this.toDelimitedString());
            writer.newLine();
        }
        return true;
    }

    public Boolean loadTextFile(String fileName) throws IOException, PolicyException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // read company line
            String[] parts = reader.readLine().split(",");
            this.name = parts[0];
            this.adminUsername = parts[1];
            this.adminPassword = parts[2];
            this.flatRate = Integer.parseInt(parts[3]);
            int numUsers = Integer.parseInt(parts[4]);
            this.users = new HashMap<>();

            for (int i = 0; i < numUsers; i++) {
                // read user line (user fields, number of policies, then the policies)
                String[] userParts = reader.readLine().split(",");
                int index = 0;
                String userName = userParts[index++];
                int userID = Integer.parseInt(userParts[index++]);
                int streetNum = Integer.parseInt(userParts[index++]);
                String street = userParts[index++];
                String suburb = userParts[index++];
                String city = userParts[index++];
                int numPolicies = Integer.parseInt(userParts[index++]);
                Address address = new Address(streetNum, street, suburb, city);
                User user = new User(userName, userID, address);

                for (int j = 0; j < numPolicies; j++) {
                    String policyType = userParts[index++];
                    String policyHolderName = userParts[index++];
                    int id = Integer.parseInt(userParts[index++]);
                    int numberOfClaims = Integer.parseInt(userParts[index++]);
                    int year = Integer.parseInt(userParts[index++]);
                    int month = Integer.parseInt(userParts[index++]);
                    int day = Integer.parseInt(userParts[index++]);
                    int manufacturingYear = Integer.parseInt(userParts[index++]);
                    double price = Double.parseDouble(userParts[index++]);
                    String model = userParts[index++];
                    Car.CarType type = Car.CarType.valueOf(userParts[index++]);
                    Car car = new Car(manufacturingYear, price, model, type);
                    MyDate expiryDate = new MyDate(year, month, day);
                    InsurancePolicy policy;
                    if (policyType.equals("TP")) {
                        String comments = userParts[index++];
                        policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
                    } else if (policyType.equals("CP")) {
                        int driverAge = Integer.parseInt(userParts[index++]);
                        int level = Integer.parseInt(userParts[index++]);
                        policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate,
                                driverAge, level);
                    } else {
                        throw new IOException("Unknown policy type: " + policyType);
                    }
                    user.addPolicy(policy);
                }
                this.users.put(userID, user);
            }
        }
        return true;
    }
}