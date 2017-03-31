package com.company.abc;

import com.company.abc.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hfire on 3/31/17.
 */
public class Decoder {

    public static void main(String[] args) {

        if (args.length<2)
            throw new IllegalArgumentException("Decoder should have at least two input arguments");

        HashMap<Integer, String> codeTable = FileUtils.readCodeTableFromFile(args[0]);
        byte[] encodedMessage = FileUtils.readEncodedDataFromFile(args[1]);

        HuffmanTree huffmanTree = creadeDecodeTree(codeTable);
        ArrayList<String> decodedMessage = decodeMessage(encodedMessage, huffmanTree);
        FileUtils.outputDecodedMessageToFile(decodedMessage);


    }

    private static ArrayList<String> decodeMessage(byte[] encodedMessage, HuffmanTree huffmanTree) {
        return null;
    }

    private static HuffmanTree creadeDecodeTree(HashMap<Integer, String> codeTable) {
        return null;
    }


}
