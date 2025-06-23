package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.common.exception.NotFoundException;
import com.project.flutterbackendapi.entity.Post;
import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.model.post.request.PostRegisterRequestDto;
import com.project.flutterbackendapi.model.post.response.PostResponseDto;
import com.project.flutterbackendapi.repository.PostRepository;
import com.project.flutterbackendapi.repository.UserRepository;
import com.project.flutterbackendapi.repository.queryDsl.PostQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostQueryDslRepository postQueryDslRepository;

    private final UserRepository userRepository;

    public PostResponseDto savePost(Long userId, PostRegisterRequestDto registerRequestDto){

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        Post post = Post.createPost(registerRequestDto, user);

        postRepository.save(post);

        return post.toResponseDto();
    }


}
