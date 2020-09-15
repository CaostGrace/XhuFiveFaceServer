package cn.logcode.xhufiveface.controller;

import cn.logcode.xhufiveface.dao.pojo.FaceManager;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.service.ManagerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manager")
@Validated
public class ManagerController {

    @Resource
    ManagerService managerService;


    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/getManagerInfo")
    public CommonResult<FaceManager> getManagerInfo(@RequestParam(value = "id",required = true)int id){
        return CommonResult.success(managerService.getManagerInfo(id));
    }

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/getAllManager")
    public CommonResult<List<FaceManager>> getAllManager(){
        return CommonResult.success(managerService.getAllManager());
    }

    @RequestMapping(method = {RequestMethod.POST},value = "/addManager")
    public CommonResult addManager(@RequestParam(value = "name",required = true)String name,
                                                      @RequestParam(value = "pwd",required = true)String pwd){
        return CommonResult.success(managerService.addManager(name,pwd));
    }

    @RequestMapping(method = {RequestMethod.POST},value = "/updateManager")
    public CommonResult updateManager(@RequestParam(value = "id",required = true)int id,
                                      @RequestParam(value = "name",required = false)String name,
                                      @RequestParam(value = "pwd",required = false)String pwd){
        FaceManager manager = new FaceManager();
        manager.setId(id);
        if(!"".equals(name)){
            manager.setUserNick(name);
        }
        if(!"".equals(pwd)){
            manager.setUserPwd(pwd);
        }
        return CommonResult.success(managerService.updateManager(manager));

    }







}
