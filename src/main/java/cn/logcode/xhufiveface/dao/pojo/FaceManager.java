package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceManager implements Serializable {
    private Integer id;

    private String username;

    private String userPwd;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceManager(Integer id, String username, String userPwd, Date createTime) {
        this.id = id;
        this.username = username;
        this.userPwd = userPwd;
        this.createTime = createTime;
    }

    public FaceManager() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
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
        sb.append(", username=").append(username);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}