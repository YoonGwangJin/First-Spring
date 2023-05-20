package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //생성자메서드 리팩토링
@ToString   //tostring 메서드 리펙토링
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    /*public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
    @Override                               //refactoring range
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    public Article toEntity() {
        return new Article(id, title, content);
    }
}