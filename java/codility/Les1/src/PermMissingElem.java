class PermMissingElem {
    public int solution(int[] A) {
        int buf = 0;
        for (int i = 1; i <= A.length+1; i++)
            buf ^= i;

        for (int i = 0; i < A.length; i++)
            buf ^= A[i];

        return buf;
    }
}