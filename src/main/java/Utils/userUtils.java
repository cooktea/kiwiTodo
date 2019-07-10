package Utils;
import bean.User;
import Dao.UserDao;

import javax.servlet.http.Cookie;


public class userUtils {

    public static User getUser(Cookie[] cookies){
        User user = null;
        long start = System.currentTimeMillis();
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("user")){
                System.out.println("从cookies中获取user cookie使用了"+(System.currentTimeMillis()-start)+"ms");
                user = new UserDao().getUser(cookies[i].getValue());
                break;
            }
        }
        System.out.println("实例化user对象使用了"+(System.currentTimeMillis()-start)+"ms");
        return user;
    }

}
