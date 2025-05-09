package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.DTO;
import com.example.finallaptrinhweb.dao.FeedbackDAO;
import com.example.finallaptrinhweb.model.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/admin/contact")
public class Contact extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "feedbackList");
        List<DTO> feedbackList = FeedbackDAO.getAllFeedbacks();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (DTO f : feedbackList) {
            String formattedDate = f.getComment().getCreatedAt().format(formatter);
            f.getComment().setFormattedCreatedAt(formattedDate); // thêm field mới hoặc xử lý xong gán luôn
        }

        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("./contact.jsp").forward(request, response);
    }
}
