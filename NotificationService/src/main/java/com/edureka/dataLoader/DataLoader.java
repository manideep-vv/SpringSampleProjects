package com.edureka.dataLoader;

import com.edureka.model.Notification;
import com.edureka.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements ApplicationRunner {


    @Autowired
    NotificationRepository repository;

    @Autowired
    Environment environment;
    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        Notification notification =  new Notification(20L,"Customer registration has been successful ","sms",1L);
        long currentCount = repository.count();
        System.out.println("current total count of notifications is "+ currentCount);
        if(currentCount==0){
            repository.save(notification);
            System.out.printf("single Notification with this data has been auto inserted successfully --> %s \n", notification);
            System.out.println("swagger url for this application is "+ "localhost:"+environment.getProperty("server.port")+"/swagger-ui.html");
        }
    }
}
