package probability;

import com.opencsv.CSVWriter;
import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomSequenceGeneration {



    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int countMachine = 3;
        List<List<Double>> tableValues = new ArrayList<>();
        for (int i=1; i<= countMachine; i++) {
            Sequence sequence = new Sequence(TypeSequence.UNIFORM,100,0.5,1.0);
            AnalysisTask analysisTask = new AnalysisTask(sequence);
            AnalysisValues analysisValues = forkJoinPool.invoke(analysisTask);
            tableValues.add(analysisValues.getSequence().getRandomValues());
        }
        writeToFile(tableValues,"result_0.csv");
        double sum = 0.0;
        for (int i=0; i<tableValues.get(0).size(); i++) {
            sum=+sum+tableValues.get(0).get(i);
        }

    }

    public static void writeToFile(List<List<Double>> table, String fullPathToFile)  {
        BufferedWriter writer = null;

        try {
            writer = Files.newBufferedWriter(Paths.get( fullPathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVWriter csvWriter = new CSVWriter(writer, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.RFC4180_LINE_END)) {
            List<String[]> data = toStringArray(table);
            csvWriter.writeAll(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> toStringArray(List<List<Double>> table) {
        List<String[]> records = new ArrayList<>();
        List<List<String>> tableString = new ArrayList();

        for (int i2 = 0; i2 < table.get(0).size(); i2++) {
            List<String> row = new ArrayList<>();
            for (int i1 = 0; i1 < table.size(); i1++) {
                row.add(String.format("%10.4f ",table.get(i1).get(i2)));
            }
            tableString.add(row);
        }


        tableString.forEach( row -> records.add(row.stream().toArray(String[]::new)));
        return records;
    }

}


class Sequence {
    List<Double> randomValues;
    Random random = new Random(System.currentTimeMillis());
    private TypeSequence typeSequence;
    private long count;
    private double average;
    private double beginValue;

    public Sequence(TypeSequence  typeSequence, long count, double beginValue, double average) {
        this.typeSequence = typeSequence;
        this.count = count;
        this.average = average;
        this.beginValue = beginValue;
    }

    public List<Double> getRandomValues() {
        if (Objects.isNull(randomValues)) {
            randomValues = Stream.generate(() -> getRandomValue() ).limit(count).collect(Collectors.toList());
        }
        return randomValues;
    }

    private double getRandomValue() {
        switch (typeSequence) {
            case UNIFORM:
                return beginValue+2.0*(average-beginValue)*random.nextDouble();
            default:
                return random.nextDouble();
        }
    }
}

enum TypeSequence {  UNIFORM }

class AnalysisValues {
    private Sequence sequence;
    public AnalysisValues(Sequence sequence) {
        this.sequence = sequence;
    }

    public Sequence getSequence() {
        return sequence;
    }
}

class AnalysisTask extends RecursiveTask<AnalysisValues> {
    private Sequence sequence;
    public AnalysisTask (Sequence sequence) {
        this.sequence = sequence;
    }
    @Override
    protected AnalysisValues compute() {
        sequence.getRandomValues();
        AnalysisValues analysisValues = new AnalysisValues(sequence);
        return analysisValues;
    }

}
