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


public class ConfirmationScreen {
    private boolean confirmed;
    private Wait wait;
    private MainFrame mainFrame;
    private JFrame window;
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color BackgroundColor = new Color(0, 128, 255);
    Color ButtonBackgroundColor = new Color(128, 128, 128);
    Color TextBackgroundColor = new Color(78, 176, 45);

    public boolean showConfirmationScreen(int amount,MainFrame mFrame) {
        mainFrame = mFrame;
        window = mainFrame.frame;
        window.setTitle("Confirmation");
        //setting of the panel
        JPanel panel = new JPanel();
        panel.setBounds(124, 0, 734, 629);
        panel.setLayout(null);
        panel.setBackground(BackgroundColor);
        //set the confirm message
        String message = " Confirm withdraw HKD " + amount + " ?" +
        "\n Press \"A\" to confirm or \"EXIT\" to cancel the\n transaction";
        JTextArea label = new JTextArea(message);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(10, 200, 714, 273);
        label.setBorder(textBorder);
        label.setForeground(new Color(255, 255, 255));
        label.setBackground(TextBackgroundColor);
        label.setOpaque(true);
        label.setEditable(false);
        panel.add(label);
        //display the panel 
        window.add(panel);
        window.setVisible(true);
        SwingUtilities.updateComponentTreeUI(panel);
        mainFrame.userChoice = -1;
        //set the value of choice
        int choice = mainFrame.userChoice;
        //lopping to ensure user already choose a vaild choice
        while(choice > 1 || choice < 0){
            choice = mainFrame.userChoice;
            //Stop the loop for 0.1s in order to let the user input successfully
            try{Thread.sleep(100);}catch (InterruptedException e) {e.printStackTrace();}
        }
        
        if (choice == 1){
           confirmed = true;
           mainFrame.HidePanel(panel);
        }
        else{ 
            confirmed = false;
            mainFrame.HidePanel(panel);
        }
        //retuen the boolean that whether the confirm or not 
        return confirmed;
    }
}