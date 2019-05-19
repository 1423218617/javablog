package hexi.blog.controller;


import hexi.blog.dao.CommentsDao;
import hexi.blog.dao.ContentsDao;
import hexi.blog.model.ArchiveVo;
import hexi.blog.model.pojo.Comments;
import hexi.blog.model.pojo.Contents;
import hexi.blog.service.CommentsService;
import hexi.blog.service.ContentsService;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController extends BaseController{



    @Autowired
    private ContentsService contentsService;

    @Autowired
    private CommentsService commentsService;


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
     * @param slug
     * @return
     */
    @GetMapping("/content/{slug}")
    public String getContent(HttpServletRequest request,@PathVariable(name = "slug") String slug){
        Contents contents;
        if (StringUtils.isNotBlank(slug)){
            contents=contentsService.findContentsBySlug(slug);
        }else {
            contents=contentsService.findContentsByCid(Integer.parseInt(request.getParameter("cid")));
        }
        request.setAttribute("article",contents);
        getComments(contents,request);
        return html("post");
    }


    public void getComments(Contents contents,HttpServletRequest request){
        String pageNum=request.getParameter("cp");
        if (StringUtils.isBlank(pageNum)){
            pageNum="1";
        }
        Page<Comments> commentsPage=commentsService.commentsPageByCid(new PageRequest(Integer.parseInt(pageNum)-1,2),contents.getCid());
        request.setAttribute("comments",commentsPage);
    }


    @GetMapping("/archives")
    public String archive(HttpServletRequest request){
        List<Contents> contentsList=contentsService.findAll();
        List<ArchiveVo> archiveVoList=new ArrayList<>();
        Set<String> dataStringSet=new TreeSet<>();
        contentsList.forEach(contents -> {
            String dataString=new SimpleDateFormat("yyyy年MM月").format(new Date(contents.getCreated()*1000L));
            dataStringSet.add(dataString);
        });
        dataStringSet.forEach(s -> {
            ArchiveVo archiveVo=new ArchiveVo();
            archiveVo.setDate(s);
            archiveVoList.add(archiveVo);
        });
        archiveVoList.forEach(archiveVo -> {
            List<Contents> list=new ArrayList<>();
            archiveVo.setArticles(list);
            contentsList.forEach(contents -> {
                if (new SimpleDateFormat("yyyy年MM月").format(contents.getCreated()*1000L).equals(archiveVo.getDate())){
                    archiveVo.getArticles().add(contents);
                }
            });
        });
        Collections.reverse(archiveVoList);
        request.setAttribute("archives",archiveVoList);
        return html("archives");
    }

    @GetMapping("/tag/{name}")
    public String tags(HttpServletRequest request,@PathVariable(name = "name") String name) {
        List<Contents> articles=contentsService.findContentsByTagAndName("tag",name);
        request.setAttribute("articles",articles);
        request.setAttribute("type","标签");
        request.setAttribute("keyWord",name);
        return html("page-category");
    }


}
