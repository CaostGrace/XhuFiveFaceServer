package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceClass;
import cn.logcode.xhufiveface.dao.pojo.FaceClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceClassMapper {
    long countByExample(FaceClassExample example);

    int deleteByExample(FaceClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceClass record);

    int insertSelective(FaceClass record);

    List<FaceClass> selectByExample(FaceClassExample example);

    FaceClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceClass record, @Param("example") FaceClassExample example);

    int updateByExample(@Param("record") FaceClass record, @Param("example") FaceClassExample example);

    int updateByPrimaryKeySelective(FaceClass record);

    int updateByPrimaryKey(FaceClass record);
}