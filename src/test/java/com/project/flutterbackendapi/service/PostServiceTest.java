package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.enums.UserType;
import com.project.flutterbackendapi.model.post.request.PostRegisterRequestDto;
import com.project.flutterbackendapi.model.post.request.PostUpdateRequestDto;
import com.project.flutterbackendapi.model.post.response.PostResponseDto;
import com.project.flutterbackendapi.model.test.response.UserResponseDTO;
import com.project.flutterbackendapi.model.user.request.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class PostServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;


    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        userResponseDTO = userService.registerUser(
                UserRequestDTO.builder()
                        .userName("사용자")
                        .userAccount("test2")
                        .userPassword("1234")
                        .userType(UserType.USER)
                        .build()
        );
        Assertions.assertEquals("사용자", userResponseDTO.getUserName());
    }

    @Test
    void savePost() {
        PostResponseDto postResponseDto = postService.savePost(
                userResponseDTO.getId(),
                PostRegisterRequestDto.builder()
                        .postTitle("제목")
                        .postContent("내용")
                        .build()
        );

        Assertions.assertEquals("제목", postResponseDto.getPostTitle());
    }

    @Test
    void updatePost() {
        PostResponseDto postResponseDto = postService.savePost(
                userResponseDTO.getId(),
                PostRegisterRequestDto.builder()
                        .postTitle("제목")
                        .postContent("내용")
                        .build()
        );

        PostResponseDto updateResponseDto = postService.updatePost(
                userResponseDTO.getId(),
                postResponseDto.getId(),
                PostUpdateRequestDto.builder()
                        .postTitle("수정된 제목")
                        .postContent("수정된 내용")
                        .build()
        );

        Assertions.assertEquals("수정된 제목", updateResponseDto.getPostTitle());
    }
}
