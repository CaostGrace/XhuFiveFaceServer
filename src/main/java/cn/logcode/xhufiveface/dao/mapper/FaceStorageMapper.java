package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceStorage;
import cn.logcode.xhufiveface.dao.pojo.FaceStorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceStorageMapper {
    long countByExample(FaceStorageExample example);

    int deleteByExample(FaceStorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceStorage record);

    int insertSelective(FaceStorage record);

    List<FaceStorage> selectByExample(FaceStorageExample example);

    FaceStorage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceStorage record, @Param("example") FaceStorageExample example);

    int updateByExample(@Param("record") FaceStorage record, @Param("example") FaceStorageExample example);

    int updateByPrimaryKeySelective(FaceStorage record);

    int updateByPrimaryKey(FaceStorage record);
}