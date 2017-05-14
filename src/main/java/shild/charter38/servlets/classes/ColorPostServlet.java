package shild.charter38.servlets.classes;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class ColorPostServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {


        request.getHeaderNames();

        String color = request.getParameter("color");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<B>The selected color is:  ");
        pw.println(color);

        Map<String,String> hashMap = getHeadersInfo(request);

        for (Map.Entry entry : hashMap.entrySet()) {

            pw.println("\n Key: " + entry.getKey() + " Value: " + entry.getValue());

        }


        pw.close();
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

}
