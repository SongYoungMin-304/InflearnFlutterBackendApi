package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.entity.Post;
import com.project.flutterbackendapi.model.post.request.PostRegisterRequestDto;
import com.project.flutterbackendapi.model.post.response.PostResponseDto;
import com.project.flutterbackendapi.repository.PostRepository;
import com.project.flutterbackendapi.repository.queryDsl.PostQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostQueryDslRepository postQueryDslRepository;

    public PostResponseDto savePost(PostRegisterRequestDto registerRequestDto){

        Post post = Post.toEntity(registerRequestDto);

        postRepository.save(post);

        return post.toResponseDto();
    }


}
