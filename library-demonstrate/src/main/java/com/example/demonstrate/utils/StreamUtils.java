package com.example.demonstrate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * InputStream的工具类
 */

public class StreamUtils {

    public static String StreamToString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String str = sb.toString();
        return str;
    }
}
