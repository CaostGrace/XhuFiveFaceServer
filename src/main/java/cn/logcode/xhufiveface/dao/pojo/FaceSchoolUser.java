package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceSchoolUser implements Serializable {
    private Integer id;

    private Integer userId;

    private String schoolUserId;

    private Integer enterYear;

    private Integer schoolId;

    private Integer collegeId;

    private Integer majorId;

    private Integer classId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceSchoolUser(Integer id, Integer userId, String schoolUserId, Integer enterYear, Integer schoolId, Integer collegeId, Integer majorId, Integer classId, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.schoolUserId = schoolUserId;
        this.enterYear = enterYear;
        this.schoolId = schoolId;
        this.collegeId = collegeId;
        this.majorId = majorId;
        this.classId = classId;
        this.createTime = createTime;
    }

    public FaceSchoolUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSchoolUserId() {
        return schoolUserId;
    }

    public void setSchoolUserId(String schoolUserId) {
        this.schoolUserId = schoolUserId == null ? null : schoolUserId.trim();
    }

    public Integer getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(Integer enterYear) {
        this.enterYear = enterYear;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", schoolUserId=").append(schoolUserId);
        sb.append(", enterYear=").append(enterYear);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", collegeId=").append(collegeId);
        sb.append(", majorId=").append(majorId);
        sb.append(", classId=").append(classId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}