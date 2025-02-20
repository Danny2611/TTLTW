package com.example.finallaptrinhweb.controller.user_page.ImageService;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public static List<String> getImgUrlById(String path) {
        List<String> imageFiles = new ArrayList<>();

        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            // Sử dụng FilenameFilter để lọc các file ảnh
            FilenameFilter imageFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg")
                            || lowercaseName.endsWith(".png") || lowercaseName.endsWith(".gif")
                            || lowercaseName.endsWith(".bmp");
                    // Thêm hoặc thay đổi các định dạng file ảnh khác nếu cần
                }
            };

            // Lấy danh sách các file trong thư mục sử dụng bộ lọc
            File[] files = folder.listFiles(imageFilter);

            // Thêm các đường dẫn file vào danh sách
            if (files != null) {
                for (File file : files) {
                    // Lấy đường dẫn từ "data" trở đi
                    String fullPath = file.getAbsolutePath();
                    int index = fullPath.indexOf("data");

                    if (index != -1) {
                        String resultPath = fullPath.substring(index);
                        imageFiles.add(resultPath);
                    }
                }
            }
        } else {
            System.out.println("Thư mục không tồn tại hoặc không phải là thư mục!");
        }

        return imageFiles;
    }

    public static String getFirstImagePath(String folderPath) {
        File folder = new File(folderPath);

        // Kiểm tra xem thư mục tồn tại và có chứa hình ảnh không
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            // Kiểm tra xem có hình ảnh trong thư mục không
            if (files != null && files.length > 0) {
                // Lấy đường dẫn của hình ảnh đầu tiên
                String absolutePath = files[0].getAbsolutePath();

                // Kiểm tra xem đường dẫn có chứa "data" không
                int index = absolutePath.indexOf("data");
                if (index != -1) {
                    // Trả về đường dẫn từ "data" trở đi
                    return absolutePath.substring(index);
                }
            }
        }

        // Trả về null nếu không có hình ảnh
        return null;
    }
}
