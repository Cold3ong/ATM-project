import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
public class LoginPanel implements ActionListener
{
    public JFrame frame;
    public JPasswordField passwordField;
    
    private JPanel LoginGUI;
    private JLabel Loginlabel_1;
    private JLabel LoginLabel_2;
    private JTextArea LoginText;
    private final String CardName[] = {"12345","98765","24689","11111","22222"};
    private JButton Account[];
    private int PinNumber, AccountNumber;
    private boolean Login, CheckAccount, CheckPin;
    private String tempforAcc;
    private LineBorder textBorder = new LineBorder(new Color(255, 255, 255), 3);
    private Color BackgroundColor = new Color(0, 128, 255);
    
    private Wait wait;

    private MainFrame mainMenu;
    private Numpad numpad;

    public JPanel takeCardPanel;
    public JTextArea txtrTakeCard;

    LoginPanel(MainFrame mainMenu)
    {
        this.mainMenu = mainMenu;
        frame = mainMenu.frame;
        numpad = mainMenu.numpad;
        LoginGUI = new JPanel();
        LoginGUI.setBackground(BackgroundColor);
        LoginGUI.setBounds(124, 0, 734, 629);
        LoginGUI.setLayout(null);
        LoginGUI.setVisible(true);
        LoginGUI.setEnabled(true);
        frame.getContentPane().add(LoginGUI);

        //--------------------LoginLabel--------------------------
        Loginlabel_1 = new JLabel(" Welcome");
        Loginlabel_1.setBorder(textBorder);
        Loginlabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        Loginlabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        Loginlabel_1.setBackground(new Color(78, 176, 45));
        Loginlabel_1.setFont(new Font("Arial", Font.BOLD, 40));
        Loginlabel_1.setForeground(new Color(255, 255, 255));
        Loginlabel_1.setBounds(10, 26, 714, 80);
        Loginlabel_1.setOpaque(true);
        LoginGUI.add(Loginlabel_1);
        
        LoginLabel_2 = new JLabel();
        LoginLabel_2.setForeground(new Color(255, 255, 255));
        LoginLabel_2.setFont(new Font("Arial", Font.BOLD, 30));
        LoginLabel_2.setBounds(57, 181, 630, 73);
        LoginGUI.add(LoginLabel_2);

        //--------------------LoginButton--------------------------
        Account = new JButton[5];

        for(int i=0; i<CardName.length; i++){
            Account[i]= new JButton(CardName[i]);
            Account[i].addActionListener(this);
            Account[i].setFont(new Font("Arial", Font.BOLD, 30));
            Account[i].setBounds(20+i*140, 372, 129, 48);
            LoginGUI.add(Account[i]);
        }

        //--------------------loginTextArea-----------------------
        LoginText = new JTextArea(" Please Insert Your Account Card");
        LoginText.setForeground(new Color(255, 255, 255));
        LoginText.setFont(new Font("Arial", Font.BOLD, 30));
        LoginText.setBounds(47, 130, 640, 57);
        LoginText.setBorder(textBorder);
        LoginText.setBackground(new Color(78, 176, 45));
        LoginText.setOpaque(true);
        LoginText.setEditable(false);
        LoginGUI.add(LoginText);

        //--------------------passwordField_1-----------------------
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        passwordField.setBounds(47, 272, 626, 42);
        passwordField.setVisible(false);
        passwordField.setEditable(false);
        LoginGUI.add(passwordField);
        
        //-------------------takeCardPanel----------------------------
        takeCardPanel = new JPanel();
        takeCardPanel.setBounds(124, 0, 734, 629);
        takeCardPanel.setLayout(null);
        takeCardPanel.setBackground(new Color(0, 128, 255));
        takeCardPanel.setVisible(false);
        takeCardPanel.setEnabled(false);

        txtrTakeCard = new JTextArea(" Please take your Card now.");
        txtrTakeCard.setFont(new Font("Arial", Font.BOLD, 30));
        txtrTakeCard.setBounds(10, 200, 714, 273);
        txtrTakeCard.setBorder(new LineBorder(new Color(255, 255, 255), 3));
        txtrTakeCard.setForeground(new Color(255, 255, 255));
        txtrTakeCard.setBackground(new Color(78, 176, 45));
        txtrTakeCard.setOpaque(true);
        txtrTakeCard.setEditable(false);
        takeCardPanel.add(txtrTakeCard);

        mainMenu.frame.add(takeCardPanel);
        SwingUtilities.updateComponentTreeUI(takeCardPanel);

    }

