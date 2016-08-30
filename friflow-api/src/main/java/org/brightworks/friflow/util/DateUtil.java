package org.brightworks.friflow.util;


import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author kyel
 */
public class DateUtil {

    private static  final String DATE_FORMAT = "MM-dd-yyyy";

    private static final String DATE_TIME_FORMAT = "MM-dd-yyyy hh:mm aa";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT);

    public static String formatDateTime(LocalDateTime dateTime){
        return (dateTime != null) ? dateTime.toString(DATE_FORMAT) : "";
    }

    public static LocalDate formatDate(String date){
        return LocalDate.parse(date, DateTimeFormat.forPattern("MM-dd-yyyy"));
    }

    public static String formatDate(LocalDate date){
        return date.toString("MM-dd-yyyy");
    }

    public static String formatDateTime(LocalDateTime dateTime, String pattern){
        return (dateTime != null) ? dateTime.toString(pattern) : "";
    }

    public static LocalDate toLocalDate(String stringDate){
        return LocalDate.parse(stringDate, DateTimeFormat.forPattern("MM-dd-yyyy"));
    }
}
