package uz.pdp.appwarehouse.helpers;

public class Utils {

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }
}
