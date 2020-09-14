package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.mapper.*;
import cn.logcode.xhufiveface.dao.pojo.*;
import cn.logcode.xhufiveface.result.ResultCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package cn.logcode.demo.dao
 * @ClassName SchoolDao
 * @Author caost
 * @Date 2020/4/15 10:33
 * @Emall caostgrace@163.com
 * @DESC
 */

@Repository
public class SchoolDao {
    public final Log logger = LogFactory.getLog(SchoolDao.class);

    @Autowired
    FaceSchoolUserMapper schoolUserMapper;

    @Autowired
    FaceCollegeMapper schoolCollegeMapper;

    @Autowired
    FaceSchoolMapper schoolMapper;

    @Autowired
    FaceMajorMapper schoolMajorMapper;

    @Autowired
    FaceClassMapper schoolClassMapper;


    public FaceSchoolUser getSchoolUserByUserId(int userId) {
        FaceSchoolUserExample schoolUserExample = new FaceSchoolUserExample();
        FaceSchoolUserExample.Criteria criteria = schoolUserExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<FaceSchoolUser> schoolUsers = schoolUserMapper.selectByExample(schoolUserExample);
        return schoolUsers.size() == 0 ? null : schoolUsers.get(0);
    }


    public FaceSchoolUser bindSchool(int userId, String schoolUserId, Integer schoolId, Integer collegeId, Integer majorId, Integer classId) {
        FaceSchoolUserExample schoolUserExample = new FaceSchoolUserExample();
        FaceSchoolUserExample.Criteria criteria = schoolUserExample.createCriteria();

        FaceSchoolUser schoolUser = new FaceSchoolUser();
        schoolUser.setUserId(userId);

        criteria.andSchoolUserIdEqualTo(schoolUserId);
        criteria.andSchoolIdEqualTo(schoolId);


        if (collegeId != null) {
            criteria.andCollegeIdEqualTo(collegeId);
        }
        if (majorId != null) {
            criteria.andMajorIdEqualTo(majorId);
        }
        if (classId != null) {
            criteria.andClassIdEqualTo(classId);
        }

        if (schoolUserMapper.updateByExampleSelective(schoolUser, schoolUserExample) == 0) {
            throw new BaseException(ResultCode.FAILED.getCode(), "学校绑定失败，未查询到学校信息或学号信息");
        }

        criteria.andUserIdEqualTo(userId);

        List<FaceSchoolUser> schoolUserList = schoolUserMapper.selectByExample(schoolUserExample);

        schoolUser = schoolUserList.size() == 0 ? null : schoolUserList.get(0);

        if (schoolUser == null) {
            throw new BaseException(ResultCode.FAILED);
        }
        return schoolUser;
    }


    public List<FaceSchool> getSchoolList() {
        return schoolMapper.selectByExample(null);
    }

    public FaceSchool getSchoolInfo(int schoolId) {
        return schoolMapper.selectByPrimaryKey(schoolId);
    }

    public List<FaceCollege> getSchoolCollegeList(int schoolId) {
        FaceCollegeExample example = new FaceCollegeExample();
        FaceCollegeExample.Criteria criteria = example.createCriteria();
        criteria.andSchoolIdEqualTo(schoolId);
        return schoolCollegeMapper.selectByExample(example);
    }

    public FaceCollege getSchoolCollegeInfo(int collegeId) {
        return schoolCollegeMapper.selectByPrimaryKey(collegeId);
    }


    public List<FaceMajor> getSchoolMajorList(Integer schoolId, Integer collegeId) {

        FaceMajorExample example = new FaceMajorExample();
        FaceMajorExample.Criteria criteria = example.createCriteria();
        if (schoolId != null) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (collegeId != null) {
            criteria.andCollegeIdEqualTo(collegeId);
        }
        return schoolMajorMapper.selectByExample(example);
    }

    public FaceMajor getSchoolMajorInfo(int majorId) {
        return schoolMajorMapper.selectByPrimaryKey(majorId);
    }

    public List<FaceClass> getSchoolClassList(Integer schoolId, Integer collegeId, Integer majorId) {

        FaceClassExample example = new FaceClassExample();
        FaceClassExample.Criteria criteria = example.createCriteria();

        if (schoolId != null) {
            criteria.andSchoolIdEqualTo(schoolId);
        }
        if (collegeId != null) {
            criteria.andCollegeIdEqualTo(collegeId);
        }
        if (majorId != null) {
            criteria.andMajorIdEqualTo(majorId);
        }

        return schoolClassMapper.selectByExample(example);
    }

    public FaceClass getSchoolClassInfo(int classId) {
        return schoolClassMapper.selectByPrimaryKey(classId);
    }




}
