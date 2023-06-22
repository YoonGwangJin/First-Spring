package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@ToString
@AllArgsConstructor
@Getter @Setter
public class CommentDto {
    private Long id;
    private Article article;
    private String nickname;
    private String body;
}
