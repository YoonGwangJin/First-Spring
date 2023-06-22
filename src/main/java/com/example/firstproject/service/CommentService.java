package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public List<CommentDto> comments(Long articleId) {
        // CommentDto는 댓글을 나타내는 DTO 클래스라고 가정합니다.
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // Comment 엔티티를 CommentDto 객체로 변환합니다.
        List<CommentDto> commentDtos = convertToCommentDtos(comments);
        return commentDtos;
    }

    private List<CommentDto> convertToCommentDtos(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto(comment.getId(), comment.getArticle(), comment.getBody(), comment.getNickname());
            // Comment 엔티티의 필드에 맞게 CommentDto 객체에 필드를 설정합니다.
            // 예를 들어, setId, setContent, setAuthor 등의 메서드를 사용하여 필드를 설정합니다.

            commentDtos.add(commentDto);
        }

        return commentDtos;
    }
}
