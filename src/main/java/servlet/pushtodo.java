package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import bean.User;
import bean.todoItem;
import Dao.TodoDao;
import Dao.UserDao;

@WebServlet(name = "pushtodo")
public class pushtodo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("xxx");
        String level = request.getParameter("level");
        String conten = request.getParameter("content");
        String phoneNumber = null;
        TodoDao todoDao = new TodoDao();
        UserDao userDao = new UserDao();
        todoItem todo = new todoItem(level,conten);
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")){
                phoneNumber = cookie.getValue();
                break;
            }
        }
        User user = userDao.getUser(phoneNumber);
        todoDao.setTodo(todo);
        todoDao.setUser(user);
        //todo 返回成功或失败的信息
        if(todoDao.push()){
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }
}
