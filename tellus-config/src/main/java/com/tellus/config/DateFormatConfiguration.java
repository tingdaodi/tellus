package com.tellus.config;

import com.tellus.support.exception.SystemErrorException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.tellus.toolkit.DateFormatConstants.YYYYMMDDHHMMSS;

/**
 * LocalDate, LocalDateTime 类型转换
 *
 * @author Roy
 * @date 2020/5/22 14:10
 */
@Configuration
public class DateFormatConfiguration {

    @Bean
    public Formatter<Date> dateFormatter() {
        return new Formatter<Date>() {
            @NonNull
            @Override
            public Date parse(@NonNull String text, @NonNull Locale locale) {
                SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
                try {
                    return sdf.parse(text);
                } catch (Exception e) {
                    throw new SystemErrorException("String to Date conversion error.");
                }
            }

            @NonNull
            @Override
            public String print(@NonNull Date object, @NonNull Locale locale) {
                SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
                return sdf.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @NonNull
            @Override
            public LocalDate parse(@NonNull String text, @NonNull Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
            }

            @NonNull
            @Override
            public String print(@NonNull LocalDate object, @NonNull Locale locale) {
                return DateTimeFormatter.ISO_DATE_TIME.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @NonNull
            @Override
            public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS));
            }

            @NonNull
            @Override
            public String print(@NonNull LocalDateTime object, @NonNull Locale locale) {
                return DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS).format(object);
            }
        };
    }

}
