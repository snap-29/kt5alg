package org.example;

public class Task3 {

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int[] numbers = new int[n];

        int xor = 0;

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
            xor ^= numbers[i];
        }

        int distinguishingBit = xor & -xor;

        int first = 0;
        int second = 0;

        for (int value : numbers) {
            if ((value & distinguishingBit) == 0) {
                first ^= value;
            } else {
                second ^= value;
            }
        }

        System.out.println(
                Math.min(first, second) + " " + Math.max(first, second)
        );
    }
}