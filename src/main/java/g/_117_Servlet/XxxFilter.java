package g._117_Servlet;


import javax.servlet.*;
import java.io.IOException;

public class XxxFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        ServletRequestWrapper wrapper = new ServletRequestWrapperSpy(servletRequest);
        filterChain.doFilter( wrapper,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

class  ServletRequestWrapperSpy extends ServletRequestWrapper {

    public ServletRequestWrapperSpy(ServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        System.out.println("name="+name);
        return super.getParameter(name);
    }
}


