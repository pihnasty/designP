package pattern;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by Max on 31.10.2016.
 */
public class ObjectPoolExampleGRAND_169_SoftReference {

    public static void main(String [] args) {

        CreationIF cR = new ClassForPool(0);
        SoftObjectPool softObjectPool = new SoftObjectPool(cR.getClass(),cR,3);

//        ClassForPool o = (ClassForPool)softObjectPool.getObject();
//        int i = o.getName();

        ClassForPool o1 = ((ClassForPool)softObjectPool.getObject());
        System.out.println("name="+o1.getName()
                            +"  instanceCount="+softObjectPool.getInstanceCount()
                            +"  size="+softObjectPool.getSize());
        softObjectPool.release(o1);
        ClassForPool o2 = ((ClassForPool)softObjectPool.getObject());
        System.out.println("name="+o2.getName()
                +"  instanceCount="+softObjectPool.getInstanceCount()
                +"  size="+softObjectPool.getSize());


        System.out.println("name="+((ClassForPool)softObjectPool.getObject()).getName()
                +"  instanceCount="+softObjectPool.getInstanceCount()
                +"  size="+softObjectPool.getSize());


    }
}

interface CreationIF {
    public Object create();
}

class SoftObjectPool {
    private ArrayList pool; // коллекция содержит управляемые объекты
    private CreationIF creator;  // управляет количеством существующих и управляющих объектов. Ответственный за создание объектов
    private int instanceCount;   // количество существующих объектов
    private int maxInstances;    // максимальное количество управляемых объектов
    private Class poolClass;     // класс управляемых объектов

    public SoftObjectPool(Class poolClass, CreationIF creator) {
        this(poolClass,creator,Integer.MAX_VALUE);
    }
    public SoftObjectPool(Class poolClass, CreationIF creator, int maxInstances) {
        this.creator = creator;
        this.poolClass = poolClass;
        this. maxInstances =  maxInstances;
        pool = new ArrayList();
    }

    public int getSize() { synchronized (pool) { return pool.size();}  } // количество объектов в пуле, ожидающих повторное использование

    public int getInstanceCount () {    return  instanceCount; }        //  количество управляемых пулом объектов

    public int getMaxInstances() {  return maxInstances;    }           //  максимальное количество объектов в пуле

    public void setMaxInstances(int newvalue) {  maxInstances = newvalue;    }

    public Object getObject() {
        synchronized (pool) {
            Object thisObject = removeObject();
            if (thisObject !=null) return thisObject;
            if (getInstanceCount() < getMaxInstances() )  return createObject();
                            else {  System.out.println("getInstanceCount() = getMaxInstances()");    return null; }
        }
    }

    public Object waitForObject() throws InterruptedException {
        synchronized (pool) {
            Object thisObject = removeObject();
            if (thisObject !=null) return thisObject;
            if (getInstanceCount() < getMaxInstances() )  return createObject();
                else do {
                pool.wait();
                thisObject  = removeObject();
            } while (thisObject == null);
            return thisObject;
        }
    }

    private Object removeObject() {
        while (pool.size()>0) {
            SoftReference thisRefn = new SoftReference(pool.remove(pool.size()-1));
            Object thisObject = thisRefn.get();
            if (thisObject !=null) return thisObject;
            instanceCount--;
        }
        return null;
    }

    private Object createObject() {
        Object newObject = creator.create();
        instanceCount++;
        return newObject;
    }

    public  void release (Object obj) {
        if (obj==null) throw new  NullPointerException();
        if (!poolClass.isInstance(obj)) {
            String actualClassName = obj.getClass().getName();
            throw new ArrayStoreException(actualClassName);
        }
        synchronized (pool) {
            pool.add(obj);
            pool.notify();
        }
    }

}

class ClassForPool implements CreationIF {

    private static int name = 0;
    public ClassForPool(int name) {        this.name = name;    }
    @Override
    public Object create() { name++; return new ClassForPool(name);  }
    public int getName() {return name;}
}