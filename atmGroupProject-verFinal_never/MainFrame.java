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

public class MainFrame extends JFrame implements ActionListener{
    public JFrame frame = new JFrame();
    private JTextArea menuOptionText = new JTextArea();
    public JPanel MainMenu = new JPanel();
    private JPanel numPadPanel = new JPanel();
    JPanel displayPanel = new JPanel();    
    JLabel balanceInformationLabel =
        new JLabel(" Balance Information (\n \"EXIT\" to return to MainMenu):");
    JPanel MenuPad_L = new JPanel();
    JPanel MenuPad_R = new JPanel();
    JButton cR;
    JButton cD;
    
    //public JScrollPane pane;
    
    public Numpad numpad = new Numpad(frame);
    
    //-------------Format Data---------------------
    LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    Color BackgroundColor = new Color(0, 128, 255);
    Color ButtonBackgroundColor = new Color(128, 128, 128);
    Color TextBackgroundColor = new Color(78, 176, 45);

    //---------------------------------------------
    CashDispenser cashDispenser = new CashDispenser();
    CardReader cardReader = new CardReader();
    
    JLabel typeLabel = new JLabel();
    JLabel availableLabel = new JLabel();
    JLabel totalLabel = new JLabel();
    JButton[] menuNumPad = new JButton[5];
    Font myFont = new Font("Arial", Font.BOLD, 25);
    public CountDownLatch latch;
    
    private int accountNumber; // indicates account involved
    public BankDatabase bankDatabase;
    public String accountType;
    public double availableBalance;
    public double totalBalance;
    public int userChoice = -1;
    
