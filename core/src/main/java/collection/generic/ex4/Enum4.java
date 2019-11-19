package collection.generic.ex4;

import java.io.Serializable;

public abstract class Enum4<E extends Enum4<E>>
        implements Comparable<E>, Serializable {

    public final int compareTo(E o) {
        Enum4<?> other = (Enum4<?>) o;
        Enum4<E> self = this;
        if (self.getClass() != other.getClass() && // optimization
                self.getDeclaringClass() != other.getDeclaringClass())
            throw new ClassCastException();
        return self.compareTo((E) other);
    }

    @SuppressWarnings("unchecked")
    public final Class<E> getDeclaringClass() {
        Class<?> clazz = getClass();
        Class<?> zuper = clazz.getSuperclass();
        return (zuper == Enum.class) ? (Class<E>) clazz : (Class<E>) zuper;
    }
}


