package org.example;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Task5 {

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int q = scanner.nextInt();
        WordTrie trie = new WordTrie();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );

        for (int i = 0; i < q; i++) {
            String operation = scanner.next();
            String word = scanner.next();

            switch (operation) {
                case "ADD" -> trie.add(word);

                case "REMOVE" -> trie.remove(word);

                case "KTH" -> {
                    int k = scanner.nextInt();

                    writer.write(trie.kth(word, k));
                    writer.newLine();
                }

                default -> throw new IllegalArgumentException(
                        "Неизвестная операция: " + operation
                );
            }
        }

        writer.flush();
    }
}