package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceManager implements Serializable {
    private Integer id;

    private String userNick;

    private String userPwd;

    private String accessToken;

    private Date expiresTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceManager(Integer id, String userNick, String userPwd, String accessToken, Date expiresTime, Date createTime) {
        this.id = id;
        this.userNick = userNick;
        this.userPwd = userPwd;
        this.accessToken = accessToken;
        this.expiresTime = expiresTime;
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

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
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
        sb.append(", userNick=").append(userNick);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", expiresTime=").append(expiresTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}