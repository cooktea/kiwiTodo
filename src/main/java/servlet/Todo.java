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
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "pushtodo")
public class Todo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("xxx");
        long start = System.currentTimeMillis();
        String type = request.getParameter("type");
        TodoDao todoDao = new TodoDao();
        System.out.println("响应花费了"+(System.currentTimeMillis()-start)+"ms");
        Cookie[] cookies = request.getCookies();
        System.out.println("响应花费了"+(System.currentTimeMillis()-start)+"ms");
        User user = userUtils.getUser(cookies);
        System.out.println("响应花费了"+(System.currentTimeMillis()-start)+"ms");
        if(null == user){
            response.sendRedirect("login");
        } else {
            todoDao.setUser(user);
            if(type.equals("pushTodo")){    //用户添加todo
                String level = request.getParameter("level");
                String conten = request.getParameter("content");
                todoItem todo = new todoItem(level,conten);
                //todo 返回失败的信息
                if(todoDao.push(todo)){
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("faild");
                }
            } else if(type.equals("getTodo")){  //获取用户的所有todo
                List<todoItem> todos = todoDao.getTodos(1);
                JSONArray json = new JSONArray();
//                json.put("length", todos.size());
                for (int i=0;i<todos.size();i++){
                    json.put(i,new JSONObject(todos.get(i)));
                }
                response.getWriter().println(json.toString());
            } else  if (type.equals("getFinished")){
                List<todoItem> todos = todoDao.getTodos(2);
                JSONArray json = new JSONArray();
//                json.put("length", todos.size());
                for (int i=0;i<todos.size();i++){
                    json.put(i,new JSONObject(todos.get(i)));
                }
                response.getWriter().println(json.toString());
            } else if (type.equals("getDeleted")){
                List<todoItem> todos = todoDao.getTodos(3);
                JSONArray json = new JSONArray();
//                json.put("length", todos.size());
                for (int i=0;i<todos.size();i++){
                    json.put(i,new JSONObject(todos.get(i)));
                }
                response.getWriter().println(json.toString());
            }else if (type.equals("finish")){
                String id = request.getParameter("id");
                if(todoDao.finishTodo(id)){
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("faild");
                }
            }else if (type.equals("remove")){
                String id = request.getParameter("id");
                System.out.println(id);
                if(todoDao.removeTodo(id)){
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("faild");
                }
            }else if(type.equals("cancel")){
                String id = request.getParameter("id");
                System.out.println(id);
                if(todoDao.activeTodo(id)){
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("faild");
                }
            }else if (type.equals("delete")){
                String id = request.getParameter("id");
                System.out.println(id);
                if(todoDao.deleteTodo(id)){
                    response.getWriter().println("success");
                } else {
                    response.getWriter().println("faild");
                }
            }
        }
        System.out.println("响应花费了"+(System.currentTimeMillis()-start)+"ms");
    }
}
