package shild.charter37;

// Show properties and events.
import java.awt.*;
import java.beans.*;

public class IntrospectorDemo {
    public static void main(String args[]) {
        try {
            Colors colors = new Colors();

            colors.getClass().getCanonicalName();

            Class<?> c = Class.forName("shild.charter37.Colors");
            BeanInfo beanInfo = Introspector.getBeanInfo(c);

            System.out.println("Properties:");
            PropertyDescriptor propertyDescriptor[] =
                    beanInfo.getPropertyDescriptors();
            for(int i = 0; i < propertyDescriptor.length; i++) {
                System.out.println("\t" + propertyDescriptor[i].getName());
            }

            System.out.println("Events:");
            EventSetDescriptor eventSetDescriptor[] =
                    beanInfo.getEventSetDescriptors();
            for(int i = 0; i < eventSetDescriptor.length; i++) {
                System.out.println("\t" + eventSetDescriptor[i].getName());
            }
        }
        catch(Exception e) {
            System.out.println("Exception caught. " + e);
        }
    }
}