package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.NotificationManager;
import com.wevioo.fileback.message.NotificationPayload;
import com.wevioo.fileback.message.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/notify")
public class NotificationsHandler {

    private final NotificationManager manager;

    @PostMapping
    public CompletableFuture<ResponseMessage> pushNotif(@RequestBody NotificationPayload payload)
    {
        return this.manager.sendNotification(payload);
    }
}
