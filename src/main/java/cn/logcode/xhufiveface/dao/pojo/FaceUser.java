package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceUser implements Serializable {
    private Integer userId;

    private String userNick;

    private String userReal;

    private Integer userSex;

    private String userHead;

    private Date userBir;

    private String userPhone;

    private Integer groupId;

    private String userEmail;

    private String loginIp;

    private Date createTime;

    private String userPwd;

    private String accessToken;

    private Date expiresTime;

    private Integer prohibit;

    private static final long serialVersionUID = 1L;

    public FaceUser(Integer userId, String userNick, String userReal, Integer userSex, String userHead, Date userBir, String userPhone, Integer groupId, String userEmail, String loginIp, Date createTime, String userPwd, String accessToken, Date expiresTime, Integer prohibit) {
        this.userId = userId;
        this.userNick = userNick;
        this.userReal = userReal;
        this.userSex = userSex;
        this.userHead = userHead;
        this.userBir = userBir;
        this.userPhone = userPhone;
        this.groupId = groupId;
        this.userEmail = userEmail;
        this.loginIp = loginIp;
        this.createTime = createTime;
        this.userPwd = userPwd;
        this.accessToken = accessToken;
        this.expiresTime = expiresTime;
        this.prohibit = prohibit;
    }

    public FaceUser() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getUserReal() {
        return userReal;
    }

    public void setUserReal(String userReal) {
        this.userReal = userReal == null ? null : userReal.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead == null ? null : userHead.trim();
    }

    public Date getUserBir() {
        return userBir;
    }

    public void setUserBir(Date userBir) {
        this.userBir = userBir;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getProhibit() {
        return prohibit;
    }

    public void setProhibit(Integer prohibit) {
        this.prohibit = prohibit;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userNick=").append(userNick);
        sb.append(", userReal=").append(userReal);
        sb.append(", userSex=").append(userSex);
        sb.append(", userHead=").append(userHead);
        sb.append(", userBir=").append(userBir);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", groupId=").append(groupId);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", createTime=").append(createTime);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", expiresTime=").append(expiresTime);
        sb.append(", prohibit=").append(prohibit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}