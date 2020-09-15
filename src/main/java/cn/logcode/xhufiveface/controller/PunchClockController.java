package cn.logcode.xhufiveface.controller;

import cn.logcode.xhufiveface.model.vo.ClockRecordBean;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.service.PunchClockService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clockRecord")
@Validated
public class PunchClockController {

    @Resource
    PunchClockService clockService;


    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/getUserTodayRecord")
    public CommonResult<ClockRecordBean> getUserTodayRecord(@RequestParam(value = "userId",required = true)int userId){
        return CommonResult.success(clockService.getUserTodayRecord(userId));
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/getTodayRecord")
    public CommonResult<List<ClockRecordBean>> getTodayRecord(){
        return CommonResult.success(clockService.getTodayRecord());
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/getAllRecord")
    public CommonResult<List<ClockRecordBean>> getAllRecord(){
        return CommonResult.success(clockService.getAllRecord());
    }

    @RequestMapping(method = {RequestMethod.POST},value = "/userClock")
    public CommonResult userClock(@RequestParam(value = "userId",required = true)int userId,
                                                         @RequestParam(value = "fileId",required = true)int fileId){
        return CommonResult.success(clockService.userClock(userId,fileId));
    }


    @RequestMapping(method = {RequestMethod.GET},value = "/getUserClockRecord")
    public CommonResult<List<ClockRecordBean>> getUserClockRecord(@RequestParam(value = "userId",required = true)int userId){
        return CommonResult.success(clockService.getUserClockRecord(userId));
    }



}
