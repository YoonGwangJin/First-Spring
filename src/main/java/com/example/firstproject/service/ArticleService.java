package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 선언(서비스 객체를 스프링부트에 선언)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
         Article article = dto.toEntity();
         if(article.getId()!=null) //기존에 있는 id일 경우 null리턴
             return null;
         return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto ){
        //1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        //2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리(없는대상, 없는 아이디)
        if(target == null || id !=article.getId()){
            //400, 잘못된 요청
            return null;
        }
        //4. 업데이트 및 정상응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {
        //1. 대상엔티티찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리
        if(target == null){
            return null;
        }
        //3. 삭제 후 리턴
        articleRepository.delete(target);
        return target;
    }


    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1.dto묶음을 entity묶음으로
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
//        List<Article> articleList = new ArrayList<>();
//        for(int i=0; i<dtos.size(); i++){
//            ArticleForm dto =dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }
        //2. entity묶음을 db로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
//        for(int i=0; i<articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }
        //3. 강제로 예외
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결재실패")
        );
        //4. 결과값 반환
        return articleList;
    }
}
