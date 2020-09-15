package cn.logcode.xhufiveface.service;


import cn.logcode.xhufiveface.AppConstant;
import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.UserDao;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.dto.RegisterUser;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import cn.logcode.xhufiveface.model.vo.UserInfoBean;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @package cn.logcode.demo.service.wx
 * @ClassName UserService
 * @Author caost
 * @Date 2020/4/15 19:56
 * @Emall caostgrace@163.com
 * @DESC
 */

@Service
public class UserService {


    @Autowired
    UserDao userDao;



    public UserInfoBean getUserInfo(int userId) {
        FaceUser user = userDao.getUserById(userId);
        if (user == null) {
            throw new BaseException(ResultCode.FAILED.getCode(), "用户不存在");
        }

        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserBir(user.getUserBir());
        userInfoBean.setUserEmail(user.getUserEmail());
        userInfoBean.setUserHead(user.getUserHead());
        userInfoBean.setUserId(user.getUserId());
        userInfoBean.setUserNick(user.getUserNick());
        userInfoBean.setUserPhone(user.getUserPhone());
        userInfoBean.setUserPwd(user.getUserPwd());
        userInfoBean.setUserReal(user.getUserReal());
        userInfoBean.setUserSex(user.getUserSex());

        return userInfoBean;
    }

    public FaceUser userLogin(String userName,String pwd){
        FaceUser faceUser;
        if ((faceUser = userDao.getUserByNickAndPwd(userName,pwd)) != null) {

            if(faceUser.getProhibit() == AppConstant.PROHIBIT_TRUE){
                throw new BaseException(ResultCode.USER_DISABLED);
            }
            return faceUser;
        }

        throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR.code,"账号或密码错误");
    }


    public boolean addUser(RegisterUser registerUser)
    {
        FaceUser faceUser = new FaceUser();
        faceUser.setUserNick(registerUser.getUserNick());
        faceUser.setUserPwd(registerUser.getPwd());
        faceUser.setGroupId(registerUser.getGroupId());
        return userDao.addUser(faceUser);
    }


    public boolean updateUserAccessToken(int userId,LoginAuthBean loginAuthBean){
        FaceUser user = new FaceUser();
        user.setUserId(userId);
        user.setAccessToken(loginAuthBean.getAccess_token());
        user.setExpiresTime(new Date(Long.parseLong(loginAuthBean.getExpires_in())));
        user.setLoginIp(ApplicationTool.getClientRequestIp());
        if(userDao.updateUserById(user)){
            return true;
        }
        throw new BaseException(ResultCode.SERVER_ERROR);
    }


    public boolean updateUserInfo(UserInfoBean userInfoBean){
        FaceUser user = new FaceUser();
        user.setUserId(userInfoBean.getUserId());
        if(userInfoBean.getUserBir() != null){
            user.setUserBir(userInfoBean.getUserBir());
        }
        if(userInfoBean.getUserEmail() != null){
            user.setUserEmail(userInfoBean.getUserEmail());
        }
        if(userInfoBean.getUserHead() != null){
            user.setUserHead(userInfoBean.getUserHead());
        }
        if(userInfoBean.getUserNick() != null){
            user.setUserNick(userInfoBean.getUserNick());
        }
        if(userInfoBean.getUserPhone() != null){
            user.setUserPhone(userInfoBean.getUserPhone());
        }
        if(userInfoBean.getUserPwd() != null){
            user.setUserPwd(userInfoBean.getUserPwd());
        }
        if(userInfoBean.getUserReal() != null){
            user.setUserReal(userInfoBean.getUserReal());
        }
        if(userInfoBean.getUserSex() != null){
            user.setUserSex(userInfoBean.getUserSex());
        }

        return userDao.updateUserById(user);
    }





}
