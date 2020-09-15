package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.mapper.FaceManagerMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceManager;
import cn.logcode.xhufiveface.dao.pojo.FaceManagerExample;
import cn.logcode.xhufiveface.result.ResultCode;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Repository
public class ManagerDao {

    @Resource
    FaceManagerMapper managerMapper;

    public FaceManager login(String name, String pwd){
        FaceManagerExample example = new FaceManagerExample();
        FaceManagerExample.Criteria criteria = example.createCriteria();
        criteria.andUserNickEqualTo(name).andUserPwdEqualTo(pwd);
        List<FaceManager> list = managerMapper.selectByExample(example);
        return list.size() == 0?null:list.get(0);
    }

    public FaceManager getById(int id){
        return managerMapper.selectByPrimaryKey(id);
    }

    public List<FaceManager> getAllManager(){
        return managerMapper.selectByExample(null);
    }

    public boolean addManager(String name,String pwd){

        if(getByName(name) != null){
            throw new BaseException(ResultCode.FAILED);
        }
        FaceManager manager = new FaceManager();
        manager.setUserNick(name);
        manager.setUserPwd(pwd);
        manager.setCreateTime(new Date());
        return managerMapper.insertSelective(manager) == 1;
    }

    public boolean updateManager(FaceManager manager){
        return managerMapper.updateByPrimaryKeySelective(manager) == 1;
    }

    public FaceManager getByName(String name){
        FaceManagerExample example = new FaceManagerExample();
        FaceManagerExample.Criteria criteria = example.createCriteria();
        criteria.andUserNickEqualTo(name);
        List<FaceManager> list = managerMapper.selectByExample(example);
        return list.size() == 0?null:list.get(0);
    }

    public FaceManager getByAccessToken(String token){
        FaceManagerExample example = new FaceManagerExample();
        FaceManagerExample.Criteria criteria = example.createCriteria();
        criteria.andAccessTokenEqualTo(token);
        List<FaceManager> list = managerMapper.selectByExample(example);
        return list.size() == 0?null:list.get(0);
    }
}
