package org.example.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Calculation {
    public int sum(int a, int b) {
        return a + b;
    }

    public int subscription(int a, int b) {
        return a - b;
    }

    static void main() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(5);
        list.add(2);
        list.set(2,4);
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(list);
    }
}
