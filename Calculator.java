import java.awt.Toolkit;

public class Calculator {
    IOManager io;
    Controller cont;
    String operator = "";
    private String operand1 = "";
    private String operand2 = "";
    private String result = "";
    boolean clearOnNext = false;

    public Calculator(IOManager io, Controller cont) {
        this.io = io;
        this.cont = cont;
    }
    
    void clear(){
        operator = "";
        operand2 = "";
        operand1 = "";
        result = "";
        clearOnNext = false;
    }
    
    public void process(String str){
        if (str.equals("=") && operand1.equals("")){
            String s = io.getDisplayedString();
            io.clearDisplay();
            io.clearExpression();
            io.sendToDisplay(s);
            return;
        }
        if (isBinaryOperator(str) && operand1.equals("")) { // first time input: do not solve
            result = operand1 = io.getDisplayedString();
            io.clearDisplay();
            operator = str;
            
        }
        else if (isBinaryOperator(str)){
            operand2 = io.getDisplayedString();
            io.clearDisplay();
            io.sendToDisplay(result = applyBinaryOperation());
            
            operand1 = result;
            clearOnNext = true;
            operator = str;
        }
        else if (isUrinaryOperator(str)){
            String s = io.getDisplayedString();
            io.clearDisplay();
            io.sendToDisplay(applyUniraryOperation(s, str));
        }
    }
    
    boolean isUrinaryOperator(String str){
        return (str.equals("rec") || str.equals("sqrt") || str.equals("neg"));
    }
    
    boolean isBinaryOperator(String str){
        return str.equals("x") || str.equals("/") || str.equals("-") || str.equals("+") 
                || str.equals("%") || str.equals("=");
    }
    
    boolean isOperator(String str){
        return isBinaryOperator(str) || isUrinaryOperator(str);
    }
    
    private String applyUniraryOperation(String str,  String op){
        //System.out.println(str);
        switch (op){
            case "rec":
                 if (str.equals("0")){
                    Toolkit.getDefaultToolkit().beep();
                    cont.hnd.clearOnNext = true;
                    return "divide by 0 not possible";
                }
                return Double.toString(1/Double.parseDouble(str));
            case "sqrt":
                if (str.contains("-")){
                    Toolkit.getDefaultToolkit().beep();
                    cont.hnd.clearOnNext = true;
                    return "square root of neg no. error";
                }
                return Double.toString(Math.sqrt(Double.parseDouble(str)));
            case "neg":
                return Double.toString(-1*(Double.parseDouble(str)));
            default:
                return "error \"applyUniraryOperation()\"";
        }
    }
    
    private String applyBinaryOperation(){
        switch (operator){
            case "+":
                return Double.toString(Double.parseDouble(operand1) + Double.parseDouble(operand2));
            case "-":
                return Double.toString(Double.parseDouble(operand1) - Double.parseDouble(operand2));
            case "x":
                return Double.toString(Double.parseDouble(operand1) * Double.parseDouble(operand2));
            case "/":
                if (Double.parseDouble(operand2) == 0.0) {
                    cont.resetCalc();
                    io.sendToDisplay("division by zero not allowed ");
                    cont.hnd.clearOnNext = true;
                    Toolkit.getDefaultToolkit().beep();
                    return "\"error applyBinaryOperation()\"";
                }
                return Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2));
            case "%":    
                if (Double.parseDouble(operand2) == 0.0) {
                    cont.resetCalc();
                    io.sendToDisplay("division by zero not allowed ");
                    cont.hnd.clearOnNext = true;
                    Toolkit.getDefaultToolkit().beep();
                    return "\"error applyBinaryOperation()\"";

                }
                return Double.toString(Double.parseDouble(operand1) % Double.parseDouble(operand2));
            default:
                return "error \"applyBinaryOperation()\"";
        }
    }
}
