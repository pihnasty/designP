package shild;

import java.applet.*;
import java.net.*;

/*
<applet code="ACDemo" width=300 height=50>
</applet>
*/

public class _08_ACDemo extends Applet{
    public void start() {
        AppletContext ac = getAppletContext();
        URL url = getCodeBase(); // get url of this applet

        try {
            ac.showDocument(new URL(url+"Test.html"));
            System.out.println("getCodeBase()= "+ getCodeBase());
        } catch(MalformedURLException e) {
            showStatus("URL not found");
        }
    }
}
