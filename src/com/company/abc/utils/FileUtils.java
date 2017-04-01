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

//        try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/data/sample_input_large.txt"))) {
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
        Path path = Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/encoded.bin");
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

    public static void outputEncodedDataToFile(String encodedDataString, String fileName) {

        StringBuilder encodedDataStringBuilder = new StringBuilder(encodedDataString);
        while (encodedDataStringBuilder.length() % 8 != 0) {
            System.out.println("not in multiples of 8");
            encodedDataStringBuilder.append("0"); // lets add some extra bits until we have full bytes
        }
        encodedDataString = encodedDataStringBuilder.toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            for (int i = 0; i < encodedDataString.length(); i += 8) {
                String byteString = encodedDataString.substring(i, i + 8); // grab a byte
                int parsedByte = 0xFF & Integer.parseInt(byteString, 2);
                //System.out.println(parsedByte + ":" + byteString);
                fos.write(parsedByte); // write a byte
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
