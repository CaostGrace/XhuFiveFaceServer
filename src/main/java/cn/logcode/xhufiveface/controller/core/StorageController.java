package cn.logcode.xhufiveface.controller.core;


import cn.logcode.xhufiveface.dao.pojo.FaceStorage;
import cn.logcode.xhufiveface.result.CommonResult;
import cn.logcode.xhufiveface.service.core.CoreStorageService;
import cn.logcode.xhufiveface.storage.StorageService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package cn.logcode.demo.controller.core
 * @ClassName StorageController
 * @Author caost
 * @Date 2020/4/8 16:20
 * @Emall caostgrace@163.com
 * @DESC 对象存储服务
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    public final Log logger = LogFactory.getLog(StorageController.class);

    @Autowired
    public StorageService storageService;
    @Autowired
    public CoreStorageService coreStorageService;


    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (ApplicationTool.httpPath.equals("")) {
            ApplicationTool.httpPath = request.getScheme() + "://" + request.getRemoteHost() + ":" + request.getServerPort() + ApplicationTool.contextPath;
        }
        String originalFilename = file.getOriginalFilename();
        FaceStorage store = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        Map<String,Object> map = new HashMap<>();
        map.put("id",store.getId());
        map.put("url",store.getUrl());
        return CommonResult.success(map);
    }

    @PostMapping("/uploads")
    public CommonResult uploads(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        if (ApplicationTool.httpPath.equals("")) {
            ApplicationTool.httpPath = request.getScheme() + "://" + request.getRemoteHost() + ":" + request.getServerPort() + ApplicationTool.contextPath;
        }
        String originalFilename;
        FaceStorage store;
        List<Map> list = new ArrayList<>();
        if (null != files && files.length > 0) {
            for (MultipartFile file : files) {
                Map<String,Object> map = new HashMap<>();
                originalFilename = file.getOriginalFilename();
                store = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
                map.put("id",store.getId());
                map.put("url",store.getUrl());
                list.add(map);
            }
        }else {
            return CommonResult.failed("文件上传失败");
        }
        return CommonResult.success(list);
    }


    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/fetch/{key:.+}")
    public ResponseEntity<Resource> fetch(@PathVariable String key) {
        FaceStorage storage = coreStorageService.findByKey(key);
        if (key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }
        String type = storage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);

        Resource file = storageService.loadAsResource(key);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/download/{key:.+}")
    public ResponseEntity<Resource> download(@PathVariable String key, HttpServletResponse response) throws IOException {

        if (key == null) {
            return ResponseEntity.notFound().build();
        }
        if (key.contains("../")) {
            return ResponseEntity.badRequest().build();
        }

        Resource file = storageService.loadAsResource(key);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        FaceStorage storage = coreStorageService.findByKey(key);

        if (storage == null || storage.getDeleted()) {
            return ResponseEntity.notFound().build();
        }

        String type = storage.getType();
        MediaType mediaType = MediaType.parseMediaType(type);
        return ResponseEntity.ok()
                .contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", URLEncoder.encode(storage.getName(), "utf-8")))
                .body(new InputStreamResource(file.getInputStream()));
    }


}
