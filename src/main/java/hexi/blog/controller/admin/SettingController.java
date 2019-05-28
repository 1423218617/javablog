package hexi.blog.controller.admin;


import hexi.blog.model.pojo.Options;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/setting")
public class SettingController {
    @Autowired
    private OptionsService optionsService;

    @GetMapping
    public String index(HttpServletRequest request){
        List<Options> optionsList= optionsService.findAll();
        Map<String,String> options=optionsList.stream().collect(Collectors.toMap(Options::getName,Options::getValue));
        System.out.println(options);
        request.setAttribute("options",options);
        return "admin/setting";
    }

    @PostMapping
    @Transactional
    @ResponseBody
    public ResultVo save(HttpServletRequest request){
        return new ResultVo(true,"保存成功");

    }
}
