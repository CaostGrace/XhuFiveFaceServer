package cn.logcode.xhufiveface.service;

import cn.logcode.xhufiveface.config.AppConfig;
import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.ClockRecordDao;
import cn.logcode.xhufiveface.dao.pojo.FaceClockRecord;
import cn.logcode.xhufiveface.model.vo.ClockRecordBean;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.service.core.FaceService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PunchClockService {

    @Autowired
    AppConfig appConfig;

    @Resource
    ClockRecordDao clockRecordDao;

    @Autowired
    FaceService faceService;

    public ClockRecordBean getUserTodayRecord(int userId){
        FaceClockRecord clockRecord = clockRecordDao.getByUserToday(userId);
        ClockRecordBean recordBean = clockToBean(clockRecord);
        return recordBean;
    }


    /**
     * 用户打卡
     * @param userId
     * @param fileId 人脸照片id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean userClock(int userId,int fileId){

        FaceClockRecord record = clockRecordDao.getByUserToday(userId);
        if(record == null){
            record = new FaceClockRecord();
            record.setToday(ApplicationTool.getCurrentDateYYYYMMDD());
            record.setUserId(userId);
            record.setStarttime(new Date());
            if (!clockRecordDao.addRecord(record)) {
                throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);
            }
        }else {
            record.setEndtime(new Date());
            if (!clockRecordDao.updateRecord(record)) {
                throw new BaseException(ResultCode.SERVICE_BUSINESS_ERROR);
            }
        }
        return faceService.faceVerification(userId, fileId);
    }



    public List<ClockRecordBean> getUserClockRecord(int userId){

        List<FaceClockRecord> list = clockRecordDao.getByUserId(userId);

        List<ClockRecordBean> recordBeans = new ArrayList<>();

        list.forEach(ob->{
            recordBeans.add(clockToBean(ob));
        });
        return recordBeans;
    }


    private ClockRecordBean clockToBean(FaceClockRecord clockRecord){
        ClockRecordBean recordBean = new ClockRecordBean();
        if(clockRecord == null){
            recordBean.today = ApplicationTool.getCurrentDateYYYYMMDD();

            recordBean.startTime = "";
            recordBean.startFlag = 0;
            recordBean.startMsg = "未打卡";

            recordBean.endTime = "";
            recordBean.endFlag = 0;
            recordBean.endMsg = "未打卡";
        }else {

            recordBean.today = clockRecord.getToday();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(clockRecord.getStarttime());

            recordBean.startMsg = "已打卡";
            if(calendar.get(Calendar.HOUR_OF_DAY) > appConfig.startTime){
                recordBean.startMsg = "迟到";
            }
            recordBean.startTime = ApplicationTool.stampDate(clockRecord.getStarttime());
            recordBean.startFlag = 1;

            if(clockRecord.getEndtime() == null){
                recordBean.endTime = "";
                recordBean.endFlag = 0;
                recordBean.endMsg = "未打卡";
            }else{
                calendar.setTime(clockRecord.getEndtime());
                recordBean.endMsg = "已打卡";
                if(calendar.get(Calendar.HOUR_OF_DAY) < appConfig.endTime){
                    recordBean.endMsg = "早退";
                }
                recordBean.endTime = ApplicationTool.stampDate(clockRecord.getEndtime());
                recordBean.startFlag = 1;
            }
        }
        return recordBean;
    }

}
