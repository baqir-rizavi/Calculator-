import java.awt.Toolkit;


public class IOManager {
    CalGUI gui;
    Controller cont;

    public IOManager(CalGUI gui, Controller cont) {
        this.gui = gui;
        this.cont = cont;
    }
    
    void sendToDisplay(String str){
        if (checkLimit())
            gui.inOut.setText(gui.inOut.getText().concat(str));
        else {
            Toolkit.getDefaultToolkit().beep();
            System.out.println("number limit reached");
            cont.resetCalc();
        }
    }
    
    void sendToExpression(String str){
        gui.expression.setText(gui.expression.getText().concat(str));
    }
    
    void clearDisplay(){
        gui.inOut.setText("");
    }
    
    void clearExpression(){
        gui.expression.setText("");
    }
    
    double getDisplayedNumber()
    {
        return Double.parseDouble(gui.inOut.getText());
    }
    
    String getDisplayedString()
    {
        return gui.inOut.getText();
    }
    
    String getExpression(){
        return gui.expression.getText();
    }
    
    void changeOp(String op){
        if (cont.calc.isBinaryOperator(String.valueOf(gui.expression.getText().charAt(gui.expression.getText().length() - 1)))){
            String s = gui.expression.getText().substring(0, gui.expression.getText().length() - 1);
            clearExpression();
            sendToExpression(s+op);   
        }
    }
    
    boolean checkLimit(){
        return gui.inOut.getText().length() < 299;        
    }
}
