package com.example.finallaptrinhweb.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    private static FirebaseAuth firebaseAuth;
    private static final String FIREBASE_CONFIG_PATH = "/serviceAccountKey.json"; // Lưu ý dấu / ở đầu

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            initializeFirebase();
        }
        return firebaseAuth;
    }

    private static void initializeFirebase() {
        try {
            InputStream serviceAccount =
                    FirebaseConfig.class.getClassLoader().getResourceAsStream("serviceAccountKey.json");

            if (serviceAccount == null) {
                System.err.println("Firebase credentials file not found in resources");
                throw new IOException("Firebase credentials file not found");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                firebaseAuth = FirebaseAuth.getInstance();
                System.out.println("Firebase initialized successfully");
            } else {
                firebaseAuth = FirebaseAuth.getInstance();
            }
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}