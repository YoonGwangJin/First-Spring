package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Comment  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 생성전략
    private Long id;
    @ManyToOne //댓글 여러개가 하나의 글에 연결
    @JoinColumn(name = "article_id") //article id 컬럼에 article 대표값 저장
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;


}
