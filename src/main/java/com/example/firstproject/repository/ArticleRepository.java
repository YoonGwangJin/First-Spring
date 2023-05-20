package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long>{
        //jap(orm) CRUD추가구현 없이 자동으로 ....
    @Override//왜 list를 arraylist?
    ArrayList<Article> findAll();
}
