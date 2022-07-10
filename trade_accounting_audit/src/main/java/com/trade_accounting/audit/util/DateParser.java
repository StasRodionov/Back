package com.trade_accounting.audit.util;

import java.time.LocalDateTime;

public class DateParser {
    public static LocalDateTime fromStringFieldInDto(String stringDate) {
        return LocalDateTime.of(
                Integer.parseInt(stringDate.substring(6, 10)),
                Integer.parseInt(stringDate.substring(3, 5)),
                Integer.parseInt(stringDate.substring(0, 2)),
                Integer.parseInt(stringDate.substring(11, 13)),
                Integer.parseInt(stringDate.substring(14))
        );
    }
}
