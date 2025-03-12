package com.example.finallaptrinhweb.session;

import jakarta.servlet.http.HttpSession;

import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static final ConcurrentHashMap<Integer, HttpSession> activeSessions = new ConcurrentHashMap<>();

    public static void addSession(int userId, HttpSession session) {
        activeSessions.put(userId, session);
    }

    public static void removeSession(int userId) {
        HttpSession session = activeSessions.remove(userId);
        if (session != null) {
            session.invalidate();  // Hủy session ngay lập tức
            System.out.println("Đã hủy session của userId: " + userId);
        }
    }

    public static HttpSession getSession(int userId) {
        return activeSessions.get(userId);
    }
}
