package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.dao.mapper.FaceManagerMapper;
import cn.logcode.xhufiveface.dao.mapper.FaceUserMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceManager;
import cn.logcode.xhufiveface.dao.pojo.FaceManagerExample;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.dao.pojo.FaceUserExample;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @package cn.logcode.demo.dao
 * @ClassName UserDao
 * @Author caost
 * @Date 2020/4/10 16:58
 * @Emall caostgrace@163.com
 * @DESC
 */

@Repository
public class UserDao {
    public final Log logger = LogFactory.getLog(UserDao.class);

    @Resource
    FaceUserMapper userMapper;

    @Resource
    FaceManagerMapper faceManagerMapper;

    /**
     *
     * @param user
     * @return
     */
    public FaceUser getUser(FaceUser user) {
        FaceUserExample userExample = new FaceUserExample();
        FaceUserExample.Criteria criteria = userExample.createCriteria();

        List<FaceUser> userList = userMapper.selectByExample(userExample);
        return userList.size() == 0 ? null : userList.get(0);
    }

    public FaceUser getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public FaceUser getUserByNickAndPwd(String nick,String pwd){
        FaceUserExample userExample = new FaceUserExample();
        FaceUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNickEqualTo(nick).andUserPwdEqualTo(pwd);
        List<FaceUser> userList = userMapper.selectByExample(userExample);
        return userList.size() == 0 ? null : userList.get(0);
    }

    public boolean addUser(FaceUser user) {
        return userMapper.insertSelective(user) == 1;
    }

    public boolean updateUserById(FaceUser user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }



    public FaceUser getUserByAccessToken(String token) {
        FaceUserExample userDataExample = new FaceUserExample();
        FaceUserExample.Criteria criteria = userDataExample.createCriteria();
        criteria.andAccessTokenEqualTo(token);
        List<FaceUser> userData = userMapper.selectByExample(userDataExample);
        return userData.size() == 1 ? userData.get(0) : null;
    }
    
}
