package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceCollege;
import cn.logcode.xhufiveface.dao.pojo.FaceCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceCollegeMapper {
    long countByExample(FaceCollegeExample example);

    int deleteByExample(FaceCollegeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceCollege record);

    int insertSelective(FaceCollege record);

    List<FaceCollege> selectByExample(FaceCollegeExample example);

    FaceCollege selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceCollege record, @Param("example") FaceCollegeExample example);

    int updateByExample(@Param("record") FaceCollege record, @Param("example") FaceCollegeExample example);

    int updateByPrimaryKeySelective(FaceCollege record);

    int updateByPrimaryKey(FaceCollege record);
}