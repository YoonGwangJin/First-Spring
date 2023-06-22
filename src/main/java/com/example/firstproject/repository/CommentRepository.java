package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글 모든 댓글 조회
    @Query(value = "SELECT * FROM comment where article_id = :articleID", nativeQuery = true)
    List<Comment> findByArticleId(Long articleID);

    //특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);
}
