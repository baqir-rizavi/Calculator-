import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class btnHandler implements ActionListener {

    InputValidator input;
    Controller cont;
    boolean clearOnNext = false;

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
        
        if (clearOnNext)
        {
            cont.resetCalc();
            clearOnNext = false;
        }
        else
        {
            if (e.getActionCommand().equals("="))
                clearOnNext = true;
        
            if (e.getActionCommand().equals("CLS"))
                cont.resetCalc();
            else 
                input.validateAndSend(e.getActionCommand());
        }
    }
    
}
