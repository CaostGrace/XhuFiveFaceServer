package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceLibrary implements Serializable {
    private Integer id;

    private String userId;

    private Integer fileId;

    private Integer groupId;

    private String faceToken;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public FaceLibrary(Integer id, String userId, Integer fileId, Integer groupId, String faceToken, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.fileId = fileId;
        this.groupId = groupId;
        this.faceToken = faceToken;
        this.createTime = createTime;
    }

    public FaceLibrary() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken == null ? null : faceToken.trim();
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
        sb.append(", fileId=").append(fileId);
        sb.append(", groupId=").append(groupId);
        sb.append(", faceToken=").append(faceToken);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}