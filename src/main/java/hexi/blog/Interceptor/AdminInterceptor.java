package hexi.blog.Interceptor;

import hexi.blog.model.pojo.Users;
import hexi.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private UsersService usersService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI();
        Users user=usersService.findByUsernameAndPassword(request.getParameter("username"),request.getParameter("password"));
        if (url.startsWith("/admin")&&!url.startsWith("/admin/login")&&user==null){
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        return true;
    }
}
