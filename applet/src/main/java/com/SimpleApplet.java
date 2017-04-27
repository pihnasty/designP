package com; /**
 * Created by Pihnastyi.O on 4/27/2017.
 */

import java.applet.Applet;
import java.awt.*;
/*
<applet code="SimpleApplet" width=200 height=60>
</applet>
*/

public class SimpleApplet extends Applet {
    public void paint(Graphics g) {
        g.drawString("A Simple Applet", 20, 20);
    }
//    public static void main(String [] arg) {
//        System.out.println("Hello Wordl!");
//    }
}

