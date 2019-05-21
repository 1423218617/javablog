package hexi.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    public static Boolean isEmail(String email){
        Matcher matcher= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }
    public static Boolean isUrl(String url){
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

}
