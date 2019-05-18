package hexi.blog.controller;


import hexi.blog.dao.ContentsDao;
import hexi.blog.model.pojo.Contents;
import hexi.blog.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController{



    @Autowired
    private ContentsService contentsService;


    /**
     *
     * @param model
     * @param pageSize
     * @return
     */
    @GetMapping({"/","/index"})
    public String index(Model model, @RequestParam(defaultValue = "12") int pageSize){
        return index(model,1,pageSize);
    }


    /**
     *
     *
     * @param model
     * @param p
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{p}")
    public String index(Model model, @PathVariable int p,@RequestParam(defaultValue = "12") int pageSize){
        Page<Contents> articles=contentsService.contentsPage(new PageRequest(p-1,2));
        model.addAttribute("articles" ,articles);
        return html("index");
    }


    /**
     *
     *
     * @param request
     * @param cid
     * @return
     */
    @GetMapping("/content/{cid}")
    public String getContent(HttpServletRequest request,@PathVariable(name = "cid") int cid){
        Contents contents=contentsService.findContentsByCid(cid);
        request.setAttribute("article",contents);
        return html("post");
    }

}
