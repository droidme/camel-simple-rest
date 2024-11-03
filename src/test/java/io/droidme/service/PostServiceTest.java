package io.droidme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PostService.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class PostServiceTest {

    @Autowired
    private MockRestServiceServer mockRestApi;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PostService service;


    @Test
    void testPost() {

        mockRestApi.expect(requestTo("/posts/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody(), MediaType.APPLICATION_JSON));

        Post post = service.getPost(1);

        assertThat(post).isEqualTo(
                Post.builder()
                        .id(1)
                        .title("simple Post")
                        .body("this is a simple post.")
                        .build()
        );
    }

    @Test
    void testPostEntity() {

        mockRestApi.expect(requestTo("/posts/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody(), MediaType.APPLICATION_JSON));

        ResponseEntity<Post> postEntity = service.getPostEntity(1);
        assertEquals(200, postEntity.getStatusCode().value());
        assertEquals(1, Objects.requireNonNull(postEntity.getBody()).getId());
    }

    private String responseBody() {
        Post post = Post.builder()
                .id(1)
                .title("simple Post")
                .body("this is a simple post.")
                .build();
        try {
            return this.mapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}