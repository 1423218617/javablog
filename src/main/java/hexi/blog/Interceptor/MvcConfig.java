package hexi.blog.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private UserInterceptor userInterceptor;
    @Resource
    private AdminInterceptor adminInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
        registry.addInterceptor(adminInterceptor).excludePathPatterns("/admin/login");
    }
}
