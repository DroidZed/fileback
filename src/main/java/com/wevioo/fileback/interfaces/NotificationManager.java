package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.message.NotificationPayload;
import com.wevioo.fileback.message.ResponseMessage;

import java.util.concurrent.CompletableFuture;

public interface NotificationManager {

    CompletableFuture<ResponseMessage> sendNotification(NotificationPayload payload);
}
