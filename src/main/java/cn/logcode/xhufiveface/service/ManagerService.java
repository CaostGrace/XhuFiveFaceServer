package cn.logcode.xhufiveface.service;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.ManagerDao;
import cn.logcode.xhufiveface.dao.pojo.FaceManager;
import cn.logcode.xhufiveface.dao.pojo.FaceUser;
import cn.logcode.xhufiveface.model.vo.LoginAuthBean;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ManagerService {

    @Resource
    ManagerDao managerDao;

    public FaceManager login(String userName, String pwd){
        FaceManager manager = managerDao.login(userName,pwd);
        if(manager == null){
            throw new BaseException(ResultCode.FAILED.code,"登录失败");
        }
        return manager;
    }

    public FaceManager getManagerInfo(int id){
        FaceManager manager = managerDao.getById(id);

        if(manager == null){
            throw new BaseException(ResultCode.FAILED);
        }
        return manager;

    }

    public List<FaceManager> getAllManager(){
        return managerDao.getAllManager();
    }

    public boolean addManager(String name,String pwd){
        if(managerDao.addManager(name,pwd)){
            return true;
        }
        throw new BaseException(ResultCode.FAILED);
    }
    public boolean updateManagerNameAndPwd(int id,String name,String pwd){

        FaceManager manager = new FaceManager();
        manager.setId(id);
        manager.setUserNick(name);
        manager.setUserPwd(pwd);
        if(managerDao.updateManager(manager)){
            return true;
        }
        throw new BaseException(ResultCode.FAILED);
    }

    public boolean updateManagerAccessToken(int id, LoginAuthBean loginAuthBean){
        FaceManager manager = new FaceManager();
        manager.setId(id);
        manager.setAccessToken(loginAuthBean.getAccess_token());
        manager.setExpiresTime(new Date(Long.parseLong(loginAuthBean.getExpires_in())));

        if(managerDao.updateManager(manager)){
            return true;
        }
        throw new BaseException(ResultCode.SERVER_ERROR);
    }


}
