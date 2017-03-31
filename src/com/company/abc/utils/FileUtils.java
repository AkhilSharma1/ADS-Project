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

        try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/data/sample_input_small.txt"))) {
            list = br.lines().
                    filter(line->line.length()>0)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return list;
    }


    public static void outputEncodedDataToFile(byte[] encodedData) {
        /*String path = new File("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/encoded.bin").getPath();
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(path);
            stream.write(encodedData);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        Path path = Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/encoded.bin");

        try {
            Files.write(path, encodedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outputCodeTableToFile(HashMap<Integer, String> codeTable) {
        //Get the file reference
        Path path = Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/code_table.txt");

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

        try (BufferedReader br = Files.newBufferedReader(Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/code_table.txt"))) {
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
            codeMap.put(Integer.parseInt(split[0]), split[1]);
        });
        return codeMap;
    }

    public static byte[] readEncodedDataFromFile(String fileName) {
        Path path = Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/encoded.bin");
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }

    public static void outputDecodedMessageToFile(ArrayList<String> decodedMessage) {
        //Get the file reference
        Path path = Paths.get("/home/hfire/Documents/CodeProjects/Java/ADSProject/src/com/company/abc/output/decoded.txt");

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
