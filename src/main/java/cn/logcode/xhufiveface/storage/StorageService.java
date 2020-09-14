package cn.logcode.xhufiveface.storage;


import cn.logcode.xhufiveface.AppConstant;
import cn.logcode.xhufiveface.dao.pojo.FaceStorage;
import cn.logcode.xhufiveface.service.core.CoreStorageService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import cn.logcode.xhufiveface.utils.CharUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.stream.Stream;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 */
public class StorageService {

    public final Log logger = LogFactory.getLog(StorageService.class);

    public String active;
    public Storage storage;

    @Autowired
    public CoreStorageService coreStorageService;


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     */
    public FaceStorage store(InputStream inputStream, long contentLength, String contentType, String fileName) throws IOException {
//        String key = fileName.substring(0,fileName.lastIndexOf('.'))+"-"+generateKey(fileName);


        String key = generateKey(fileName);

        Path localPath = null;

        if (storage instanceof LocalStorage) {
            localPath = FileSystems.getDefault().getPath(((LocalStorage) storage).getRootLocation().toFile().getAbsolutePath() + File.separator + ApplicationTool.getCurrentDateYYYYMMDD());
            localPath.toFile().mkdirs();
            localPath = localPath.resolve(key);
            storage.store(inputStream, contentLength, contentType, localPath.toFile().getAbsolutePath());
        } else {
            storage.store(inputStream, contentLength, contentType, key);
        }

        String url = generateUrl(key);

        FaceStorage storageInfo = new FaceStorage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) contentLength);
        storageInfo.setType(contentType);
        storageInfo.setFileKey(key);
        storageInfo.setUrl(url);
        storageInfo.setAddTime(new Date());
        storageInfo.setDeleted(false);
        if (storage instanceof LocalStorage) {
            storageInfo.setIslocal(AppConstant.LOCAL_STORAGE);
            storageInfo.setLocalpath(localPath.toFile().getAbsolutePath());
        }

        Resource resource;
        if (storage instanceof LocalStorage) {
            resource = storage.loadAsResource(storageInfo.getLocalpath());
        } else {
            resource = storage.loadAsResource(key);
        }
        
        coreStorageService.add(storageInfo);

        return storageInfo;
    }

    public String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        FaceStorage storageInfo = null;

        do {
            key = CharUtil.getRandomString(40) + suffix;
            storageInfo = coreStorageService.findByKey(key);
        }
        while (storageInfo != null);

        return key;
    }

    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    public Path load(String keyName) {

        if (storage instanceof LocalStorage) {
            FaceStorage storageInfo = coreStorageService.findByKey(keyName);
            if (storageInfo != null) {
                return storage.load(storageInfo.getLocalpath());
            }
            return null;
        }

        return storage.load(keyName);
    }

    public Resource loadAsResource(String keyName) {
        if (storage instanceof LocalStorage) {
            FaceStorage storageInfo = coreStorageService.findByKey(keyName);
            if (storageInfo == null) {
                return null;
            }
            return storage.loadAsResource(storageInfo.getLocalpath());
        }
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        if (storage instanceof LocalStorage) {
            FaceStorage storageInfo = coreStorageService.findByKey(keyName);
            if (storageInfo != null) {
                storage.delete(storageInfo.getLocalpath());
            }
            return;
        }
        storage.delete(keyName);
    }

    public String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
