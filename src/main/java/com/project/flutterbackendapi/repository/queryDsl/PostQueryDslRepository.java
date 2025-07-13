package com.project.flutterbackendapi.repository.queryDsl;

import com.project.flutterbackendapi.entity.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.project.flutterbackendapi.entity.QPost.post;

@Repository
public class PostQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    public PostQueryDslRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Post> findAll(Pageable pageable) {
        return queryFactory
                .selectFrom(post)
                .leftJoin(post.user)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Optional<Post> findById(Long id) {
        Post foundPost = queryFactory
                .selectFrom(post)
                .leftJoin(post.user)
                .fetchJoin()
                .where(post.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(foundPost);
    }
}
