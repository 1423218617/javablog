package hexi.blog.controller;

import hexi.blog.model.ArchiveVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private IndexController indexController;
    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void archive(){
        List<ArchiveVo> archiveVoList=new ArrayList<>();
        archiveVoList.forEach(archiveVo -> {
            System.out.print(archiveVo.getDate()+"   ");
            archiveVo.getArticles().forEach(contents -> {
                System.out.print(new SimpleDateFormat("yyyy年MM月").format(new Date(contents.getCreated()*1000L)));
            });
            System.out.println();
        });
    }
}