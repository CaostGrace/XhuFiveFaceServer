package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceManager;
import cn.logcode.xhufiveface.dao.pojo.FaceManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceManagerMapper {
    long countByExample(FaceManagerExample example);

    int deleteByExample(FaceManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceManager record);

    int insertSelective(FaceManager record);

    List<FaceManager> selectByExample(FaceManagerExample example);

    FaceManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceManager record, @Param("example") FaceManagerExample example);

    int updateByExample(@Param("record") FaceManager record, @Param("example") FaceManagerExample example);

    int updateByPrimaryKeySelective(FaceManager record);

    int updateByPrimaryKey(FaceManager record);
}