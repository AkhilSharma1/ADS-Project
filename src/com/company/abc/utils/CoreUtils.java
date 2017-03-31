package com.company.abc.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hfire on 3/30/17.
 */
public class CoreUtils {

    public static Map<Integer, Integer> generateFreqTableFromInput(List<String> input) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int defaultValue=0;

        for (String s : input) {
            int intS = Integer.parseInt(s);
            Integer val = hashMap.getOrDefault(intS, defaultValue);
            hashMap.put(intS, ++val);
        }
        return hashMap;
    }

    public static byte[] encodeData(List<String> inputData, HashMap<Integer, String> codeTable) {
        StringBuilder encodedData = new StringBuilder();

        inputData.forEach(line->{
            String code = codeTable.get(Integer.parseInt(line));
            encodedData.append(code);
        });

        return encodedData.toString().getBytes();
    }
}