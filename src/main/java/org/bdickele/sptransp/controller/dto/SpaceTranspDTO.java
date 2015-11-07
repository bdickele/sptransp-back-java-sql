package org.bdickele.sptransp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by bdickele
 */
public interface SpaceTranspDTO {

    DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    DateTimeFormatter DATE_FORMATTER_FOR_COMPARISON = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");


    default String formatDate(LocalDateTime date) {
        return date.format(DEFAULT_DATE_FORMATTER);
    }

    default String formatDateForComparison(LocalDateTime date) {
        return date.format(DATE_FORMATTER_FOR_COMPARISON);
    }
}
