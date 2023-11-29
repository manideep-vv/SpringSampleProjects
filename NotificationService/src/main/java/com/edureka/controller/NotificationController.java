package com.edureka.controller;

import com.edureka.model.Notification;
import com.edureka.repository.NotificationRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Notifications")
public class NotificationController {

    @Autowired
    NotificationRepository repository;

    //working
    @PostMapping("/register")
    public List<Notification> registerANotification(@RequestBody Notification notification) {
        System.out.println("received Notification details  as " + notification);
        repository.save(notification);
        return repository.findAll();
    }

    @Scheduled(cron = "*/10 * * * * *")
    @Retry(name = "backendA")
    @GetMapping("/getAllNotifications")
    public List<Notification> getAllNotifications() {
        System.out.println("invoking  endpoint- about to throw exception ran by "+Thread.currentThread().getName());
        throw new RuntimeException("bro testing retry");
        //        return repository.findAll();
    }

    @GetMapping("/")
    @Retry(name="backendA")
    public String welcome() {

        System.out.println("invoking default endpoint");

        return "welcome";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {

        Optional<Notification> optional = repository.findById(id);
        if (optional.isPresent()) {
            Notification notification = optional.get();
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // logic
    }
}
