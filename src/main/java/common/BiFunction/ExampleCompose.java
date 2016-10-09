package common.BiFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ExampleCompose {

    public static void main(String[] args) {
        ExampleCompose ex = new ExampleCompose();
        Function<Double , Double> sin = d -> ex.sin(d);
        Function<Double , Double> x = d -> 2*d;


        ExampleCompose compose = new ExampleCompose();


//--------------------------------------------------------------------------------------------
        System.out.println(
                String.format("x, Math.PI/6) =%f",compose.calculate(x, Math.PI/6))
                );

        System.out.println(
                String.format("compose.calculate(sin.andThen(x), Math.PI/6 =%f",compose.calculate(sin.andThen(x), Math.PI/6))
        );
        System.out.println(
                String.format("compose.calculate(sin.compose(x), Math.PI/6) =%f",compose.calculate(sin.compose(x), Math.PI/12))
        );

    }

    public Double calculate(Function<Double , Double> operator, Double d) {
        return operator.apply(d);
    }

    public Double sin(Double d) {
        System.out.print("sin:");
        return Math.sin(d);
    }

}
