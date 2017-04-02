package com.company.abc;


import com.company.abc.heaps.PQ_ENUM;
import com.company.abc.utils.CoreUtils;
import com.company.abc.utils.FileUtils;

import java.util.List;
import java.util.Map;

import static com.company.abc.utils.CoreUtils.startTimer;
import static com.company.abc.utils.CoreUtils.stopTimer;

public class Compare {
    private static Map<Integer, Integer> frequencyTable;
    private static final int NUMBER_OF_ITER = 50;


    public static void main(String[] args) {

        List<String> inputData = FileUtils.readInputFile(args[0]);

        frequencyTable = CoreUtils.generateFreqTableFromInput(inputData);

        PQ_ENUM fastestPQ = comparePriorityQueues(frequencyTable);
        System.out.println("fastest Priority Queue is :" + fastestPQ);
    }

    public static PQ_ENUM comparePriorityQueues(Map<Integer, Integer> frequencyTable) {

        long timeTaken4WH, timeTakenBH,timeTakenPH;

        startTimer();
        for(int i = 0; i<NUMBER_OF_ITER; i++){
            new HuffmanTree(PQ_ENUM.FOUR_WAY_HEAP, frequencyTable);
        }
        timeTaken4WH = stopTimer();
        System.out.println("Time in sec taken 4WH: " + timeTaken4WH);

        startTimer();
        for(int i = 0; i< NUMBER_OF_ITER; i++){
            new HuffmanTree(PQ_ENUM.BINARY_HEAP, frequencyTable);
        }
        timeTakenBH = stopTimer();
        System.out.println("Time in sec taken BH: " + timeTakenBH);

        startTimer();
        for(int i = 0; i<NUMBER_OF_ITER; i++){
            new HuffmanTree(PQ_ENUM.PAIRING_HEAP, frequencyTable);
        }
        timeTakenPH = stopTimer();
        System.out.println("Time in sec taken PH: " + timeTakenPH);


        if(timeTakenBH<timeTaken4WH && timeTakenBH<timeTakenPH)
            return  PQ_ENUM.BINARY_HEAP;
        if(timeTaken4WH<timeTakenBH && timeTaken4WH<timeTakenPH)
            return  PQ_ENUM.FOUR_WAY_HEAP;

        return PQ_ENUM.PAIRING_HEAP;
    }

}
