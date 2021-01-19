package com.example.demodata.repository;

import com.example.demodata.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){

        System.out.println(memoRepository.getClass().getName());
    }

    @Test  // 삽입 테스트 (한번에 여러개)
    public void testInsertDummies(){

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    // 조회 - getOne, findById
    @Test
    public void testSelect(){

        Long mno = 100L;  // ???
        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("==================================");

        if(result.isPresent()){
            Memo memo = result.get();
        }
    }

    @Transactional
    @Test
    public void TestSelect2(){

        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("===========================");
        System.out.println(memo);
    }

    // 수정 - save
    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(100L).memoText("update text").build();
        System.out.println(memoRepository.save(memo));
    }


    // 삭제 - deleteById
    @Test
    public void testDelete(){

        Long mno = 100L;
        memoRepository.deleteById(mno);
    }

    //페이징 & 정렬
    @Test
    public void testPageDefault() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("PAGE");
        System.out.println("Total Pages: "+ result.getTotalPages());
        System.out.println("Page Number"+ result.getNumber());
        System.out.println("Page Size"+result.getSize());
        System.out.println("has Next Page"+result.hasNext());
        System.out.println("First Page" + result.isFirst());
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("Mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort.and(sort2);

        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    // 쿼리 메소드
    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for(Memo memo: list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(memo -> System.out.println(memo));
    }
}
