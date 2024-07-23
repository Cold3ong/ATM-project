import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Numpad implements ActionListener {
    //store the text temporarily
    private String text;
    //the textfield to be input
    private JTextField currTextField;
    //create button
    private JButton button[];
    //store the text of input
    private String textToOutput;
    //let other class to manage for loop
    private boolean nextStage;
    //count how many times of "." used
    private int count =0;
    //stop the while loop to wait for the input
    private Wait wait;
    private ATM atm;
    private int counterAfterdot=0;
    private String afterdot;

    //for login page
    //check if it is in login page
    public boolean CheckPw;
    //
    public boolean Checkcan;
    //password field
    private JPasswordField currPwField;
    //store the passwordToOutput
    private String passwordToOutput;

    //numpad constructor
    public Numpad(JFrame mainFrame){
        this.textToOutput = "";
        this.nextStage = false;
        //link the mainframe with the numpad
        this.genKeypadPanel(mainFrame);
    }
    //generate keypad panel
    private void genKeypadPanel(JFrame mainFrame){
        //create panel for the button and link it with mainframe
        JPanel myPanel = new JPanel();
        myPanel.setBackground(new Color(161, 175, 201));
        myPanel.setBounds(0, 629, 492, 331);
        myPanel.setVisible(true);
        myPanel.setLayout(new GridLayout(4,4));
        mainFrame.getContentPane().add(myPanel);
        SwingUtilities.updateComponentTreeUI(myPanel);

        //Create buttons
        button = new JButton[15];
        //-------------set specific name for special input------------
        button[11] = new JButton("CANCEL");
        button[7] = new JButton("CLEAR");
        button[3] = new JButton("ENTER");
        button[3].setEnabled(false);
        button[2] = new JButton(".");
        button[1] = new JButton("0");
        button[0] = new JButton("00");
        //-------------set different number to buttons------------
        for(int i=14,j=0;i>=12;i--,j+=2){
            int temp = j;
            button[i] = new JButton(Integer.toString(i-(7-temp)));
        }
        for(int i=10,j=0;i>=8;i--,j+=2){
            int temp = j;
            button[i] = new JButton(Integer.toString(i-(6-temp)));
        }
        for(int i=6,j=0;i>=4;i--,j+=2){
            int temp = j;
            button[i] = new JButton(Integer.toString(i-(5-temp)));
        }

        //-------------set action listener and size to buttons------------
        for(int i=14, k=0,x=0,y=0;i>=0 && k<=3;){
            button[i].setBounds(x,y,160,180);
            button[i].addActionListener(this);
            x+=160;
            i--;
            if(i==11||i==7||i==3){
                x=0;
                y+=180;
                k++;
            }
        }
        //-------------set font to buttons------------
        for(int i=14;i>=0;i--){
            button[i].setFocusable(false);
            button[i].setFont(new Font("Arial", Font.BOLD, 20));
            myPanel.add(button[i]);
        }
    }
    //------------override the action listener---------
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            text = ((JButton) e.getSource()).getText();
            //perform action when the "ENTER" is pressed
            if(((JButton) e.getSource()).getText() =="ENTER"){
                count=0;
                counterAfterdot=0;
                if(CheckPw)    //---------------------check if it is in login page--------------------------
                {
                    passwordToOutput = getPwInput(currPwField);    //-------------------------prepare the output -----
                    nextStage = true;    //-----------------------set next stage -------------------------
                    //clear the field after getting the input
                    setPw("",currPwField);
                }
                else{
                    //copy the text in textfield to textToOutput
                    textToOutput = getInput(currTextField);
                    //set nextStage to true
                    nextStage = true;
                    //clear the field after getting the input
                    setText("",currTextField);
                    wait.latch.countDown();
                }

            }else if(((JButton) e.getSource()).getText() =="CLEAR"){
                count =0;
                counterAfterdot =0;
                if(CheckPw)
                {
                    //clear all text in the textfield
                    setPWText("",currPwField, true);
                    return;
                }
                else{
                    //clear all text in the textfield
                    setText("",currTextField);
                    currPwField.getText();
                    button[3].setEnabled(false);
                }


            }else if(((JButton) e.getSource()).getText() =="CANCEL"){
                count =0;
                if(CheckPw)
                {
                    //set checkcan to true, break the loop
                    Checkcan =true;
                }
                else{
                    //set next stage to false, break the loop
                    nextStage = false;
                    //let the loop stop
                    wait.latch.countDown();
                }
                nextStage = false;
                wait.latch.countDown();
            }else if(((JButton) e.getSource()).getText() =="."&&count==0){ //to control only one "." will be input
                count++;
                String temp = currPwField.getText();
                //set the textfield
                temp = currTextField.getText();
                setText(temp+text,currTextField);

            }else if(((JButton) e.getSource()).getText() =="."&&count==1){

            }
            else {
                String temp;
                if(CheckPw)
                {
                    //store the value to passwordfield
                    temp = currPwField.getText();
                    setPWText(text, currPwField, false);
                    button[3].setEnabled(true);

                }
                else{ //store the value to passwordfield
                    if(count==1&&counterAfterdot>2){

                    }else if(count==1&&((JButton) e.getSource()).getText() =="00"){
                        
                    }
                    else if(count==1&&counterAfterdot<2){
                        
                        button[3].setEnabled(true);
                        temp = currTextField.getText();
                        setText(temp + text, currTextField);
                        counterAfterdot++;
                    }
                    else if(count==0){
                        
                        button[3].setEnabled(true);
                        temp = currTextField.getText();
                        setText(temp + text, currTextField);
                    }
                }


            }
        }
    }

    /*public void setText(String text, JLabel label){
        label.setText(text);
    }*/
    //set the text to specific textfield
    public void setText(String text, JTextField textField){
        textField.setText(text);
    }
    //get the input in specific textfield
    public String getInput(JTextField textField){
        return textField.getText();
    }
    //get the input in current textfield
    public String returnInput(){
        return currTextField.getText();
    }
    //get the nextStage boolean
    public boolean getNextStage(){
        return nextStage;
    }
    //get the output of the value
    public String getTextToOutput(){
        return textToOutput;
    }
    //set which textfield to be input
    public void setCurrTextField(JTextField x)
    {
        currTextField =x;
        button[3].setEnabled(false);
        button[2].setEnabled(true);
    }
    //get current textfield
    public JTextField getCurrTextField()
    {
        return currTextField;
    }
    public void waitInput(){
        wait = new Wait();
        this.wait.waitFor();
    }
    //clear the output
    public void forceClear(){
        button[3].setEnabled(false);
        textToOutput = "";
        text ="";
    }
    //set text to output with any string
    public void setTextToOutput(String x ){
        textToOutput = x;
    }
    //set which passwordfield to be input
    public void setCurrPwField(JPasswordField PWField){
        currPwField = PWField;
        button[3].setEnabled(false);
        button[2].setEnabled(false);
        CheckPw= true;
    }
    public void setCurrEmptyField(JPasswordField textField){
        currPwField = textField;
        
        for(int i =0;i<15;i++){
            button[i].setEnabled(false);
        }
        
    }
    //get the password input
    public String getPwInput(JPasswordField PWField){
        return PWField.getText();
    }
    //set the next stage
    public void setNextStage(boolean booleana){
        nextStage = booleana;
    }
    //set the text in password field
    public void setPWText(String text, JPasswordField PWField, boolean checkEnterCase){
        if(checkEnterCase)
            PWField.setText(null);
        else
        {
            PWField.setText(currPwField.getText()+text);
        }
    }
    //disable the numpad
    public void setNumpadDisable()
    {
        for (int i = 0; i < 15 ; i++)
        {
            button[i].setEnabled(false);
        }
    }
    //enable the numpad
    public void setNumpadEnable()
    {
        for (int i = 0; i < 15 ; i++)
        {
            button[i].setEnabled(true);
        }
    }
    public void setspecialNumpadEnable()
    {
        for (int i = 0; i < 15 ; i++)
        {
            button[i].setEnabled(true);
        }
    }
    //set the password to passwordfield
    public void setPw(String text, JPasswordField currPwField)
    {
        currPwField.setText(text);
    }

}
