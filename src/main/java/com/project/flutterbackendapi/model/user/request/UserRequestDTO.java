package com.project.flutterbackendapi.model.user.request;

import com.project.flutterbackendapi.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequestDTO {

    private String userName;

    private String userAccount;

    private String userPassword;

    private UserType userType;

}
