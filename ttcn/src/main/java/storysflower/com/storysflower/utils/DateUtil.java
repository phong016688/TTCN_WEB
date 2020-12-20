package storysflower.com.storysflower.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DateUtil {
    public static Time convertFromStringToTimeStamp(String time) {
        return Time.valueOf(time+ ":00");
    }

    public static Date convertFromStringToDaTe(String date) {
        return Date.valueOf(date);
    }
}
