package pattern;

import java.util.ArrayList;

/**
 * Created by Max on 31.10.2016.
 */
public class ObjectPoolExampleGRAND_169_SoftReference {

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
            if (getInstanceCount() < getMaxInstances() ) { return createObject(); }
                            else return null;
        }
    }

}