package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.beans.Transient;
import java.util.List;

@Slf4j  //logging 기능 mapping? 수행시 마다  log남김...
@RestController //데이터를 json의 형태로 반환
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

//    @Autowired //스프링부트 미리 생성해둔 객체를 연결해줌 =new articleRepository();
//    private ArticleRepository articleRepository;
//
    //get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    //post
    @PostMapping("/api/articles/{id}")
    public ResponseEntity<Article> show(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //patch
    @PatchMapping("/api/articles/{id}") //controller에서는 뭐를 받고 뭐를 리턴하는지만 알면됌
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted !=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 -> 실패? -> 롤백
    @PostMapping("/api/transactional-test")
    public ResponseEntity<List<Article>> transcationTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos); //생성자가 없이 클래스 메서드 소환햇는데 static으로 선언안되있음
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable Long id){
//        return articleRepository.findById(id).orElse(null);
//    }
//    //post
//    @PostMapping("/api/articles")
//    public Article index(@RequestBody ArticleForm dto){  //@requestbody json의 body부분을 dto로 들고옴
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//    //patch
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {    //respoenseentity상태를 담아서 보낼 수 있음
//        //1. 수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id : {}, article : {}", id, article.toString());
//        //2. 대상 엔티티 조회
//        Article target = articleRepository.findById(id).orElse(null);
//        //3. 잘못된 요청 처리(없는대상, 없는 아이디)
//        if(target == null || id !=article.getId()){
//            //400, 잘못된 요청
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //4. 업데이트 및 정상응답
//        target.patch(article);
//        Article update = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(update); //body에 실에서 데이터를 보냄
//    }
//    //delete
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        log.info("삭제요청");
//        //대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        //잘못된 요청 처리
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
