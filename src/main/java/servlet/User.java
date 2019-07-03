package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Dao.UserDao;
import bean.*;

@WebServlet(name = "User")
public class User extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String number = request.getParameter("phoneNumber");
        String pwd = request.getParameter("password");
        if(type.equals("getUser")){
            System.out.println("验证有没有用户："+number);
            UserDao dao = new UserDao();
            bean.User user = dao.getUser(number);
            if(null != user){
                response.getWriter().println("HaveUser");
            } else {
                response.getWriter().println("NoUser");
            }
        } else if(type.equals("login")){

        } else if(type.equals("register")){
            System.out.println("注册用户："+number);
            UserDao dao = new UserDao();
            bean.User user = new bean.User(number,pwd);
            if(dao.register(user)){
            response.getWriter().println("success");
                System.out.println("注册成功");
            } else {
                response.getWriter().println("faild");
                System.out.println("注册失败");
            }
        } else {

        }
    }
}
