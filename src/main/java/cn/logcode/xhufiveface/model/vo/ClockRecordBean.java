package cn.logcode.xhufiveface.model.vo;

import lombok.Data;

@Data
public class ClockRecordBean {

    public String startTime;
    public int startFlag; //0-未打卡，1-已打卡
    public String startMsg;

    public String endTime;
    public int endFlag;
    public String endMsg;

    public String today;

}
