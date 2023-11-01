package com.example.pathfinder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUtil {
    public static String getURL(String fullVideoURL) {
        String regex = "v=(.{11})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullVideoURL);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
