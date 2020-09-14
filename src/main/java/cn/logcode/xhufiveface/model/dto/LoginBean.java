package cn.logcode.xhufiveface.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginBean {

    @NotNull(message = "传入的用户名为null，请传值")
    @NotEmpty(message = "传入的用户名为空字符串，请传值")
    public String userName;
    @NotNull(message = "传入的密码为null，请传值")
    @NotEmpty(message = "传入的密码为空字符串，请传值")
    public String pwd;


}
