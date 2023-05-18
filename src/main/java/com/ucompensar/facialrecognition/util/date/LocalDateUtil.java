package com.ucompensar.facialrecognition.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class LocalDateUtil {

   public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

   public static String localDateToString(final LocalDateTime localDateTime) {
      final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
      return localDateTime.format(dateTimeFormatter);

   }

   public static LocalDateTime randomLocalDateTime() {
      final LocalDateTime periodStart = LocalDateTime.now().minusDays(30);
      final LocalDateTime periodEnd = LocalDateTime.now();
      final int randomSeconds = new Random().nextInt((int) periodStart.until(periodEnd, ChronoUnit.SECONDS));
      return periodEnd.minusSeconds(randomSeconds);
   }

}
