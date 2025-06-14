package com.project.flutterbackendapi.repository.queryDsl;

import com.project.flutterbackendapi.entity.Test;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.project.flutterbackendapi.entity.QTest.test;

@Repository
public class TestQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public TestQueryDslRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<Test> findById(Long id) {

        Test test1 = queryFactory
                .selectFrom(test)
                .where(test.id.eq(id)).fetchOne();

        return Optional.ofNullable(test1);
    }
}
