package com.example.test.service;

import com.example.test.dto.response.BaseResponse;
import com.example.test.dto.response.Post;
import com.example.test.util.ExternalApiConnect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
  public TestService() {
    testMethod();
  }

  public void testMethod() {
    String url = "https://jsonplaceholder.typicode.com/posts/1";

    BaseResponse<Post> response1 =
        ExternalApiConnect.restExternalConnect(HttpMethod.GET, url, null, null);

    BaseResponse<Post> response2 =
        ExternalApiConnect.restExternalConnect(HttpMethod.GET, url, null, null, Post.class);

    BaseResponse<Post> response3 =
        ExternalApiConnect.restExternalConnect(
            HttpMethod.GET, url, null, null, new ParameterizedTypeReference<Post>() {});

    log.info("Response: {}", response1);
    log.info("Response: {}", response2);
    log.info("Response: {}", response3);
  }
}
