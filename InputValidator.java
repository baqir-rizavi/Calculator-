
import java.util.Stack;

public class InputValidator {
    Calculator calc;
    IOManager io;
    private Stack<String> stack = new Stack<String>();
    

    public InputValidator(Calculator calc, IOManager io) {
        this.calc = calc;
        this.io = io;
    }
    
    public void clear()
    {
        stack.clear();
    }
    
    public void validateAndSend(String str){
        if (validate(str)) {
            stack.push(str);
            sendtoIO(str);
            calc.process(str);
        }
        // invalid input shall never be displayed or processed
    }
    
    public void sendtoIO(String str){
        if (calc.clearOnNext){
            io.clearDisplay();
            calc.clearOnNext = false;
        }
        if (calc.isOperator(str)) {
            io.sendToExpression(str);
        }
        else {
            io.sendToDisplay(str);
            io.sendToExpression(str);
        }
        
    }
    
    public boolean validate(String in){
        if (calc.isBinaryOperator(in) && stack.empty())
            return false;
        if (calc.isBinaryOperator(in) && calc.isOperator(stack.peek()))
            return false;
        if (calc.isUrinaryOperator(in) && !stack.empty() && !calc.isOperator(stack.peek()))
            return false;
        
        return true;
    }
    enum ERRORS {
        
    }
}
