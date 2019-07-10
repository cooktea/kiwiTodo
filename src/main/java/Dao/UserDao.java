package Dao;
import bean.User;
import Utils.Database;
import java.sql.*;


public class UserDao extends myDao{
    public UserDao(){
    }
    public boolean login(User user){
        Connection con = new Database().getConnection();
        String sql = String.format("select * from user where phoneNumber = \"%s\" and password = \"%s\"",user.getPhoneNumber(),user.getPwd());
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while(res.next()){
                user.setId(res.getString("id"));
                user.setPhoneNumber(res.getString("phoneNumber"));
                user.setPwd(res.getString("password"));
                user.setEmail(res.getString("email"));
                user.setUserName("name");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con,stmt);
        }
        return false;
    }

    public boolean register(User user){
        Connection con = new Database().getConnection();
        String sql = String.format("insert into user(phoneNumber,password) values (\"%s\",\"%s\")",user.getPhoneNumber(),user.getPwd());
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con,stmt);
        }
        return true;
    }

    public User getUser(String phoneNumber){
        long start = System.currentTimeMillis();
        User user = null;
        Connection con = new Database().getConnection();
        System.out.println(System.currentTimeMillis()-start);
        String sql = String.format("select * from user where phoneNumber = %s",phoneNumber);
        Statement stmt = null;
        System.out.println(System.currentTimeMillis()-start);
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            System.out.println(System.currentTimeMillis()-start);
            while (res.next()){
                user = new User();
                user.setId(res.getString("id"));
                user.setPhoneNumber(res.getString("phoneNumber"));
                user.setPwd(res.getString("password"));
                user.setEmail(res.getString("email"));
                user.setUserName("name");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con,stmt);
        }
        System.out.println(System.currentTimeMillis()-start);
        return user;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        UserDao dao = new UserDao();
        System.out.println(System.currentTimeMillis()-start);
        User user = dao.getUser("18936023725");
        System.out.println(System.currentTimeMillis()-start);
        if(null != user){
            System.out.println("用户存在:");
            System.out.println(user.toString());
        } else {
            System.out.println("未找到用户");
        }
    }
}
