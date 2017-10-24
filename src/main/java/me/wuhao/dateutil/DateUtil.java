package me.wuhao.dateutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by everseeker on 2017/10/24.
 */
public class DateUtil {
    /**
     * 判断入参date是否为有效的日期
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        boolean isValid = true;
        if (date == null) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            df.setLenient(false);
            df.parse(date);
        } catch (ParseException e) {
            isValid = false;
        }
        return isValid;
    }

    public static void main(String[] args) {
        System.out.println(isValidDate("2017-02-29"));
    }
}
