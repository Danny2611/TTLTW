package com.example.finallaptrinhweb.filter;

import com.example.finallaptrinhweb.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {

    private static final Map<Integer, List<String>> roleAccessMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleAccessMap.put(3, List.of("/admin/product", "/admin/delete-product", "/admin/edit-product"));
        roleAccessMap.put(4, List.of("/admin/total-report"));
        roleAccessMap.put(5, List.of("/admin/contact"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();




        boolean isLoggedIn = (session != null && session.getAttribute("auth") != null);
        boolean isAdminLoggedIn = (session != null && session.getAttribute("adminAuth") != null);

        if (!isLoggedIn && !isAdminLoggedIn) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/signIn.jsp");
            return;
        }

        // Lấy thông tin user
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("auth");
            if (user == null) {
                user = (User) session.getAttribute("adminAuth");
            }
        }

        if (user != null && requestURI.startsWith(httpRequest.getContextPath() + "/admin")) {
            int roleId = user.getRoleId();

            // Admin có toàn quyền truy cập
            if (roleId == 2) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            // Kiểm tra xem roleId có quyền truy cập vào URI hiện tại không
            List<String> allowedURIs = roleAccessMap.get(roleId);
            if (allowedURIs != null && allowedURIs.stream().anyMatch(requestURI::contains)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            // Nếu không có quyền, chuyển hướng 403
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/403.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
