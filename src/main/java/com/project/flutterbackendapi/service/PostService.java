package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.common.exception.NotFoundException;
import com.project.flutterbackendapi.entity.Post;
import com.project.flutterbackendapi.entity.User;
import com.project.flutterbackendapi.model.post.request.PostRegisterRequestDto;
import com.project.flutterbackendapi.model.post.request.PostUpdateRequestDto;
import com.project.flutterbackendapi.model.post.response.PostResponseDto;
import com.project.flutterbackendapi.repository.PostRepository;
import com.project.flutterbackendapi.repository.UserRepository;
import com.project.flutterbackendapi.repository.queryDsl.PostQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponseDto updatePost(Long userId, Long postId,  PostUpdateRequestDto postUpdateRequestDto){

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new NotFoundException("해당 게시글에 대한 권한이 없습니다.");
        }

        post.updatePost(postUpdateRequestDto);

        return post.toResponseDto();
    }

    public PostResponseDto deletePost(Long userId, Long postId){

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));

        if (!post.getUser().getId().equals(user.getId())) {
            throw new NotFoundException("해당 게시글에 대한 권한이 없습니다.");
        }

        post.delete();

        return post.toResponseDto();

    }

    public List<PostResponseDto> getPosts(Pageable pageable) {
        return postQueryDslRepository.findAll(pageable)
                .stream()
                .map(Post::toResponseDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long id) {
        Post post = postQueryDslRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
        return post.toResponseDto();
    }

}
