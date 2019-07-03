package Utils;
import java.sql.*;


public class Database {
    private String url = "jdbc:mysql://134.175.104.191:3306/kiwitodo";
    private String user = "root";
    private String pwd = "19972279999";
    private Connection con = null;

    public Database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pwd);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("数据库装载失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.con;
    }

    public static void main(String[] args) {
        Database db = new Database();
    }
}
