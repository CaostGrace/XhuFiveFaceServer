package cn.logcode.xhufiveface.dao.mapper;

import cn.logcode.xhufiveface.dao.pojo.FaceClockRecord;
import cn.logcode.xhufiveface.dao.pojo.FaceClockRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceClockRecordMapper {
    long countByExample(FaceClockRecordExample example);

    int deleteByExample(FaceClockRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceClockRecord record);

    int insertSelective(FaceClockRecord record);

    List<FaceClockRecord> selectByExample(FaceClockRecordExample example);

    FaceClockRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceClockRecord record, @Param("example") FaceClockRecordExample example);

    int updateByExample(@Param("record") FaceClockRecord record, @Param("example") FaceClockRecordExample example);

    int updateByPrimaryKeySelective(FaceClockRecord record);

    int updateByPrimaryKey(FaceClockRecord record);
}