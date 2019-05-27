package hexi.blog.controller.admin;


import hexi.blog.emun.ContentsStatusEnum;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/page")
public class PageController {
    @Autowired
    private ContentsService contentsService;


    @GetMapping
    public String index(HttpServletRequest request){
        Page<Contents> articles=contentsService.findAllContentsByTypeOrderByCreatedDesc(ContentsStatusEnum.PAGE.getType(),new PageRequest(0,5));
        request.setAttribute("articles",articles);
        return "admin/page_list";
    }

    @GetMapping("{cid}")
    public String modifyPage(@PathVariable String cid, HttpServletRequest request){
        Contents contents=contentsService.findContentsByCid(Integer.parseInt(cid));
        request.setAttribute("contents",contents);
        return "admin/page_edit";
    }

    @PostMapping("delete")
    @ResponseBody
    @Transactional
    public ResultVo deletePage(@RequestParam String cid){
        contentsService.deleteContents(Integer.parseInt(cid));
        return new ResultVo(true,"删除页面成功");
    }

    @GetMapping("new")
    public String newPage(HttpServletRequest request){
        return "admin/page_edit";
    }

    @PostMapping("publish")
    @ResponseBody
    public ResultVo doNewPage(@RequestParam String status,@RequestParam String title,
                              @RequestParam String slug,@RequestParam String content){
        Contents contents=new Contents();
        contents.setContent(content);
        contents.setType(ContentsStatusEnum.PAGE.getType());
        contents.setTitle(title);
        contents.setSlug(slug);
        contents.setStatus(status);
        contentsService.saveNew(contents);
        return new ResultVo(true,"新增页面成功");
    }

}
