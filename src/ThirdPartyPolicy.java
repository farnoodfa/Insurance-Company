public class ThirdPartyPolicy extends InsurancePolicy {
    protected String comments;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate,
            String comments) {
        super(policyHolderName, id, car, numberOfClaims, expiryDate);
        this.comments = comments;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("comments: " + comments);
    }

    @Override
    public String toString() {
        return super.toString() + "\ncomments: " + comments;
    }

    @Override
    public double calcPayment(double flatRate) {
        double premiumRate = car.getPrice() / (100 + numberOfClaims * 200 + flatRate);
        return premiumRate;
    }
}
