import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
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

public class AdvancedOptionGUI {
    private JTextField textField;
    private int typeAmount;
    public Numpad numpad;
    public MainFrame mainFrame;
    private CountDownLatch latch;
    //setting of panel
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color TextBackgroundColor = new Color(78, 176, 45);
    Color BackgroundColor = new Color(0, 128, 255);
    Font WordFont = new Font("Arial", Font.BOLD, 25);
    JPanel panel;

    public AdvancedOptionGUI(Numpad numpad, MainFrame mainFrame) {
        createAndShowGUI(numpad, mainFrame);
    }

    private void createAndShowGUI(Numpad numpad, MainFrame mainFrame) {
        this.numpad = numpad;
        this.mainFrame = mainFrame;

        // Create the panel
        panel = new JPanel();
        panel.setBounds(124, 0, 734, 629);
        panel.setBackground(new Color(0, 128, 255));
        panel.setLayout(null);

        // Create the label
        JTextArea label = new JTextArea(" Please Input the withdrawal amount that is the"
        + "\n multiple of 100, 500, or 1000" +
        "\n [Press \"Cancel\" to return to the Withdrawal Menu]");
        label.setBounds(59, 60, 614, 141);
        label.setBackground(TextBackgroundColor);
        label.setForeground(new Color(255, 255, 255));
        label.setFont(WordFont);
        label.setBorder(textBorder);
        label.setEditable(false);
        
        panel.add(label);

        // Create the text field
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(59, 404, 614, 55);
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(WordFont);
        textField.setBorder( new LineBorder(new Color(0, 0, 0), 3));
        panel.add(textField, BorderLayout.CENTER);
        
        numpad.setCurrTextField(textField);
        mainFrame.frame.add(panel);
        SwingUtilities.updateComponentTreeUI(panel);
        //hide the panel by defalft
            mainFrame.HidePanel(panel);
            latch = new CountDownLatch(1);
        }
    //process the user input into useful value and ensure the input is vaild
        public String getTypeAmount() {
        try {
  
            numpad.forceClear();
            numpad.waitInput();
        } catch (Exception e) {
            e.printStackTrace();
            numpad.forceClear();
        }
        String temp = numpad.getTextToOutput();
        //hide the panel 
        mainFrame.HidePanel(panel);
        return temp;
    }
    public JTextField getTextField(){
        return textField;
    }
    //call the panel
    public JPanel getPanel(){
        return panel;
    }
    }
