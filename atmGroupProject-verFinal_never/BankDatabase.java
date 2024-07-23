// BankDatabase.java
// Represents the bank account information database 

public class BankDatabase
{
   private Account accounts[]; // array of Accounts
   
   // no-argument BankDatabase constructor initializes accounts
   public BankDatabase()
   {
      accounts = new Account[ 5 ]; // 8 accounts for testing
      accounts[ 0 ] = new SavingAccount( 12345, 54321, 1000.0, 1200.0, "SavingAccount");
      accounts[ 1 ] = new SavingAccount( 98765, 56789, 2000.0, 2000.0, "SavingAccount");  
      accounts[ 2 ] = new SavingAccount( 24689, 98642, 100000.0, 120000.0, "SavingAccount");
      accounts[ 3 ] = new ChequeAccount( 11111, 11111, 800000.0, 1000000.0, "ChequeAccount");
      accounts[ 4 ] = new ChequeAccount( 22222, 22222, 5000.0, 5000.0, "ChequeAccount");
      //accounts[ 5 ] = new ChequeAccount( 33333, 33333, 23333.0, 23333.0, "ChequeAccount");
      //accounts[ 6 ] = new ChequeAccount( 22422, 44244, 999999.0, 999999.0, "ChequeAccount");
      //accounts[ 7 ] = new ChequeAccount( 11451, 15411, 114514.0, 114514.0, "SavingAccount");
   } // end no-argument BankDatabase constructor
   
   // retrieve Account object containing specified account number
   private Account getAccount( int accountNumber )
   {
      // loop through accounts searching for matching account number
      for ( Account currentAccount : accounts )
      {
         // return current account if match found
         if ( currentAccount.getAccountNumber() == accountNumber )
            return currentAccount;
      } // end for

      return null; // if no matching account was found, return null
   } // end method getAccount

   // determine whether user-specified account number and PIN match
   // those of an account in the database
   public boolean authenticateUser( int userAccountNumber, int userPIN )
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );

      // if account exists, return result of Account method validatePIN
      if ( userAccount != null )
         return userAccount.validatePIN( userPIN );
      else
         return false; // account number not found, so return false
   } // end method authenticateUser
   
      public boolean checkAccount( int userAccountNumber,int currentAccountNumber)
   {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount( userAccountNumber );
      Account currentAccount = getAccount(currentAccountNumber);
      if(userAccount == currentAccount){
        //suppose the user can't transfer money to themself
        return false;
      }
      else if ( userAccount != null )
         return true; // account number found
      else
         return false; // account number not found, so return false
   } // end method authenticateUser

   // return available balance of Account with specified account number
   public double getAvailableBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAvailableBalance();
   } // end method getAvailableBalance

   // return total balance of Account with specified account number
   public double getTotalBalance( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getTotalBalance();
   } // end method getTotalBalance
   
   public String getAccountType( int userAccountNumber )
   {
      return getAccount( userAccountNumber ).getAccountType();
   } // end method getTotalBalance

   // credit an amount to Account with specified account number
   public void credit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).credit( amount );
   } // end method credit
   // credit an amount to Account with specified account number during transfer
   public void creditForTransfer( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).creditForTransfer( amount );
   } 
   // debit an amount from of Account with specified account number
   public void debit( int userAccountNumber, double amount )
   {
      getAccount( userAccountNumber ).debit( amount );
   } // end method debit
} // end class BankDatabase



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/