// CashDispenser.java
// Represents the cash dispenser of the ATM

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashDispenser implements ActionListener
{
   // the default initial number of Hong Kong Dollars (HKD) in the cash dispenser
   private final static int INITIAL_COUNT = 500;
   private int count; // number of $20 HKD remaining
   private JPanel cashDispenser;
   private boolean cashWithdrawal = false;
   public JButton cD;
   
   // no-argument CashDispenser constructor initializes count to default
   public CashDispenser()
   {
      count = INITIAL_COUNT;
      this.dispenser();
      // set count attribute to default
   } // end CashDispenser constructor

   // simulates dispensing of specified amount of cash
   public void dispenseCash( int amount )
   {
      int hkdsRequired = amount / 20; // number of $20 HKD required
      count -= hkdsRequired; // update the count of hkd
   } // end method dispenseCash

   // indicates whether cash dispenser can dispense desired amount
   public boolean isSufficientCashAvailable( int amount )
   {
      int hkdsRequired = amount / 20; // number of $20 HKD required

      if ( count >= hkdsRequired  )
         return true; // enough HKD available
      else 
         return false; // not enough HKD available
   }
   //create panel and button for the dispenser
   public void dispenser() {
      cashDispenser = new JPanel();
      cashDispenser.setLayout(null);
      cashDispenser.setVisible(true);
      cashDispenser.setBounds(492, 794, 490, 165);
      cashDispenser.setBackground(new Color(255, 255, 128));
      cD = new JButton("Cash Dispenser");
      cD.setBounds(83, 55, 351, 31);
      cD.setFocusable(false);
      cashDispenser.add(cD);
      SwingUtilities.updateComponentTreeUI(cashDispenser);
   }
   //get the panel of the cash dispenser
   public JPanel getCashDispenser(){
      return cashDispenser;
   }
   //get the status of cash withdrawal
   public boolean isCashWithdrawal(){
      return cashWithdrawal;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(((JButton)e.getSource()).getText() == "cashdispenser"){
         cashWithdrawal = true;
      }
   }
   // end method isSufficientCashAvailable
} // end class CashDispenser



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