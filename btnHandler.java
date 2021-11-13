import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnHandler implements ActionListener {

    InputValidator input;
    Controller cont;
    boolean clearOnNext = false;
    boolean clearWithResultantOnNext = false;

    public btnHandler(InputValidator input, Controller cont) {
        this.input = input;
        this.cont = cont;
    }
    
    public void clear()
    {
        clearOnNext = false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println(e.getActionCommand());
        
        if (clearWithResultantOnNext){
            clearWithResultantOnNext = false;
            if (cont.clearWithResultant(e.getActionCommand()))
                return;
        }
        
        if (clearOnNext) {
            cont.resetCalc();
            clearOnNext = false;
            if (cont.calc.isOperator(e.getActionCommand()))
                return;
        }
        
        if (e.getActionCommand().equals("="))
            clearWithResultantOnNext = true;
        
        if (e.getActionCommand().equals("<=")){
            cont.eraseCurrent();
            return;
        }
        
        if (e.getActionCommand().equals("CLS"))
            cont.resetCalc();
        else 
            input.validateAndSend(e.getActionCommand());
    }   
}
