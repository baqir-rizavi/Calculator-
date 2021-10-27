

public class Calculator {
    IOManager io;
    private String operator = "";
    private String operand1 = "";
    private String operand2 = "";
    private String result = "";
    boolean clearOnNext = false;

    public Calculator(IOManager io) {
        this.io = io;
    }
    
    void clear(){
        operator = "";
        operand2 = "";
        operand1 = "";
        result = "";
        clearOnNext = false;
    }
    
    public void process(String str){
        if (isBinaryOperator(str) && operand1.equals("")) { // first time input: do not solve
            operand1 = io.getDisplayedString();
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
        // TODO: urinary operations
    }
    
    boolean isUrinaryOperator(String str){
        return (str.equals("1/x") || str.equals("sqrt"));
    }
    
    boolean isBinaryOperator(String str){
        return str.equals("x") || str.equals("/") || str.equals("-") || str.equals("+") 
                || str.equals("%") || str.equals("=");
    }
    
    boolean isOperator(String str){
        return isBinaryOperator(str) || isUrinaryOperator(str);
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
                return Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2));
            case "%":                
                return Double.toString(Double.parseDouble(operand1) % Double.parseDouble(operand2));
            default:
                return "error";
        }
    }
}
