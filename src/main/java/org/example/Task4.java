package org.example;

public class Task4 {

    private static long countPlacements(
            int row,
            int n,
            int fullMask,
            int[] blocked,
            int columns,
            int leftDiagonals,
            int rightDiagonals
    ) {
        if (row == n) {
            return 1L;
        }

        int available = fullMask & ~(
                columns
                        | leftDiagonals
                        | rightDiagonals
                        | blocked[row]
        );

        long result = 0;

        while (available != 0) {
            int position = available & -available;
            available ^= position;

            result += countPlacements(
                    row + 1,
                    n,
                    fullMask,
                    blocked,
                    columns | position,
                    ((leftDiagonals | position) << 1) & fullMask,
                    (rightDiagonals | position) >>> 1
            );
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);

        int n = scanner.nextInt();
        int b = scanner.nextInt();

        int[] blocked = new int[n];

        for (int i = 0; i < b; i++) {
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            blocked[row] |= 1 << column;
        }

        int fullMask = (1 << n) - 1;

        long answer = countPlacements(
                0,
                n,
                fullMask,
                blocked,
                0,
                0,
                0
        );

        System.out.println(answer);
    }
}