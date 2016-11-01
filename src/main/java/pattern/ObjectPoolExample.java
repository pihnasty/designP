package pattern;

/**
 * Created by Max on 31.10.2016.
 */
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class ObjectPoolExample {
}


class PooledObject {
    public String temp1;
    public String temp2;
    public String temp3;

    public String getTemp1() {
        return temp1;
    }
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }
    public String getTemp2() {
        return temp2;
    }
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }
    public String getTemp3() {
        return temp3;
    }
    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }



}

class PooledObjectPool {
    private static long expTime = 60000;//6 seconds
    public static HashMap<PooledObject, Long> available = new HashMap<PooledObject, Long>();
    public static HashMap<PooledObject, Long> inUse = new HashMap<PooledObject, Long>();


    public synchronized static PooledObject getObject() {
        long now = System.currentTimeMillis();
        if (available.isEmpty()) {
            PooledObject po = new PooledObject();
            push(inUse, po, now);
            return po;

        } else {
            for (Map.Entry<PooledObject, Long> entry : available.entrySet()) {
                if (now - entry.getValue() > expTime) { //object has expired
                    popElement(available);
                } else {
                    PooledObject po = popElement(available, entry.getKey());
                    cleanUp(po);
                    push(inUse, po, now);
                    return po;
                }
            }
        }
        return null;
    }

    private synchronized static void push(HashMap<PooledObject, Long> map,
                                          PooledObject po, long now) {
        map.put(po, now);
    }

    public static void releaseObject(PooledObject po) {
        cleanUp(po);
        available.put(po, System.currentTimeMillis());
        inUse.remove(po);
    }

    public static PooledObject popElement(HashMap<PooledObject, Long> map) {
        Map.Entry<PooledObject, Long> entry = map.entrySet().iterator().next();
        PooledObject key= entry.getKey();
        //Long value=entry.getValue();
        map.remove(entry.getKey());
        return key;

    }

    public static PooledObject popElement(HashMap<PooledObject, Long> map, PooledObject key) {
        map.remove(key);
        return key;

    }

    public static void cleanUp(PooledObject po) {
        po.setTemp1(null);
        po.setTemp2(null);
        po.setTemp3(null);
    }

}