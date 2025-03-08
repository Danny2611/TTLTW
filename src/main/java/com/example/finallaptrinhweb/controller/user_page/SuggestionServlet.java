package com.example.finallaptrinhweb.controller.user_page;

import com.example.finallaptrinhweb.dao.ProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/suggestions")
public class SuggestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String term = request.getParameter("term");

        if (term != null && !term.trim().isEmpty()) {
            ProductDAO productDAO = new ProductDAO();
            List<String> suggestions = productDAO.getSuggestions(term);

            // Tạo JSON array từ danh sách gợi ý
            StringBuilder jsonBuilder = new StringBuilder("[");
            for (int i = 0; i < suggestions.size(); i++) {
                jsonBuilder.append("\"").append(suggestions.get(i)).append("\"");
                if (i < suggestions.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");

            PrintWriter out = response.getWriter();
            out.print(jsonBuilder.toString());
            out.flush();
        } else {
            PrintWriter out = response.getWriter();
            out.print("[]");
            out.flush();
        }
    }
}