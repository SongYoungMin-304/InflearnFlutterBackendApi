package com.project.flutterbackendapi.model.response;

import com.project.flutterbackendapi.enums.UserType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDTO {

    private long id;

    private String userName;

    private String userAccount;

    private UserType userType;

}
