package com.company.abc;

import com.company.abc.heaps.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hfire on 4/2/17.
 */
public class DecoderTree {


    private Node root;

    public DecoderTree(HashMap<Integer, String> codeTable) {
        buildDecoderTree(codeTable);
    }

    private void buildDecoderTree(HashMap<Integer, String> codeTable) {
                root = new Node();
                Node temp = root;

        for (Map.Entry<Integer, String> entry : codeTable.entrySet()) {
            int elem = entry.getKey();
            String code = entry.getValue();
            for(char c :code.toCharArray()) {
                switch (c){
                    case '0':
                        if(temp.left==null){
                            temp.left = new Node();
                        }
                        temp = temp.left;
                        break;
                    case '1':
                        if(temp.right==null){
                            temp.right = new Node();
                        }
                        temp = temp.right;
                        break;
                }
            }
            temp.elem = elem;
            temp = root;
        }

    }

    public ArrayList<String> decodeMessage(String encodedMessage) {
        ArrayList<String> decodedMessage = new ArrayList<>();
        Node temp = root;
        for(char c :encodedMessage.toCharArray()) {
            temp = c=='0'?temp.left:temp.right;
            if (temp.elem!=-1){
//                System.out.println(temp.elem);
                decodedMessage.add(temp.elem+"");
                temp = root;
            }
        }
        return decodedMessage;
    }
}
