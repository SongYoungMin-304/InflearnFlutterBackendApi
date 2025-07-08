package com.project.flutterbackendapi.entity;

import com.project.flutterbackendapi.model.post.request.PostRegisterRequestDto;
import com.project.flutterbackendapi.model.post.response.PostResponseDto;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name ="t_post")
@Builder
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_cont")
    private String postContent;

    @Column(name = "like_cnt")
    private long likeCnt;

    @Column(name = "view_cnt")
    private long viewCnt;

    @Column(name = "comment_cnt")
    private long commentCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 게시글 작성자

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    public static Post createPost(PostRegisterRequestDto postRegisterRequestDto,
                                  User user) {
        Post post = Post.builder()
                .user(user)
                .postTitle(postRegisterRequestDto.getPostTitle())
                .postContent(postRegisterRequestDto.getPostContent())
                .likeCnt(0L) // 초기 좋아요 수
                .viewCnt(0L) // 초기 조회수
                .commentCnt(0L) // 초기 댓글 수
                .build();
        return post;
    }

    public PostResponseDto toResponseDto(){
        return PostResponseDto.builder()
                .id(this.id)
                .postTitle(this.postTitle)
                .postContent(this.postContent)
                .likeCnt(this.likeCnt)
                .viewCnt(this.viewCnt)
                .commentCnt(this.commentCnt)
                .build();
    }

}
