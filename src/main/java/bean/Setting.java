package bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/14
 * Info    :    用户个人设置
 */

public class Setting {
    private boolean emailService;
    private boolean autoDeleteFinished;
    private boolean autoDeleteRemoved;

    public Setting(boolean emailService, boolean autoDeleteFinished, boolean autoDeleteRemoved) {
        this.emailService = emailService;
        this.autoDeleteFinished = autoDeleteFinished;
        this.autoDeleteRemoved = autoDeleteRemoved;
    }

    public Setting() {

    }

    public JSONArray toJSON(){
        JSONArray json = new JSONArray();
        Map<String,Object> col = new HashMap<>();
        col.put("name","emailService");
        col.put("value",this.emailService);
        json.put(new JSONObject(col));
        col.clear();
        col.put("name","autoDeleteFinished");
        col.put("value",this.autoDeleteFinished);
        json.put(new JSONObject(col));
        col.clear();
        col.put("name","autoDeleteRemoved");
        col.put("value",this.autoDeleteRemoved);
        json.put(new JSONObject(col));
        col.clear();
        return json;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "emailService=" + emailService +
                ", autoDeleteFinished=" + autoDeleteFinished +
                ", autoDeleteRemoved=" + autoDeleteRemoved +
                '}';
    }

    public void setEmailService(boolean emailService) {
        this.emailService = emailService;
    }

    public void setAutoDeleteFinished(boolean autoDeleteFinished) {
        this.autoDeleteFinished = autoDeleteFinished;
    }

    public void setAutoDeleteRemoved(boolean autoDeleteRemoved) {
        this.autoDeleteRemoved = autoDeleteRemoved;
    }

    public boolean isEmailService() {
        return emailService;
    }

    public boolean isAutoDeleteFinished() {
        return autoDeleteFinished;
    }

    public boolean isAutoDeleteRemoved() {
        return autoDeleteRemoved;
    }
}
