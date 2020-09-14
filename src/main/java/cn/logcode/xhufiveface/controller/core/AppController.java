package cn.logcode.xhufiveface.controller.core;

import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.dto.LoginBean;
import cn.logcode.xhufiveface.model.dto.RegisterUser;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.service.UserService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/app")
@Validated
public class AppController {

    @Resource
    UserService userService;


    @PostMapping(value = "/login")
    public CommonResult<LoginAuthBean> login(@Validated LoginBean loginBean) throws Exception {
        FaceUser user = userService.userLogin(loginBean.userName,loginBean.pwd);
        LoginAuthBean loginAuthBean = ApplicationTool.createLoginToken(user);
        userService.updateUserAccessToken(user.getUserId(),loginAuthBean);
        return CommonResult.success(loginAuthBean, "登录成功");
    }

    @PostMapping(value = "/register")
    public CommonResult register(@Validated RegisterUser registerUser){
        return CommonResult.success(userService.addUser(registerUser));
    }


}
