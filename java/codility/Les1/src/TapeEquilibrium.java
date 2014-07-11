class TapeEquilibrium{
    public int solution(int[] A) {
        int n = A.length;
        int[] sums = new int[n];
        sums[0] = A[0];

        int min = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + A[i];

        }

        for (int p = 1; p < n; p++) {

            min = Math.min(min, Math.abs(2 * sums[p - 1] - sums[n - 1]));
        }

        return min;
    }
}
