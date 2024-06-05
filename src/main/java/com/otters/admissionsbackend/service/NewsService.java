package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.News;
import com.otters.admissionsbackend.repository.NewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    private final NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public News add(News request) {
        return repository.save(request);
    }

    public void delete(String id) {
        Optional<News> news = repository.findById(id);
        if (news.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        repository.deleteById(id);
    }

    public List<News> findAll() {
        return repository.findAll();
    }

    public News findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public News update(String id, News request) {
        Optional<News> oldNews = repository.findById(id);
        if (oldNews.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        News news = new News();
        news.setContent(request.getContent());
        news.setTitle(request.getTitle());

        return repository.save(news);
    }
}
