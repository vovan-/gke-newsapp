package com.ss.gke.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NewsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String details;

    public NewsItem() {
    }

    public NewsItem(Long id, String title, String details) {
        this.id = id;
        this.title = title;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewsItem)) {
            return false;
        }
        NewsItem newsItem = (NewsItem) o;
        return Objects.equals(id, newsItem.id) && Objects.equals(title, newsItem.title) && Objects.equals(details, newsItem.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, details);
    }
}
