package io.droidme.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService service;

    @Test
    void serviceInjected() {
        assertNotNull(service);
    }

    @Test
    void testPost() {
        Post post = service.getPost(1);
        assertEquals(1, post.getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void testPostEntity(int id) {
        ResponseEntity<Post> postEntity = service.getPostEntity(id);
        assertEquals(200, postEntity.getStatusCode().value());
        assertEquals(id, Objects.requireNonNull(postEntity.getBody()).getId());
    }

}