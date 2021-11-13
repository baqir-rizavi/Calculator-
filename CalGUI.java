
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CalGUI extends JFrame {

    JPanel btnArea, ioArea, numPad, operatorPad;
    JButton[] numBtn, opBtn;
    JLabel inOut;
    JLabel expression;
    
    public CalGUI() {
        initGUI();
    }
    
    public void initGUI(){
        setFrame();
        setIoArea();
        setBtnArea();
        addComponents();
        setSize(new Dimension(500, 400));
    }
    
    private void setOperatorPad(){
        operatorPad = new JPanel(new GridLayout(5,2));
        opBtn = new JButton[10];
        operatorPad.add(opBtn[0] = new JButton("/"));
        operatorPad.add(opBtn[1] = new JButton("sqrt"));
        operatorPad.add(opBtn[2] = new JButton("x"));
        operatorPad.add(opBtn[3] = new JButton("-"));
        operatorPad.add(opBtn[4] = new JButton("+"));
        operatorPad.add(opBtn[5] = new JButton("%"));
        operatorPad.add(opBtn[8] = new JButton("neg"));
        operatorPad.add(opBtn[7] = new JButton("rec"));
        operatorPad.add(opBtn[9] = new JButton("<="));
        operatorPad.add(opBtn[6] = new JButton("="));

    }
    
    private void setFrame(){
        setTitle("CALCULATOR v1.0.0");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));
    }
    
    private void addComponents(){
        add(ioArea);
        add(btnArea);
    }
    
    private void setBtnArea(){
        btnArea = new JPanel(new GridLayout(1, 2));
        setNumpad();
        setOperatorPad();
        btnArea.add(numPad);
        btnArea.add(operatorPad);
        
    }
    
    private void setNumpad(){
        numPad = new JPanel(new GridLayout(4,3));
        numBtn = new JButton[12];
        numPad.add(numBtn[0] = new JButton("7"));
        numPad.add(numBtn[1] = new JButton("8"));
        numPad.add(numBtn[2] = new JButton("9"));
        numPad.add(numBtn[3] = new JButton("4"));
        numPad.add(numBtn[4] = new JButton("5"));
        numPad.add(numBtn[5] = new JButton("6"));
        numPad.add(numBtn[6] = new JButton("1"));
        numPad.add(numBtn[7] = new JButton("2"));
        numPad.add(numBtn[8] = new JButton("3"));
        numPad.add(numBtn[9] = new JButton("."));
        numPad.add(numBtn[10] = new JButton("0"));
        numPad.add(numBtn[11] = new JButton("CLS"));

    }
    
    private void setIoArea(){
        ioArea = new JPanel(new GridLayout(2, 1));
        ioArea.add(expression = new JLabel());
        ioArea.add(inOut = new JLabel());
        expression.setFont(new Font(null, WIDTH, 15));
        inOut.setFont(new Font(null, WIDTH, 22));        
    }
    
}
