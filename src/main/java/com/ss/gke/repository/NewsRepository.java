package com.ss.gke.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.gke.model.NewsItem;

public interface NewsRepository extends PagingAndSortingRepository<NewsItem, Long> {
}
