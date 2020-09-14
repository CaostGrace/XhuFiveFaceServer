package cn.logcode.xhufiveface.storage.config;

import cn.logcode.xhufiveface.storage.LocalStorage;
import cn.logcode.xhufiveface.storage.StorageService;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

    public final StorageProperties properties;

    public StorageAutoConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    @Autowired
    ApplicationTool springBeanTool;

    @Bean
    public StorageService storageService() {

        StorageService storageService = new StorageService();
        String active = this.properties.getActive();
        storageService.setActive(active);
        if (active.equals("local")) {
            storageService.setStorage(localStorage());
        } else {
            throw new RuntimeException("当前存储模式 " + active + " 不支持");
        }

        return storageService;
    }

    @Bean
    public LocalStorage localStorage() {
        LocalStorage localStorage = new LocalStorage();
        StorageProperties.Local local = this.properties.getLocal();
        localStorage.setAddress(local.getAddress());
        localStorage.setStoragePath(local.getStoragePath());
        return localStorage;
    }


}
