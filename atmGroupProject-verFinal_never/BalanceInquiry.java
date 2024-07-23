import java.util.concurrent.CountDownLatch;
import java.text.DecimalFormat;
// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction
{
   // BalanceInquiry constructor
   int userChoice = -1;
   private Keypad keypad = new Keypad();
   final int CANCEL = 0;
   MainFrame mainFrame = new MainFrame();
   DecimalFormat df = new DecimalFormat("#.##");
   
   public BalanceInquiry( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, MainFrame mFrame )
   {
      super( userAccountNumber, atmScreen, atmBankDatabase );
      mainFrame = mFrame;
   } // end BalanceInquiry constructor

   // performs the transaction
   public void execute()
   {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      // get the available balance for the account involved
      String availableBalance = 
         df.format(bankDatabase.getAvailableBalance( getAccountNumber() ));

      // get the total balance for the account involved
      String totalBalance = 
         df.format(bankDatabase.getTotalBalance( getAccountNumber() ));
      
      //get the account type
      String accountType = bankDatabase.getAccountType( getAccountNumber() );
     
      while(userChoice != CANCEL){
      // display the balance information on the screen
      /*
      screen.displayMessageLine( "\nBalance Information:" );
      screen.displayMessage( " - Account Type: " ); 
      screen.displayMessageLine( accountType );
      screen.displayMessage( " - Available balance: " ); 
      screen.displayDollarAmount( availableBalance );
      screen.displayMessage( "\n - Total balance:     " );
      screen.displayDollarAmount( totalBalance );
      screen.displayMessageLine( "" );
      */

      //userChoice = keypad.getInput();
      mainFrame.latch = new CountDownLatch(1);
      mainFrame.waitFor();      
      userChoice = mainFrame.userChoice;
    }
        mainFrame.HidePanel(mainFrame.displayPanel);
   } // end method execute
} // end class BalanceInquiry



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