package hexi.blog.dao;

import hexi.blog.model.pojo.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface OptionsDao extends JpaRepository< Options,String> {
  /*  @Modifying
    @Query(value = "update t_options set value =:#{social_github} where name='social_zhihu'," +
            "set value =:#{social_twitter} where name='social_twitter'," +
            "set value =:#{social_weibo} where name='social_weibo'," +
            "set value =:#{social_zhihu} where name='social_zhihu'",nativeQuery = true)
    public void updateOption(String social_github,String social_twitter,String social_weibo,String social_zhihu);*/
}
