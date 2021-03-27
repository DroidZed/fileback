package com.wevioo.fileback.interfaces;

import com.wevioo.fileback.model.SubNotification;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface NotificationManager {

    SubNotification saveNotification(SubNotification notif);

    List<SubNotification> getNotifsOfJobber(Long id);

    List<SubNotification> getUnReadNotifsOfJobber(Long id);

    void clearNotifsOfJobber(Long id);

    void clearOneNotif(Long id_jobber, Long id_notif);

    CompletableFuture<SubNotification> setNotifRead(Long id_notif);
}
