package Utils;
import java.sql.*;


public class Database {
    private String url = "jdbc:mysql://134.175.104.191:3306/kiwitodo";
    private String user = "root";
    private String pwd = "19972279999";
    private Connection con = null;

    public Database(){
        long start = System.currentTimeMillis();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println(System.currentTimeMillis()-start);
            //todo 实例化Databa对象时获取连接用时过久
            con = DriverManager.getConnection(url,user,pwd);
            System.out.println(System.currentTimeMillis()-start);
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
        long start = System.currentTimeMillis();
        Database db = new Database();
//        System.out.println(System.currentTimeMillis()-start);
//        Connection conn = db.getConnection();
//        System.out.println(System.currentTimeMillis()-start);
//        Connection conn2 = db.getConnection();
//        System.out.println(System.currentTimeMillis()-start);
    }
}
