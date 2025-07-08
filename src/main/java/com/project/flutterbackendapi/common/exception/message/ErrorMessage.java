package com.project.flutterbackendapi.common.exception.message;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_APPLYING("USER_APPLYING");

    private final String errorMessage;
    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}