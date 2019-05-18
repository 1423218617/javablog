package hexi.blog.dao;

import hexi.blog.model.pojo.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsDao extends JpaRepository< Options,String> {
}
