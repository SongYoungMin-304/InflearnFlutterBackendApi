package com.project.flutterbackendapi.model.post.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostRegisterRequestDto {

    private String postTitle;
    private String postContent;

}
