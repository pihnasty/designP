package g._117_Servlet;

import javax.servlet.*;
import java.io.*;

public class LodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long tic = System.nanoTime();
        filterChain.doFilter(servletRequest, servletResponse);
        long toc = System.nanoTime();
        System.out.println("dt="+(toc-tic));
    }

    @Override
    public void destroy() {

    }
}

class T {
    public static void main(String[] args) throws IOException {
        OutputStream out = new BufferedOutputStream( new FileOutputStream("d:/tmp.bin"));
        out.write(42);
        out.write(42);
        out.write(42);
        out.flush();
        out.close();
    }
}

class T2 {
    public static void main(String[] args) throws IOException {
        OutputStream fos = new FileOutputStream( new File("d:/tmp.bin"));
        DataOutput dataOutput = new DataOutputStream(fos);

        dataOutput.writeDouble(42.42);
        fos.flush();
        fos.close();
    }
}