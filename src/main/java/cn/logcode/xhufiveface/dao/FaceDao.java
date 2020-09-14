package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.mapper.FaceGroupMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceGroup;
import cn.logcode.xhufiveface.dao.pojo.FaceGroupExample;
import cn.logcode.xhufiveface.result.ResultCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class FaceDao {
    public final Log logger = LogFactory.getLog(FaceDao.class);

    @Autowired
    FaceGroupMapper faceGroupMapper;


    /**
     * 添加组
     * @param groupName
     * @return
     */
    public boolean addGroup(String groupName){

        if(getGroupByName(groupName) != null){
            logger.warn("用户组已存在");
            throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);
        }

        FaceGroup group = new FaceGroup();
        group.setGroupName(groupName);
        group.setCreateTime(new Date());
        return faceGroupMapper.insertSelective(group) == 1;
    }


    /**
     * 获取用户组
     * @param groupName
     * @return
     */
    public FaceGroup getGroupByName(String groupName){
        FaceGroupExample example = new FaceGroupExample();
        FaceGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameEqualTo(groupName);

        List<FaceGroup> groups = faceGroupMapper.selectByExample(example);

        if(groups.size() != 0){
            return groups.get(0);
        }
        return null;
    }

    public FaceGroup getGroupById(int id){
        return faceGroupMapper.selectByPrimaryKey(id);
    }

    public boolean  deleteGroupByName(String groupName){
        FaceGroupExample example = new FaceGroupExample();
        FaceGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameEqualTo(groupName);
        return faceGroupMapper.deleteByExample(example) == 1;
    }

    public boolean  deleteGroupById(int id){
        return faceGroupMapper.deleteByPrimaryKey(id) == 1;
    }

    /**
     * 获取所有用户组
     * @return
     */
    public List<FaceGroup> getAllGroups(){
        return faceGroupMapper.selectByExample(null);
    }

}
