package com.company.abc;


import com.company.abc.heaps.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hfire on 3/26/17.
 */
public class HuffmanTree {

     final Node root;
    private PriorityQueue priorityQueue;

    public HuffmanTree(PQ_ENUM priorityQueue, Map<Integer, Integer> frequencyTable) {
        this.priorityQueue = getPriorityQueue(priorityQueue);
        root = buildHuffmanTree(frequencyTable);
    }

    public void buildCodeTableFromHuffmanTree( HashMap<Integer, String> codes) {
        assignCode(root,codes);
    }

    private static void assignCode(Node root, HashMap<Integer, String> codeTable) {

        if (root.left != null) {
            root.left.code = root.code + String.valueOf(0);
            assignCode(root.left, codeTable);

            root.right.code = root.code + String.valueOf(1);
            assignCode(root.right, codeTable);
        }
        else {
            codeTable.put(root.elem, root.code);
        }
    }

    private Node buildHuffmanTree(Map<Integer, Integer> frequencyTable) {

       frequencyTable.forEach((elem,freq)->{
           priorityQueue.add(new Node(freq, elem ));
       });

       while(priorityQueue.getSize()>1){
           Node left = priorityQueue.removeMin();
           Node right = priorityQueue.removeMin();
//           System.out.println("combining freqs :" + left.freq + "," + right.freq);
           Node parent = new Node(left, right, left.freq + right.freq, -111111);
           priorityQueue.add(parent);
       }
        return priorityQueue.removeMin();
    }



    private PriorityQueue getPriorityQueue(PQ_ENUM ds) {
        switch (ds){
            case BINARY_HEAP:  return new BinaryHeap();
            case FOUR_WAY_HEAP: return new FourWayHeap();
            case PAIRING_HEAP: return new PairingHeap();
            default: return null;
        }
    }
}
