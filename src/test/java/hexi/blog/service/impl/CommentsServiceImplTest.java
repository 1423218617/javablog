package hexi.blog.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentsServiceImplTest {
    @Autowired
    private CommentsServiceImpl commentsService;


    @Test
    public void getComments(){
        commentsService.commentsPageByCid(new PageRequest(0,7),6).getContent().forEach(comments -> System.out.println(comments.getContent()));
    }

}