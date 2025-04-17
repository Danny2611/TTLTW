package com.example.finallaptrinhweb.utill;

import com.example.finallaptrinhweb.db.DbProperties;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ReCaptchaVerifier {
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.isEmpty()) {
            System.out.println("reCAPTCHA response is empty or null");
            return false;
        }

        try {
            URL url = new URL(RECAPTCHA_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String postParams = "secret=" + URLEncoder.encode(DbProperties.RECAPTCHA_SECRET_KEY, StandardCharsets.UTF_8.name()) +
                    "&response=" + URLEncoder.encode(gRecaptchaResponse, StandardCharsets.UTF_8.name());

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.writeBytes(postParams);
                wr.flush();
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            String responseString = response.toString();
            System.out.println("reCAPTCHA response: " + responseString);

            JSONObject jsonObject = new JSONObject(responseString);
            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            System.out.println("Error verifying reCAPTCHA: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}