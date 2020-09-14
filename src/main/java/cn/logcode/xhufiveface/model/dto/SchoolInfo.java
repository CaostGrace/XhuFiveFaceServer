package cn.logcode.xhufiveface.model.dto;

import cn.logcode.xhufiveface.dao.pojo.*;
import lombok.Data;
import lombok.ToString;

/**
 * @package cn.logcode.demo.model.dto
 * @ClassName BindSchoolInfo
 * @Author caost
 * @Date 2020/4/19 19:31
 * @Emall caostgrace@163.com
 * @DESC
 */

@Data
@ToString
public class SchoolInfo {

    public FaceSchoolUser schoolUser;

    public FaceSchool school;

    public FaceCollege schoolCollege;

    public FaceMajor schoolMajor;

    public FaceClass schoolClass;


}
