package com.project.flutterbackendapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="t_post")
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

    @OneToMany(mappedBy = "post")
    private List<File> fileList; // 게시글에 첨부된 파일들

}
