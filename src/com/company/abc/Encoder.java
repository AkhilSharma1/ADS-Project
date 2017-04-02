package com.company.abc;

import com.company.abc.heaps.PQ_ENUM;
import com.company.abc.utils.CoreUtils;
import com.company.abc.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hfire on 3/31/17.
 */
public class Encoder {
    public static final PQ_ENUM FASTEST_PRIORITY_QUEUE = PQ_ENUM.BINARY_HEAP;
    public static final String CODE_TABLE_FILE = "code_table.txt";
    public static final String ENCODED_FILE = "encoded.bin";

    public static void main(String[] args) {
        if (args.length!=1)
            throw new IllegalArgumentException("Encoder should have only one input argument");

        CoreUtils.startTimer();

        List<String> inputData = FileUtils.readInputFile(args[0]);
        Map<Integer, Integer> frequencyTable = CoreUtils.generateFreqTableFromInput(inputData);

        HuffmanTree huffmanTree = new HuffmanTree(FASTEST_PRIORITY_QUEUE, frequencyTable);
        HashMap<Integer, String> codeTable = new HashMap<>();
        huffmanTree.buildCodeTableFromHuffmanTree(codeTable);

        String encodedData =  CoreUtils.encodeData(inputData,codeTable);
        FileUtils.outputEncodedDataToFile(encodedData, ENCODED_FILE);
        FileUtils.outputCodeTableToFile(codeTable, CODE_TABLE_FILE);

        System.out.println("encoding finished!!! time taken(including File IO): "
                + CoreUtils.stopTimer() + " sec");
    }
}
