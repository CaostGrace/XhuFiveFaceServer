package cn.logcode.xhufiveface.controller;


import cn.logcode.xhufiveface.annotation.CurrentUser;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.vo.ValidTest;
import cn.logcode.xhufiveface.service.core.CoreStorageService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Validated
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    CoreStorageService storageService;

    @Autowired
    ApplicationTool springBeanTool;



    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        logger.info("Hello world");

        logger.info(request.getScheme());
        logger.info(request.getServerPort() + "");
        logger.info(request.getRemoteHost());
        logger.info(request.getLocalAddr());
        logger.info(request.getLocalPort() + "");


        logger.info(springBeanTool.getServletContext().getContextPath());
        logger.info(springBeanTool.getServletContext().getServerInfo());

        return "Hello World!";
    }



    @GetMapping("/api/v1/loginInterceptorTest")
    public String loginInterceptorTest() {
        return "Hello world";
    }

    @GetMapping("/api/v1/validTest")
    public String validTest(@Validated ValidTest validTest) {
        return "Hello world";
    }



    @RequestMapping("/api/v1/testlogin")
    public Object loginTest(@CurrentUser FaceUser user, @Validated ValidTest validTest) {
        logger.info(user.toString());
        logger.info(validTest.toString());
        return null;
    }

}
