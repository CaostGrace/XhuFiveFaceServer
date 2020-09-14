package cn.logcode.xhufiveface.service;


import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.UserDao;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import cn.logcode.xhufiveface.model.vo.UserInfoBean;
import cn.logcode.xhufiveface.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Autowired
    SchoolService schoolService;

    public UserInfoBean getUserInfo(int userId) {
        FaceUser user = userDao.getUserById(userId);
        if (user == null) {
            throw new BaseException(ResultCode.FAILED.getCode(), "用户不存在");
        }

        LoginAuthBean authBean = new LoginAuthBean();
        authBean.setAccess_token(user.getAccessToken());
        authBean.setExpires_in(user.getExpiresTime().getTime()+"");

        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setAuth(authBean);
        userInfoBean.setCreateTime(user.getCreateTime());
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
        return null;
    }

    public boolean addUser(){
        return true;
    }


}
