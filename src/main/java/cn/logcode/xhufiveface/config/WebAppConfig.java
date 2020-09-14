package cn.logcode.xhufiveface.config;

import cn.logcode.xhufiveface.annotation.support.LoginUserHandlerMethodArgumentResolver;
import cn.logcode.xhufiveface.utils.ApplicationTool;
import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @package cn.logcode.demo.config
 * @ClassName WebAppConfig
 * @Author caost
 * @Date 2020/4/9 19:18
 * @Emall caostgrace@163.com
 * @DESC
 */

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public AipFace baiduFaceBean(){
        return ApplicationTool.getBaiduAiFaceInstance();
    }

    @Autowired
    LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(loginInterceptor)
                .addPathPatterns("/api/v1/**")
                .excludePathPatterns("/api/v1/app/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}
