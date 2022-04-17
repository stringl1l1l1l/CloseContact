package com.example.CloseContactSearcher.service.impl;

import com.example.CloseContactSearcher.entity.*;
import com.example.CloseContactSearcher.mapper.*;
import com.example.CloseContactSearcher.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {


    @Resource
    private ArticleMapper articleMapper;


    @Override
    public List<Article> findAllArticle() {
        return articleMapper.selectList(null);
    }

}
