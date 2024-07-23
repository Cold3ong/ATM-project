import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
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


public class WithdrawalGUI {
    private int choice;
    private CountDownLatch latch;
    private MainFrame mainFrame;
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color BackgroundColor = new Color(0, 128, 255);
    Color ButtonBackgroundColor = new Color(128, 128, 128);
    Color TextBackgroundColor = new Color(78, 176, 45);

    public WithdrawalGUI(MainFrame mFrame) {
        mainFrame = mFrame;
        latch = new CountDownLatch(1);
        
    }

    public void displayMenu(MainFrame mFrame, JFrame mainFrame) {
        
        mainFrame.setTitle("Withdrawal Menu");
        //setting of the panel 
        JPanel panel = new JPanel();
        JLabel menuLabel = new JLabel("Withdrawal Menu:");
        
        panel.setBounds(124, 0, 734, 629);
        panel.setLayout(null);
        panel.setBackground(BackgroundColor);
        panel.add(menuLabel, BorderLayout.NORTH);
        //setting message
        JTextArea infoArea = new JTextArea(" - HKD 100 -                                         - HKD 500 - \r\n\r" + 
            "\n\r\n - HKD1000 -                           - AdvancedOption -\r\n\r\n\r\n" +
            "                                                                     - Exit - ");
            infoArea.setFont(new Font("Arial", Font.BOLD, 30));
            infoArea.setBounds(10, 302, 714, 273);
            infoArea.setBorder(textBorder);
            infoArea.setForeground(new Color(255, 255, 255));
            infoArea.setBackground(TextBackgroundColor);
        infoArea.setEditable(false);
        infoArea.setOpaque(true);
        panel.add(infoArea, BorderLayout.CENTER);
        //add the panel
        mainFrame.add(panel);
        SwingUtilities.updateComponentTreeUI(panel);
        //wait for user input
        try{Thread.sleep(100);}catch (InterruptedException e) {e.printStackTrace();}
        //save the input into choice
        mFrame.userChoice = -1;
        choice = mFrame.userChoice;
        //display the message
        panel.setVisible(true);
        //a lopping to ensure the input  is vaild
        while(choice > 4 || choice < 0){
           choice = mFrame.userChoice;
           //Stop the loop for 0.1s in order to let the user input successfully
           try{Thread.sleep(100);}catch (InterruptedException e) {e.printStackTrace();}
       }
       if (choice == 0){
           choice = 5;
           mFrame.HidePanel(panel);
        }
        //hidepanel by defalft
        mFrame.HidePanel(panel);
        // Close the panel
    }
    //get the variable.
    public int getChoice() {
        return choice;
    }
}