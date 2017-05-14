package shild.charter38.servlets.classes;

import java.io.*;
import javax.servlet.*;

public class HelloServlet extends GenericServlet {

    public void service(ServletRequest request,
                        ServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<B>Hello!  !!!");
        pw.close();

        System.out.println("Hello World!!!");

    }
}
//C:\D\CloudRuInnov\ileaUltimate\designP\src\main\java\shild\charter38\servlets\HelloServlet.html