package com.company.abc.heaps;

/**
 * Created by hfire on 3/30/17.
 */
public class Node implements Comparable<Node>{

    public Node left;
    public Node right;
    public int freq;
    public int elem;//only for leaf nodes
    public String code = "";

    public Node(Node left, Node right, int freq, int elem){

        this.left = left;
        this.right = right;
        this.freq = freq;
        this.elem = elem;
    }

    public Node(Integer freq, Integer elem) {

        this.freq = freq;
        this.elem = elem;
    }

    @Override
    public int compareTo(Node node) {
        int nodeFreq = node.freq;

        if (nodeFreq ==freq)
            return 0;
        else if (nodeFreq>freq)
            return 1;
        else
            return -1;
    }

}