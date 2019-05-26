package hexi.blog.utils;


import hexi.blog.model.pojo.Metas;
import org.springframework.stereotype.Component;

@Component
public class AdminCommonMethod {
    public static Boolean existCat(Metas metas,String c){
        if (metas.getName().equals(c)){
            return true;
        }
        return false;
    }

}
