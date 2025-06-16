package com.project.flutterbackendapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="t_comment_like")
public class CommentLike {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_comment_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 게시글 좋아요를 누른 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment; // 좋아요가 눌린 게시글
}
