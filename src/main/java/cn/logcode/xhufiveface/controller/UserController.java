package cn.logcode.xhufiveface.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
