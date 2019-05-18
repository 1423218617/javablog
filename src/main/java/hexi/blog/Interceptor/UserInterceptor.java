package hexi.blog.Interceptor;

import hexi.blog.utils.UserCommonMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    private UserCommonMethod userCommonMethod;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("commons",userCommonMethod);
    }
}
