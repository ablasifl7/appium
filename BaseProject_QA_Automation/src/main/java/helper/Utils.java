package helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Utils {
    public static boolean equals(String s1, String s2) {
        if (0 == s1.compareTo(s2)) {
            return true;
        }
        return false;
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (0 == s1.compareToIgnoreCase(s2)) {
            return true;
        }
        return false;
    }

    public static int initialBlanks(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                return n;
            } else {
                ++n;
            }
        }
        return n;
    }

    public static void openDirectory(String path) {
        try {
            java.awt.Desktop.getDesktop().open(new java.io.File(path));
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static boolean openDirectoryIfItCanBe(String path) {
        try {
            java.awt.Desktop.getDesktop().open(new java.io.File(path));
            return true;
        } catch (java.io.IOException e) {
            return false;
        }
    }
    
    public static long aleatori(long min, long max) {
        return (long) (Math.random() * (max + 1 - min)) + min;
    }
    public static void openFile(String path){
    	try {
			Desktop.getDesktop().open(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
