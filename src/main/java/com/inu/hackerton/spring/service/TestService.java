package com.inu.hackerton.spring.service;

import com.inu.hackerton.spring.model.Test;
import com.inu.hackerton.spring.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;


    public Test create(Test test) {
       return testRepository.save(test);
    }

    public Optional<Test> read(Long id) {
        return testRepository.findById(id);
    }

    public Optional<Test> update(Test test) {
        // 저장되어있는 클래스파일 가져오기
        return testRepository
                .findById(test.getId())
                .map((savedTest) -> {
                    savedTest.setName(test.getName());
                    savedTest.setEmail(test.getEmail());

                    return testRepository.save(savedTest); // 다시 레포지토리에 저장
                });
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }
}
