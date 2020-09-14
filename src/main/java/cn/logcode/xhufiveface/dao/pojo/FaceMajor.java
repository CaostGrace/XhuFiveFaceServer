package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceMajor implements Serializable {
    private Integer id;

    private Integer schoolId;

    private Integer collegeId;

    private String name;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceMajor(Integer id, Integer schoolId, Integer collegeId, String name, Date createTime) {
        this.id = id;
        this.schoolId = schoolId;
        this.collegeId = collegeId;
        this.name = name;
        this.createTime = createTime;
    }

    public FaceMajor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        sb.append(", schoolId=").append(schoolId);
        sb.append(", collegeId=").append(collegeId);
        sb.append(", name=").append(name);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}