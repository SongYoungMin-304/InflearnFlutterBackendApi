package com.project.flutterbackendapi.entity;

import com.project.flutterbackendapi.model.response.TestResponseDTO;
import jakarta.persistence.*;

@Entity
@Table(name ="test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long id;

    @Column(name = "test_name")
    private String testName;

    public void setTestName(String testName) {
        this.testName = testName;
    }


    public TestResponseDTO toResponseDTO() {
        return TestResponseDTO.builder()
                .id(id)
                .name(testName)
                .build();
    }
}
