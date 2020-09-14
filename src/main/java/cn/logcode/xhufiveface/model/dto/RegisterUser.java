package cn.logcode.xhufiveface.model.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RegisterUser {

    @NotNull(message = "传入的用户昵称为null，请传值")
    @NotEmpty(message = "传入的用户昵称为空字符串，请传值")
    public String userNick;
    @NotNull(message = "传入的用户密码为null，请传值")
    @NotEmpty(message = "传入的用户密码为空字符串，请传值")
    public String pwd;
    @NotNull(message = "传入的组id为null，请传值")
    public int groupId;
}
