
import javax.swing.JButton;



public class Controller {

    CalGUI gui;
    IOManager io;
    Calculator calc;
    btnHandler hnd;
    InputValidator valid;
    
    public Controller() {
        gui = new CalGUI();
        io = new IOManager(gui);
        calc = new Calculator(io);
        valid = new InputValidator(calc, io);
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
    
}
