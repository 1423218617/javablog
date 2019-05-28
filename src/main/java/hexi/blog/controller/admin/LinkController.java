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

@Controller
@RequestMapping("admin/links")
public class LinkController {

    @Autowired
    private MetasService metasService;

    @GetMapping
    public String index(HttpServletRequest request){
        List<Metas> metas=metasService.findAllByType(MetasTypeEnum.LINK.getType());
        request.setAttribute("links",metas);
        return "admin/links";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultVo save(@RequestParam String mid,@RequestParam String title,@RequestParam String url,@RequestParam String logo,@RequestParam String sort){
        Metas metas=new Metas();
        if (StringUtils.isNotBlank(mid)){
            metas.setMid(Integer.parseInt(mid));
        }
        metas.setType(MetasTypeEnum.LINK.getType());
        metas.setName(title);
        metas.setSlug(url);
        metas.setSort(Integer.parseInt(sort));
        metas.setDescription(logo);
        metasService.save(metas);
        return new ResultVo(true,"保存成功");
    }

    @PostMapping("delete")
    @Transactional
    @ResponseBody
    public ResultVo delete(@RequestParam String mid){
        metasService.deleteByMid(Integer.parseInt(mid));
        return new ResultVo(true,"删除成功");
    }

}
