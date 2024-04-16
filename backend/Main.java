package backend;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = new Runtime();

        // Expression Creating numeric values for the average, median and type checker
        Expr expr1 = new IntLiteral(10);
        Expr expr2 = new IntLiteral(20);
        Expr expr3 = new IntLiteral(30);

        Expr addExpr = new Arithmetics(Operator.ADD, expr1, expr2);
        Data result = addExpr.eval(runtime);

        // Testing numerical type checking
        System.out.println("The numeric result via addition is: " + result.toString());
        if (result.getNumericValue() != 30.0) {
            throw new Error("Numeric addition test failed.");
        }
        // Testing average and median
        AvgFunction avg = new AvgFunction(java.util.Arrays.asList(expr1, expr2, expr3));
        System.out.println("Average: " + avg.eval(runtime).toString());

        MedianFunction median = new MedianFunction(java.util.Arrays.asList(expr1, expr2, expr3));
        System.out.println("Median: " + median.eval(runtime).toString());
    }
}
