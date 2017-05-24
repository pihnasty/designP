package shild.chapter14._08;

import java.io.Serializable;

/**
 * Created by POM on 14.05.2017.
 */
public interface B2<T> extends Serializable {
    int compareTo(T o);

    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();
}
