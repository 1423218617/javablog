package hexi.blog.controller.admin;


import hexi.blog.dao.CommentsDao;
import hexi.blog.emun.CommentsStatusEnum;
import hexi.blog.model.pojo.Comments;
import hexi.blog.model.vo.ResultVo;
import hexi.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/comments")
public class CommentsController {
    @Autowired
    private CommentsDao commentsDao;

    @GetMapping
    public String adminComments(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "5") int limit, HttpServletRequest request){
        Page<Comments> comments=commentsDao.findAllByTypeOrderByCreatedDesc(new PageRequest(page-1,limit), CommentsStatusEnum.APPROVED.getType());
        System.out.println(comments.getContent());
        request.setAttribute("comments",comments);
        return "admin/comment_list";
    }

    @PostMapping("delete")
    @Transactional
    @ResponseBody
    public ResultVo deleteComments(@RequestParam String coid){
        commentsDao.deleteByCoid(Integer.parseInt(coid));
        return new ResultVo(true,"删除评论成功");
    }
}
