package servlet;

import Dao.SettingDao;
import Utils.userUtils;
import bean.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Setting")
public class Setting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SettingDao dao = new SettingDao();
        User user = userUtils.getUser(request.getCookies());
        String type = request.getParameter("type");

        if("modifiy".equals(type)){
            String col = request.getParameter("para");
            String cmd = request.getParameter("command");
            if (dao.modifiySetting(Integer.parseInt(user.getId()),col,cmd)){
                response.getWriter().println("success");
            } else {
                response.getWriter().println("faild");
            }
        } else if ("getSettings".equals(type)){
            try {
                bean.Setting s = dao.getSettings(user);
                response.getWriter().println(s.toJSON());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
