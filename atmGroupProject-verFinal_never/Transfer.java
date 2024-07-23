import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

/* function(sub class of Transaction) created as the group project required
   (feature (D))*/
public class Transfer extends Transaction
{
   private double amount; // amount to Transfer
   private Keypad keypad; // reference to keypad
   private final static int CANCELED = -1; // constant for cancel option;
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color TextBackgroundColor = new Color(78, 176, 45);
    Color BackgroundColor = new Color(0, 128, 255);
    Font WordFont = new Font("Arial", Font.BOLD, 25);
   
   private TransferGUI transferGUI; //reference to TransferGUI
   
   private Numpad numpad; //reference to Numpad
   
   private ATM atm; //reference to ATM
   
   private MainFrame mainMenu;
   
   private Wait timer;
   
   public class TransferGUI{
       //assign the variables of the GUI
       //private JFrame transfer;
       private JTextField transferAccount;
       private JTextField transferAmount;
       private JPanel transferAmountPanel;
       private JPanel transferAccountPanel;
       private JPanel confirmMSG;
       private JTextArea txtrTransferMSG;
       private JTextArea txtrTransferAmount;
       private JTextArea txtrTransferAccount;
       private JPanel waitingPanel;
       private JTextArea txtrWaiting;
       
       public TransferGUI(JFrame mainFrame) {
           //--------------------------------Frame-----------------------------------------------------------------------------
           /*transfer = new JFrame();
           transfer.setBounds(100, 100, 1000, 1000);
           transfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           transfer.setLayout(null);
           transfer.setVisible(true);
           transfer.setEnabled(true);*/
           
           //------------------------------Transfer Amount Panel---------------------------------------------------------------
           transferAmountPanel = new JPanel();
           transferAmountPanel.setBounds(124, 0, 734, 629);
           transferAmountPanel.setBackground(new Color(0, 128, 255));
           mainFrame.getContentPane().add(transferAmountPanel);
           transferAmountPanel.setEnabled(false);
           transferAmountPanel.setVisible(false);
           transferAmountPanel.setLayout(null);
            
           //----------------------------------Transfer Amount TextField-------------------------------------------------------
           transferAmount = new JTextField();
           transferAmount.setBounds(59, 404, 614, 55);
           transferAmount.setBackground(new Color(255, 255, 255));
           transferAmount.setForeground(new Color(0, 0, 0));
           transferAmount.setBorder( new LineBorder(new Color(0, 0, 0), 3));
           transferAmount.setFont(WordFont);
           transferAmountPanel.add(transferAmount);
           transferAmount.setColumns(10);
           transferAmount.setEditable(false);
            
           //----------------------------------Transfer Amount TextArea--------------------------------------------------------
           txtrTransferAmount = new JTextArea();
           txtrTransferAmount.setBounds(59, 60, 614, 141);
           txtrTransferAmount.setBackground(TextBackgroundColor);
           txtrTransferAmount.setForeground(new Color(255, 255, 255));
           txtrTransferAmount.setFont(WordFont);
           txtrTransferAmount.setBorder(textBorder);
           txtrTransferAmount.setText(" Please enter a transfer amount in HK Dollars");
           transferAmountPanel.add(txtrTransferAmount);
           txtrTransferAmount.setEditable(false);
           
           //--------------------------------------Transfer Account Panel-------------------------------------------------------
           transferAccountPanel = new JPanel();
           transferAccountPanel.setBounds(124, 0, 734, 629);
           transferAccountPanel.setBackground(new Color(0, 128, 255));
           mainFrame.getContentPane().add(transferAccountPanel);
           transferAccountPanel.setLayout(null);
           transferAccountPanel.setEnabled(false);
           transferAccountPanel.setVisible(false);
            
           //-----------------------------------Transfer Account TextField-----------------------------------------------------
           transferAccount = new JTextField();
           transferAccount.setBounds(59, 404, 614, 55);
           transferAccount.setBackground(new Color(255, 255, 255));
           transferAccount.setForeground(new Color(0, 0, 0));
           transferAccount.setFont(WordFont);
           transferAccount.setBorder( new LineBorder(new Color(0, 0, 0), 3));
           transferAccountPanel.add(transferAccount);
           transferAccount.setColumns(10);
           transferAccount.setEditable(false);
           
           //----------------------------------Transfer Account TextArea-------------------------------------------------------
           txtrTransferAccount = new JTextArea();
           txtrTransferAccount.setText(" Please enter a transfer taget account.");
           txtrTransferAccount.setBounds(59, 60, 614, 141);
           txtrTransferAccount.setBackground(TextBackgroundColor);
           txtrTransferAccount.setForeground(new Color(255, 255, 255));
           txtrTransferAccount.setFont(WordFont);
           txtrTransferAccount.setBorder(textBorder);
           transferAccountPanel.add(txtrTransferAccount);
           txtrTransferAccount.setEditable(false);
            
           //----------------------------------Transfer Confirm Message--------------------------------------------------------
           confirmMSG = new JPanel();
           confirmMSG.setEnabled(false);
           confirmMSG.setBounds(124, 0, 734, 629);
           confirmMSG.setBackground(new Color(0, 128, 255));
           mainFrame.getContentPane().add(confirmMSG);
           confirmMSG.setLayout(null);
           confirmMSG.setVisible(false);
           
           //---------------------------------Transfer Message TextArea--------------------------------------------------------
           txtrTransferMSG = new JTextArea();
           txtrTransferMSG.setBounds(59, 60, 614, 141);
           txtrTransferMSG.setBackground(TextBackgroundColor);
           txtrTransferMSG.setForeground(new Color(255, 255, 255));
           txtrTransferMSG.setFont(WordFont);
           txtrTransferMSG.setBorder(textBorder);
           txtrTransferMSG.setEditable(false);
           //txtrTransferMSG.setBackground(UIManager.getColor("Button.background"));
           confirmMSG.add(txtrTransferMSG);
           txtrTransferMSG.setEditable(false);
           
           //---------------------------------Transfer Cancel Panel------------------------------------------------------------
           waitingPanel = new JPanel();
           waitingPanel.setBounds(124, 0, 734, 629);
           waitingPanel.setBackground(new Color(0, 128, 255));
           mainFrame.getContentPane().add(waitingPanel);
           waitingPanel.setLayout(null);
           waitingPanel.setVisible(false);
           waitingPanel.setEnabled(false);
        
           //-------------------------------Transfer Cancel TextArea-----------------------------------------------------------
           txtrWaiting = new JTextArea();
           txtrWaiting.setBounds(59, 60, 614, 141);
           txtrWaiting.setBackground(TextBackgroundColor);
           txtrWaiting.setForeground(new Color(255, 255, 255));
           txtrWaiting.setFont(WordFont);
           txtrWaiting.setBorder(textBorder);
           txtrWaiting.setEditable(false);
           waitingPanel.add(txtrWaiting);
           txtrWaiting.setEditable(false);
       }
       
