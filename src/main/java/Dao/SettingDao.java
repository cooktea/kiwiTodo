package Dao;

import Utils.Database;
import bean.Setting;
import bean.User;

import java.sql.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/14
 * Info    :
 */

public class SettingDao extends myDao{

    public static void main(String[] args) {
        User user = new UserDao().getUser("18936023725");
        try {
            System.out.println(new SettingDao().getSettings(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean modifiySetting(int id,String col,String cmd){
        Connection conn = null;
        PreparedStatement stmt = null;
        String status = cmd.equals("open") ? "true":"false";
        String sql = "update settings set "+ col +" = " + status + " where user = ?";
        try {
            conn = new Database().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Setting getSettings(User user) throws Exception{
        if (user.getSetting() == null){
            Connection conn = null;
            Statement stmt = null;
            String sql = "select * from settings where user = "+user.getId();
            Setting s = null;
            try {
                conn = new Database().getConnection();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                s = new Setting();
                while (rs.next()){
                    s.setAutoDeleteRemoved(rs.getBoolean("autoDeleteRemoved"));
                    s.setAutoDeleteFinished(rs.getBoolean("autoDeleteFinished"));
                    s.setEmailService(rs.getBoolean("emailService"));
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(conn,stmt);
            }
            user.setSetting(s);
            if (s == null){
                throw new Exception();
            }
        }
        return user.getSetting();
    }

    public boolean initSettings(User user){
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new Database().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            user = new UserDao().getUser(user.getPhoneNumber());
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

}
