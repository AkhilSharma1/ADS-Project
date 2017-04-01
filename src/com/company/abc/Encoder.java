package com.company.abc;

import com.company.abc.heaps.PQ_ENUM;
import com.company.abc.utils.CoreUtils;
import com.company.abc.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hfire on 3/31/17.
 */
public class Encoder {
    public static final PQ_ENUM FASTEST_PRIORITY_QUEUE = PQ_ENUM.BINARY_HEAP;
    public static final String CODE_TABLE_FILE = "code_table.txt";

    public static void main(String[] args) {
        if (args.length<2)
            throw new IllegalArgumentException("Encoder should have at least one input argument");


        long startTime, endTime, timeTaken;
        startTime = System.currentTimeMillis();
        List<String> inputData = FileUtils.readInputFile(args[0]);
        Map<Integer, Integer> frequencyTable = CoreUtils.generateFreqTableFromInput(inputData);

        HuffmanTree huffmanTree = new HuffmanTree(FASTEST_PRIORITY_QUEUE, frequencyTable);
        HashMap<Integer, String> codeTable = new HashMap<>();
        huffmanTree.buildCodeTableFromHuffmanTree(codeTable);

        String encodedData =  CoreUtils.encodeData(inputData,codeTable);
        FileUtils.outputEncodedDataToFile(encodedData);
        FileUtils.outputCodeTableToFile(codeTable, CODE_TABLE_FILE);
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        System.out.println("encoding finished!!!, time taken in sec :" + TimeUnit.MILLISECONDS.toSeconds(timeTaken));
    }
}
