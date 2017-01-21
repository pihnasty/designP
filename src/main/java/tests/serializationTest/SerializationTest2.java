package tests.serializationTest;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Max on 21.01.2017.
 */
public class SerializationTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        _serializationMethod_01();
        _cloneableMethod_01();

    }

    private static void _serializationMethod_01() throws IOException, ClassNotFoundException {

        A[] a1mas =  {new A("Oleh",47),new A("Oleh",48)};
        A[] a2mas = copySerializable(a1mas);
        System.out.println(Arrays.toString(a2mas));



        A a1 = new A("Oleh",47);
        A a2 = copySerializable(a1);
        System.out.println(a2.name+"     "+a2.age);

        Object[] src = new Object[0];
        Object[] dst = copySerializable(src);
        System.out.println("Hello="+Arrays.toString(dst));

        Object[] src1 = {new Object[0]};
        Object[] dst1 = copySerializable(src1);
        System.out.println("Hello1="+Arrays.toString(dst1));


        Object[] o1 = {new Object()};
        Object[] o2 = copySerializable(o1);
        System.out.println(o2);
     }

    private static <T > T copySerializable(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(buff);
        objectOutput.writeObject(obj);
        objectOutput.flush();
        objectOutput.close();

        byte[] rawData = buff.toByteArray();
        ObjectInput objectInput = new ObjectInputStream(new ByteArrayInputStream(rawData));
        return (T) objectInput.readObject();
    }

    private static void _cloneableMethod_01() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        {   //  create Objects
            Object[] src = new Object[0];
            Object[] dst = copyCloneable(src);
            System.out.println(src != dst);
   //         System.out.println(src.length == dst.length);

        }
        {   //  deep copy Objects
            Object[] src = {new Object[0]};
            Object[] dst = copyCloneable(src);
            System.out.println(src[0] == dst[0]);

        }
        {   //  safe the gragh of the Objects
            Object[] src0 = {new Object[1]};
            Object[] src1 = copyCloneable(src0);
            src0[0] = src1;
            Object[] dst = copySerializable(src0);
            System.out.println(((Object[]) dst[0])[0] != dst);
        }
    }

    private static <T> T copyCloneable(T obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = Object.class.getDeclaredMethod("clone");
        m.setAccessible(true);
        return (T) m.invoke(obj);
    }

}

class A implements Serializable {
    String name;
    Integer age;
    public A (String name,Integer age) {
        this.name = name;
        this.age = age;
    }
}