package com.project.flutterbackendapi.common.exception;

import com.project.flutterbackendapi.common.exception.message.ErrorMessage;
import org.webjars.NotFoundException;

public class UserApplyingException extends NotFoundException {

    public UserApplyingException(String message) {
        super(message);
    }

    public UserApplyingException() {
        super(ErrorMessage.USER_APPLYING.getErrorMessage());
    }

}
