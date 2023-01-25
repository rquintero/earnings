package com.nu.stock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtil {
    public static List<String> fromCollectionString(String col) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\{.*?})");
        Matcher matcher = pattern.matcher(col);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }
}
