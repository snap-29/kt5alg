package org.example;

public class Task6 {

    private static class BinaryTrie {
        private final int[] zero;
        private final int[] one;

        private int nodes = 1;

        BinaryTrie(int numberCount) {
            int capacity = 1 + 30 * numberCount;

            zero = new int[capacity];
            one = new int[capacity];
        }

        public void add(int value) {
            int node = 0;

            for (int bit = 29; bit >= 0; bit--) {
                int currentBit = (value >>> bit) & 1;

                if (currentBit == 0) {
                    if (zero[node] == 0) {
                        zero[node] = nodes++;
                    }

                    node = zero[node];
                } else {
                    if (one[node] == 0) {
                        one[node] = nodes++;
                    }

                    node = one[node];
                }
            }
        }

        public int maximumXor(int value) {
            int node = 0;
            int result = 0;

            for (int bit = 29; bit >= 0; bit--) {
                int currentBit = (value >>> bit) & 1;

                if (currentBit == 0) {
                    if (one[node] != 0) {
                        result |= 1 << bit;
                        node = one[node];
                    } else {
                        node = zero[node];
                    }
                } else {
                    if (zero[node] != 0) {
                        result |= 1 << bit;
                        node = zero[node];
                    } else {
                        node = one[node];
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();

        BinaryTrie trie = new BinaryTrie(n + 1);
        trie.add(0);

        int prefix = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            prefix ^= scanner.nextInt();

            answer = Math.max(answer, trie.maximumXor(prefix));

            trie.add(prefix);
        }

        System.out.println(answer);
    }
}