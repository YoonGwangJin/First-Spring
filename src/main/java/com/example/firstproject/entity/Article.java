package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter //모든 속성에 대해 get메서드 추가
@AllArgsConstructor  //생성자메서드 리펙토링
@ToString   //tostring 리펙토링
@NoArgsConstructor //default생성자 추가
@Entity //해당 객체로 테이블을 만듬
 public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 id 자동생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
       if(article.title!=null){
          this.title = article.title;
       }
       if(article.content!=null){
          this.content = article.content;
       }

    }
   /* public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/
}