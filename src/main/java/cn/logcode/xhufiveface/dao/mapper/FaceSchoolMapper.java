package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceSchool;
import cn.logcode.xhufiveface.dao.pojo.FaceSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceSchoolMapper {
    long countByExample(FaceSchoolExample example);

    int deleteByExample(FaceSchoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceSchool record);

    int insertSelective(FaceSchool record);

    List<FaceSchool> selectByExample(FaceSchoolExample example);

    FaceSchool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceSchool record, @Param("example") FaceSchoolExample example);

    int updateByExample(@Param("record") FaceSchool record, @Param("example") FaceSchoolExample example);

    int updateByPrimaryKeySelective(FaceSchool record);

    int updateByPrimaryKey(FaceSchool record);
}