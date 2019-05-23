package hexi.blog.controller.admin;


import hexi.blog.model.pojo.Users;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("AdminIndexController")
@RequestMapping("/admin")
public class IndexController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVo login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        System.out.println(username+"  "+password);
        System.out.println();
        System.out.println();
        Users users=usersService.findByUsernameAndPassword(username,password);
        if (users!=null){
            HttpSession session =request.getSession();
            session.setAttribute("users",users);
            return new ResultVo(true,"登录成功");
        }
        return new ResultVo(false,"账号或密码错误");
    }



    @GetMapping("/index")
    public String index(HttpServletRequest request){
return "";
    }
}
