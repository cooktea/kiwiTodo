package Task;

import Dao.SettingDao;
import Utils.EmailUtil;
import bean.Setting;
import bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/21
 * Info    :
 */

public class EmailTask {
    public static void main(String[] args)
    {


    }

    public void doTask(List<User> users){
        EmailUtil emailUtil = new EmailUtil();
        for (User user:users){
            try{
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
