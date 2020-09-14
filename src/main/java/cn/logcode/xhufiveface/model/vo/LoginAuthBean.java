package cn.logcode.xhufiveface.model.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginAuthBean {
	public String access_token;
	public String expires_in;
}
