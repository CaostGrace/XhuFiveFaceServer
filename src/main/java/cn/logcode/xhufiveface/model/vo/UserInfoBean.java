package cn.logcode.xhufiveface.model.vo;

import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @package cn.logcode.demo.model.dto
 * @ClassName UserInfoBean
 * @Author caost
 * @Date 2020/4/9 20:43
 * @Emall caostgrace@163.com
 * @DESC
 */

@Data
public class UserInfoBean {

    public LoginAuthBean auth;
    public Integer userId;
    public String userNick;
    public String userReal;
    public Integer userSex;
    public String userHead;
    public Date userBir;
    public String userPhone;
    public String userEmail;
    public Date createTime;
    public String userPwd;
    public boolean isManager = false;
}
