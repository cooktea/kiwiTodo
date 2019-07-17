package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Utilities;
import java.io.IOException;
import Dao.UserDao;
import bean.*;
import Utils.userUtils;
import org.json.JSONObject;

@WebServlet(name = "User")
public class User extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String type = request.getParameter("type");
        String number = request.getParameter("phoneNumber");
        String pwd = request.getParameter("password");
        if(type.equals("getUser")){
//            System.out.println("验证有没有用户："+number);
            bean.User user = dao.getUser(number);
            if(null != user){
                response.getWriter().println("HaveUser");
            } else {
                response.getWriter().println("NoUser");
            }
        } else if(type.equals("login")){
            bean.User user = new bean.User(number,pwd);
            System.out.println(user.toString());
            if(dao.login(user)){
//                System.out.println(user.toString()+"存在");
                Cookie cookie = new Cookie("user",user.getPhoneNumber());
//                cookie.setMaxAge(60*10);
                response.addCookie(cookie);
                response.sendRedirect("index.html");
            } else {
                //todo 跳转至登陆失败页面
                response.getWriter().println("登陆失败,用户名或密码不正确。请重新登陆。即将跳转");
//                System.out.println(user.toString()+"不存在");
            }
        } else if(type.equals("register")){
            System.out.println("注册用户："+number);
            bean.User user = new bean.User(number,pwd);
            if(dao.register(user)){
            response.getWriter().println("success");
//                System.out.println("注册成功");
            } else {
                response.getWriter().println("faild");
//                System.out.println("注册失败");
            }
        } else if (type.equals("logout")){
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            for (int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("user")){
                    cookie = cookies[i];
                    break;
                }
            }
            if(null != cookie){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.sendRedirect("index");
            } else {
                response.getWriter().println("退出登陆失败，请稍后再试");
                response.setHeader("refresh","3,URL=index.html");
            }
        } else if (type.equals("getUserInfo")){
            response.getWriter().println(new JSONObject(userUtils.getUser(request.getCookies())));
        } else if (type.equals("modifiyUserInfo")){
            bean.User user = userUtils.getUser(request.getCookies());
            user.setUserName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            if(dao.modifiyUserInfo(user)){
                response.getWriter().println("success");
            } else {
                response.getWriter().println("faild");
            }
        }
    }
}
