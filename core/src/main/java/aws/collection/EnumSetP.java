package aws.collection;

import java.util.EnumSet;

public class EnumSetP {
    public static void main(String[] args) {
       EnumSet<Color> enumSet1 = EnumSet.allOf(Color.class);

        EnumSet<Color> enumSet2 = EnumSet.noneOf(Color.class);
        EnumSet<Color> enumSet3 =  EnumSet.range(Color.YELLOW, Color.BLUE);
        enumSet3.add(null);

    }
}

enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}