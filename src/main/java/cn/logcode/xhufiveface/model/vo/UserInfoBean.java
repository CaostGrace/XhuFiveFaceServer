package cn.logcode.xhufiveface.model.vo;

import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "传入的用户id为null，请传值")
    public Integer userId;
    public String userNick;
    public String userReal;
    public Integer userSex;
    public String userHead;
    public Date userBir;
    public String userPhone;
    public String userEmail;
    public String userPwd;
}
