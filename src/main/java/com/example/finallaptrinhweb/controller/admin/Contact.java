package com.example.finallaptrinhweb.controller.admin;

import com.example.finallaptrinhweb.dao.FeedbackDAO;
import com.example.finallaptrinhweb.model.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/contact")
public class Contact extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "feedbackList");
        List<Feedback> feedbackList = FeedbackDAO.getAllFeedbacks();
        request.setAttribute("feedbackList", feedbackList);
        request.getRequestDispatcher("./contact.jsp").forward(request, response);
    }
}
