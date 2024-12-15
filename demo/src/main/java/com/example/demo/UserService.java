package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WebClient webClient;

    public GitUserDTO getUser(String name) {
        try {
            return webClient.get()
                    .uri("/users/{name}", name)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            response -> Mono.error(new ClientErrorException("User not found"))

                    )
                    .bodyToMono(GitUserDTO.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ExternalApiException("Error during API call", e);
        }
    }

}
