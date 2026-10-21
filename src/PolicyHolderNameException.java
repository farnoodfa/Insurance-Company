public class PolicyHolderNameException extends Exception {
    private String policyHolderName;

    public PolicyHolderNameException(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    @Override
    public String toString() {
        return UserInterface.BOLD + " The Policy Holder Name was not valid: " + policyHolderName + UserInterface.RESET;
    }
}