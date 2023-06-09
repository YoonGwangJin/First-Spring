package com.example.firstproject.controller;
import java.util.List;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller     //Controller선언
@Slf4j  //log.info refactoring
public class ArticleController {

    @Autowired //객체 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        // 1. Dto를 Entity 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);// save jpa에 제공하는...
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId(); //리다이렉트 페이지 정의
    }

//    @GetMapping("/articles/{id}")
//        public String show(@PathVariable Long id, Model model){
//            log.info("id = "+id);
//            //1. id로 데이터 가져옴 (repository가 서버에서 data들고옴)
//             Article artilceEntity=articleRepository.findById(id).orElse(null);
//            //2. 가져온 데이터 모델에 등록
//            model.addAttribute("article", artilceEntity);
//            //3. 보여줄 페이지 설정
//            return"articles/show";
//        }
        @GetMapping("/articles")
        public String index(Model model){
            //1. 모든 Article을 가져온다
            List<Article> articleEntityList = articleRepository.findAll();
            //2. 가져온 Article 묶음을 view로 전달
            model.addAttribute("articleList", articleEntityList);
            //3. 뷰 페이지 작성
        return"articles/index";
        }

        @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable Long id, Model model){ //url에서 id 가져옴
        //1. 수정할 데이터 가져오기
            Article articleEntity=articleRepository.findById(id).orElse(null);
            //2. 모델에 데이터 등록
            model.addAttribute("article", articleEntity);
            //3. 뷰 페이지 설정
        return "articles/edit";
    }
        @PostMapping("/articles/update")
        public String update(ArticleForm form){
        log.info(form.toString());

            //1. dto를 entity로 변환
            Article articleEntity = form.toEntity();
            //2. entity를 db로 변환
            //2-1 : db에서 기존 데이터 가져오기
            Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            //2-2 : 기존 데이터 값 갱신
            if(target != null){
                articleRepository.save(articleEntity);
            }
        return "redirect:/articles/"+articleEntity.getId();
        }

        @GetMapping("/articles/{id}/delete")
        public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청");
            //1. 삭제 대상을 가져옴
            Article target = articleRepository.findById(id).orElse(null);
            log.info(target.toString());
            //2. 대상 삭제
            if(target!=null){
                articleRepository.delete(target);
                rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다.");
            }
            //3. 결과 페이지로 리다이렉트
        return"redirect : /articles/index";
        }

    @GetMapping("/articles/{id}") // 해당 URL요청을 처리 선언
    public String show(@PathVariable Long id, Model model) { // URL에서 id를 변수로 가져옴
        log.info("id = " + id);
        // 1: id로 데이터를 가져옴!
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        // 3: 보여줄 페이지를 설정!
        return "articles/show";
    }


}