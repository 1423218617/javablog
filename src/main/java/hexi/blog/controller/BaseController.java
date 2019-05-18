package hexi.blog.controller;

public abstract class BaseController {
    public String baseView="themes/default/";


    public String html(String viewName){
        return baseView+viewName;
    }
}
