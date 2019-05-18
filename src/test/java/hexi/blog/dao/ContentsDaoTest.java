package hexi.blog.dao;

import hexi.blog.model.pojo.Contents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContentsDaoTest {

    @Autowired
    private ContentsDao contentsDao;
    @Test
    public void test(){
        PageRequest pageable=new PageRequest(1,2);
        Page<Contents> page=contentsDao.findAll(pageable);
        System.out.println(page);
    }

}