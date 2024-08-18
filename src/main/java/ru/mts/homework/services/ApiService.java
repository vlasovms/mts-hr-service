package ru.mts.homework.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mts.homework.dto.Application;

import java.util.List;

@Service
public class ApiService {
    @Value("${api.url}")
    private String API_URL;

    public List<Application> getAllVacations() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Application>> applicationsResponse =
                restTemplate.exchange(API_URL,
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        return applicationsResponse.getBody();
    }
}
