package tests.annotation;

import java.lang.annotation.Annotation;

public class AnnotationTest {

    public static void main(String[] args) {

    }

    void annotationTest() {
        Annotation a = new Annotation() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
        };
    }



}
