package com.company.abc;


import com.company.abc.heaps.PQ_ENUM;
import com.company.abc.utils.CoreUtils;
import com.company.abc.utils.FileUtils;

import java.util.List;
import java.util.Map;

public class Main {
    private static Map<Integer, Integer> frequencyTable;


    public static void main(String[] args) {

        List<String> inputData = FileUtils.readInputFile(args[0]);
//        System.out.println("Input is :");
//        System.out.println(inputData);
        frequencyTable = CoreUtils.generateFreqTableFromInput(inputData);
//        System.out.println("frequencyTable is:");

//        System.out.printf(frequencyTable+"\n");

        PQ_ENUM fastestPQ = comparePriorityQueues(frequencyTable);
        System.out.println(fastestPQ);
//        PQ_ENUM fastestPQ = PQ_ENUM.PAIRING_HEAP;
       /* PQ_ENUM fastestPQ = PQ_ENUM.BINARY_HEAP;

        HuffmanTree huffmanTree = new HuffmanTree(fastestPQ, frequencyTable);
      //build code Table
        HashMap<Integer, String> codeTable = new HashMap<>();
        huffmanTree.buildCodeTableFromHuffmanTree( codeTable);
        System.out.printf("");
        System.out.println("BINARY HEAP code table:==================");
        System.out.println(codeTable);


        System.out.println("PAIRING HEAP code table: ==================");
        HuffmanTree huffmanTree1 = new HuffmanTree(PQ_ENUM.PAIRING_HEAP, frequencyTable);

        //build code Table
        HashMap<Integer, String> codeTable1 = new HashMap<>();
        huffmanTree1.buildCodeTableFromHuffmanTree(codeTable1);
        System.out.printf("");
        System.out.println(codeTable1);



        System.out.println("4WAY HEAP code table: ==================");
        HuffmanTree huffmanTree2 = new HuffmanTree(PQ_ENUM.FOUR_WAY_HEAP, frequencyTable);

        //build code Table
        HashMap<Integer, String> codeTable2 = new HashMap<>();
        huffmanTree2.buildCodeTableFromHuffmanTree(codeTable2);
        System.out.printf("");
        System.out.println(codeTable2);*/


    }

    public static PQ_ENUM comparePriorityQueues(Map<Integer, Integer> frequencyTable) {

        long startTime, endTime, timeTakenBH, timeTaken4WH, timeTakenPH;

        startTime = System.currentTimeMillis();
        for(int i = 0; i<1; i++){
            new HuffmanTree(PQ_ENUM.BINARY_HEAP, frequencyTable);
        }
        endTime = System.currentTimeMillis();
        timeTakenBH = endTime - startTime;
        System.out.println("Time in ms taken BH: " + timeTakenBH);


        startTime = System.currentTimeMillis();
        for(int i = 0; i<5; i++){
            new HuffmanTree(PQ_ENUM.FOUR_WAY_HEAP, frequencyTable);
        }
        endTime = System.currentTimeMillis();
        timeTaken4WH = endTime - startTime;
        System.out.println("Time in ms taken 4WH: " + timeTaken4WH);


        startTime = System.currentTimeMillis();
        for(int i = 0; i<5; i++){
            new HuffmanTree(PQ_ENUM.PAIRING_HEAP, frequencyTable);
        }
        endTime = System.currentTimeMillis();
        timeTakenPH = endTime - startTime;
        System.out.println("Time in ms taken PH: " + timeTakenPH);


        if(timeTakenBH<timeTaken4WH && timeTakenBH<timeTakenPH)
            return  PQ_ENUM.BINARY_HEAP;
        if(timeTaken4WH<timeTakenBH && timeTaken4WH<timeTakenPH)
            return  PQ_ENUM.FOUR_WAY_HEAP;

        return PQ_ENUM.PAIRING_HEAP;
    }

}
