package hexi.blog.utils;


import hexi.blog.model.pojo.Contents;
import hexi.blog.model.pojo.Options;
import hexi.blog.service.OptionsService;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class UserCommonMethod {

    @Autowired
    private static OptionsService optionsService;

    public static final String[] icons= {"bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat", "bg-ico-code", "bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock"};


    /**
     *
     *
     * @param cid
     * @return
     * @throws IOException
     */
    public static String showThumb(int cid) throws IOException {
        File file=new File("/home/hexi/桌面/blog/src/main/resources/static/user/img/rand");
        String[] list= file.list();
        return "/user/img/rand/"+ list[cid%list.length];
    }

    /**
     *
     *
     * @param cid
     * @return
     */
    public static String showIcon(int cid){
        return icons[cid % icons.length];
    }


    /**
     *
     *
     * @param unixTime
     * @return
     */
    public static String fmtdate(Integer unixTime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
        String date=simpleDateFormat.format(new Date(unixTime*1000L));
        return date;
    }


    /**
     *
     *
     * @param tags
     * @return
     */
    public static String showTags(String tags){
        if (StringUtils.isNotBlank(tags)){
            String[] tagslist=tags.split(",");
            StringBuffer buffer=new StringBuffer();
            for (String s :
                    tagslist) {
                buffer.append("<a href=\"/tag/" +s + "\">" + s + "</a>");
            }
            return buffer.toString();
        }
        return "";
    }

    /**
     *
     *
     * @param categories
     * @return
     */
    public static String showCategories(String categories){
        if (StringUtils.isNotBlank(categories)){
            String[] categorylist=categories.split(",");
            StringBuffer buffer=new StringBuffer();
            for (String s :
                    categorylist) {
                buffer.append("<a href=\"/category/" +s + "\">" + s + "</a>");
            }
            return buffer.toString();
        }
        return showCategories("默认分类");
    }


    /**
     *
     *
     * @param content
     * @return
     */
    public static String contentFormat(String content){
        if (StringUtils.isNotBlank(content)){
            Node node= Parser.builder().build().parse(content);
            HtmlRenderer htmlRenderer=HtmlRenderer.builder().build();
            String html= htmlRenderer.render(node);
            return html;
        }
        return "";
    }

    public static Map<String,String> social(){
        final String pre="social_";
        List<Options> optionsList=optionsService.findAll();
        Map<String,String> map =new HashMap<>();
        optionsList.forEach(option -> map.put(option.getName(),option.getValue()));
        Map<String,String> socialMap=new HashMap<>();
        socialMap.put("weibo",map.get(pre+"weibo"));
        socialMap.put("zhihu",map.get(pre+"zhihu"));
        socialMap.put("github",map.get(pre+"github"));
        socialMap.put("twitter",map.get(pre+"twitter"));
        return socialMap;
    }

}
