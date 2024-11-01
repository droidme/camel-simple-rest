package io.droidme.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {

    private final RestTemplate restTemplate;

    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Post getPost(int id) {
        return restTemplate.getForObject("/posts/{id}", Post.class, id);
    }

    public ResponseEntity<Post> getPostEntity(int id) {
        return restTemplate
                .getForEntity("/posts/{id}", Post.class, id);
    }
}
