package cn.logcode.xhufiveface.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${core.appconfig.startTime}")
    public int startTime;
    @Value("${core.appconfig.endTime}")
    public int endTime;

}
