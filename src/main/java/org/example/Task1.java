package org.example;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Task1 {

    private static void generate(
            int n,
            int opened,
            int closed,
            int position,
            char[] sequence,
            BufferedWriter writer
    ) throws Exception {

        if (position == sequence.length) {
            writer.write(sequence);
            writer.newLine();
            return;
        }

        if (opened < n) {
            sequence[position] = '(';

            generate(
                    n,
                    opened + 1,
                    closed,
                    position + 1,
                    sequence,
                    writer
            );
        }

        if (closed < opened) {
            sequence[position] = ')';

            generate(
                    n,
                    opened,
                    closed + 1,
                    position + 1,
                    sequence,
                    writer
            );
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out)
        );

        generate(n, 0, 0, 0, new char[2 * n], writer);

        writer.flush();
    }
}