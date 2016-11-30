package pattern;

import brus.brus_047_PrivateProtected.example.E;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Max on 27.11.2016.
 */
public class CacheManagementExampleGRAND_287 {
    String s;

    static public void main(String[] args) {
        EmployeeID employeeID_1 = new EmployeeID("1");
        EmployeeID employeeID_2 = new EmployeeID("2");

        System.out.println(employeeID_1.equals(employeeID_2));
    }

}

class EmployeeProfileManager {
    private EmployeeCache cache = new EmployeeCache();
    private EmployeeProfileFetcher server;

    public EmployeeProfile fetchEmploysee(EmployeeID id) {
        EmployeeProfile profile = cache.fetchEmploysee(id);
        if (profile == null) {
            profile = server.fetchEmployee(id);
            if (profile != null) cache.addEmployee(profile);
        }
        return profile;
    }
}

class EmployeeCache {
    private Map cache = new HashMap();
    private LinkedListP mru = null;
    private LinkedListP lru = null;
    private final int MAX_CACHE_SIZE = 80;
    private int currentCacheSize = 0;
    private CleanupQueueP myCleanup = new CleanupQueueP();

    public void addEmployee(EmployeeProfile emp) {
        EmployeeID id = emp.getId();
        if (cache.get(id) == null) {
            if (currentCacheSize == 0) lru = mru = new LinkedListP(emp);
            else {
                LinkedListP newLink;
                if (currentCacheSize >= MAX_CACHE_SIZE) {
                    newLink = lru;
                    lru = newLink.previouns;
                    cache.remove(id);
                    lru.next = null;
                    newLink.setProfile(emp);
                } else {
                    newLink = new LinkedListP(emp);
                }
                newLink.next = mru;
                mru.previouns = newLink;
                newLink.previouns = null;
                mru = newLink;
            }
            cache.put(id, mru);
            currentCacheSize++;
        } else fetchEmploysee(id);
    }


    public EmployeeProfile fetchEmploysee(EmployeeID id) {
        myCleanup.cleanup();

        LinkedListP foundLink = (LinkedListP) cache.get(id);
        if (foundLink == null) return null;
        if (mru != foundLink) {
            if (foundLink == lru) {
                lru = foundLink.previouns;
                lru.next = null;
            }
            if (foundLink.previouns != null) foundLink.previouns.next = foundLink.next;
            if (foundLink.next != null) foundLink.next.previouns = foundLink.previouns;
            mru.previouns = foundLink;
            foundLink.previouns = null;
            foundLink.next = mru;
        }
        return foundLink.getProfile();
    }

    public void removeEmployee(EmployeeID id) {
        LinkedListP foundLink = (LinkedListP) cache.get(id);
        if (foundLink != null) {
            if (mru == foundLink) mru = foundLink.next;
            if (foundLink == lru) lru = foundLink.previouns;
            if (foundLink.previouns != null) foundLink.previouns.next = foundLink.next;
            if (foundLink.next != null) foundLink.previouns.next = foundLink.previouns;
        }
    }

    private class LinkedListP implements CleanupIF {
        SoftReference profilReference;
        LinkedListP previouns;
        LinkedListP next;

        public LinkedListP(EmployeeProfile profile) {
            setProfile(profile);
        }

        @Override
        public void extricate() {
            EmployeeProfile profile = (EmployeeProfile) profilReference.get();
            removeEmployee(profile.getId());

        }

        public void setProfile(EmployeeProfile profile) {
            profilReference = myCleanup.createSoftReference(profile, this);
        }

        public EmployeeProfile getProfile() {
            return (EmployeeProfile) profilReference.get();
        }

    }

}

class EmployeeProfileFetcher {
    public EmployeeProfile fetchEmployee(EmployeeID id) {
        return null;
    }
}

class EmployeeProfile {
    private EmployeeID id;
    private Locale locale;
    private boolean supervisor;

    public boolean isSupervisor() {
        return supervisor;
    }

    public Locale getLocale() {
        return locale;
    }

    private String name;

    public EmployeeProfile(EmployeeID id, Locale locale, boolean supervisor, String name) {
        this.id = id;
        this.locale = locale;
        this.supervisor = supervisor;
        this.name = name;
    }


    public EmployeeID getId() {
        return id;
    }
}


class EmployeeID {
    private String id;

    public EmployeeID(String id) {
        this.id = id;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(EmployeeID obj) {
        System.out.println(obj.id);
        return (obj instanceof EmployeeID && id.equals(obj.id));
    }

    public String toString() {
        return id;
    }
}

class CleanupQueueP {
    private ReferenceQueue refQueue = new ReferenceQueue();
    private boolean cleaning;

    public SoftReference createSoftReference(Object obj, CleanupIF cleanup) {
        return null; // new SoftCleanupReference (obj, (ReferenceQueue) cleanup);
    }

    void cleanup() {
        synchronized (this) {
            if (cleaning) return;
            cleaning = true;
        }
//        try {
//            while (ref.poll() !=null ) {
//                SoftCleanupReference = (SoftCleanupReference) refQueue.remove();
//                r.extricate();
//            }
//        } catch (InterruptedException e) {}
//        finally {
//            cleaning = false;
//        }

    }
}

interface CleanupIF {
    void extricate();
}