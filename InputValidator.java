import java.awt.Toolkit;
import java.util.Stack;

public class InputValidator {
    Calculator calc;
    IOManager io;
    Controller cont;
    Stack<String> stack = new Stack<String>();
    boolean opChange = false;

    public InputValidator(Calculator calc, IOManager io, Controller cont) {
        this.calc = calc;
        this.io = io;
        this.cont = cont;
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
        else 
        {
            if (!opChange)
                Toolkit.getDefaultToolkit().beep();
            opChange = false;
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
        if (calc.isOperator(in) && stack.empty())
            return false;
        if (calc.isBinaryOperator(in) && calc.isBinaryOperator(stack.peek())){
            if (!in.equals("=") && !in.equals(stack.peek())){
                System.out.println("dd");
                stack.pop();
                stack.push(in);
                calc.operator = in;
                io.changeOp(in);
                opChange = true;
            }
            return false;
        }
        if (!stack.empty() && calc.isUrinaryOperator(stack.peek()) && !calc.isOperator(in))
            return false;
        if (!stack.empty())
            System.out.println(stack.peek() + "  " + in);
        if (calc.isUrinaryOperator(in) && !stack.empty() && calc.isBinaryOperator(stack.peek()))
            return false;
        if (!stack.empty() && stack.peek().equals(".") && calc.isOperator(in))
            return false;
        if (!stack.empty() && stack.peek().equals(".") && in.equals("."))
            return false;
        if (io.getDisplayedString().contains(".") && in.equals("."))
            return false;
        if (io.getDisplayedString().equals("0") && in.equals("0"))
            return false;
        return true;
    }
    
    enum ERRORS {
        // TODO: implement error types
    }
}
