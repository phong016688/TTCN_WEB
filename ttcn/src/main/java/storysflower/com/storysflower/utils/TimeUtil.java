package storysflower.com.storysflower.utils;

import java.sql.Time;

public class TimeUtil {
    public static Time convertFromStringToTimeSql(String time) {
        return Time.valueOf(time);
    }
}
