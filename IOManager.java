
public class IOManager {
    CalGUI gui;

    public IOManager(CalGUI gui) {
        this.gui = gui;
    }
    
    void sendToDisplay(String str){
        gui.inOut.setText(gui.inOut.getText().concat(str));
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
}
