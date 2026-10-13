package org.example;

import java.util.Arrays;

public class WordTrie {
    private int[] firstChild = new int[16];
    private int[] nextSibling = new int[16];
    private int[] count = new int[16];

    private char[] letter = new char[16];
    private boolean[] terminal = new boolean[16];

    private int nodes = 1;

    private void ensureCapacity() {
        if (nodes < count.length) {
            return;
        }

        int capacity = count.length * 2;

        firstChild = Arrays.copyOf(firstChild, capacity);
        nextSibling = Arrays.copyOf(nextSibling, capacity);
        count = Arrays.copyOf(count, capacity);
        letter = Arrays.copyOf(letter, capacity);
        terminal = Arrays.copyOf(terminal, capacity);
    }

    private int findChild(int node, char symbol) {
        int child = firstChild[node];

        while (child != 0 && letter[child] < symbol) {
            child = nextSibling[child];
        }

        if (child != 0 && letter[child] == symbol) {
            return child;
        }

        return 0;
    }

    private int getOrCreateChild(int node, char symbol) {
        int previous = 0;
        int child = firstChild[node];

        while (child != 0 && letter[child] < symbol) {
            previous = child;
            child = nextSibling[child];
        }

        if (child != 0 && letter[child] == symbol) {
            return child;
        }

        ensureCapacity();

        int created = nodes++;
        letter[created] = symbol;
        nextSibling[created] = child;

        if (previous == 0) {
            firstChild[node] = created;
        } else {
            nextSibling[previous] = created;
        }

        return created;
    }

    private int findNode(String prefix) {
        int node = 0;

        for (int i = 0; i < prefix.length(); i++) {
            node = findChild(node, prefix.charAt(i));

            if (node == 0) {
                return -1;
            }
        }

        return node;
    }

    public void add(String word) {
        int node = 0;
        count[node]++;

        for (int i = 0; i < word.length(); i++) {
            node = getOrCreateChild(node, word.charAt(i));
            count[node]++;
        }

        terminal[node] = true;
    }

    public void remove(String word) {
        int node = 0;
        count[node]--;

        for (int i = 0; i < word.length(); i++) {
            node = findChild(node, word.charAt(i));
            count[node]--;
        }

        terminal[node] = false;
    }

    public int countPrefix(String prefix) {
        int node = findNode(prefix);

        return node == -1 ? 0 : count[node];
    }

    public String kth(String prefix, int k) {
        int node = findNode(prefix);

        if (node == -1 || count[node] < k) {
            return "NONE";
        }

        StringBuilder result = new StringBuilder(prefix);

        while (true) {
            if (terminal[node]) {
                if (k == 1) {
                    return result.toString();
                }

                k--;
            }

            int child = firstChild[node];

            while (child != 0) {
                if (k <= count[child]) {
                    break;
                }

                k -= count[child];
                child = nextSibling[child];
            }

            if (child == 0) {
                return "NONE";
            }

            result.append(letter[child]);
            node = child;
        }
    }
}