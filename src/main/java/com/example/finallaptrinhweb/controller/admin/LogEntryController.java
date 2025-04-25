package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.model.LogEntry;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/logs")
public class LogEntryController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> logs = Files.readAllLines(Paths.get("D:/Workspace/TTLTW/logs/app.logs"));
        List<LogEntry> logEntries = new ArrayList();
        for (String line : logs) {
            String[] parts = line.split(" ", 6); // tách dòng thành các phần
            if (parts.length >= 6) {
                LogEntry entry = new LogEntry();
                entry.setTimestamp(parts[0] + " " + parts[1]);
                entry.setLevel(parts[2]);
                entry.setController(parts[3]);
                entry.setMessage(parts[5]);
                logEntries.add(entry);
            }
        }

        req.setAttribute("logs", logEntries);
        req.getRequestDispatcher("logs.jsp").forward(req,resp);
    }
}
