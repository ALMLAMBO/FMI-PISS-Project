package com.rateuni.backend.integration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.rateuni.backend.BackendApplication;
import com.rateuni.backend.models.request_response.response.UniUserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest(classes = BackendApplication.class)
public class GetUserInfoTest {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeAll
    public static void initFirebase() {
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
    }

    @Test
    public void testUnauthorizedGetUserInfo() {
        Assertions.assertThrows(Exception.class, () ->
                testRestTemplate
                        .getForObject("http://localhost:8080/api/user/get-user-info", UniUserInfo.class)
        );
    }
}
