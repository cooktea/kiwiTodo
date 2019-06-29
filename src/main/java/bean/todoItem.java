package bean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class todoItem {
    private String level;
    private String content;
    private String time;
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

    public String getTime() {
        return time;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return level;
    }

    public String getContent() {
        return content;
    }

}
