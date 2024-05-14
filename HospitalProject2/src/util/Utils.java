package util;

import java.util.Scanner;

public interface Utils {
    Scanner scanner = new Scanner(System.in);
    Scanner strScanner = new Scanner(System.in);

    static int getInt(String text) {
        try {
            System.out.println(text);
            String n = strScanner.nextLine();
            return Integer.parseInt(n);
        } catch (Exception e) {
            return getInt(text);
        }
    }
}
