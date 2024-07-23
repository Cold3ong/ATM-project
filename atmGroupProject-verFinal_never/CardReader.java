import javax.swing.*;
import java.awt.*;

public class CardReader {
    //create instance for card reader
    private JPanel CardReader;
    private boolean cardInject = false;
    public JButton cR;
    private boolean cardCanEnject = false;
    public CardReader(){
        genCardReader();
    }
    //creat panel and button for card reader
    public void genCardReader(){
        CardReader = new JPanel();
        CardReader.setLayout(null);
        CardReader.setVisible(true);
        CardReader.setBounds(492, 629, 490, 165);
        CardReader.setBackground(new Color(255, 128, 64));
        cR = new JButton("Card Reader");
        cR.setBounds(84, 64, 351, 31);
        cR.setFocusable(false);
        CardReader.add(cR);
        SwingUtilities.updateComponentTreeUI(CardReader);
    }
    //to set if the card injected
    public void setCardInject(boolean x){
        cardInject =x;
    }
    //to get the status of the card reader
    public boolean getCardInject(){
        return cardInject;
    }
    //to get the panel of the card reader
    public JPanel getCardReaderPanel(){
        return CardReader;
    }



}
