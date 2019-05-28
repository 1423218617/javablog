package hexi.blog.controller.admin;


import hexi.blog.emun.MetasTypeEnum;
import hexi.blog.model.pojo.Metas;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.MetasService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    @Autowired
    private MetasService metasService;

    @GetMapping
    public String adminCategory(HttpServletRequest request){
        List<Metas>  tags= metasService.findAllByType(MetasTypeEnum.TAG.getType());
        List<Metas> categories=metasService.findAllByType(MetasTypeEnum.CATEGORY.getType());
        request.setAttribute("tags" ,tags);
        request.setAttribute("categories",categories);
        return "admin/category";

    }

    @PostMapping("delete")
    @ResponseBody
    @Transactional
    public ResultVo deleteMeta(@RequestParam String mid){
        metasService.deleteByMid(Integer.parseInt(mid));
        return new ResultVo(true,"删除成功");
    }


    @PostMapping("save")
    @ResponseBody
    public ResultVo save(@RequestParam String mid,@RequestParam String cname){
        Metas metas=new Metas();
        if (StringUtils.isNotBlank(mid)){
            metas.setMid(Integer.parseInt(mid   ));
        }
        metas.setName(cname);
        metas.setSlug(cname);
        metas.setType(MetasTypeEnum.CATEGORY.getType());
        metasService.save(metas);
        return new ResultVo(true,"添加成功");
    }


}
