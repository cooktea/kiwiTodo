package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bean.User;
import bean.todoItem;
import Dao.TodoDao;
import Dao.UserDao;
import Utils.userUtils;
import org.json.JSONObject;

@WebServlet(name = "pushtodo")
public class Todo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("xxx");
        String type = request.getParameter("type");
        TodoDao todoDao = new TodoDao();
        Cookie[] cookies = request.getCookies();
        User user = userUtils.getUser(cookies);
        if(null == user){
            response.sendRedirect("login");
        } else {
            todoDao.setUser(user);
            if(type.equals("pushTodo")){    //用户添加todo
                String level = request.getParameter("level");
                String conten = request.getParameter("content");
                todoItem todo = new todoItem(level,conten);
                //todo 返回成功或失败的信息
                if(todoDao.push(todo)){
                    System.out.println("添加成功");
                } else {
                    System.out.println("添加失败");
                }
            } else if(type.equals("getTodo")){  //获取用户的所有todo
                List<todoItem> todos = todoDao.getTodos();
                JSONObject json = new JSONObject();
                json.put("length", todos.size());
                for (todoItem todo:todos){
                    json.put(String.valueOf(todo.getId()),new JSONObject(todo));
                }
                response.getWriter().println(json.toString());
            }
        }
    }
}
