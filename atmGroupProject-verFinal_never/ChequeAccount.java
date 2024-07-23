
// sub class of Account created as the group project required (feature (B))
public class ChequeAccount extends Account
{
    // initialize the limit per cheque to 10000
    private int limitPerCheque = 10000;

    // constructor of ChequeAccount
    public ChequeAccount(int theAccountNumber, int thePIN, 
    double theAvailableBalance, double theTotalBalance, String theAccountType)
    {
        // explicit call to superclass Account constructor
        super(theAccountNumber, thePIN, theAvailableBalance, theTotalBalance, theAccountType);
    }
    //set the cheque to specific limit
    public void setLimitPerCheque(int limitPerCheque){
        this.limitPerCheque =limitPerCheque;
    }
    //get the cheque limit
    public int getLimitPerCheque(){
        return limitPerCheque;
    }
}
