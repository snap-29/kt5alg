package org.example;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class FastScanner {
    private final InputStream input;
    private final byte[] buffer = new byte[1 << 16];

    private int position;
    private int length;

    public FastScanner(InputStream input) {
        this.input = input;
    }

    private int read() throws IOException {
        if (position >= length) {
            length = input.read(buffer);
            position = 0;

            if (length == -1) {
                return -1;
            }
        }

        return buffer[position++] & 0xFF;
    }

    private int skipSpaces() throws IOException {
        int c;

        do {
            c = read();

            if (c == -1) {
                throw new EOFException("Недостаточно входных данных");
            }
        } while (c <= ' ');

        return c;
    }

    public int nextInt() throws IOException {
        int c = skipSpaces();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = read();
        }

        int value = 0;

        while (c >= '0' && c <= '9') {
            value = value * 10 + c - '0';
            c = read();
        }

        return value * sign;
    }

    public String next() throws IOException {
        int c = skipSpaces();
        StringBuilder result = new StringBuilder();

        while (c > ' ') {
            result.append((char) c);
            c = read();
        }

        return result.toString();
    }
}