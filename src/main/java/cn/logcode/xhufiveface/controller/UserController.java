package cn.logcode.xhufiveface.controller;


import cn.logcode.xhufiveface.annotation.CurrentUser;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.vo.UserInfoBean;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @package cn.logcode.demo.controller.core
 * @ClassName LoginController
 * @Author caost
 * @Date 2020/4/9 19:00
 * @Emall caostgrace@163.com
 * @DESC
 */
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {

    public final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping(value = "/getUserInfo")
    public CommonResult<UserInfoBean> getUserInfo(@CurrentUser FaceUser user){
        return CommonResult.success(userService.getUserInfo(user.getUserId()));
    }

    @GetMapping(value = "/getUserInfoById")
    public CommonResult<UserInfoBean> getUserInfoById(@RequestParam(value = "userId",required = true)int userId){
        return CommonResult.success(userService.getUserInfo(userId));
    }


    @PostMapping(value = "/updateUserInfo")
    public CommonResult updateUserInfo(@CurrentUser FaceUser user,UserInfoBean userInfoBean){
        userInfoBean.setUserId(user.getUserId());
        return CommonResult.success(userService.updateUserInfo(userInfoBean));
    }
    @PostMapping(value = "/updateUserInfoById")
    public CommonResult updateUserInfoById(@RequestParam(value = "userId",required = true)int userId,UserInfoBean userInfoBean){
        return CommonResult.success(userService.updateUserInfo(userInfoBean));
    }





}
