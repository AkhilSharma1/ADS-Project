package com.company.abc.heaps;

/**
 * Created by hfire on 3/30/17.
 * left and right variables are only used the HuffmanTree and not by any of the Priority Queues.
 */
public class Node implements Comparable<Node>{

    public Node left;
    public Node right;


    public int freq;
    public int elem = -1;// value >=0 only for leaf nodes
    public String code = "";

    public Node(Node left, Node right, int freq, int elem){

        this.left = left;
        this.right = right;
        this.freq = freq;
        this.elem = elem;
    }

    public Node(){

    };
    public Node(Integer freq, Integer elem) {

        this.freq = freq;
        this.elem = elem;
    }

    @Override
    public int compareTo(Node node) {
        int thatFreq = node.freq;

        if (thatFreq ==freq)
            return 0;
        else if (thatFreq>freq) //this<that, return -1
            return -1;
        else
            return 1; //this>that, return 1
    }

}