package com.wevioo.fileback.controller;

import com.wevioo.fileback.interfaces.NotificationManager;
import com.wevioo.fileback.model.SubNotification;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationsController {

    private final NotificationManager manager;

    @PostMapping(path = "save")
    public SubNotification registerNotif(@RequestBody SubNotification payload)
    {
        return this.manager.saveNotification(payload);
    }

    @GetMapping(path="get/{id_jobber}")
    public List<SubNotification> collectNotificationsOfJobber(@PathVariable Long id_jobber)
    {
        return this.manager.getNotifsOfJobber(id_jobber);
    }

    @GetMapping(path= "/get-undread/{id_jobber}")
    public List<SubNotification> collectUnReadOfJobber(@PathVariable Long id_jobber)
    {
        return this.manager.getUnReadNotifsOfJobber(id_jobber);
    }

    @PutMapping(path = "read/{id}")
    public CompletableFuture<SubNotification> markNotifAsRead(@PathVariable Long id)
    {
        return this.manager.setNotifRead(id);
    }

    @DeleteMapping(path = "del/all/{id_jobber}")
    public void deleteNotificationsOfJobber(@PathVariable Long id_jobber)
    {
        this.manager.clearNotifsOfJobber(id_jobber);
    }

    @DeleteMapping(path = "del/one/{id_notif}")
    public void deleteOneNotifOfJobber(@PathVariable Long id_notif, @RequestParam("jobber") Long id_jobber)
    {
        this.manager.clearOneNotif(id_jobber, id_notif);
    }
}
