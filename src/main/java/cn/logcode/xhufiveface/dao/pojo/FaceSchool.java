package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceSchool implements Serializable {
    private Integer id;

    private String name;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceSchool(Integer id, String name, Date createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public FaceSchool() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        sb.append(", name=").append(name);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}