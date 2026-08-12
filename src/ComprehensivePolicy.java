public class ComprehensivePolicy extends InsurancePolicy {
    protected int driveAge;
    protected int level;

    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driveAge, int level) {
        super(policyHolderName, id, car, numberOfClaims);
        this.driveAge = driveAge;
        this.level = level;

    }

    @Override
    public void print() {
        super.print();
        System.out.println(" Driver Age: " + driveAge + " Level: " + level);
    }

    @Override
    public double calcPayment(double flatRate) {
        double premiumRate = car.getPrice() / (50 + numberOfClaims * 200 + flatRate);
        if (driveAge < 30) {
            premiumRate += (30 - driveAge) * 50;
        }
        return premiumRate;
    }
}