       // getMethod of Transfer Account Panel
       public JPanel getTransferAccountPanel()
       {
           return transferAccountPanel;
       }
       
       //getMethod of Transfer Account TextField
       public JTextField getTransferAccount()
       {
           return transferAccount;
       }
       
       //getMethod of Transfer Account TextArea
       public JTextArea getTxtrTransferAccount()
       {
           return txtrTransferAccount;
       }
       
       //getMethod of Transfer Frame
       /*public JFrame getTransfer()
       {
           return transfer;
       }*/
       
       //getMethod of Transfer Amount Panel
       public JPanel getTransferAmountPanel()
       {
           return transferAmountPanel;
       }
       
       //getMethod of Transfer Amount TextField
       public JTextField getTransferAmount()
       {
           return transferAmount;
       }
       
       //getMethod of Transfer Amount TextArea
       public JTextArea getTxtrTransferAmount()
       {
           return txtrTransferAmount;
       }
       
       //getMethod of Transfer Message TextArea
       public JTextArea getTxtrTransferMSG()
       {
           return txtrTransferMSG;
       }
       
       //getMethod of Confirm Message Panel
       public JPanel getConfirmMSG()
       {
           return confirmMSG;
       }
       
       //getMethod of Waiting Panel
       public JPanel getWaitingPanel()
       {
           return waitingPanel;
       }
       
       //getMethod of Waiting TextArea
       public JTextArea getTxtrWaiting()
       {
           return txtrWaiting;
       }
       
       /*public MainFrame getMainFrame()
       {
           return mainFrame;
       }*/
   }
   
   // Transfer constructor
   public Transfer( int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, MainFrame mainMenu)
   {
      // initialize superclass variables
      super( userAccountNumber, atmScreen, atmBankDatabase );
      //store the current account number
      // initialize references to keypad
      keypad = atmKeypad;
      this.numpad = mainMenu.numpad;
      transferGUI = new TransferGUI(mainMenu.frame);
      this.mainMenu = mainMenu;
   } // end constructor
   
   // perform transaction
   public void execute()
   {
      BankDatabase bankDatabase = getBankDatabase(); // get reference
      Screen screen = getScreen(); // get reference
      double availableBalance; // amount available for transfer
      int targetAccountNumber; // target account number for transfer
      availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() ); // get available balance of account involved