    MainFrame(){
        //--------------------FrameSetting--------------------------
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        /*pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
        
        //frame.getContentPane().add(pane);
        //pane.setBounds(100, 100, 10000, 10000);
        //pane.setLayout(null);
        frame.getContentPane().add(numPadPanel);
        frame.getContentPane().add(MenuPad_L);
        frame.getContentPane().add(MenuPad_R);
        frame.getContentPane().add(cashDispenser.getCashDispenser());
        frame.getContentPane().add(cardReader.getCardReaderPanel());
        

        //--------------------menuNumPad--------------------------
        menuNumPad[1] = new JButton("A");
        menuNumPad[2] = new JButton("B");
        menuNumPad[3] = new JButton("C");
        menuNumPad[4] = new JButton("D");
        menuNumPad[0] = new JButton("EXIT");

        menuNumPad[1].setBackground(ButtonBackgroundColor);
        menuNumPad[1].setForeground(new Color(255, 255, 255));        
        menuNumPad[1].setFont(new Font("Arial", Font.BOLD, 20));
        menuNumPad[1].setBounds(10, 302, 106, 46);
    
        menuNumPad[3].setBackground(ButtonBackgroundColor);
        menuNumPad[3].setForeground(new Color(255, 255, 255));
        menuNumPad[3].setFont(new Font("Arial", Font.BOLD, 20));
        menuNumPad[3].setBounds(10, 411, 106, 46);
    
        menuNumPad[4].setBackground(ButtonBackgroundColor);
        menuNumPad[4].setForeground(new Color(255, 255, 255));
        menuNumPad[4].setFont(new Font("Arial", Font.BOLD, 20));
        menuNumPad[4].setBounds(10, 411, 106, 46);
    
        menuNumPad[2].setBackground(ButtonBackgroundColor);
        menuNumPad[2].setForeground(new Color(255, 255, 255));
        menuNumPad[2].setFont(new Font("Arial", Font.BOLD, 20));
        menuNumPad[2].setBounds(10, 302, 106, 46);
    
        menuNumPad[0].addActionListener(this);
        menuNumPad[0].setFont(new Font("Arial", Font.BOLD, 20));
        menuNumPad[0].setForeground(new Color(255, 255, 255));
        menuNumPad[0].setFocusable(false);
        menuNumPad[0].setBackground(ButtonBackgroundColor);
        menuNumPad[0].setBounds(10, 520, 106, 46);
        
        for(int i = 1; i <= 4; i++) {
            menuNumPad[i].addActionListener(this);
            menuNumPad[i].setFocusable(false);
        }//set Button
        
        //--------------------numPadPanel--------------------------
        /*numPadPanel.setBackground(ButtonBackgroundColor);
        numPadPanel.setBounds(0, 629, 492, 331);
        numPadPanel.setLayout(null);*/
        cR = cardReader.cR;
        cD = cashDispenser.cD;
        //--------------------menuNumPadPanel--------------------------
        MenuPad_L.setLayout(null);
        MenuPad_L.setBackground(new Color(0, 128, 128));
        MenuPad_L.setBounds(0, 0, 126, 629);
    
        MenuPad_R.setLayout(null);
        MenuPad_R.setBackground(new Color(0, 128, 128));
        MenuPad_R.setBounds(856, 0, 126, 629);
    
        //-----------addingMenuNumPad--------------
        MenuPad_L.add(menuNumPad[1]);
        MenuPad_R.add(menuNumPad[2]);
        MenuPad_L.add(menuNumPad[3]);
        MenuPad_R.add(menuNumPad[4]);
        MenuPad_R.add(menuNumPad[0]);
        
        //--------------------MainMenuPanel--------------------------
        MainMenu.setBackground(BackgroundColor);
        MainMenu.setBounds(124, 0, 734, 629);
        MainMenu.setLayout(null);
        MainMenu.setVisible(false);
        MainMenu.setEnabled(false);
        frame.getContentPane().add(MainMenu);
        
        //--------------------BalancePanel--------------------------
        displayPanel.setBackground(BackgroundColor);
        displayPanel.setBounds(124, 0, 734, 629);
        displayPanel.setLayout(null);
        frame.getContentPane().add(displayPanel);
        displayPanel.setVisible(false);
        displayPanel.setEnabled(false);
        
        //--------------------MainMenuLabel--------------------------
        JLabel mainMenuLabel = new JLabel("MainMenu:");
        mainMenuLabel.setBorder(textBorder);
        mainMenuLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        mainMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainMenuLabel.setBackground(TextBackgroundColor);
        mainMenuLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mainMenuLabel.setForeground(new Color(255, 255, 255));
        mainMenuLabel.setBounds(10, 26, 714, 80);
        mainMenuLabel.setOpaque(true);
        MainMenu.add(mainMenuLabel);
        
        //--------------------BalanceLabel--------------------------
        balanceInformationLabel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        balanceInformationLabel.setBackground(TextBackgroundColor);
        balanceInformationLabel.setFont(new Font("Arial", Font.BOLD, 25));
        balanceInformationLabel.setBounds(10, 27, 632, 49);
        balanceInformationLabel.setForeground(new Color(255, 255, 255));
        balanceInformationLabel.setOpaque(true);
        displayPanel.add(balanceInformationLabel);
        
        typeLabel.setBackground(TextBackgroundColor);
        typeLabel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        typeLabel.setForeground(new Color(255, 255, 255));
        typeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        typeLabel.setBounds(69, 107, 632, 49);
        typeLabel.setOpaque(true);
        displayPanel.add(typeLabel);
        
        availableLabel.setBackground(TextBackgroundColor);
        availableLabel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        availableLabel.setForeground(Color.WHITE);
        availableLabel.setFont(new Font("Arial", Font.BOLD, 25));
        availableLabel.setBounds(69, 222, 632, 49);
        availableLabel.setOpaque(true);
        displayPanel.add(availableLabel);
        
        totalLabel.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        totalLabel.setBackground(TextBackgroundColor);
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 25));
        totalLabel.setBounds(69, 337, 632, 49);
        totalLabel.setOpaque(true);
        displayPanel.add(totalLabel);
        
        //--------------------MainMenuTextArea-----------------------
        menuOptionText.setBorder(textBorder);
        menuOptionText.setForeground(new Color(255, 255, 255));
        menuOptionText.setText(" - View My Balance -                 - Withdraw Cash - \r\n\r" + 
            "\n\r\n - Fund Transfer -\r\n\r\n\r\n" +
            "                                                                    - Exit - ");
        menuOptionText.setEditable(false);
        menuOptionText.setFont(new Font("Arial", Font.BOLD, 30));
        menuOptionText.setRows(5);
        menuOptionText.setBackground(TextBackgroundColor);
        menuOptionText.setBounds(10, 302, 714, 273);
        MainMenu.add(menuOptionText);
    }
            
    //-------------------Show_GUI------------------------
    public void showGUI(){
        frame.setVisible(true);
        frame.setEnabled(true);
    }
    
    //-------------------Close_GUI------------------------
    public void closeGUI(){
        frame.setVisible(false);
        frame.setEnabled(false);
    }
    
    //-------------------Hide_MainScreen-----------------
    public void HidePanel(JPanel panel){
        panel.setVisible(false); //hide the panel
        panel.setEnabled(false); //disable the panel
    }
    
    //-------------------Show_Panel-----------------
    public void ShowPanel(JPanel panel){
        panel.setVisible(true); //show the panel
        panel.setEnabled(true); //enable the panel
    }
    
    //-------------------Get_User_Data_From_Database-----------------
    public void getUserData(int userAccountNumber, BankDatabase atmBankDatabase){
        accountNumber = userAccountNumber;
        bankDatabase = atmBankDatabase;
        accountType = bankDatabase.getAccountType( accountNumber );
        availableBalance = bankDatabase.getAvailableBalance( accountNumber );
        totalBalance = bankDatabase.getTotalBalance( accountNumber );
    }
    
    //-------------------UpdateBalanceText-----------------
    public void UpdateBalance(){
        typeLabel.setText(" - Account Type: " + accountType); //Show the Account Type
        availableLabel.setText(" - Available balance: HKD " + availableBalance);
        totalLabel.setText(" - Total Balance: HKD " + totalBalance);
    }
    
    //------getUserChoiceInput-------
    public int getChoice(){
        return userChoice;
    }
    
    public void waitFor()
    {
        try
        {
            latch.await();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    //-------------------MenuPad-----------------
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            if(((JButton) e.getSource()).getText() =="EXIT"){
                userChoice = 0;
                latch.countDown();
            }else if(((JButton) e.getSource()).getText() =="A"){
                String temp = "1";
                userChoice = Integer.parseInt(temp);
                latch.countDown();
            }else if(((JButton) e.getSource()).getText() =="B"){
                String temp = "2";
                userChoice = Integer.parseInt(temp);
                latch.countDown();
            }else if(((JButton) e.getSource()).getText() =="C"){
                String temp = "3";
                userChoice = Integer.parseInt(temp);
                latch.countDown();
            }else if(((JButton) e.getSource()).getText() =="D"){
                String temp = "4";
                userChoice = Integer.parseInt(temp);
                latch.countDown();
            }
        }    
    }
    
    public void setNumpad(Numpad numpad)
    {
        this.numpad = numpad;
    }
    
    public Numpad getNumpad()
    {
        return numpad;
    }
    public void setLatch()
    {
        latch = new CountDownLatch(1);
    }
}
