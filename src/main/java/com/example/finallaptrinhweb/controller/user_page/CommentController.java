package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.CommentDAO;
import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/user/comment")
public class CommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get user
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("auth");
        if(user == null) {
            resp.sendRedirect(req.getContextPath()+"/user/signin");
        }
        else {
            String content = req.getParameter("content");
            int star = Integer.parseInt(req.getParameter("star-value"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            System.out.println("Value:" + content + star+productId);

            CommentDAO commentDAO = new CommentDAO();

            if(commentDAO.insertComment(user.getId(), productId,star, content)){
                resp.sendRedirect(req.getContextPath() +"/user/product?id=" + productId);
            }
        }

    }
}
