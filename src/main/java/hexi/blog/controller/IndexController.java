package hexi.blog.controller;


import hexi.blog.dao.CommentsDao;
import hexi.blog.emun.ResultEnum;
import hexi.blog.exception.IllegalCommentException;
import hexi.blog.model.ArchiveVo;
import hexi.blog.model.pojo.Comments;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.pojo.Metas;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.CommentsService;
import hexi.blog.service.ContentsService;
import hexi.blog.service.MetasService;
import hexi.blog.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController extends BaseController{



    @Autowired
    private ContentsService contentsService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private MetasService metasService;



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
        Page<Contents> articles=contentsService.contentsPage(new PageRequest(p-1,pageSize),"post","publish");
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


    /**
     *
     *
     * @param contents
     * @param request
     */
    public void getComments(Contents contents,HttpServletRequest request){
        String pageNum=request.getParameter("cp");
        if (StringUtils.isBlank(pageNum)){
            pageNum="1";
        }
        Page<Comments> commentsPage=commentsService.commentsPageByCid(new PageRequest(Integer.parseInt(pageNum)-1,2),contents.getCid());
        request.setAttribute("comments",commentsPage);
    }


    /**
     *
     *
     * @param request
     * @return
     */
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


    /**
     *
     *
     * @param request
     * @param name
     * @return
     */
    @GetMapping("/tag/{name}")
    public String tags(HttpServletRequest request,@PathVariable(name = "name") String name) {
        List<Contents> articles=contentsService.findAllContentsByTagAndName("tag",name);
        request.setAttribute("articles",articles);
        request.setAttribute("type","标签");
        request.setAttribute("keyWord",name);
        return html("page-category");
    }


    @GetMapping("/category/{name}")
    public String category(HttpServletRequest request,@PathVariable("name") String name){
        List<Contents> articles=contentsService.findAllContentsByCategories(name);
        request.setAttribute("articles",articles);
        request.setAttribute("type","分类");
        request.setAttribute("keyWord",name);
        return html("page-category");
    }

    @GetMapping("/links")
    public String links(HttpServletRequest request){
        List<Metas> links=metasService.findAllByType("link");
        request.setAttribute("links",links);
        return html("links");
    }


    @PostMapping("/comment")
    @ResponseBody
    @Transactional
    public ResultVo comment(HttpServletRequest request, @RequestParam String cid,@RequestParam String author,
                            @RequestParam String mail,@RequestParam String url,@RequestParam String text){
        String referer=request.getHeader("Referer");
        if (StringUtils.isBlank(referer)||cid==null){
            return new ResultVo(false,"非法请求　请在博客内评论");
        }
        if (StringUtils.isNotBlank(author)&&author.length()>50){
            return new ResultVo(false,"姓名过长");
        }
        if (StringUtils.isBlank(text)||author.length()>200){
            return new ResultVo(false,"评论过长或评论为空");
        }
        if (StringUtils.isNotBlank(mail)&& !PatternUtil.isEmail(mail)){
            return new ResultVo(false,"请输入正确的邮箱地址");
        }
        if (StringUtils.isNotBlank(url)&&!PatternUtil.isUrl(url)){
            return new ResultVo(false,"请输入正确的网址");
        }
        Comments comments=new Comments();
        comments.setAuthor(author);
        comments.setMail(mail);
        comments.setUrl(url);
        comments.setContent(text);
        comments.setCid(Integer.parseInt(cid));
        Contents contents= contentsService.findContentsByCid(Integer.parseInt(cid));
        contents.setCommentsNum(contents.getCommentsNum()+1);
        contentsService.update(contents);
        commentsService.save(comments);

        return new ResultVo(true,"评论成功");
    }


    @GetMapping("/{pageName}")
    public String page(HttpServletRequest request,@PathVariable  String pageName){
        Contents contents= contentsService.findContentsBySlugAndType(pageName,"page");
        getComments(contents,request);
        request.setAttribute("article",contents);
        return html("page");
    }
}
