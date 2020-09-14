package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.dao.mapper.FaceUserMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.dao.pojo.FaceUserExample;
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


    /**
     *
     * @param user
     * @return
     */
    public FaceUser getUser(FaceUser user) {
        FaceUserExample userExample = new FaceUserExample();
        FaceUserExample.Criteria criteria = userExample.createCriteria();

        if(user.getUserBir() != null){
            criteria.andUserBirEqualTo(user.getUserBir());
        }
        if(user.getUserId() != null){
            criteria.andUserIdEqualTo(user.getUserId());
        }
        criteria.andUserNickEqualTo(user.getUserNick())
                .andUserSexEqualTo(user.getUserSex())
                .andUserHeadEqualTo(user.getUserHead());
        List<FaceUser> userList = userMapper.selectByExample(userExample);
        return userList.size() == 0 ? null : userList.get(0);
    }

    public FaceUser getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
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
