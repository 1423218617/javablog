package hexi.blog.Interceptor;

import hexi.blog.model.pojo.Users;
import hexi.blog.service.UsersService;
import hexi.blog.utils.AdminCommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AdminCommonMethod adminCommonMethod;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI();
        HttpSession session=request.getSession();
        if (url.startsWith("/admin")&&!url.startsWith("/admin/login")&&session.getAttribute("users")==null){
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("adminCommons",adminCommonMethod);
    }
}
