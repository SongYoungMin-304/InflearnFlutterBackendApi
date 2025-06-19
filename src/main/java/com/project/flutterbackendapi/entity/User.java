package com.project.flutterbackendapi.entity;

import com.project.flutterbackendapi.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name ="t_user")
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType accountType;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;




}
