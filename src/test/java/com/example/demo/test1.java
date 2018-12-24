package com.example.demo;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class test1 {
    public static void main(String[] args) {
//        String fromTimeStamp = null;
//
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//        try {
//            Date startDate = format.parse(fromTimeStamp);
//            System.out.println(startDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        String[] arr = processTime("", "2018-11-11 11:21:11");
        System.out.println(Arrays.toString(arr));


    }


    public static String[] processTime(String from, String to) {
        String[] arr = new String[2];
        LocalDate localDate = null;
        if (StringUtils.isEmpty(to)) {
            localDate = LocalDate.now();
            to = localDate.toString() + " 23:59:59";
        }

        if (StringUtils.isEmpty(from)) {
            String[] arr2 = to.substring(0, to.indexOf(" ")).split("-");
            localDate = LocalDate.of(Integer.valueOf(arr2[0]), Integer.valueOf(arr2[1]), Integer.valueOf(arr2[2])).plusDays(-1);
            from = localDate.toString() + " 00:00:01";
        }

        arr[0] = from;
        arr[1] = to;

        return arr;
    }
}
