package cn.logcode.xhufiveface.dao;

import cn.logcode.xhufiveface.dao.mapper.FaceClockRecordMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceClockRecord;
import cn.logcode.xhufiveface.dao.pojo.FaceClockRecordExample;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ClockRecordDao {

    @Resource
    FaceClockRecordMapper clockRecordMapper;

    public List<FaceClockRecord> getByUserId(int userId){

        FaceClockRecordExample example = new FaceClockRecordExample();
        FaceClockRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause(" id desc ");
        return clockRecordMapper.selectByExample(example);
    }

    public List<FaceClockRecord> getByToday(){
        FaceClockRecordExample example = new FaceClockRecordExample();
        FaceClockRecordExample.Criteria criteria = example.createCriteria();
        criteria.andTodayEqualTo(ApplicationTool.getCurrentDateYYYYMMDD());
        example.setOrderByClause(" id desc ");
        return clockRecordMapper.selectByExample(example);
    }

    public FaceClockRecord getByUserToday(int userId){
        FaceClockRecordExample example = new FaceClockRecordExample();
        FaceClockRecordExample.Criteria criteria = example.createCriteria();
        criteria.andTodayEqualTo(ApplicationTool.getCurrentDateYYYYMMDD()).andUserIdEqualTo(userId);
        example.setOrderByClause(" id desc ");

        List<FaceClockRecord> list = clockRecordMapper.selectByExample(example);
        return list.size() == 0?null:list.get(0);
    }

    public boolean addRecord(FaceClockRecord record){

       return clockRecordMapper.insertSelective(record) == 1;

    }

    public boolean updateRecord(FaceClockRecord record){

        return clockRecordMapper.updateByPrimaryKeySelective(record) == 1;

    }

}
