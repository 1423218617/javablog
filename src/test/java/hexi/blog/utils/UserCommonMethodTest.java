package hexi.blog.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserCommonMethodTest {


    @Resource
    private UserCommonMethod userCommonMethod;
    @Test
    public void ss()throws IOException {
        System.out.println(UserCommonMethod.showThumb(3));
    }

    @Test
    public void time(){
        System.out.println(UserCommonMethod.fmtdate(1558089001));
    }

    @Test
    public void social(){
        Map map=UserCommonMethod.social();
        map.forEach((n,v)-> System.out.println(n+"  "+v));
    }

}