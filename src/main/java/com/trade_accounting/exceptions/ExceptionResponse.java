package com.trade_accounting.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;

    private Integer status;

    private String error;

    private String message;

    public ExceptionResponse(HttpStatus status, String message) {
        this(new Date(), status.value(), status.getReasonPhrase(), message);
    }
}
