package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceMajor;
import cn.logcode.xhufiveface.dao.pojo.FaceMajorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceMajorMapper {
    long countByExample(FaceMajorExample example);

    int deleteByExample(FaceMajorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceMajor record);

    int insertSelective(FaceMajor record);

    List<FaceMajor> selectByExample(FaceMajorExample example);

    FaceMajor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceMajor record, @Param("example") FaceMajorExample example);

    int updateByExample(@Param("record") FaceMajor record, @Param("example") FaceMajorExample example);

    int updateByPrimaryKeySelective(FaceMajor record);

    int updateByPrimaryKey(FaceMajor record);
}