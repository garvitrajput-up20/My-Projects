package com.bank.ams.dto;

import lombok.Data;


@Data
public class ErrorResponse {

    private final int code;

    private final String message;

}