      targetAccountNumber = promptForTransferTargetAccount(); // get Transter target account from user
      
      boolean cancelTransfer; // cancel checker for  
      
      // check whether the user canceled or entered a valid account
      if (targetAccountNumber == CANCELED)
      {
          //screen.displayMessageLine( "\nCanceling transaction..." );
          Cancelling();
          return; // return to main menu because user canceled
      }
      
      amount = promptForTransferAmount(availableBalance); // get Transfer amount from user
      
      // check whether the user canceled or entered a valid amount
      if (amount == CANCELED)
      {
          //screen.displayMessageLine( "\nCanceling transaction..." );
          Cancelling(); //open cancel panel
          return; // return to main menu because user canceled
      }
      
      //opend the Transfer Cancel Panel and get the boolean to decide cancel or not
      cancelTransfer = transferComfirm(amount,targetAccountNumber);
      
      //decide cancel or not
      if(!cancelTransfer)
      {
          //screen.displayMessageLine( "\nCanceling transaction..." );
          Cancelling();//open cancel panel
          return; // return to main menu because user canceled
      }
      
      // update user account involved to reflect transfer
      bankDatabase.debit( getAccountNumber(), amount );
                
      // update target account involved to reflect transfer
      bankDatabase.creditForTransfer( targetAccountNumber , amount );
      
      //screen.displayMessage( "\nFund transfered successfully." );
      Success();
      
