import java.util.*;

public class InsuranceCompany {
    private String name;
    private ArrayList<User> users; // list of all the users having a policy with the company
    private String adminUsername;
    private String adminPassword;
    private int flatRate;

    public InsuranceCompany(String name, String adminUserName, String adminPassword, int flatRate) {
        if (flatRate < 1) {
            throw new IllegalArgumentException("Flat rate must be a positive integer.");
        }
        this.name = name;
        this.users = new ArrayList<User>();
        this.adminUsername = adminUserName;
        this.adminPassword = adminPassword;
        this.flatRate = flatRate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public int getFlatRate() {
        return flatRate;
    }

    // Validates admin credentials against stored details
    public boolean validateAdmin(String username, String password) {
        return this.adminUsername.equals(username) && this.adminPassword.equals(password);
    }

    // Finds a user by userID, returns null if not found
    public User findUser(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    // Adds a user if the user object is not null and userID is unique
    public boolean addUser(User user) {
        if (user != null && findUser(user.getUserID()) == null) {
            users.add(user);
            return true;
        }
        return false;
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

    // Prints all users and all of their policies with calculated premiums
    public void print() {
        for (User user : users) {
            System.out.println(
                    "User: " + user.getName() + " | ID: " + user.getUserID() + " | Address: " + user.getAddress());
            user.printPolicies(flatRate);
            System.out.println("-----------------------");
        }
    }

    // Converts the company details, all users, and their policies to String
    public String toString() {
        String result = "===== Insurance Company: " + name + "\nUsers:\n ======";
        for (User user : users) {
            result += user.toString() + "\n";
        }
        return result;
    }

    // Creates and adds a Third-Party policy to the specified user
    public boolean createThirdPartyPolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, String comments) {
        User user = findUser(userID);
        if (user != null) {
            return user.createThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        }
        return false;
    }

    // Creates and adds a Comprehensive policy to the specified user
    public boolean createComprehensivePolicy(int userID, String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, int driverAge, int level) {
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

    // Calculates total premium payments across all users in the company
    public double calcTotalPayments() {
        double total = 0.0;
        for (User user : users) {
            total += user.calcTotalPremiums(flatRate);
        }
        return total;
    }

    // Increases car prices by risePercent for all policies of a specific user
    public boolean carPriceRise(int userID, double risePercent) {
        User user = findUser(userID);
        if (user != null) {
            user.carPriceRiseAll(risePercent);
            return true;
        }
        return false;
    }

    // Increases car prices by risePercent across all users in the company
    public void carPriceRise(double risePercent) {
        for (User user : users) {
            user.carPriceRiseAll(risePercent);
        }
    }

    // Returns a consolidated list of all policies held across all users
    public ArrayList<InsurancePolicy> allPolicies() {
        ArrayList<InsurancePolicy> allPolicies = new ArrayList<InsurancePolicy>();
        for (User user : users) {
            if (user.getPolicies() != null) {
                allPolicies.addAll(user.getPolicies());
            }
        }
        return allPolicies;
    }

    // Filters policies by car model across all users in the company
    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        ArrayList<InsurancePolicy> filteredList = new ArrayList<InsurancePolicy>();
        for (User user : users) {
            ArrayList<InsurancePolicy> userMatchedPolicies = user.filterByCarModel(carModel);
            if (userMatchedPolicies != null) {
                filteredList.addAll(userMatchedPolicies);
            }
        }
        return filteredList;
    }

    // Overloaded method to filter policies by car model for a specific user
    public ArrayList<InsurancePolicy> filterByCarModel(int userID, String carModel) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByCarModel(carModel);
        }
        return null;
    }

    // Filters policies by expiry date for a specific user
    public ArrayList<InsurancePolicy> filterByExpiryDate(int userID, MyDate date) {
        User user = findUser(userID);
        if (user != null) {
            return user.filterByExpiryDate(date);
        }
        return null;
    }

    // Filters policies expired by the given date across all users in the company
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        ArrayList<InsurancePolicy> filtered = new ArrayList<>();
        for (User user : users) {
            ArrayList<InsurancePolicy> expired = user.filterByExpiryDate(date);
            if (expired != null) {
                filtered.addAll(expired);
            }
        }
        return filtered;

    }
}
