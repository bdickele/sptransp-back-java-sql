package org.bdickele.sptransp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by bdickele
 */
public interface SpaceTranspDTO {

    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    default String formatDate(LocalDateTime date) {
        return date.format(DEFAULT_DATE_FORMATTER);
    }
}