      return; // return to main menu because the transaction completed
      } // end method execute

   // prompt user to enter a transfer amount in HKDs 
   private double promptForTransferAmount(double availableBalance )
   {
       Screen screen = getScreen(); // get reference to screen
       double input;
       boolean test = true; /*only the input doesn't contain 3 decimals places, the test will become false in order to escape
                            the while loop*/
                            
       int failTime = 0; //mark the times of fail
       
       double temp1;
       //enable the Amount Panel
       transferGUI.getTransferAmountPanel().setVisible(true);
       transferGUI.getTransferAmountPanel().setEnabled(true);
       
       //display the prompt
       //screen.displayMessage( "\nPlease enter a transfer amount in " + "HK Dollars (or 0 to cancel): " );
       do
       {
           //relink the numpad with other TextField
           temp1 = 0;
           numpad.setCurrTextField(transferGUI.getTransferAmount());
           
           // reset the  if invalid input received
           if(failTime >0){
               numpad.setCurrTextField(transferGUI.getTransferAmount());//relink the numpad with other TextField
               numpad.forceClear();// reset the stored input in the numpad
               numpad.setText("",transferGUI.getTransferAmount()); //reset the linked TextField
               numpad.waitInput(); // wait for the input
           }
           
           //temp1 and temp2 are used to exclude the cases that have 3 decimals places

           numpad.forceClear();
           numpad.waitInput();
           



               /*if(numpad.getNextStage() == false)
               {
                   transferGUI.getTransferAmountPanel().setVisible(false);
                   //enable Transfer Amount Panel
                   transferGUI.getTransferAmountPanel().setEnabled(false);
                   numpad.forceClear();// reset the stored input in the numpad
                   numpad.setText("",transferGUI.getTransferAccount()); //reset the linked TextField
                   return -1;
               }*/
               while (numpad.getTextToOutput() == "") {
                   
                   if (numpad.getTextToOutput() == "" && numpad.getNextStage() == false) {
                       transferGUI.getTransferAmountPanel().setVisible(false);
                       //enable Transfer Amount Panel
                       transferGUI.getTransferAmountPanel().setEnabled(false);
                       numpad.forceClear();// reset the stored input in the numpad
                       numpad.setText("", transferGUI.getTransferAccount()); //reset the linked TextField
                       return -1;
                   } else {
                       temp1 = Double.parseDouble(numpad.getTextToOutput()) * 100;
                       numpad.waitInput();
                   }
               }


           /*if (temp1 == 0){
               while (temp1 == 0)
               {
                   temp1 = Double.parseDouble(numpad.getTextToOutput())*100;
                   numpad.waitInput();
               }}
           else {
               numpad.waitInput();
           }*/
           int temp2 = (int)temp1;
           
           // debug with case that 0 is inputted
           
           if (Double.parseDouble(numpad.getTextToOutput()) == 0.0)
           {
               
               numpad.setNextStage(false);
               transferGUI.getTxtrTransferAmount().setText(" The value cannot be 0. Please try again.");
               //update the Transfer Amount TextArea
               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAmount());
               numpad.forceClear();// reset the stored input in the numpad
               numpad.setText("",transferGUI.getTransferAmount()); //reset the linked TextField
               numpad.waitInput(); // wait for the input
           }
           
           // debug with case that improper value with decimal point inputted
           if (numpad.getTextToOutput().startsWith(".") || numpad.getTextToOutput().endsWith("."))
           {
               transferGUI.getTxtrTransferAmount().setText(" Invalid format. Please try again.");
               //update the Transfer Amount TextArea
               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAmount());
               continue;
           }
           
           //check the user cancel the transaction or not
           if (numpad.getNextStage() == false)
           {
               //enable Transfer Amount Panel
               transferGUI.getTransferAmountPanel().setVisible(false);
               //enable Transfer Amount Panel
               transferGUI.getTransferAmountPanel().setEnabled(false);
               numpad.forceClear();// reset the stored input in the numpad
               numpad.setText("",transferGUI.getTransferAccount()); //reset the linked TextField
               return -1;
           }
           //ensure the transfer amount is smaller than availableBalance
           else if (Double.parseDouble(numpad.getTextToOutput()) > availableBalance)
           {
               transferGUI.getTxtrTransferAmount().setText(" Insufficient cash available in your account." +
                   "\n Please enter a smaller amount.");
               //update the Transfer Amount Text Area
               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAmount());
           }
           else if ((temp1 - temp2) != 0) //check if the input has 3 decimals places
           {
               //numpad.setNextStage(false);
               
               transferGUI.getTxtrTransferAmount().setText(" Only accept two decimals places input."  +
                   "\n Please enter again");
               //upadate the Transfer Amount Text Area
               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAmount());
               numpad.forceClear();// reset the stored input in the numpad
               numpad.setText("",transferGUI.getTransferAmount()); //reset the linked TextField
               numpad.waitInput();
               test = true;
           }
           else test = false;
           
       } while (test || Double.parseDouble(numpad.getTextToOutput()) > availableBalance||Double.parseDouble(numpad.getTextToOutput())==0);
       
       //removed
       /*do
       {
           input = keypad.getDoubleInput(); // receive input of transfer amount
           double temp1 = input*100; //temp1 and temp2 are used to exclude the cases that have 3 decimals places. 
           int temp2 = (int)temp1;
           //screen.displayDollarAmount(availableBalance);
           if(input > availableBalance && input>=CANCELED){
               screen.displayMessageLine( "\nInsufficient cash available in your account." +
                   "\n\nPlease enter a smaller amount (or 0 to cancel)" );
           }
           else if((temp1-temp2) !=CANCELED){//check if the input has 3 decimals places
                screen.displayMessageLine( "\nOnly accept two decimals places input."  +
                   "\n\nPlease enter again (or 0 to cancel)" );
                test = true;
           }else{
           test =false;
           }
       } while((test ||input > availableBalance) && input != CANCELED); 
       */   
       
       //convert the input from numpad from String to double
       input = Double.parseDouble(numpad.getTextToOutput());
       
       //disable the Transfer Amount Panel
       transferGUI.getTransferAmountPanel().setVisible(false);
       transferGUI.getTransferAmountPanel().setEnabled(false);
       return input; // return dollar amount 
   } // end method promptForTransferAmount
   
   // prompt user to enter a transfer target account in HKDs 
   private int promptForTransferTargetAccount()
   {
       BankDatabase bankDatabase = getBankDatabase(); // get reference
       Screen screen = getScreen(); // get reference to screen
       int input; // get input of user
       int failTime =0;
       
       //enable Transfer Account Pancel
       transferGUI.getTransferAccountPanel().setVisible(true);
       transferGUI.getTransferAccountPanel().setEnabled(true);
       
       //screen.displayMessage( "\nPlease enter a transfer taget account (or 0 to cancel): " );
       numpad.setCurrTextField(transferGUI.getTransferAccount());
       numpad.waitInput();
       
       do
       {    
           if(failTime >0){
               // relink the numpad with another TextField
               numpad.setCurrTextField(transferGUI.getTransferAccount());
               numpad.forceClear();//reset the input stored in the numpad
               //reset the current TextField
               numpad.setText("",transferGUI.getTransferAccount());
               //wait for the numpad for input
               numpad.waitInput();
           }
           
           //check the user cancel the transaction or not
           if (numpad.getNextStage() == false)
           {
               numpad.forceClear(); //reset the value of the input stored in the numpad
               //reset the linked TextField
               numpad.setText("",transferGUI.getTransferAccount());
               
               //disable the Transfer Account Panel
               transferGUI.getTransferAccountPanel().setVisible(false);
               transferGUI.getTransferAccountPanel().setEnabled(false);

               return -1;
           }
           //check whether the input contains decimal point
           else if (numpad.getTextToOutput().contains("."))
           {
               transferGUI.getTxtrTransferAccount().setText(" Account number cannot contain decimal point. "+ 
                   "\n Please try again.");
               
               
               failTime +=1;

               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAccount());


           }
           //check whether the inputed account is same as the current account
           //check the existence of the account
           else if(!bankDatabase.checkAccount(Integer.parseInt(numpad.getTextToOutput()),
                   getAccountNumber()))
           {
               failTime +=1;
               /*numpad.forceClear();
               numpad.setText("",transferGUI.getTransferAccount());
               numpad.waitInput();*/
               transferGUI.getTxtrTransferAccount().setText(" Invalid account number or same number with\n current account. " + 
                   "\n Please try again.");
               SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrTransferAccount());
           }
       } while (numpad.getTextToOutput().contains(".") || 
           !bankDatabase.checkAccount(Integer.parseInt(numpad.getTextToOutput()),getAccountNumber()) || !numpad.getNextStage());
       // removed
       /*do
       {
           input = keypad.getInput(); // receive input of transfer account
           
           // check whether the user canceled or entered a valid account and distinct account from current account
           if (!(bankDatabase.checkAccount(input,getAccountNumber())))
               screen.displayMessage( "\nInvalid account number or same number with current account. Please try again." +
               "(or 0 to cancel)" );
       }while (!bankDatabase.checkAccount(input,getAccountNumber()) && input != CANCELED); // return target account number
       */
       
       //convert the input from string to integer
       input = Integer.parseInt(numpad.getTextToOutput());
       
       //disable the Transfer Account Panel
       transferGUI.getTransferAccountPanel().setVisible(false);
       transferGUI.getTransferAccountPanel().setEnabled(false);
       return input;
   }
   
   //The method of opening teh Comfirm panel
   private boolean transferComfirm(double amount, int account)
   {
       transferGUI.getTxtrTransferMSG().setText(" Are you sure to transfer HK$" + amount + " to\n the account " + account + "?" +
           "\n Press \"A\" to confirm or \"EXIT\" to cancel the\n transaction");
       
       //enable Comfirm panel
       transferGUI.getConfirmMSG().setEnabled(true);
       transferGUI.getConfirmMSG().setVisible(true);
       
       int choice;//check whether the user cancel the transaction
       mainMenu.MenuPad_R.setEnabled(true);
       mainMenu.MenuPad_L.setEnabled(true);
       mainMenu.userChoice = -1;
       choice = mainMenu.userChoice;
       
       //When Choice Not (1 XOR 0) loop until user input
       while(!((choice == 1 || choice == 0) && (choice != 1 || choice != 0))){
           choice = mainMenu.userChoice;
           //Stop the loop for 0.1s in order to let the user input successfully
           try{Thread.sleep(100);}catch (InterruptedException e) {e.printStackTrace();}
       };
       
       if (choice == 1){
           //transferGUI.getConfirmMSG().setEnabled(false);
           transferGUI.getConfirmMSG().setVisible(false);
           return  true;
        }
       else{ 
           //transferGUI.getConfirmMSG().setEnabled(false);
           transferGUI.getConfirmMSG().setVisible(false);
           return false;
        }
    }
   
   //method for opening Waiting Panel with cancel message
   private void Cancelling()
   {
       try 
       {
           //enable Waiting Panel
           transferGUI.getWaitingPanel().setVisible(true);
           transferGUI.getWaitingPanel().setEnabled(true);
           transferGUI.getTxtrWaiting().setText(" Canceling transaction...");
           SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrWaiting());
           
           //wait for 4 seconds
           Thread.sleep(3000);
           
           //disable Waiting Panel
           transferGUI.getWaitingPanel().setVisible(false);
           transferGUI.getWaitingPanel().setEnabled(false);
       }
       catch (InterruptedException e) 
       {
           e.printStackTrace();
       }
   }
   
   //method for opening Waiting Panel with transfer success message
   private void Success()
   {
       try 
       {
           //enable Waiting Panel
           transferGUI.getWaitingPanel().setVisible(true);
           transferGUI.getWaitingPanel().setEnabled(true);
           transferGUI.getTxtrWaiting().setText(" Fund transfered successfully.");
           SwingUtilities.updateComponentTreeUI(transferGUI.getTxtrWaiting());
           
           //wait for 4 seconds
           Thread.sleep(3000);
           
           //disable Waiting Panel
           transferGUI.getWaitingPanel().setVisible(false);
           transferGUI.getWaitingPanel().setEnabled(false);
       }
       catch (InterruptedException e) 
       {
           e.printStackTrace();
       }
   }
}
