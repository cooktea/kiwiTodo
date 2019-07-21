package Task;

import Dao.SettingDao;
import Dao.UserDao;
import bean.Setting;
import bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/21
 * Info    :
 */

public class Tasks {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        List<User> users = dao.getUsers();
        List<User> autoDeleteRemoved = new ArrayList<>();
        List<User> autoDeleteFinished = new ArrayList<>();
        List<User> doEmailService = new ArrayList<>();
        for (User user : users){
            if (user.getSetting().isEmailService()){
                doEmailService.add(user);
            }
            if (user.getSetting().isAutoDeleteFinished()){
                autoDeleteFinished.add(user);
            }
            if (user.getSetting().isAutoDeleteRemoved()){
                autoDeleteRemoved.add(user);
            }
        }
        EmailTask emailTask = new EmailTask();
        emailTask.doTask(doEmailService);
    }
}
