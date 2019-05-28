package hexi.blog.dao;

import hexi.blog.model.pojo.Attach;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachDao extends JpaRepository<Attach,Integer> {
    public void deleteById(Integer id);
}
