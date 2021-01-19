package com.example.demodata.repository;

import com.example.demodata.entity.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    //이렇게 상속받아서 엔티티 타입 정보랑 Id타입만 지정하면 작업 끝! JPA repository의 기능들 사용 가능. 쿼리 메소드 만드는 곳이 여기.

    // Query 메서드 추가 - 이름만 이렇게 만들어 놓으면 가져가서 사용 가
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // 이름이 너무 긺 -> 정렬은 Pageable 사용하기
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
}
