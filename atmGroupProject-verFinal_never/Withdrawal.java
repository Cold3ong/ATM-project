// Withdrawal.java
// Represents a withdrawal ATM transaction
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.util.concurrent.CountDownLatch;

public class Withdrawal extends Transaction
{
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private MainFrame mainFrame;
   private WithdrawalGUI withdrawalGUI;
   private Numpad numpad;
   private AdvancedOptionGUI advancedOptionGUI;
   static boolean withdrew; //check whether user withdrew

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 5;

   // Withdrawal constructor
   public Withdrawal( int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser, MainFrame mFrame)
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      
      // initialize references to keypad and cash dispenser
      keypad = atmKeypad;
      numpad = mFrame.numpad;
      cashDispenser = atmCashDispenser;
      mainFrame = mFrame;
      withdrawalGUI = new WithdrawalGUI(mainFrame);
      advancedOptionGUI = new AdvancedOptionGUI(mFrame.numpad, mFrame);
   } // end Withdrawal constructor

   // perform transaction
   public void execute()
   {
      boolean cashDispensed = false; // cash was not dispensed yet
      double availableBalance; // amount available for withdrawal

      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase(); 
      Screen screen = getScreen();

      // loop until cash is dispensed or the user cancels
      do
      {
         // obtain a chosen withdrawal amount from the user 
         amount = displayMenuOfAmounts();
         
         // check whether user chose a withdrawal amount or canceled
         if ( amount != CANCELED )
         {
            // get available balance of account involved
            availableBalance = 
               bankDatabase.getAvailableBalance( getAccountNumber() );
      
            // check whether the user has enough money in the account 
            if ( amount <= availableBalance )
            {   
               // check whether the cash dispenser has enough money
               if ( cashDispenser.isSufficientCashAvailable( amount ) )
               {
                  // update the account involved to reflect withdrawal
                  bankDatabase.debit( getAccountNumber(), amount );
                  
                  cashDispenser.dispenseCash( amount ); // dispense cash
                  cashDispensed = true; // cash was dispensed
                 WithDrawScreen.takeitemScreen("Card", mainFrame.cR, mainFrame);
                 WithDrawScreen.messageScreen(" You have successfully retrieved your bank card.\n Please remember to collect your cash.", mainFrame);
                 WithDrawScreen.takeitemScreen("Cash", mainFrame.cD, mainFrame);
                 WithDrawScreen.messageScreen(" You have successfully retrieved your cash.", mainFrame);
               } // end if
               else{ // cash dispenser does not have enough cash
                  /*screen.displayMessageLine( 
                     "\nInsufficient cash available in the ATM." +
                     "\n\nPlease choose a smaller amount.\nReturning to Withdrawal Menu" );*/
                  WithDrawScreen.messageScreen(" Insufficient cash in the ATM.", mainFrame);
                  advancedOptionGUI.numpad.forceClear();
                }
            } // end if
            else // not enough money available in user's account
            {
               //screen.displayMessageLine( 
                  //"\nInsufficient funds in your account." +
                  //"\n\nPlease choose a smaller amount.\nReturning to Withdrawal Menu" );
                  //WithDrawScreen.InsuffFundsScreen();
               WithDrawScreen.messageScreen(" Insufficient funds in your account.", mainFrame);
            } // end else
         } // end if
         else // user chose cancel menu option 
         {
            //screen.displayMessageLine( "\nCanceling transaction..." );
            WithDrawScreen.messageScreen(" Canceling transaction...", mainFrame);
            return; // return to main menu because user canceled
         } // end else
      } while ( !cashDispensed );
      
      withdrew = true;
   } // end method execute

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts()
   {
      double userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      withdrew = false; //initialise withdrew
      
      // array of amounts to correspond to menu numbers
      // modifly because of the requirement of Part II (A)
      //int amounts[] = { 0, 20, 40, 60, 100, 200 };
      int amounts[] = { 0, 100, 500, 1000 };

      // loop while no valid choice has been made
      while ( userChoice == 0 )
      {
         withdrawalGUI.displayMenu(mainFrame, mainFrame.frame);
         /*screen.displayMessageLine( "\nWithdrawal Menu:" );
         screen.displayMessageLine( "1 - HKD100" );
         screen.displayMessageLine( "2 - HKD500" );
         screen.displayMessageLine( "3 - HKD1000" );
         screen.displayMessageLine( "4 - Advance Option" ); updataed as a screen*/
         //Allow User to input the Withdrawal amount themselves
         /*
         screen.displayMessageLine( "5 - $200" );
         */ //Remove because of the requirement of Part II (A)
         /*screen.displayMessageLine( "5 - Cancel transaction" );
         screen.displayMessage( "\nChoose a withdrawal amount: " );*/

         /*double input = -1; // get user input through keypad
         if(input%1!=0){
                  userChoice =0;
                  input =-1;
                }
         // determine how to proceed based on the input value ,updataed as a input by screen*/
         int choice = withdrawalGUI.getChoice();
         switch (choice)
         {
             // if the user chose a withdrawal amount
             // (i.e., chose option 1, 2, 3, 4 or 5), return the
             // corresponding amount from amounts array
            case 1: 
            case 2: 
            case 3: 
                ConfirmationScreen confirmationScreen = new ConfirmationScreen();
                //mainFrame.HidePanel();
                boolean confirmed = confirmationScreen.showConfirmationScreen(amounts[ choice ], mainFrame);
                if (confirmed){
                    userChoice = amounts[ choice ];
                    
                    break;
                }else{
                    userChoice = CANCELED; 
                    break;
                }
            case 4:
                /*screen.displayMessage(
                "\nPlease Input the withdrawal amount that is the ");
                screen.displayMessage(
                "\nmultiple of 100,500,1000 [Input \"0\" to return the Withdrawal Menu]: " );
                double inputAmount = keypad.getDoubleInput(); 
                // get user input through keypad to notice the amount that user
                // want to withdraw.
                //--Check wheather the input amount is the multiple of 100,500,1000--*/
                numpad.setCurrTextField(advancedOptionGUI.getTextField());
                advancedOptionGUI.getPanel().setVisible(true);
                advancedOptionGUI.getPanel().setEnabled(true);
                String temp; // temp to prevent invaild input 
                do//a loop to make sure the input can be read by the logic
                {
                    temp = advancedOptionGUI.getTypeAmount();
                    //advancedOptionGUI.numpad.waitInput();
                    if(temp.contains(".") || (temp.endsWith("0") && temp.startsWith("0"))) {
                        WithDrawScreen.messageScreen("Invalid input. Try again.", mainFrame);
                        advancedOptionGUI.mainFrame.ShowPanel(advancedOptionGUI.getPanel());
                    }
                    advancedOptionGUI.numpad.forceClear();
                }while (temp.contains(".") || (temp.endsWith("0") && temp.startsWith("0")));
                
                int inputAmount;
                //advanced option logic
                if (temp == "")
                    inputAmount = 0;
                else
                    inputAmount = Integer.parseInt(temp); 
                
                if (inputAmount == 0){
                    userChoice = CANCELED; 
                    break;
                } else if (inputAmount % 1000 == 0){
                    ConfirmationScreen confirmationScreen1 = new ConfirmationScreen();
                    confirmed = confirmationScreen1.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else if(inputAmount % 500 == 0){
                    ConfirmationScreen confirmationScreen2 = new ConfirmationScreen();
                    confirmed = confirmationScreen2.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else if(inputAmount % 100 == 0){
                    ConfirmationScreen confirmationScreen3 = new ConfirmationScreen();
                    confirmed = confirmationScreen3.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else{
                    //User input is invalid
                    //screen.displayMessageLine( "\nIvalid input. Try again." );
                    WithDrawScreen.messageScreen(" Invalid input. Try again.", mainFrame);
                    //advancedOptionGUI.mainFrame.ShowPanel(advancedOptionGUI.getPanel());
                    userChoice = 0;
                    break;
                }
                /*
                if (inputAmount == 0){
                    userChoice = CANCELED; 
                    break;
                } else if (inputAmount % 1000 == 0){
                    ConfirmationScreen confirmationScreen1 = new ConfirmationScreen();
                    confirmed = confirmationScreen1.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else if(inputAmount % 500 == 0){
                    ConfirmationScreen confirmationScreen2 = new ConfirmationScreen();
                    confirmed = confirmationScreen2.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else if(inputAmount % 100 == 0){
                    ConfirmationScreen confirmationScreen3 = new ConfirmationScreen();
                    confirmed = confirmationScreen3.showConfirmationScreen(inputAmount, mainFrame);
                    if (confirmed){
                        userChoice = inputAmount;
                    break;
                    }else{
                        userChoice = CANCELED; 
                    break;
                    }
                } else{
                    //User input is invalid
                    //screen.displayMessageLine( "\nIvalid input. Try again." );
                    WithDrawScreen.messageScreen(" Ivalid input. Try again.", mainFrame);
                    userChoice = 0;
                    break;
                }*/
            /*
            case 5:
               userChoice = amounts[ input ]; // save user's choice
               break;
            */      //Remove because of the requirement of Part II (A)
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               /*screen.displayMessageLine( 
                  "\nIvalid selection. Try again." );*/
         } // end switch
      } // end while

      return (int)userChoice; // return withdrawal amount or CANCELED
   } // end method displayMenuOfAmounts
   
} // end class Withdrawal



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