package cn.logcode.xhufiveface.storage;


import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * 服务器本地对象存储服务
 */
public class LocalStorage implements Storage {


    public final Log logger = LogFactory.getLog(LocalStorage.class);

    public String storagePath;
    public String address;

    public Path rootLocation;


    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
        this.rootLocation = Paths.get(storagePath);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Path getRootLocation() {
        return rootLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
//            Files.copy(inputStream, rootLocation.resolve(keyName), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(inputStream, FileSystems.getDefault().getPath(keyName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + keyName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(path -> rootLocation.relativize(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
//        return rootLocation.resolve(filename);
        return FileSystems.getDefault().getPath(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            Files.delete(file);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return ApplicationTool.httpPath + address + keyName;
    }
}