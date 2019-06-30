package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "login")

public class login implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        System.out.println(request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        if(request.getRequestURI().endsWith("login.html") || request.getRequestURI().endsWith("login") || request.getRequestURI().endsWith(".css") || request.getRequestURI().endsWith(".png") || request.getRequestURI().endsWith("index.html")){
            System.out.println("isLogin");
            chain.doFilter(request,response);
        }else{
            boolean isLogin = false;
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("user")){
                    isLogin = true;
                }
            }
            if(isLogin){
                System.out.println("isLogin");
                chain.doFilter(request,response);
            }else {
                System.out.println("notLogin");
                response.sendRedirect("login.html");
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
