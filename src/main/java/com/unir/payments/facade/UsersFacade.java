package com.unir.payments.facade;

import com.unir.payments.facade.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersFacade {

    @Value("${getUser.url}")
    private String getUserUrl;

    private final RestTemplate restTemplate;

    public User getUser(String id) {

        try {
            String url = String.format(getUserUrl, id);
            log.info("Getting user with ID {}. Request to {}", id, url);
            return restTemplate.getForObject(url, User.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, user with ID {}", e.getStatusCode(), id);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, user with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, user with ID {}", e.getMessage(), id);
            return null;
        }
    }
}
