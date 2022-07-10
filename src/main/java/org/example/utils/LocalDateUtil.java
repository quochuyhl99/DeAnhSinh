package org.example.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import org.apache.commons.validator.routines.DateValidator;

public class LocalDateUtil {
    public static LocalDate stringToLocalDate(String dob) {
        LocalDate localDate = null;

        if (DateValidator.getInstance().isValid(dob, "uuuu-M-d")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "uuuu-M-d"
            ).withResolverStyle(ResolverStyle.STRICT);
            localDate = LocalDate.parse(dob, formatter);
        } else if (DateValidator.getInstance().isValid(dob, "d/M/uuuu")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "d/M/yyyy"
            ).withResolverStyle(ResolverStyle.STRICT);
            localDate = LocalDate.parse(dob, formatter);
        } else if (DateValidator.getInstance().isValid(dob, "d-MMM-uuuu")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "d-MMM-uuuu"
            ).withResolverStyle(ResolverStyle.STRICT);
            localDate = LocalDate.parse(dob, formatter);
        }
//        else throw new DateException("Invalid date format.");

//        if (localDate.isBefore(LocalDate.of(1900, 1, 1))) {
//            throw new DateException("Date must be from 01/01/1900 toward.");
//        }
        return localDate;
    }
}
