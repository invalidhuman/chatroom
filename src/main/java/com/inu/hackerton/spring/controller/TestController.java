package com.inu.hackerton.spring.controller;

import com.inu.hackerton.spring.model.Test;
import com.inu.hackerton.spring.model.TestResponse;
//import com.inu.hackerton.spring.repository.TestRepository;
import com.inu.hackerton.spring.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/test-api")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    /*curl -X POST http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"A\",\"email\":\"B\"}"*/
    @PostMapping("/test")
    public TestResponse create(@RequestBody Test test) {
        Test ret = testService.create(test);

        return new TestResponse(ret.getId(),ret.getName());
    }


    /*curl -X GET http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"A\",\"email\":\"B\"}"*/
    @GetMapping("/test")
    public TestResponse read(@RequestParam Long id) {
        Optional<Test> ret = testService.read(id);
        // map은 값이 있을때만동작하고 없으면 동작하지않음
        return ret
                .map(it -> new TestResponse(it.getId(),it.getName()))
//                .orElseThrow(NoSuchElementException::new);
                // 알고있는 상황에 대해 status 코드 설정
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity not found"));
    }

    /*curl -X PUT http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"A\",\"email\":\"B\"}"*/
    @PutMapping("/test")
    public TestResponse update(@RequestBody Test test) {
        Optional<Test> ret = testService.update(test);

        return ret
                .map(it -> new TestResponse(it.getId(),it.getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Entity not found"));
    }

    /*curl -X DELETE http://localhost:8080/test-api/test -H "Content-Type: application/json" -d "{\"id\":1,\"name\":\"A\",\"email\":\"B\"}"*/
    @DeleteMapping("/test")
    public void delete(@RequestParam Long id) {
        testService.delete(id);
    }

}
