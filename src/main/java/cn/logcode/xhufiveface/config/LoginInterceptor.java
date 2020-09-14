package cn.logcode.xhufiveface.config;


import cn.logcode.xhufiveface.AppConstant;
import cn.logcode.xhufiveface.dao.UserDao;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @package cn.logcode.demo.interceptor
 * @ClassName LoginInterceptors
 * @Author caost
 * @Date 2020/4/9 20:20
 * @Emall caostgrace@163.com
 * @DESC
 */

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public final Log logger = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(AppConstant.ACCESS_TOKEN);

        if (token == null || token.equals("")) {
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }

        logger.info(token);
        FaceUser userData = userDao.getUserByAccessToken(token);

        if(userData == null){
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }
        //用户被禁用
        if(userData.getProhibit() == AppConstant.PROHIBIT_TRUE){
            throw new BaseException(ResultCode.USER_DISABLED);
        }

        //登录过期
        if(userData.getExpiresTime().getTime() < System.currentTimeMillis()){
            throw new BaseException(ResultCode.UNAUTHORIZED);
        }

        request.setAttribute(AppConstant.LOGIN_USER_ID,userData.getUserId());

        FaceUser tmp = new FaceUser();
        tmp.setUserId(userData.getUserId());
        tmp.setLoginIp(ApplicationTool.getClientRequestIp());
        userDao.updateUserById(tmp);

        return true;
    }
}
