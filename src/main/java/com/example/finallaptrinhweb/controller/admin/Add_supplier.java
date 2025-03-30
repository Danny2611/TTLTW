package com.example.finallaptrinhweb.controller.admin;

import com.cloudinary.utils.ObjectUtils;
import com.example.finallaptrinhweb.dao.SupplierDAO;
import com.example.finallaptrinhweb.model.Supplier;
import com.example.finallaptrinhweb.utill.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@MultipartConfig
@WebServlet(urlPatterns = "/admin/add-supplier")
public class Add_supplier extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("current_page", "supplier");
        String type = request.getParameter("type");
        if (type != null) {
            if (type.equalsIgnoreCase("enterAdd")) {
                request.setAttribute("type", "add");
                request.setAttribute("title", "Thêm nhà cung cấp");
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
                return;
            } else if (type.equalsIgnoreCase("enterEdit")) {
                request.setAttribute("type", "edit");
                request.setAttribute("title", "Chỉnh sửa nhà cung cấp");
                int id = Integer.parseInt(request.getParameter("id"));
                Supplier supplier = SupplierDAO.loadSupplier(id);
                request.setAttribute("supplier", supplier);
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
                return;
            }
        }
        if (type.equalsIgnoreCase("add")) {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            Part filePart = request.getPart("logo");
            String logoUrl = null;

            if (filePart != null && filePart.getSize() > 0) {
                try {
                    InputStream fileInputStream = filePart.getInputStream();
                    byte[] fileBytes = fileInputStream.readAllBytes();
                    Map uploadResult = CloudinaryConfig.getInstance().uploader().upload(fileBytes, ObjectUtils.emptyMap());
                    logoUrl = (String) uploadResult.get("secure_url");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            boolean isInsert = SupplierDAO.insertSupplier(name, address, phone, email, logoUrl);
            if (isInsert) {
                response.sendRedirect("supplier");
            } else {
                request.setAttribute("error", "Thêm nhà cung cấp thất bại!");
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
            }
        } else if (type.equalsIgnoreCase("edit")) {
            request.setAttribute("type", "edit");
            request.setAttribute("title", "Chỉnh sửa nhà cung cấp");
            System.out.println("co vao edit");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            Supplier supplier = SupplierDAO.loadSupplier(id);
            String logoUrl = supplier.getImageUrl(); // Mặc định giữ logo cũ

            Part filePart = request.getPart("logo");
            if (filePart != null && filePart.getSize() > 0) {
                try {
                    InputStream fileInputStream = filePart.getInputStream();
                    byte[] fileBytes = fileInputStream.readAllBytes();
                    Map uploadResult = CloudinaryConfig.getInstance().uploader().upload(fileBytes, ObjectUtils.emptyMap());
                    logoUrl = (String) uploadResult.get("secure_url");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            boolean isUpdate = SupplierDAO.updateSupplier(id, name, address, phone, email, logoUrl);
            if (isUpdate) {
                response.sendRedirect("supplier");
            } else {
                request.setAttribute("error", "Cập nhật thất bại!");
                request.getRequestDispatcher("./add-supplier.jsp").forward(request, response);
            }

        }

    }
}