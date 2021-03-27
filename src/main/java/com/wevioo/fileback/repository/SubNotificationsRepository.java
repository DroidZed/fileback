package com.wevioo.fileback.repository;

import com.wevioo.fileback.model.SubNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubNotificationsRepository extends JpaRepository<SubNotification, Long> {

    List<SubNotification> findAllByIdJobber(Long idJobber);

    @Query(value = "SELECT sn FROM SubNotification sn WHERE sn.idJobber = ?1 AND sn.read = false")
    List<SubNotification> getSubNotificationsByIdJobberAndReadFalse(Long IdJobber);

    void deleteSubNotificationsByIdJobber(Long idJobber);

    void deleteSubNotificationByIdJobberAndNotifId(Long idJobber, Long idUser);
}
