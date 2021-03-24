package com.wevioo.fileback.service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {
        try {

            FileInputStream serviceAccount = new FileInputStream(
                    "./src/main/resources/pfe-firebase-4fee7-firebase-adminsdk-mkjdl-e3d29965b2.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fir-spring-boot-test-default-rtdb.firebaseio.com").build();

            FirebaseApp.initializeApp(options);
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
