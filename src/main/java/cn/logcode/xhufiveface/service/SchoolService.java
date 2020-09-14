package cn.logcode.xhufiveface.service;


import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.SchoolDao;
import cn.logcode.xhufiveface.dao.pojo.*;
import cn.logcode.xhufiveface.model.dto.SchoolInfo;
import cn.logcode.xhufiveface.result.ResultCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @package cn.logcode.demo.service.wx
 * @ClassName SchoolService
 * @Author caost
 * @Date 2020/4/19 19:04
 * @Emall caostgrace@163.com
 * @DESC
 */

@Service
public class SchoolService {

    public final Log logger = LogFactory.getLog(SchoolService.class);

    @Autowired
    SchoolDao schoolDao;

    public FaceSchoolUser getSchoolUserByUserId(int userId) {
        return schoolDao.getSchoolUserByUserId(userId);
    }


    public SchoolInfo getSchoolUserInfo(FaceSchoolUser schoolUser){
        SchoolInfo schoolInfo = new SchoolInfo();

        schoolInfo.setSchoolUser(schoolUser);
        schoolInfo.setSchool(getSchoolInfo(schoolUser.getSchoolId()));
        schoolInfo.setSchoolCollege(getSchoolCollegeInfo(schoolUser.getCollegeId()));
        schoolInfo.setSchoolMajor(getSchoolMajorInfo(schoolUser.getMajorId()));
        schoolInfo.setSchoolClass(getSchoolClassInfo(schoolUser.getClassId()));

        return schoolInfo;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public SchoolInfo bindSchool(int userId, String schoolUserId, Integer schoolId, Integer collegeId, Integer majorId, Integer classId) {

        FaceSchoolUser schoolUser = schoolDao.bindSchool(userId, schoolUserId, schoolId, collegeId, majorId, classId);

        SchoolInfo schoolInfo = new SchoolInfo();

        schoolInfo.setSchoolUser(schoolUser);
        schoolInfo.setSchool(getSchoolInfo(schoolUser.getSchoolId()));
        schoolInfo.setSchoolCollege(getSchoolCollegeInfo(schoolUser.getCollegeId()));
        schoolInfo.setSchoolMajor(getSchoolMajorInfo(schoolUser.getMajorId()));
        schoolInfo.setSchoolClass(getSchoolClassInfo(schoolUser.getClassId()));

        return schoolInfo;
    }

    public List<FaceSchool> getSchoolList() {
        return schoolDao.getSchoolList();
    }

    public FaceSchool getSchoolInfo(int schoolId) {
        return schoolDao.getSchoolInfo(schoolId);
    }

    public List<FaceCollege> getSchoolCollegeList(int schoolId) {
        return schoolDao.getSchoolCollegeList(schoolId);
    }

    public FaceCollege getSchoolCollegeInfo(int collegeId) {
        return schoolDao.getSchoolCollegeInfo(collegeId);
    }


    public List<FaceMajor> getSchoolMajorList(Integer schoolId, Integer collegeId) {
        if (schoolId == null && collegeId == null) {
            throw new BaseException(ResultCode.PARAM_ERROR.getCode(), "学校id和分院id必须有一个不为空");
        }
        return schoolDao.getSchoolMajorList(schoolId, collegeId);
    }

    public FaceMajor getSchoolMajorInfo(int majorId) {
        return schoolDao.getSchoolMajorInfo(majorId);
    }

    public List<FaceClass> getSchoolClassList(Integer schoolId, Integer collegeId, Integer majorId) {

        if (schoolId == null && collegeId == null && majorId == null) {
            throw new BaseException(ResultCode.PARAM_ERROR.getCode(), "学校id、分院id、专业id必须有一个不为空");
        }
        return schoolDao.getSchoolClassList(schoolId, collegeId, majorId);
    }

    public FaceClass getSchoolClassInfo(int classId) {
        return schoolDao.getSchoolClassInfo(classId);
    }

}
