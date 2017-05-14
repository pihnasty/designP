package shild.chapter23;


import java.applet.Applet;
import java.awt.*;


public class _00_AppletMy extends Applet {
    public void paint(Graphics g) {

        g.drawString("A1 Simple Applet", 20, 20);
        g.drawString(getDocumentBase().getPath(), 20, 40);
        g.drawString(getCodeBase().getPath(), 20, 50);
        stop();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.drawString(getCodeBase().getPath(), 20, 70);




        try {
            Thread.sleep(1000);
            g.drawString(getLocale().getCountry(), 20, 80);
            start();
            resize(500, 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        destroy();

    }
//    public static void main(String [] arg) {
//        System.out.println("Hello Wordl!");
//    }
}