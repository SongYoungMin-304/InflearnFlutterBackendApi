package com.project.flutterbackendapi.model.post.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequestDto {

    private String postTitle;
    private String postContent;

}
