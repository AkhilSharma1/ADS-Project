package com.company.abc;

import com.company.abc.utils.CoreUtils;
import com.company.abc.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hfire on 3/31/17.
 */
public class Decoder {
    public static final String DECODED_TEXT_FILE = "decoded.txt";

    public static void main(String[] args) {

        if (args.length!=2)
            throw new IllegalArgumentException("Decoder should have exactly two input arguments");
        CoreUtils.startTimer();
        HashMap<Integer, String> codeTable = FileUtils.readCodeTableFromFile(args[1]);
        String encodedMessage = FileUtils.readEncodedDataFromFile(args[0]);

        DecoderTree decoderTree = new DecoderTree(codeTable);

        ArrayList<String> decodedMessage = decoderTree.decodeMessage(encodedMessage);
        FileUtils.outputDecodedMessageToFile(decodedMessage, DECODED_TEXT_FILE);

        System.out.println("decoding finished!!! time taken(including File IO): "
                + CoreUtils.stopTimer() + " sec");
    }
}
