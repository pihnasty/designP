package tests.serializationTest;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Max on 21.01.2017.
 */
public class SerializationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        _serializationMethod_01();
        _cloneableMethod_01();

    }

    private static void _serializationMethod_01() throws IOException, ClassNotFoundException {
        {   //  create Objects
            Object[] src = new Object[0];
            Object[] dst = copySerializable(src);
            System.out.println(src != dst);
        }
        {   //  deep copy Objects
            Object[] src = {new Object[0]};
            Object[] dst = copySerializable(src);
            System.out.println(src != dst);
//            System.out.println(src.length == dst.length);
        }
        {   //  safe the gragh of the Objects
            Object[] src0 = {new Object[1]};
            Object[] src1 = copySerializable(src0);
            src0[0] = src1;
            Object[] dst = copySerializable(src0);
            System.out.println(((Object[]) dst[0])[0] != dst);
        }
    }

    private static <T extends Serializable> T copySerializable(T obj) throws IOException, ClassNotFoundException {
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
