package com.ss.gke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.gke.model.NewsItem;
import com.ss.gke.repository.NewsRepository;

@RestController
@RequestMapping(value = "/api/v1/news")
public class NewsController {
    private NewsRepository newsRepository;

    @Autowired
    public void EduController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public Iterable<NewsItem> listAll() {
        return newsRepository.findAll();
    }

    @PostMapping(value = "/create")
    public NewsItem create(@RequestBody NewsItem newsItem) {
        return newsRepository.save(newsItem);
    }
}
