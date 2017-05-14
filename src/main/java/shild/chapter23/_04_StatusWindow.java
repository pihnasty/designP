package shild.chapter23;

// Using the Status Window.
import java.awt.*;
import java.applet.*;
/*
<applet code="_04_StatusWindow" width=300 height=50>
</applet>
*/

public class _04_StatusWindow extends Applet{
    public void init() {
        setBackground(Color.cyan);
    }

    // Display msg in applet window.
    public void paint(Graphics g) {
        g.drawString("This is in the applet window.", 10, 20);
        showStatus("This is shown in the status window.");
    }
}
