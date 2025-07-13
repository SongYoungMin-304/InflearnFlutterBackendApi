package com.project.flutterbackendapi.model.post.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostResponseDto {

    private Long id;
    private String postTitle;
    private String postContent;
    private long likeCnt;
    private long viewCnt;
    private long commentCnt;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
