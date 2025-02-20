package com.example.finallaptrinhweb.model;

import java.text.NumberFormat;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.Normalizer;
import java.text.ParseException;

public class Util {
    public static String formatCurrency(double price) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedPrice = numberFormat.format(price);

        // Loại bỏ dấu "đ" và gạch từ chuỗi
        formattedPrice = formattedPrice.replaceAll("[đ₫,]", "");

        return formattedPrice.trim(); // Loại bỏ khoảng trắng thừa
    }
    public static String formatTimestamp(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timestamp.getTime()));
    }
    public static String formatTimestampWithoutTime(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timestamp.getTime()));
    }
    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String dateFormatNoTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static String generateSlug(String input) {
        String convertedString =
                Normalizer
                        .normalize(input, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "-");
        return convertedString;
    }
    public static String revertDate(String date){
        String day = date.substring(0,date.indexOf("-"));
        String month = date.substring(date.indexOf("-")+1,date.lastIndexOf("-"));
        String year = date.substring(date.lastIndexOf("-")+1,date.length());
        return year+"-"+month+"-"+day;
    }


}

