package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.NotifNotFoundException;
import lombok.AllArgsConstructor;
import com.wevioo.fileback.interfaces.NotificationManager;
import com.wevioo.fileback.model.SubNotification;
import com.wevioo.fileback.repository.SubNotificationsRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
public class NotificationService implements NotificationManager {

    private final SubNotificationsRepository subNotificationsRepository;

    @Override
    public SubNotification saveNotification(SubNotification notif) {
        return this.subNotificationsRepository.save(notif);
    }

    @Override
    public List<SubNotification> getNotifsOfJobber(Long id) {
        return this.subNotificationsRepository.findAllByIdJobber(id);
    }

    @Override
    public List<SubNotification> getUnReadNotifsOfJobber(Long id) {
        return this.subNotificationsRepository.getSubNotificationsByIdJobberAndReadFalse(id);
    }

    @Override
    @Transactional
    public void clearNotifsOfJobber(Long id) {
        this.subNotificationsRepository.deleteSubNotificationsByIdJobber(id);
    }

    @Override
    @Transactional
    @Async
    public void clearOneNotif(Long id_jobber, Long id_notif) {
        this.subNotificationsRepository.deleteSubNotificationByIdJobberAndNotifId(id_jobber,id_notif);
    }

    @Override
    @Async
    public CompletableFuture<SubNotification> setNotifRead(Long id_notif) {
       return CompletableFuture.completedFuture(this.subNotificationsRepository.findById(id_notif)
               .map(
                       notif -> {
                           notif.setRead(true);
                           return this.subNotificationsRepository.save(notif);
                       }
               ).orElseThrow(() -> new NotifNotFoundException(id_notif)));
    }


}
