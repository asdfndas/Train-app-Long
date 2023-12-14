package com.example.trainticketproject.utils;

import android.os.Build;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @TypeConverter
    public static LocalDateTime toLocalDateTime(String value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return value == null ? null : LocalDateTime.parse(value, formatter);
        }
        return null;
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime date) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date == null ? null : date.format(formatter);
        }
        return null;
    }

    public static String formatFullDate(LocalDateTime dateTime) {
        String formattedDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedDate = dateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedDate = addDayOfMonthSuffix(dateTime.getDayOfMonth(), formattedDate);
        }

        return formattedDate;
    }

    private static String addDayOfMonthSuffix(int dayOfMonth, String formattedDate) {
        String dayOfMonthString = String.valueOf(dayOfMonth);

        if (dayOfMonth >= 11 && dayOfMonth <= 13) {
            formattedDate = formattedDate.replace(dayOfMonthString, dayOfMonthString + "th");
        } else {
            switch (dayOfMonth % 10) {
                case 1:
                    formattedDate = formattedDate.replace(dayOfMonthString, dayOfMonthString + "st");
                    break;
                case 2:
                    formattedDate = formattedDate.replace(dayOfMonthString, dayOfMonthString + "nd");
                    break;
                case 3:
                    formattedDate = formattedDate.replace(dayOfMonthString, dayOfMonthString + "rd");
                    break;
                default:
                    formattedDate = formattedDate.replace(dayOfMonthString, dayOfMonthString + "th");
                    break;
            }
        }

        return formattedDate;
    }

}
