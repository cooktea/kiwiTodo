package cn.todo.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        EmailUtil util = new EmailUtil();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("E:\\桌面文件\\kiwiTodo\\src\\other\\templates\\emailTest.html")));
            String s = null;
            do {
                if(s!=null){
                    sb.append(s);
                }
                s = reader.readLine();
            } while (s!=null);
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        util.sentEamil("623285624@qq.com","小陈同学",sb.toString());
    }

    public boolean sentEamil(String to,String toUser,String content){
        boolean success = true;
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host",SMTP_HOST);
        props.setProperty("mail.smtp.auth","true");
        Session session = Session.getInstance(props);
        try {
            MimeMessage message = getMessage(to,toUser==null?"":toUser,FROM,content,session);
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
        message.setSubject("JAVA EMAIL TEST","UTF-8");
        message.setContent(content,"text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }




}
