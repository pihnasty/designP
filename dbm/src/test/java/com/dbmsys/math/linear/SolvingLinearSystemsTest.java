package com.dbmsys.math.linear;

import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
public class SolvingLinearSystemsTest {

    double eps = 0.001;

    @Test
    public void  getSolutionTest () {

        SolvingLinearSystems solvingLinearSystems = new SolvingLinearSystems();

        double[][] arrayCoefficients = new double[][] {{2, 3, -2},
            {-1, 7, 6},
            {4, -3, -5}};

        double[] arrayfreeTerm = new double[] {1, -2, 1};



        List<List<Double>> listCoefficients = SolvingLinearSystems.convertArrayToList2(arrayCoefficients);
        List<Double> listfreeTerm = SolvingLinearSystems.convertArrayToList(arrayfreeTerm);

         List<Double> solution =  solvingLinearSystems.getSolution(listCoefficients,listfreeTerm);

        double[] result = new double[3];

       for (int row = 0; row < arrayCoefficients.length; row++) {
            for (int column = 0; column < arrayCoefficients[row].length; column++) {
                result[row] = result[row] + arrayCoefficients[row][column] * solution.get(column);
            }
        }

        System.out.println();
    }

    @Test
    public void convertListToArrayTest() {
        double[] arrayOriginal = new double [] {1.0,2.0,3.0,4.0};
        List<Double> list = SolvingLinearSystems.convertArrayToList(arrayOriginal);
        double[] array = SolvingLinearSystems.convertListToArray(list);
        assertArrayEquals(array , arrayOriginal, eps );
    }

    @Test
    public void convertListToArray2Test() {
        double[][] arrayOriginal = new double [][] {{1.0,2.0,3.0,4.0}
                                                   ,{1.1,2.1,3.1,4.1}
                                                   ,{1.2,2.2,3.2,4.2}};

        List<List<Double>> list =  SolvingLinearSystems.convertArrayToList2(arrayOriginal);
        double[][] array = SolvingLinearSystems.convertListToArray2(list);
        assertArrayEquals(array , arrayOriginal);
    }

}
