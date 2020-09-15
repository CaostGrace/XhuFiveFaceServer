package cn.logcode.xhufiveface.controller;

import cn.logcode.xhufiveface.config.BaseException;
import cn.logcode.xhufiveface.dao.pojo.FaceGroup;
import cn.logcode.xhufiveface.model.vo.UserFaceData;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.result.ResultCode;
import cn.logcode.xhufiveface.service.core.FaceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author caostgrace
 */
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

    @GetMapping(value = "/getAllGroups")
    public CommonResult<List<FaceGroup>> getAllGroups(){
        return CommonResult.success(faceService.getGroupList());
    }

    @PostMapping(value = "/deleteFaceUser")
    public CommonResult deleteFaceUser(@RequestParam(value = "userId",required = true)int userId){
        return CommonResult.success(faceService.deleteFaceUser(userId));
    }

    /**
     * 更新用户人脸库，会删除之前的
     * @param userId
     * @param fileId
     * @return
     */
    @PostMapping(value = "/updateUserFaceData")
    public CommonResult updateUserFaceData(@RequestParam(value = "userId",required = true)int userId,
                                           @RequestParam(value = "fileId",required = true)int fileId){
        return CommonResult.success(faceService.updateUserFaceData(userId,fileId));
    }

    /**
     * 删除用户的一张人脸数据
     * @param userId
     * @param groupId
     * @param faceToken
     * @return
     */
    @PostMapping(value = "/faceDelete")
    public CommonResult faceDelete(@RequestParam(value = "userId",required = true)int userId,
                                           @RequestParam(value = "groupId",required = true)int groupId,
                                   @RequestParam(value = "faceToken",required = true)String faceToken){
        return CommonResult.success(faceService.faceDelete( userId, groupId, faceToken));
    }

    /**
     * 用户人脸数据添加
     * @param userId
     * @param fileId
     * @return
     */
    @PostMapping(value = "/userFaceAdd")
    public CommonResult userFaceAdd(@RequestParam(value = "userId",required = true)int userId,
                                    @RequestParam(value = "fileId",required = true)int fileId){
        return CommonResult.success(faceService.userFaceRegisterOrAdd( userId, fileId));
    }

    @PostMapping(value = "/getUserFaceData")
    public CommonResult<List<UserFaceData>> getUserFaceData(@RequestParam(value = "userId",required = true)int userId){
        return CommonResult.success(faceService.getUserFaceData(userId));
    }

    @PostMapping(value = "/getGroupFaceData")
    public CommonResult<List<UserFaceData>> getGroupFaceData(@RequestParam(value = "groupId",required = true)int groupId){
        return CommonResult.success(faceService.getGroupFaceData(groupId));
    }


    @PostMapping(value = "/updateUserFaceGroup")
    public CommonResult updateUserFaceGroup(@RequestParam(value = "userId",required = true)int userId,
                                            @RequestParam(value = "groupId",required = true)int groupId){
        return CommonResult.success(faceService.updateUserFaceGroup(userId,groupId));
    }


}
