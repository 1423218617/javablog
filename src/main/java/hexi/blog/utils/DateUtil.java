package hexi.blog.utils;

import java.util.Date;

public class DateUtil {
    public static int getUnixTime(){
        return (int)(new Date().getTime()/1000L);
    }
}
