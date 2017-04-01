package com.company.abc;

import com.company.abc.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by hfire on 3/31/17.
 */
public class Decoder {

    public static void main(String[] args) {

        if (args.length!=2)
            throw new IllegalArgumentException("Decoder should have exactly two input arguments");

        long startTime, endTime, timeTaken;
        startTime = System.currentTimeMillis();

        HashMap<Integer, String> codeTable = FileUtils.readCodeTableFromFile(args[0]);
        String encodedMessage = FileUtils.readEncodedDataFromFile(args[1]);
     /*   System.out.println("encoded message is:" + encodedMessage);
        System.out.println("code table is:" + codeTable);*/
        DecoderTree decoderTree = new DecoderTree(codeTable);

        ArrayList<String> decodedMessage = decoderTree.decodeMessage(encodedMessage);
        FileUtils.outputDecodedMessageToFile(decodedMessage);

        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        System.out.println("decoding finished!!!, time taken in sec :" + TimeUnit.MILLISECONDS.toSeconds(timeTaken));

    }


}
