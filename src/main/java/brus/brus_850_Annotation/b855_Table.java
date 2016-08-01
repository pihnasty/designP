package brus.brus_850_Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Pihnastyi.O on 7/28/2016.
 */
public class b855_Table {
}

@DBTable (name="MEMBER")
class Member {
    @SQLString(30)
    String firstName;

    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString (value = 30, constraints = @Constraints (primaryKey = true))
    String handle;

    static int memberCount;
    public String getHandle() {return handle;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String toString() {return handle;}
    public Integer getAge()  {return age;}
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DBTable {
    String name() default "";
}

@Target(ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default true;
}

@Target(ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
@interface SQLString {
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}

@Target(ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
@interface SQLInteger{
    String name() default "";
    Constraints constraints() default @Constraints;
}

@interface Uniqueness {
    Constraints constraints() default @Constraints(unique=true);
}
