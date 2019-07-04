package bean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.*;


public class todoItem {
    private String level;
    private String content;
    private String time;
    private String ststus;
    private int id;
    private Date now;
    private DateFormat date;
    public todoItem(){
    }
    public todoItem(String level,String content){
        this.level = level;
        this.content = content;
        this.now = new Date();
        this.date = new SimpleDateFormat("yyyy-MM-dd");
        this.time = date.format(now);
    }

    @Override
    public String toString() {
        return "todoItem{" +
                "level='" + level + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStstus(String ststus) {
        this.ststus = ststus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getStstus() {
        return ststus;
    }

    public int getId() {
        return id;
    }


    public static void main(String[] args) {
        todoItem todo = new todoItem("1","something need to do");
        todo.setStstus("1");
        System.out.println(new JSONObject(todo).toString());
    }

}
