package com.inu.hackerton.spring.repository;

import com.inu.hackerton.spring.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 싱글톤으로 등록
public interface TestRepository extends JpaRepository<Test,Long> { // 실제데이터랑 매핑되는 jpa entity, key

}
