package cn.ChengZhiYa;

import java.util.HashMap;

public class String_HashMap {
    static HashMap<String, String> Temp = new HashMap<>();
    public static void set(String HashMapName, String Value) {
        Temp.put(HashMapName,Value);
    }

    public static String get(String HashMapName) {
        return Temp.get(HashMapName);
    }
}
