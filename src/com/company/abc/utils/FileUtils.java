package com.company.abc.utils;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hfire on 3/30/17.
 */
public class FileUtils {

    public static List<String> readInputFile(String fileName) {

        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            list = br.lines().
                    filter(line->line.length()>0)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return list;
    }

    public static void outputCodeTableToFile(HashMap<Integer, String> codeTable, String codeTableFile) {
        //Get the file reference
        Path path = Paths.get(codeTableFile);

        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {

            for (Map.Entry<Integer, String> entry : codeTable.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();

                writer.write(key + " " + value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static HashMap<Integer, String> readCodeTableFromFile(String fileName) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            list = br.lines().
                    filter(line->line.length()>0)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        HashMap<Integer, String> codeMap = convertToMap(list);
        return codeMap;
    }

    private static HashMap<Integer, String>  convertToMap(List<String> list) {
        HashMap<Integer, String> codeMap = new HashMap<>();
        list.forEach(line->{
            String[] split = line.split(" ");
            if (split.length>1)
                codeMap.put(Integer.parseInt(split[0]), split[1]);
        });
        return codeMap;
    }

    public static String readEncodedDataFromFile(String fileName) {
        StringBuilder encodedDataString = new StringBuilder();
        Path path = Paths.get(fileName);
        try {
            byte[] bytes = Files.readAllBytes(path);
            for (byte b : bytes) {
                int i = b;
                if(i<0)
                    i = 256+i;
                String s = Integer.toBinaryString(i);
                int diff = 8-s.length();
               // System.out.println("diff is "+diff);
                for(int l = 0; l<diff;l++)
                    s="0"+s;
               // System.out.println(i + ":" + s);
                encodedDataString.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return encodedDataString.toString();
    }

    public static void outputEncodedDataToFile(String encodedText, String fileName) {

        if (encodedText.length() % 8 != 0) {
            System.out.println("not in multiples of 8");
            System.exit(1);
        }

        byte[] barray = new byte[encodedText.length() / 8];
        for (int i = 0; i < (encodedText.length() / 8); i++) {
            barray[i] = (byte) Short.parseShort(encodedText.substring(8 * i, 8 * (i + 1)), 2);
        }

        Path path = Paths.get(fileName);

        try {
            Files.write(path, barray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void outputDecodedMessageToFile(ArrayList<String> decodedMessage, String decodedTextFile) {
        //Get the file reference
        Path path = Paths.get(decodedTextFile);

        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {

            for (String line : decodedMessage) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
