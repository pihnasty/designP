package pattern;

/**
 * Created by Pihnastyi.O on 10/12/2016.
 */
public class PrototypeExample {
    public static void main(String args[]) throws CloneNotSupportedException {
        Cookie tempCookie = null;
        //----------------------------------
        Cookie prot = new CoconutCookie();
        CookieMachine cm = new CookieMachine(prot);
        for (int i = 0; i < 100; i++) {
            tempCookie = cm.makeCookie();
            System.out.println("№"+i+"   "+tempCookie+"   "+ ((CoconutCookie)tempCookie).getO()) ;
        }
        //----------------------------------
        Cookie protM = new M_Cookie();
        cm.setCookie(protM);
        for (int i = 0; i < 100; i++) {
            tempCookie = cm.makeCookie();
            System.out.println("№"+i+"   "+tempCookie+"   "+ ((M_Cookie)tempCookie).getO1()) ;
        }

    }
}


/** Client Class */
class CookieMachine {
    private Cookie cookie; // Could have been a private Cloneable cookie.

    public CookieMachine(Cookie cookie) {   this.cookie = cookie;    }
    public Cookie makeCookie() throws CloneNotSupportedException {  return cookie.clone();  }
    public void setCookie(Cookie cookie) {   this.cookie = cookie;   }
}

/** Prototype Class */
class Cookie implements Cloneable {

    @Override
    public Cookie clone() throws CloneNotSupportedException {
        //In an actual implementation of this pattern you might now change references to
        //the expensive to produce parts from the copies that are held inside the prototype.
        return (Cookie) super.clone();
    }
}

/** Concrete Prototypes to clone */
class CoconutCookie extends Cookie {
    private Object o;
    public CoconutCookie() {    this.o=new Object();    }
    public Object getO() { return o;}
}

/** Concrete Prototypes to clone */
class M_Cookie extends Cookie {
    private Object o1;
    private Object o2;
    private Object o3;
    public M_Cookie() {    this.o1=new Object(); this.o2=new Object(); this.o3=new Object(); }
    public Object getO1() { return o1;}     public Object getO2() { return o2;}     public Object getO3() { return o3;}
}