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

    public static void main(String[] args) {

        List<String> inputData = FileUtils.readInputFile(args[0]);
        Map<Integer, Integer> frequencyTable = CoreUtils.generateFreqTableFromInput(inputData);

        HuffmanTree huffmanTree = new HuffmanTree(FASTEST_PRIORITY_QUEUE, frequencyTable);
        HashMap<Integer, String> codeTable = new HashMap<>();
        huffmanTree.buildCodeTableFromHuffmanTree(codeTable);

        byte[] encodedData =  CoreUtils.encodeData(inputData,codeTable);
        FileUtils.outputEncodedDataToFile(encodedData);
        FileUtils.outputCodeTableToFile(codeTable);
    }
}
