package cn.logcode.xhufiveface.service.core;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.FaceDao;
import cn.logcode.xhufiveface.dao.pojo.FaceGroup;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import com.baidu.aip.face.AipFace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class FaceService {
    public final Log logger = LogFactory.getLog(FaceService.class);

    @Autowired
    FaceDao faceDao;


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean addGroup(String groupName){
        if (faceDao.addGroup(groupName)) {
            AipFace client = ApplicationTool.getBaiduAiFaceInstance();
            // 传入可选参数调用接口
            HashMap<String, String> options = new HashMap<String, String>();
            // 创建用户组
            JSONObject res = client.groupAdd(groupName, options);
            if(res.getInt("error_code") == 0){
                return true;
            }
        }
        throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean deleteGroup(int groupId){
        FaceGroup group = faceDao.getGroupById(groupId);
        if(group != null){
            if (faceDao.deleteGroupById(groupId)) {
                AipFace client = ApplicationTool.getBaiduAiFaceInstance();
                // 传入可选参数调用接口
                HashMap<String, String> options = new HashMap<String, String>();
                // 删除用户组
                JSONObject res = client.groupDelete(group.getGroupName(), options);
                if(res.getInt("error_code") == 0){
                    return true;
                }
            }
        }
        throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);

    }


}
