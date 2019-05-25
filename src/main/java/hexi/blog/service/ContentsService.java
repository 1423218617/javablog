package hexi.blog.service;

import hexi.blog.model.pojo.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentsService {
    public Page<Contents> contentsPage(Pageable pageable,String type);
    public Contents findContentsByCid(Integer cid);
    public Contents findContentsBySlug(String Slug);
    public List<Contents> findAll();
    public List<Contents> findAllContentsByTagAndName(String tag,String name);
    public List<Contents> findAllContentsByCategories(String category);
    public Contents findContentsBySlugAndType(String slug,String type);
    public void save(Contents contents);
}
