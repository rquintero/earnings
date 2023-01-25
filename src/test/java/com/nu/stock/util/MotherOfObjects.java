package com.nu.stock.util;

import com.nu.stock.view.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MotherOfObjects {

    public static final String EMPTY = "";

    public static List<String> getCase6() {
        String line = loadCase("case6.json");
        return JsonUtil.fromCollectionString(line);
    }
    public static List<String> getCase1() {
        String line = loadCase("case1.json");
        return JsonUtil.fromCollectionString(line);
    }
    public static List<String> getCase1_1() {
        String line = loadCase("case1-1.json");
        return JsonUtil.fromCollectionString(line);
    }
    public static List<String> getCase2() {
        String line = loadCase("case2.json");
        return JsonUtil.fromCollectionString(line);
    }
    public static List<String> getCase3() {
        String line = loadCase("case3.json");
        return JsonUtil.fromCollectionString(line);
    }
    public static List<String> getCase4() {
        String line = loadCase("case4.json");
        return JsonUtil.fromCollectionString(line);
    }

    public static List<String> getCase4_1() {
        String line = loadCase("case4-1.json");
        return JsonUtil.fromCollectionString(line);
    }

    public static List<String> getCase5() {
        String line = loadCase("case5.json");
        return JsonUtil.fromCollectionString(line);
    }

    public static List<String> getCase7() {
        String line = loadCase("case7.json");
        return JsonUtil.fromCollectionString(line);
    }

    private static String loadCase(String caseFileName) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader bfr = new BufferedReader(new InputStreamReader(
                MotherOfObjects.class.getClassLoader().getResourceAsStream(caseFileName)))) {
            String line;
            InputReader iReader = new InputReader();
            while((line = bfr.readLine()) !=null
                    && !EMPTY.equals(line)) {
                iReader.processLine.accept(line, sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
