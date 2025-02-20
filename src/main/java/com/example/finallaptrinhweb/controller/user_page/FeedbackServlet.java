package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.FeedbackDAO;
import com.example.finallaptrinhweb.model.Feedback;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet("/user/feedback")
public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String content = request.getParameter("form_fields[message]");


        if (name == null || email == null || content == null || name.isEmpty() || email.isEmpty() || content.isEmpty()) {

            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
        } else if (!isValidEmail(email)) {

            request.setAttribute("emailError", "Địa chỉ email không hợp lệ. Vui lòng nhập lại");
        } else if (content.length() < 10) {

            request.setAttribute("contentError", "Nội dung đóng góp phải có ít nhất 10 ký tự.");
        } else {

            Feedback feedback = new Feedback();
            feedback.setName(name);
            feedback.setEmail(email);
            feedback.setContent(content);
            feedback.setSubmissionDate(new Timestamp(new Date().getTime()));


            boolean success = FeedbackDAO.addFeedback(feedback);


            if (success) {
                request.setAttribute("feedbackMessage", "Phản hồi của bạn đã được gửi thành công!");
            } else {
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi gửi phản hồi. Vui lòng thử lại sau.");
            }
        }


        request.getRequestDispatcher("./contact.jsp").forward(request, response);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}