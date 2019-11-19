package String;

import java.util.Objects;
import java.util.Optional;

public class StringNull {
    public static void main(String[] args) {
        Query query1 = // null;
         new Query(null);

//        if (Objects.isNull(query1) || query1.getText().isEmpty()) {
//            System.out.println("True");
//        }


        if (Objects.isNull(query1) || Optional.ofNullable(query1.getText()).orElse("").isEmpty()) {
            System.out.println("True");
        }
        System.out.println("False");
    }
}

class Query {

    public String getText() {
        return text;
    }

    String text;

    public Query(String text) {
        this.text = text;
    }
}
