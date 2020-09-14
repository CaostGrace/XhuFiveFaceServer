package cn.logcode.xhufiveface.controller.core;

import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.dto.LoginBean;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import cn.logcode.xhufiveface.model.vo.UserInfoBean;
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
    public CommonResult<LoginAuthBean> wxLoginAuth(@Validated LoginBean loginBean) throws Exception {

        FaceUser user = userService.userLogin(loginBean.userName,loginBean.pwd);

        LoginAuthBean loginAuthBean = ApplicationTool.createLoginToken(user);

        return CommonResult.success(loginAuthBean, "登录成功");
    }

}
