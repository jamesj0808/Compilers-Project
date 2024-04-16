package backend;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Expr {
    public abstract Data eval(Runtime runtime);
}

enum Operator {
    ADD, SUB, MUL, DIV
}

class IntLiteral extends Expr {
    private Data value;

    public IntLiteral(double value) {
        this.value = new NumericData(value);
    }

    @Override
    public Data eval(Runtime runtime) {
        return value;
    }
}

class Arithmetics extends Expr {
    private Expr left;
    private Expr right;
    private Operator op;

    public Arithmetics(Operator op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Data eval(Runtime runtime) {
        Data leftVal = left.eval(runtime);
        Data rightVal = right.eval(runtime);

        if (!"Numeric".equals(leftVal.getType()) || !"Numeric".equals(rightVal.getType())) {
            throw new IllegalStateException("Arithmetic operations require numeric types.");
        }

        if (op == Operator.ADD) {
            return new NumericData(leftVal.getNumericValue() + rightVal.getNumericValue());
        } else if (op == Operator.SUB) {
            return new NumericData(leftVal.getNumericValue() - rightVal.getNumericValue());
        } else if (op == Operator.MUL) {
            return new NumericData(leftVal.getNumericValue() * rightVal.getNumericValue());
        } else if (op == Operator.DIV) {
            if (rightVal.getNumericValue() == 0) {
                throw new ArithmeticException("Division by zero.");
            }
            return new NumericData(leftVal.getNumericValue() / rightVal.getNumericValue());
        } else {
            throw new IllegalArgumentException("Unsupported operator " + op);
        }
    }
}

// Class functions that calculate the Average and Median 
class AvgFunction extends Expr {
    private List<Expr> expressions;

    public AvgFunction(List<Expr> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Data eval(Runtime runtime) {
        double sum = 0;
        for (Expr expr : expressions) {
            sum += expr.eval(runtime).getNumericValue();
        }
        return new NumericData(sum / expressions.size());
    }
}

class MedianFunction extends Expr {
    private List<Expr> expressions;

    public MedianFunction(List<Expr> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Data eval(Runtime runtime) {
        List<Double> sortedValues = expressions.stream()
            .map(expr -> expr.eval(runtime).getNumericValue())
            .sorted()
            .collect(Collectors.toList());
        int middle = sortedValues.size() / 2;
        if (sortedValues.size() % 2 == 1) {
            return new NumericData(sortedValues.get(middle));
        } else {
            return new NumericData((sortedValues.get(middle - 1) + sortedValues.get(middle)) / 2.0);
        }
    }
}
