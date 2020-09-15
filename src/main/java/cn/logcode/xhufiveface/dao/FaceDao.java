package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.mapper.FaceGroupMapper;
import cn.logcode.xhufiveface.dao.mapper.FaceLibraryMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceGroup;
import cn.logcode.xhufiveface.dao.pojo.FaceGroupExample;
import cn.logcode.xhufiveface.dao.pojo.FaceLibrary;
import cn.logcode.xhufiveface.dao.pojo.FaceLibraryExample;
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

    @Autowired
    FaceLibraryMapper faceLibraryMapper;

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


    public boolean addFaceLibrary(FaceLibrary faceLibrary){
        return faceLibraryMapper.insertSelective(faceLibrary) == 1;
    }

    public boolean deleteFaceLibraryByFaceToken(String faceToken){
        FaceLibraryExample example = new FaceLibraryExample();
        FaceLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andFaceTokenEqualTo(faceToken);
        return faceLibraryMapper.deleteByExample(example) != -1;

    }

    public boolean deleteFaceLibraryByUserId(int userId){
        FaceLibraryExample example = new FaceLibraryExample();
        FaceLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return faceLibraryMapper.deleteByExample(example) != -1;
    }

    public boolean deleteFaceLibraryByGroupId(int groupId){
        FaceLibraryExample example = new FaceLibraryExample();
        FaceLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return faceLibraryMapper.deleteByExample(example) != -1;
    }

    public List<FaceLibrary> getFaceDataByUserId(int userId){
        FaceLibraryExample example = new FaceLibraryExample();
        FaceLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return faceLibraryMapper.selectByExample(example);

    }
    public List<FaceLibrary> getFaceDataByGroupId(int groupId){

        FaceLibraryExample example = new FaceLibraryExample();
        FaceLibraryExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        return faceLibraryMapper.selectByExample(example);

    }


}
