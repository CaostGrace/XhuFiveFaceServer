package cn.logcode.xhufiveface.model.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class AccessTokenBean {
    public int userId;
    public String userPhone;
    public String userEmail;
    public Date createTime;
    public String userPwd;
    public String userNick;
    public String userReal;
    public Integer userSex;
    //当前时间
    public long currentTime;
    //时长
    public long timeDuration;
    //过期时间
    public long expiresTime;
}
