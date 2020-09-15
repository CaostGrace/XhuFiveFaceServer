package cn.logcode.xhufiveface.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class FaceStorage implements Serializable {
    private Integer id;

    private String fileKey;

    private String name;

    private String type;

    private Integer size;

    private String url;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private Boolean islocal;

    private String localpath;

    private static final long serialVersionUID = 1L;

    public FaceStorage(Integer id, String fileKey, String name, String type, Integer size, String url, Date addTime, Date updateTime, Boolean deleted, Boolean islocal, String localpath) {
        this.id = id;
        this.fileKey = fileKey;
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
        this.islocal = islocal;
        this.localpath = localpath;
    }

    public FaceStorage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey == null ? null : fileKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getIslocal() {
        return islocal;
    }

    public void setIslocal(Boolean islocal) {
        this.islocal = islocal;
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath == null ? null : localpath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileKey=").append(fileKey);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", size=").append(size);
        sb.append(", url=").append(url);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", islocal=").append(islocal);
        sb.append(", localpath=").append(localpath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}