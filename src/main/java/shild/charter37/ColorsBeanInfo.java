package shild.charter37;

// A Bean information class.
import java.beans.*;
public class ColorsBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor rectangular = new PropertyDescriptor("rectangular", Colors.class);
            PropertyDescriptor pom = new PropertyDescriptor("pom", Colors.class);
            PropertyDescriptor pd[] = {rectangular,pom};
            return pd;
        }
        catch(Exception e) {
            System.out.println("Exception caught. " + e);
        }
        return null;
    }
}