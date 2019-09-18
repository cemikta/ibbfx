/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

/**
 * Date Helpers
 *
 * @author Cem Ikta
 */
public class DateHelpers {

    public DateHelpers() {
    }

    /**
     * Convert {@link java.util.Date} to {@link java.time.LocalDate}.
     * 
     * @param dateToConvert date to convert
     * @return converted local date
     */

    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        LocalDate convertedLocalDate = null;
        
        if (dateToConvert != null) {
            convertedLocalDate = LocalDateTime.ofInstant(
                    dateToConvert.toInstant(), 
                    ZoneId.systemDefault()).toLocalDate();
        }
        
        return convertedLocalDate;
    }

    /**
     * Convert {@link java.time.LocalDate} to {@link java.util.Date}.
     * 
     * @param localDateToConvert local date
     * @return converted date
     */
    public static Date convertLocalDateToDate(LocalDate localDateToConvert) {
        Date convertedDate = null;
        
        if (localDateToConvert != null) {
            convertedDate = Date.from(localDateToConvert.atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant());
        }
        
        return convertedDate;
    }

    /**
     * Formats date to formatted timestamp.
     *
     * @param date date
     * @return formatted timestamp
     */
    public static String formatDateToTimestamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(date);
    }

    /**
     * Gets difference between two {@link LocalDate} as days.
     *
     * @param startDate
     * @param endDate
     * @return days
     */
    public static long differenceDays(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        long difference = 1 + ChronoUnit.DAYS.between(startDate.atStartOfDay(), endDate.atStartOfDay());
        return difference;
    }

}
