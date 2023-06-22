package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        //댓글목록 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        //엔터티를 dto형태로 반환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i<comments.size(); i++){
            Comment comment = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(comment);
            dtos.add(dto);
        }
        //반환
        return dtos;
    }
}
