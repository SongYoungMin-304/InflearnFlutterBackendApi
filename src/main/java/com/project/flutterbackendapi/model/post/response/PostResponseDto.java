package com.project.flutterbackendapi.model.post.response;

import lombok.Builder;

@Builder
public class PostResponseDto {

    private Long id;
    private String postTitle;
    private String postContent;
    private long likeCnt;
    private long viewCnt;
    private long commentCnt;

}
