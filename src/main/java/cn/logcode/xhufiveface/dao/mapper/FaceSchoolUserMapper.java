package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceSchoolUser;
import cn.logcode.xhufiveface.dao.pojo.FaceSchoolUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceSchoolUserMapper {
    long countByExample(FaceSchoolUserExample example);

    int deleteByExample(FaceSchoolUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceSchoolUser record);

    int insertSelective(FaceSchoolUser record);

    List<FaceSchoolUser> selectByExample(FaceSchoolUserExample example);

    FaceSchoolUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceSchoolUser record, @Param("example") FaceSchoolUserExample example);

    int updateByExample(@Param("record") FaceSchoolUser record, @Param("example") FaceSchoolUserExample example);

    int updateByPrimaryKeySelective(FaceSchoolUser record);

    int updateByPrimaryKey(FaceSchoolUser record);
}