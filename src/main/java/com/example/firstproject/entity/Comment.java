package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
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


    public static Comment createComment(CommentDto dto, Article article) {
        //예외발생
        if (dto.getId()!=null){
            throw new IllegalArgumentException("댓글생성 실패, 댓글의 id가 없어야합니다.");
        }
        if(dto.getArticleId()!=article.getId()){
             throw new IllegalArgumentException("댓글생성 실패, 게시글의 id가 잘못되었음");
        }
        //엔터티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );

        
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
