package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebFilter(filterName = "login")

public class login implements Filter {
    private List<String> others = new LinkedList<>();
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        System.out.println(request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        for (String end : others){
            if (request.getRequestURI().endsWith(end)){
                flag = true;
                break;
            }
        }
        if(flag){
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
        others.add("login.html");
//        others.add("login");
        others.add("index.html");
        others.add(".css");
        others.add(".js");
        others.add(".png");
        others.add("user");
    }
}
