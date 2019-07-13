package bean;

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
