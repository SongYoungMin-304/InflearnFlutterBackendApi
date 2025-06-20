package com.project.flutterbackendapi.service;

import com.project.flutterbackendapi.common.exception.NotFoundException;
import com.project.flutterbackendapi.entity.Test;
import com.project.flutterbackendapi.repository.TestRepository;
import com.project.flutterbackendapi.repository.queryDsl.TestQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    private final TestQueryDslRepository testQueryDslRepository;

    public Test findById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found with id: " + id));
    }

    public Test findV2ById(Long id) {
        return testQueryDslRepository.findById(id).orElseThrow(() -> new NotFoundException("Test not found with id: " + id));
    }

}
