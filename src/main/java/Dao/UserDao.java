package Dao;
import bean.Setting;
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
//            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
//            con.commit();
            this.initSettings(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(con,stmt);
        }
        return true;
    }

    public User getUser(String phoneNumber){
        User user = null;
        Connection con = new Database().getConnection();
        String sql = String.format("select * from user where phoneNumber = %s",phoneNumber);
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
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
        user.setSetting(this.getSetting(user));
        return user;
    }

    private Setting getSetting(User user){
        Connection conn = null;
        Statement stmt = null;
        Setting setting = null;
        try {
            conn = new Database().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "select * from settings where user = "+user.getId();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                setting = new Setting();
                setting.setEmailService(rs.getBoolean("emailService"));
                setting.setAutoDeleteFinished(rs.getBoolean("autoDeleteFinished"));
                setting.setAutoDeleteRemoved(rs.getBoolean("autoDeleteRemoved"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn,stmt);
        }
        return setting;
    }

    private boolean initSettings(User user){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new Database().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            user = this.getUser(user.getPhoneNumber());
            String sql = "insert into settings(user) values ("+user.getId()+")";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            close(conn,stmt);
        }
        return true;
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();
        User user = new User();
//        user.setPhoneNumber("13770520587");
//        user.setPwd("123456");
        user = dao.getUser("18936023725");
        if(null != user){
            System.out.println("用户存在:");
            System.out.println(user.getSetting().toString());
        } else {
            System.out.println("未找到用户");
        }
    }
}
