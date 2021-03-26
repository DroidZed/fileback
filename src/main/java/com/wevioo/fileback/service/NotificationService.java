package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.NotificationManager;
import com.wevioo.fileback.message.NotificationPayload;
import com.wevioo.fileback.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService implements NotificationManager {

    private static final String fcm = "https://fcm.googleapis.com/fcm/send";

    @Value("${fcm.server.key}")
    private String fcm_key;

    private final RestTemplate restTemplate;

    public NotificationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<ResponseMessage> sendNotification(NotificationPayload payload) {

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `Authorization` header
        headers.set("Authorization","key="+this.fcm_key);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<NotificationPayload> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<ResponseMessage> response = this.restTemplate.postForEntity(fcm, entity, ResponseMessage.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            return CompletableFuture.completedFuture(response.getBody());
        } else {
            return CompletableFuture.completedFuture(new ResponseMessage("Failed to notify !"));
        }
    }
}
