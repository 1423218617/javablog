package hexi.blog.service.impl;

import hexi.blog.model.pojo.Contents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContentsServiceImplTest {

    @Autowired
    private ContentsServiceImpl contentsService;


    @Test
    public void findContent(){
        contentsService.findAll()
                .forEach(contents ->
                        System.out.println(new SimpleDateFormat("yyyy年MM月")
                                .format(contents.getCreated()*1000L)));
    }

    @Test
    public void tagSort(){
        List<Contents> list=contentsService.findAll();
        Collections.sort(list);
        list.forEach(contents -> System.out.println(contents.getHits()));
    }

}