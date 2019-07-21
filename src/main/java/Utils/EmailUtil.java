package Utils;

import Dao.TodoDao;
import bean.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/18
 * Info    :    java邮件功能
 */

public class EmailUtil {
    private final String FROM = "KiwiTodo@163.com";
    private final String PASSWORD = "19972279999CK";
    private final String CODING = "UTF-8";
    private final String SMTP_HOST = "smtp.163.com";
    private final String PROTOCOL = "smtp";

    public static void main(String[] args) throws IOException {
        EmailUtil util = new EmailUtil();
        User user = new User();
        user.setId("1");
        user.setEmail("mangrandemihoutao@163.com");
        user.setUserName("茫然的猕猴桃");
        util.sentEamil(user);
    }

    public String getEmailContent(List<bean.todoItem> todos,User user) throws IOException {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(System.getProperty("user.dir"));
        pathBuilder.append("/src/other/templates/emailTemplate.html");
        File file = new File(pathBuilder.toString());
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder contentBuilder = new StringBuilder();
        String s = null;
        do {
            if (s != null){
                if (s.indexOf("{{todos}}")!=-1){
                    int count = 0;
                    StringBuilder todosBuilder = new StringBuilder();
                    for (int i=0;i<todos.size();i++){
                        todosBuilder.append(s.replaceAll("\\{\\{todos\\}\\}",todos.get(i).getContent()));
                        todosBuilder.append("\n\r");
                        count++;
                        if (count == 10){
                            break;
                        }
                    }
                    s = s.replaceAll("\\{\\{todos\\}\\}","...........");
                    todosBuilder.append(s);
                    todosBuilder.append("\n\r");
                    s = todosBuilder.toString();
                }
                if (s.indexOf("{{userName}}")!=-1){
                    s = s.replaceAll("\\{\\{userName\\}\\}",user.getUserName());
                }
                if (s.indexOf("{{Date}}")!=-1){
                    Calendar calendar = Calendar.getInstance();
                    String date = calendar.get(Calendar.YEAR)+"年"+calendar.get(Calendar.MONTH)+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
                    s = s.replaceAll("\\{\\{Date\\}\\}",date);
                }
                contentBuilder.append(s);
                contentBuilder.append("\n\r");
            }
            s = reader.readLine();
        } while (s != null);
        return contentBuilder.toString();
    }

    public boolean sentEamil(User user) throws IOException {
        TodoDao dao = new TodoDao();
        dao.setUser(user);
        List<bean.todoItem> todos = dao.getTodos(1);
        String content = getEmailContent(todos,user);
        boolean success = true;
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host",SMTP_HOST);
        props.setProperty("mail.smtp.auth","true");
        Session session = Session.getInstance(props);
        try {
            MimeMessage message = getMessage(user.getEmail(),user.getUserName()==null?"":user.getUserName(),FROM,content,session);
            sent(session,message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            success = false;
        } catch (MessagingException e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }




    private void sent(Session session,MimeMessage message) throws MessagingException {
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect("KiwiTodo@163.com","19972279999CK");
            transport.sendMessage(message,message.getAllRecipients());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (null != transport){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private MimeMessage getMessage(String to, String toUser,String from, String content, Session session) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from,"KiwoTodo","UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to,toUser,"UTF-8"));
        message.setSubject("Todo通知","UTF-8");
        message.setContent(content,"text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
