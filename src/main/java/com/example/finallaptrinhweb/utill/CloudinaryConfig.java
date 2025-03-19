package com.example.finallaptrinhweb.utill;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
    private static Cloudinary cloudinary;

    static {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "duztah40b",
                "api_key", "249624827726748",
                "api_secret", "JjH-Q2O-U18hAxBHzoHxRmrFAXI"
        ));
    }

    public static Cloudinary getInstance() {
        return cloudinary;
    }
}
