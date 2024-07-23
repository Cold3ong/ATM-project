import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.util.concurrent.CountDownLatch;

public class WithDrawScreen {
    private static JFrame getcashframe;
    private static JFrame cardframe;
    private static CountDownLatch latch;
    //panel setting
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color BackgroundColor = new Color(0, 128, 255);
    Color ButtonBackgroundColor = new Color(128, 128, 128);
    Color TextBackgroundColor = new Color(78, 176, 45);
    
    public static void messageScreen(String message, MainFrame mainFrame) {
        mainFrame.frame.setTitle("Cash Withdrawal");
        //panel setting
        JPanel panel = new JPanel();
        panel.setBounds(124, 0, 734, 629);
        panel.setLayout(null);
        panel.setBackground(new Color(0, 128, 255));
    
        JTextArea label = new JTextArea(message);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(10, 200, 714, 273);
        label.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        label.setForeground(new Color(255, 255, 255));
        label.setBackground(new Color(78, 176, 45));
        label.setOpaque(true);
        label.setEditable(false);
        panel.add(label, BorderLayout.CENTER);
        //add the panel to the mainframe
        mainFrame.frame.add(panel);
        SwingUtilities.updateComponentTreeUI(panel);
    
        Wait wait = new Wait();
        //display the message for 2s
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call the actionlistener
                mainFrame.HidePanel(panel);
                wait.latch.countDown();
            }
        });
        timer.setRepeats(false);
        timer.start();
    
        wait.waitFor();
    }
    //tell user to get item message
        public static void takeitemScreen(String item,JButton Button, MainFrame mainFrame) {
        mainFrame.frame.setTitle("Cash Withdrawal");
    
        JPanel panel1 = new JPanel();
        panel1.setBounds(124, 0, 734, 629);
        panel1.setLayout(null);
        panel1.setBackground(new Color(0, 128, 255));
        //create the text
        JTextArea label1 = new JTextArea(" Please take your " + item + " now.");
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setBounds(10, 200, 714, 273);
        label1.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        label1.setForeground(new Color(255, 255, 255));
        label1.setBackground(new Color(78, 176, 45));
        label1.setOpaque(true);
        label1.setEditable(false);
        panel1.add(label1);
        //show it 
        mainFrame.frame.add(panel1);
        mainFrame.ShowPanel(panel1);
        SwingUtilities.updateComponentTreeUI(panel1);
        //wait for user to get the item
        Wait wait = new Wait();
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.HidePanel(panel1);
                wait.latch.countDown();
            }
        });
        
        wait.waitFor();
    }
    
}