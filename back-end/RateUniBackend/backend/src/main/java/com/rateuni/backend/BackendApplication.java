package com.rateuni.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        InputStream serviceAccount = BackendApplication.class
                .getClassLoader()
                .getResourceAsStream("credentials.json");

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        SpringApplication.run(BackendApplication.class, args);
    }
}
