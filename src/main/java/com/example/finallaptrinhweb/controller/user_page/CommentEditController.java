package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/user/comment/edit")
public class CommentEditController  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Long commentId  = Long.valueOf(req.getParameter("commentId"));
            String content = req.getParameter("content");
            String productId = req.getParameter("productId");
        System.out.println("Change content:" + content);
        CommentDAO commentDAO= new CommentDAO();
        if(commentDAO.updatedComment(commentId, content)){
            resp.sendRedirect(req.getContextPath() +"/user/product?id=" + productId);
        }
    }
}
