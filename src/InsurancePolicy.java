abstract class InsurancePolicy {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public void print() {
        System.out.println("Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims
                + "\n Car Info: " + car);
    }

    public String toString() {
        return "Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims
                + "\n Car Info: " + car;
    }

    public abstract double calcPayment(double flatRate);

    public void printTotal(double flatRate) {
        double total = calcPayment(flatRate);
        System.out.println("The Totall Amount Is: " + total);
    }

}
