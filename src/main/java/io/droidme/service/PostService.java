package io.droidme.service;

import io.droidme.config.PostServiceConfigProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final PostServiceConfigProperty configProperty;

    public PostService(RestTemplate restTemplate, PostServiceConfigProperty configProperty) {
        this.restTemplate = restTemplate;
        this.configProperty = configProperty;
    }

    public Post getPost(int id) {
        return restTemplate.getForObject(configProperty.getUrl(), Post.class, id);
    }

    public ResponseEntity<Post> getPostEntity(int id) {
        return restTemplate
                .getForEntity(configProperty.getUrl(), Post.class, id);
    }
}
