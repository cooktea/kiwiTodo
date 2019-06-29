package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import bean.todoItem;


@WebServlet(name = "pushtodo")
public class pushtodo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("xxx");
        String level = request.getParameter("level");
        String conten = request.getParameter("content");
        todoItem todo = new todoItem(level,conten);
        response.getWriter().println(todo.toString());
        //todo 数据库操作
    }
}
