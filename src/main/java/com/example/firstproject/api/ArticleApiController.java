package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {


    @Autowired
    private ArticleRepository articleRepository;

    //get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //post
    @PostMapping("/api/articles")
    public Article index(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {    //상태를 담아서 보낼 수 있음
        //1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        //2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리(없는대상, 없는 아이디)
        if(target == null || id !=article.getId()){
            //400, 잘못된 요청
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 정상응답
        target.patch(article);
        Article update = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
