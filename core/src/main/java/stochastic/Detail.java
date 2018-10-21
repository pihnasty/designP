package stochastic;

public class Detail {
    private static int count = 0;
    private int number;

    public Detail() {
        number = ++count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
