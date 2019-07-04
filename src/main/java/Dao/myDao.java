package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class myDao {
    public void close(Connection con, Statement stmt){
        if(null != stmt){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != con){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
