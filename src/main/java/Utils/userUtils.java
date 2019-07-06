package Utils;
import bean.User;
import Dao.UserDao;

import javax.servlet.http.Cookie;


public class userUtils {

    public static User getUser(Cookie[] cookies){
        User user = null;
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("user")){
                user = new UserDao().getUser(cookies[i].getValue());
                break;
            }
        }
        return user;
    }
}
