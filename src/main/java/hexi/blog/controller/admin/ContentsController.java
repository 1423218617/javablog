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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class ContentsController {
    @Autowired
    private ContentsService contentsService;
    @Autowired
    private MetasService metasService;

    @GetMapping("/publish")
    public String newContents(HttpServletRequest request){
        List<Metas> categories=metasService.findAllByType(MetasTypeEnum.CATEGORY.getType());
        request.setAttribute("categories",categories);
        return "admin/article_edit";
    }

    @PostMapping("/publish")
    @ResponseBody
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
        contentsService.save(contents);
        return new ResultVo(true,"发布成功");
    }

    @GetMapping("")
    public String adminContents(HttpServletRequest request){
        Page<Contents> contents=contentsService.findAllContentsByTypeOrderByCreatedDesc(ContentsStatusEnum.POST.getType(),new PageRequest(0,5));
        request.setAttribute("articles",contents);
        System.out.println(contents);
        return "admin/article_list";
    }
}
