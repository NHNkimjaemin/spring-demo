package com.example.demodata.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "tbl_memo")
@ToString
@Getter  // Getter Method 생성
@Builder  // 객체를 생성할 수 있도록 해줌
@AllArgsConstructor  // Builder 이용하려면 얘와 아래애까지 써줘야 에러 없
@NoArgsConstructor

public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)  //칼럼임을 알려줌. 칼럼이 아닌 경우 Transient 사용
    private String memoText;
}
