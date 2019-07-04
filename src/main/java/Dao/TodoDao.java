package Dao;
import Utils.Database;
import bean.todoItem;
import bean.User;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TodoDao extends myDao{
    private todoItem todo = null;
    private User user = null;
    private Database db = new Database();

    public List<todoItem> getTodos(){
        Connection con = db.getConnection();
        Statement statement = null;
        String sql = String.format("select * from todo where user = %s",user.getId());
        List<todoItem> todos = new ArrayList<>();
        try {
            statement = con.createStatement();
            ResultSet res = statement.executeQuery(sql);
            int count = 0;
            while (res.next()){
                todoItem todo = new todoItem();
                todo.setContent(res.getString("content"));
                todo.setLevel(res.getString("level"));
                todo.setStstus(res.getString("status"));
                todo.setTime(res.getString("time"));
                todo.setId(count);
                count++;
                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con,statement);
        }
        return todos;
    }

    public boolean push(){
        Connection con = db.getConnection();
        Statement statement = null;
        String sql = String.format("insert into todo(user,content,level,time) values (%s,\"%s\",%s,\"%s\")",user.getId(),todo.getContent(),todo.getLevel(),todo.getTime());
//        System.out.println(sql);
        try {
            statement = con.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con,statement);
        }
        return true;
    }

    public TodoDao(){

    }

    public TodoDao(todoItem todo, User user) {
        this.todo = todo;
        this.user = user;
    }

    public TodoDao(todoItem todo) {
        this.todo = todo;
    }

    public TodoDao(User user) {
        this.user = user;
    }

    public void setTodo(todoItem todo) {
        this.todo = todo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public todoItem getTodo() {
        return todo;
    }

    public User getUser() {
        return user;
    }

    public static void main(String[] args) {
        User user = new User("18936023725","19972279999");
        user.setId("1");
        TodoDao dao = new TodoDao(user);
        List<todoItem> todos = dao.getTodos();
        for (todoItem todo:todos){
            System.out.println(new JSONObject(todo).toString());
        }
    }
}
