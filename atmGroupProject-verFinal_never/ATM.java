import java.util.concurrent.CountDownLatch;
import javax.swing.*;

// ATM.java
// Represents an automated teller machine

public class ATM
{
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private MainFrame mainFrame = new MainFrame();
    private LoginPanel loginPanel = new LoginPanel(mainFrame);
    private Numpad numpad;
    //private fg loginFrame = new fg();
   /*
   private DepositSlot depositSlot; // ATM's deposit slot 
   */ //Remove because of the requirement of Part II (C)
    private BankDatabase bankDatabase; // account information database

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    /*
    private static final int DEPOSIT = 3;
    */ //Remove because of the requirement of Part II (C)
    private static final int TRANSFER = 3;
    private static final int EXIT = 0;

    //boolean withdrew;

    // no-argument ATM constructor initializes instance variables
    public ATM()
    {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad
        cashDispenser = new CashDispenser(); // create cash dispenser
      /*       
      depositSlot = new DepositSlot(); // create deposit slot
      */ //Remove because of the requirement of Part II (C)
        bankDatabase = new BankDatabase(); // create acct info database
        //mainFrame.setNumpad(numpad);
    } // end no-argument ATM constructor

    // start ATM
    public void run()
    {
        // welcome and authenticate user; perform transactions
        while ( true )
        {
            // loop while user is not yet authenticated
            //loginFrame.ShowPanel(loginFrame.LoginGUI);
            //loginFrame.ShowLogin();

            mainFrame.showGUI();
            mainFrame.HidePanel(mainFrame.MainMenu);
            while ( !userAuthenticated )
            {
                //screen.displayMessageLine( "\nWelcome!" );
                authenticateUser(); // authenticate user
            } // end while

            loginPanel.closeLogin();
            //mainFrame.showGUI();// start the GUI Frame
            performTransactions(); // user is now authenticated
            loginPanel.resetLogin();
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            //screen.displayMessageLine( "\nThank you! Goodbye!" );
        } // end while
    } // end method run

    // attempts to authenticate user against database
    private void authenticateUser()
    {
      /*screen.displayMessage( "\nPlease enter your account number: " );
      int accountNumber = keypad.getInput(); // input account number
      screen.displayMessage( "\nEnter your PIN: " ); // prompt for PIN
      int pin = keypad.getInput(); // input PIN*/

        // set userAuthenticated to boolean value returned by database
        //userAuthenticated =
        //  bankDatabase.authenticateUser( accountNumber, pin );
        loginPanel.openLogin();
        mainFrame.numpad.setNumpadDisable();
        while(true)
        {
            //mainFrame.numpad.setNumpadDisable();
            loginPanel.openLogin();
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
            if(loginPanel.getCheckAccount())
            {
                loginPanel.ConfirmAccount2();
            }
            else if(loginPanel.getCheckPin())
            {
                loginPanel.openPinGUI();
            }
            if(loginPanel.getLogin()){
                userAuthenticated =
                        bankDatabase.authenticateUser( loginPanel.getAccount(),
                                loginPanel.getPinNumber() );
                if ( userAuthenticated )
                {
                    currentAccountNumber = loginPanel.getAccount();
                    break;// save user's account #
                } // end if
                else{
                    loginPanel.RewritePin();
                }
            }
        }
    } // end method authenticateUser

