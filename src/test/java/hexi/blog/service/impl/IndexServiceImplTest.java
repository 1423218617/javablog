package hexi.blog.service.impl;

import hexi.blog.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexServiceImplTest {

    @Autowired
    private IndexService indexService;

    @Test
    public  void getStatistical(){
        indexService.getStatistical();
    }
}