
// sub class of Account created as the group project required (feature (B))
public class SavingAccount extends Account
{
    // initialize Interest Rate to 0.1% per annum
    private double interestRate = 1/1000;
    
    // constructor of SavingAccount
    public SavingAccount(int theAccountNumber, int thePIN,
    double theAvailableBalance, double theTotalBalance, String theAccountType)
    {
        // explicit call to superclass Account constructor
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, theAccountType);
    }
    //set the interest rate
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    //get the interest rate
     public double getInterestRate( ){
        return this.interestRate;
    }
}
