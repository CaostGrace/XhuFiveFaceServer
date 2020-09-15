package cn.logcode.xhufiveface.model.vo;

import cn.logcode.xhufiveface.dao.pojo.FaceLibrary;
import lombok.Data;

import java.util.Date;

@Data
public class UserFaceData {

    public Integer id;
    public Integer userId;
    public String userName;
    public Integer fileId;
    public String filePath;
    public Integer groupId;
    public String groupName;
    public String faceToken;

}
