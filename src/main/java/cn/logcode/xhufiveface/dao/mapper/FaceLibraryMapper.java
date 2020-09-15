package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceLibrary;
import cn.logcode.xhufiveface.dao.pojo.FaceLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceLibraryMapper {
    long countByExample(FaceLibraryExample example);

    int deleteByExample(FaceLibraryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceLibrary record);

    int insertSelective(FaceLibrary record);

    List<FaceLibrary> selectByExample(FaceLibraryExample example);

    FaceLibrary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceLibrary record, @Param("example") FaceLibraryExample example);

    int updateByExample(@Param("record") FaceLibrary record, @Param("example") FaceLibraryExample example);

    int updateByPrimaryKeySelective(FaceLibrary record);

    int updateByPrimaryKey(FaceLibrary record);
}