package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReduceTest {
    public static void main(String[] args) {

        List<List<Double>> wsLayerListWs = new ArrayList<>();
        List<Double> row1 = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> row2 = Arrays.asList(4.0, 5.0, 6.0);
        List<Double> row3 = Arrays.asList(7.0, 8.0, 9.0);

        wsLayerListWs.add(row1);
        wsLayerListWs.add(row2);
        wsLayerListWs.add(row3);




        List<Double> summaryValuesRow = wsLayerListWs.stream().map(row->row.stream().mapToDouble(value->value).sum()).collect(Collectors.toList());

        System.out.println();

    }
}