    //--------------------LoginButtonAction--------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==Account[0]){
            ConfirmAccountMessage(Account[0]);
            
        }
        else if (e.getSource()==Account[1]){
            ConfirmAccountMessage(Account[1]);
            
        }
        else if (e.getSource()==Account[2]){
            ConfirmAccountMessage(Account[2]);
            
        }
        else if (e.getSource()==Account[3]){
            ConfirmAccountMessage(Account[3]);
            
        }
        else if (e.getSource()==Account[4]){
            ConfirmAccountMessage(Account[4]);
            
        }
    }

    public JPanel getLoginlabel_1(){
        return LoginGUI;
    }

    //-------------------Show_Panel------------------------
    public void ShowPanel(JPanel panel){
        panel.setVisible(true);
        panel.setEnabled(true);
    }

    //-------------------Confirm_Account#------------------------
    private void ConfirmAccountMessage(JButton Button) {
        LoginText.setBounds(47, 130, 640, 150);
        LoginText.setText(" Are you sure account number is " + Button.getText() + "?\n Press \"A\" to continue or \"EXIT\" to \n eject you card.");

        SwingUtilities.updateComponentTreeUI(LoginText);
        tempforAcc = Button.getText();

        for(int i=0;i < CardName.length;i++)
        {
            Account[i].setVisible(false);
            Account[i].setEnabled(false);
        }
        CheckAccount = true;
    }

    public void ConfirmAccount2() {
        int choice =-1;
        mainMenu.userChoice = -1;
        //When Choice Not (1 XOR 0) loop until user input
        while(choice !=1 &&choice !=0)
        {
            mainMenu.setLatch();
            choice = mainMenu.userChoice;
            //Stop the loop for 0.1s in order to let the user input successfully
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
        if (choice == 0){
            getCard();
        }
        else if (choice == 1){ // Change insert PIN_Nunber
            setAccount(tempforAcc);
            CheckAccount =false;
            LoginLabel_2.setText(" Please enter your PIN");
            openPinGUI();
        }
    }

    //-------------------set_Account_Number------------------------
    private void setAccount(String Acc){
        AccountNumber = Integer.valueOf(Acc);
    }

    //-------------------set_PinNumber------------------------
    private void setPinNumber( String Pw){
        PinNumber = Integer.valueOf(Pw);
        Login = true;
    }

    //-------------------get_Account_Number------------------------
    public int getAccount(){
        return AccountNumber;
    }

    //-------------------get_Sin_Number------------------------
    public int getPinNumber(){
        return PinNumber;
    }

    //-------------------get_Login------------------------
    public boolean getLogin(){
        return Login;
    }
    
    //-------------------set_Check_Account------------------------
    public void setLogin( boolean login){
        Login =login;
    }
    
    //-------------------get_Check_Account------------------------
    public boolean getCheckAccount(){
        return CheckAccount;
    }
    
    //-------------------set_CheckPin------------------------
    public void setCheckAccount( boolean Checkacc){
        CheckAccount =Checkacc;
    }

    //-------------------get_CheckPin------------------------
    public boolean getCheckPin(){
        return CheckPin;
    }
    
    //-------------------set_Login------------------------
    public void setCheckPin( boolean Checkpin){
        CheckPin =Checkpin;
    }
    
    //-------------------set_Pin_Number------------------------
    public void RewritePin(){
        numpad.setNextStage(false);
        LoginLabel_2.setText(" Invalid account PIN. Please try again.");
        Login = false;
        numpad.CheckPw = true;
        CheckPin = true;
    }

    //-------------------get_Card------------------------
    public void getCard(){
        LoginLabel_2.setText(null);
        
        passwordField.setEditable(false);
        passwordField.setVisible(false);
        
        LoginGUI.setVisible(false);
        LoginGUI.setEnabled(false);
        AccountNumber=0;
        PinNumber=0;
        Login = false;
        
        takeCardPanel(mainMenu.cR);
        
        cardTook();
    }

    public void openPinGUI()
    {
        String tempPw;
        passwordField.setVisible(true);
    
        LoginText.setBounds(47, 130, 640, 57);
        LoginText.setText(" Your account number is " + AccountNumber);
        SwingUtilities.updateComponentTreeUI(LoginText);
        
        numpad.setNumpadEnable();
        numpad.setCurrPwField(passwordField);

        while (true){
            numpad.CheckPw = true;
            tempPw = numpad.getPwInput(passwordField);//getpassword from numpad

            if(numpad.getNextStage()){
                setPinNumber(tempPw);
                numpad.CheckPw = false;
                break;
            }

            else if (numpad.Checkcan)
            {
                numpad.Checkcan =false;
                getCard();
                break;
            }

        }
    }

    public void openLogin()
    {
        LoginGUI.setVisible(true);
        LoginGUI.setEnabled(true);
        LoginText.setBounds(47, 130, 640, 57);
        SwingUtilities.updateComponentTreeUI(LoginText);
    }

    public void closeLogin()
    {
        LoginGUI.setVisible(false);
        LoginGUI.setEnabled(false);
    }

    public void resetLogin()
    {
        passwordField.setEditable(false);
        passwordField.setVisible(false);
        passwordField.setText(null);
        
        AccountNumber=0;
        PinNumber=0;
        
        LoginText.setText(" Please Insert Your Account Card");

        Login= false;
        CheckAccount = false;
        CheckPin = false;
        tempforAcc= null;
        numpad.setNextStage(false);
        numpad.setNumpadDisable();
        
        for(int i=0;i<CardName.length ;i++){
            Account[i].setVisible(true);
            Account[i].setEnabled(true);
        }
        
        LoginLabel_2.setText(null);
    }

    public void takeCardPanel(JButton button)
    {
        takeCardPanel.setVisible(true);
        takeCardPanel.setEnabled(true);
        txtrTakeCard.setText(" Please take your Card now.");
        
        wait = new Wait();
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wait.latch.countDown();
            }
        });
        
        wait.waitFor();
    }
    
    public void cardTook()
    {
        txtrTakeCard.setText(" Card Took");
        SwingUtilities.updateComponentTreeUI(txtrTakeCard);
        
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        takeCardPanel.setVisible(false);
        takeCardPanel.setEnabled(false);
        
        resetLogin();
    }
}