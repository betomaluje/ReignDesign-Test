package com.betomaluje.android.reigndesigntest.utils;

import android.content.Context;

import com.betomaluje.android.reigndesigntest.R;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by betomaluje on 3/12/16.
 */
public class DateUtils {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static DateTime convertDate(String createdAt) {
        if (createdAt == null || createdAt.isEmpty())
            return null;
        else
            return DateTime.parse(createdAt, formatter);
    }

    public static String getDate(Context context, String createdAt) {
        if (createdAt == null || createdAt.isEmpty())
            return "";

        DateTime program = convertDate(createdAt);

        //2016-02-08T14:33:40.749-03:00
        DateTime now = new DateTime();

        String temp = now.toString();

        int minus = Integer.parseInt(temp.substring(temp.length() - 1 - 5, temp.length() - 1 - 2));

        if (minus < 0) {
            program = program.plusHours(minus);
        } else {
            program = program.minusHours(minus);
        }

        int weeksBetween = Math.abs(Weeks.weeksBetween(program, now).getWeeks());
        int daysBetween = Math.abs(Days.daysBetween(program, now).getDays());
        int hoursBetween = Math.abs(Hours.hoursBetween(program, now).getHours());
        int minutesBetween = Math.abs(Minutes.minutesBetween(program, now).getMinutes());

        String time;

        //weeks
        if (weeksBetween >= 1) {
            time = String.format(context.getResources().getQuantityString(R.plurals.weeks, weeksBetween), weeksBetween);
        } else if (daysBetween > 0 && weeksBetween < 1) {
            time = String.format(context.getResources().getQuantityString(R.plurals.days, daysBetween), daysBetween);
        } else {
            if (hoursBetween > 0) {
                time = String.format(context.getResources().getQuantityString(R.plurals.hours, hoursBetween), hoursBetween);
            } else {
                if (minutesBetween > 0) {
                    time = String.format(context.getResources().getQuantityString(R.plurals.minutes, minutesBetween), minutesBetween);
                } else {
                    time = context.getString(R.string.date_now);
                }
            }
        }

        return time;
    }
}
