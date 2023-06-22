package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
            comment.getId(),
            comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