    // display the main menu and perform transactions
    private void performTransactions()
    {
        // local variable to store transaction currently being processed
        Transaction currentTransaction = null;

        boolean userExited = false; // user has not chosen to exit
        Withdrawal.withdrew = false;

        // loop while user has not chosen option to exit system
        while ( !userExited )
        {
            // show main menu and get user selection
            mainFrame.ShowPanel(mainFrame.MainMenu);
            SwingUtilities.updateComponentTreeUI(mainFrame.MainMenu);
            mainFrame.latch = new CountDownLatch(1);
            mainFrame.numpad.setNumpadDisable();
            mainFrame.waitFor();

            double tempChoice = mainFrame.getChoice();             //temparory store the user

            int mainMenuSelection;                             //main menu choice
            if(tempChoice%1==0){
                //tempChoice = mainFrame.getChoice();
                mainMenuSelection = (int)tempChoice;
            }
            else{
                mainMenuSelection = -1;
            }
            // decide how to proceed based on user's menu selection
            switch ( mainMenuSelection )
            {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                    mainFrame.numpad.setNumpadEnable();
                    currentTransaction =
                            createTransaction( mainMenuSelection );
                    currentTransaction.execute();
                    if (Withdrawal.withdrew == true)
                    {
                        userExited = true;
                        mainFrame.HidePanel(mainFrame.MainMenu);
                        //mainFrame.closeGUI();
                    }
                    break;
            /*
                case DEPOSIT:

                // initialize as new object of chosen type
                currentTransaction =
                    createTransaction( mainMenuSelection );

                currentTransaction.execute(); // execute transaction
                break;
            */ //Remove because of the requirement of Part II (C)

                case TRANSFER:
                    mainFrame.numpad.setNumpadEnable();
                    // initialize as new object of chosen type
                    currentTransaction =
                            createTransaction( mainMenuSelection );

                    currentTransaction.execute(); // execute transaction
                    break;
                case EXIT: // user chose to terminate session
                    //screen.displayMessageLine( "\nExiting the system..." );
                    userExited = true; // this ATM session should end
                    mainFrame.HidePanel(mainFrame.MainMenu);
                    loginPanel.takeCardPanel(mainFrame.cR);
                    loginPanel.cardTook();
                    //mainFrame.closeGUI();
                    break;
                default: // user did not enter an integer from 1-4
                    screen.displayMessageLine(
                            "\nYou did not enter a valid selection. Try again." );
                    break;
            } // end switch
        } // end while
    } // end method performTransactions

    // display the main menu and return an input selection
    private double displayMainMenu()
    {
        mainFrame.ShowPanel(mainFrame.MainMenu);
        mainFrame.setTitle("MainMenu");
    /*screen.displayMessageLine( "\nMain menu:" );
    screen.displayMessageLine( "1 - View my balance" );
    screen.displayMessageLine( "2 - Withdraw cash" );
    //screen.displayMessageLine( "3 - Deposit funds" );
    //Remove because of the requirement of Part II (C)
    screen.displayMessageLine( "3 - Fund transfer" );
    screen.displayMessageLine( "4 - Exit\n" );
    screen.displayMessage( "Enter a choice: " );*/
        //-------------------Show_GUI------------------------
        mainFrame.showGUI();
        return keypad.getDoubleInput(); // return user's selection
    } // end method displayMainMenu

    // return object of specified Transaction subclass
    private Transaction createTransaction( int type )
    {
        Transaction temp = null; // temporary Transaction variable

        // determine which type of Transaction to create
        switch ( type )
        {
            case BALANCE_INQUIRY: // create new BalanceInquiry transaction

                temp = new BalanceInquiry(
                        currentAccountNumber, screen, bankDatabase, mainFrame );
                mainFrame.HidePanel(mainFrame.MainMenu);
                mainFrame.getUserData(currentAccountNumber, bankDatabase);
                mainFrame.UpdateBalance();
                mainFrame.ShowPanel(mainFrame.displayPanel);
                //mainFrame.setPanelBounds(balancePanel.displayPanel);
                //balancePanel.ShowPanel();
                //mainFrame.AddPanel(temp.displayPanel);
                break;
            case WITHDRAWAL: // create new Withdrawal transaction
                mainFrame.HidePanel(mainFrame.MainMenu);
                temp = new Withdrawal( currentAccountNumber, screen,
                        bankDatabase, keypad, cashDispenser, mainFrame);


                //mainFrame.ShowPanel(mainFrame.MainMenu);
                break;
         /*
            case DEPOSIT: // create new Deposit transaction
            temp = new Deposit( currentAccountNumber, screen, 
               bankDatabase, keypad, depositSlot );
            break; 
         */ //Remove because of the requirement of Part II (C)

            case TRANSFER: // create new Deposit transaction
                mainFrame.HidePanel(mainFrame.MainMenu);
                temp = new Transfer( currentAccountNumber, screen,
                        bankDatabase, keypad, mainFrame);
                break;
        } // end switch

        return temp; // return the newly created object
    } // end method createTransaction

    public MainFrame getMainFrame()
    {
        return mainFrame;
    }
} // end class ATM



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