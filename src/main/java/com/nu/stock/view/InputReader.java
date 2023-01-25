package com.nu.stock.view;

import com.nu.stock.util.JsonUtil;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class InputReader {

    private static final String EMPTY = "";
    private static final String NEWLINE = "/n";
    public static final String UNIT = "unit";

    private final StringBuilder sb = new StringBuilder();

    public List<String> read() {
        System.out.println("Write json well-formed Stock Market Operations next: ");
        try (Scanner scan = new Scanner(System.in);) {
            String line;
            while(scan.hasNextLine() && (line = scan.nextLine()) != null  && !EMPTY.equals(line)) {
                processLine.accept(line, sb);
            }
        }
        return JsonUtil.fromCollectionString(sb.toString());
    }
    public BiConsumer<String, StringBuilder> processLine = (a, sb) ->  {
        String clearStr = a.replaceAll(NEWLINE, EMPTY);
        char stx = 2;
        int index = clearStr.indexOf(stx);
        if (index != -1) {
            int startIndex = index -4;
            if (startIndex >=0 && UNIT.equals(a.substring(startIndex, index))) {
                clearStr = clearStr.replace(stx, '-');
            }
        }
        sb.append(clearStr);
    };
}
