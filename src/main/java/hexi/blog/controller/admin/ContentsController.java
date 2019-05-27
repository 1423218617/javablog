package hexi.blog.controller.admin;

import hexi.blog.emun.ContentsStatusEnum;
import hexi.blog.emun.MetasTypeEnum;
import hexi.blog.model.pojo.Contents;
import hexi.blog.model.pojo.Metas;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.ContentsService;
import hexi.blog.service.MetasService;
import hexi.blog.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class ContentsController {
    @Autowired
    private ContentsService contentsService;
    @Autowired
    private MetasService metasService;

    /**
     *
     * 新增文章
     * @param request
     * @return
     */
    @GetMapping("/publish")
    public String newContents(HttpServletRequest request){
        List<Metas> categories=metasService.findAllByType(MetasTypeEnum.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }

    /**
     * 新增文章
     *
     * @param contents
     * @param request
     * @return
     */
    @PostMapping("/publish")
    @ResponseBody
    @Transactional
    public ResultVo doNewArticle(Contents contents,HttpServletRequest request){
        String referer=request.getHeader("Referer");
        if (StringUtils.isBlank(referer)){
            return new ResultVo(false,"非法请求");
        }
        if (StringUtils.isBlank(contents.getTitle())){
            return new ResultVo(false,"标题不可以为空");
        }
        if (StringUtils.isBlank(contents.getContent())){
            return new ResultVo(false,"内容不可以为空");
        }
        contents.setCreated(DateUtil.getUnixTime());
        contents.setModified(DateUtil.getUnixTime());
        contents.setHits(0);
        contents.setCommentsNum(0);
        contents.setType(ContentsStatusEnum.POST.getType());
        contentsService.saveNew(contents);
        return new ResultVo(true,"发布成功");
    }


    /**
     * 文章列表
     *
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping("")
    public String adminContents(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "limit", defaultValue = "5") int limit, HttpServletRequest request){
        Page<Contents> contents=contentsService.findAllContentsByTypeOrderByCreatedDesc(ContentsStatusEnum.POST.getType(),new PageRequest(page-1,limit));
        request.setAttribute("articles",contents);
        System.out.println(contents);
        return "admin/article_list";
    }

    /**
     *
     *
     * @param cid
     * @param request
     * @return
     */
    @GetMapping("/{cid}")
    public String editContents(@PathVariable(name = "cid") String cid,HttpServletRequest request){
        Contents contents=contentsService.findContentsByCid(Integer.parseInt(cid));
        List<Metas> categories=metasService.findAllByType(MetasTypeEnum.CATEGORY.getType());
        request.setAttribute("contents",contents);
        request.setAttribute("categories",categories);
        request.setAttribute("active","publish");
        return "admin/article_edit";

    }


    /**
     * 修改文章
     *
     * @param contents
     * @return
     */
    @PostMapping("/modify")
    @ResponseBody
    @Transactional
    public ResultVo modifyContents(Contents contents){
        contents.setType(ContentsStatusEnum.POST.getType());
        contentsService.saveOld(contents);
        return new ResultVo(true,"更新成功");

    }


    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public ResultVo deleteContents(@RequestParam String cid){
        Integer contentsCid=Integer.parseInt(cid);
        contentsService.deleteContents(contentsCid);
        return new ResultVo(true,"删除文章成功");
    }
}
