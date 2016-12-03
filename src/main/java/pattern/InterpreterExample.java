package pattern;

public class InterpreterExample {
    public static void main(String [] args){

        Expression termilaldExpressionJava = new TerminalExpression("Java");
        Expression termilaldExpressionSpring = new TerminalExpression("Java+Spring");

        System.out.println("AndExpression: "+new AndExpression(termilaldExpressionJava,termilaldExpressionSpring).interpreted("Java"));
        System.out.println("OrExpression: "+new OrExpression(termilaldExpressionJava,termilaldExpressionSpring).interpreted("Java"));


    }
}

interface Expression {
    boolean interpreted(String context);
}

class AndExpression implements Expression {
    Expression exp1;
    Expression exp2;

    public AndExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public boolean interpreted(String context) {
        return exp1.interpreted(context) && exp2.interpreted((context));
    }
}

class OrExpression implements Expression{
    Expression exp1;
    Expression exp2;

    public OrExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public boolean interpreted(String context) {
        return exp1.interpreted(context) || exp2.interpreted((context));
    }
}class TerminalExpression implements Expression{
    String data;


    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpreted(String context) {
            if (context.equals(data)) return true;
        return false;
    }
}
