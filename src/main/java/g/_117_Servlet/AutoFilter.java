package g._117_Servlet;


import javax.servlet.*;
import java.io.IOException;

public class AutoFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("secret_word").equals("XYZ")) {
            filterChain.doFilter(servletRequest,servletResponse);
        }   else {
            servletResponse.setContentType("index.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
