package cn.logcode.xhufiveface.controller;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.service.core.FaceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/face")
@Validated
public class FaceController {
    public final Log logger = LogFactory.getLog(FaceController.class);

    @Autowired
    FaceService faceService;

    @PostMapping(value = "/addGroup")
    public CommonResult addGroup(@RequestParam(value = "groupName",required = true)String groupName){

        if("".equals(groupName)){
            throw new BaseException(ResultCode.PARAM_ERROR);
        }
        faceService.addGroup(groupName);
        return CommonResult.success(new Object());
    }

    @GetMapping(value = "/deleteGroup")
    public CommonResult deleteGroup(@RequestParam(value = "groupId",required = true)int groupId){
        if(groupId == 0){
            throw new BaseException(ResultCode.PARAM_ERROR);
        }
        faceService.deleteGroup(groupId);
        return CommonResult.success(new Object());
    }

}
