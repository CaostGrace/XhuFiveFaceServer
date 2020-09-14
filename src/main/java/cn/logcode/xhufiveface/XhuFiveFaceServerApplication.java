package cn.logcode.xhufiveface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan
@EnableAsync
@EnableTransactionManagement
@MapperScan("cn.logcode.xhufiveface.dao.mapper")
public class XhuFiveFaceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhuFiveFaceServerApplication.class, args);
    }

}
