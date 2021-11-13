import java.awt.Toolkit;
import javax.swing.JButton;

public class Controller {

    CalGUI gui;
    IOManager io;
    Calculator calc;
    btnHandler hnd;
    InputValidator valid;
    
    public Controller() {
        gui = new CalGUI();
        io = new IOManager(gui, this);
        calc = new Calculator(io, this);
        valid = new InputValidator(calc, io, this);
        hnd = new btnHandler(valid, this);
        attachHandler();
    }
    
    public void attachHandler(){
        for (JButton btn : gui.numBtn) {
            btn.addActionListener(hnd);
        }
        for (JButton btn : gui.opBtn) {
            btn.addActionListener(hnd);
        }
    }
    public void resetCalc(){
        io.clearDisplay();
        io.clearExpression();
        calc.clear();
        valid.clear();
        hnd.clear();
    }
    
    public void eraseCurrent(){
        if (io.getDisplayedString().matches(".*[a-zA-Z]+.*")){
            resetCalc();
            return;
        }
        if (valid.stack.empty())
        {
            resetCalc();
            return;
        }
        if (calc.isBinaryOperator(valid.stack.peek()))
        {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        int i = 0;
        int index = io.getExpression().length() - 1;
        while (!(index - i < 0) && !calc.isBinaryOperator(String.valueOf(io.getExpression().charAt(index - i)))){    
            i++;
        }
        int length = io.getDisplayedString().length();
        io.clearDisplay();
        String s = io.getExpression().substring(0, index - i + 1);
        if (io.getExpression().length() > index - i && index - i >= 0){
            char op = io.getExpression().charAt(index - i);
            System.out.println("op is " + op);
            io.clearExpression();
            io.sendToExpression(s);
            if (!valid.stack.empty())
            {
                while (!valid.stack.peek().equals(String.valueOf(op)))
                {
                    System.out.println("hehe: " + valid.stack.peek()); 
                    valid.stack.pop();
                }
            }
        }
        else 
        {
            resetCalc();
        }
    }
    
    public boolean clearWithResultant(String operator){
        if (calc.isOperator(operator) && !operator.equals("=")){
            String op1 = io.getDisplayedString();
            resetCalc();
            int i = 0;
            if (op1.charAt(0) == '-')
                i++;
            while (i < op1.length()){
                valid.validateAndSend(String.valueOf(op1.charAt(i)));
                i++;
            }
            if (op1.charAt(0) == '-')
                valid.validateAndSend("neg");
            valid.validateAndSend(operator);
        } 
        else if (!operator.equals("=")) 
        {
            resetCalc();
            hnd.clearWithResultantOnNext = false;
            return false;
        }
        else {
            resetCalc();
            Toolkit.getDefaultToolkit().beep();
        }
        return true;
    }
    
}