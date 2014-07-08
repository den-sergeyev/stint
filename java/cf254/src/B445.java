/**
 * Created with IntelliJ IDEA.
 * User: den
 * Date: 7/7/14
 * Time: 6:06 AM
 * To change this template use File | Settings | File Templates.
 */


import java.io.*;
import java.util.*;

public class B445 extends Thread {

    class DSU {
        public int[] parents;
        public int[] weights;
        int size;

        public DSU(int n) {
            size = n;
            parents = new int[size];
            weights = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                weights[i] = 1;
            }

        }

        public int root(int i) {
            int current = i;
            while (current != parents[current]) {
                current = parents[current];
            }
            return current;
        }

        public void join(int i, int j) {
            int rootOne = root(i);
            int rootTwo = root(j);


            if (rootOne != rootTwo) {
                if (weights[rootOne] < weights[rootTwo]) {
                    int tempRoot = rootOne;
                    rootOne = rootTwo;
                    rootTwo = tempRoot;
                }

                parents[rootTwo] = rootOne;
                weights[rootOne] += weights[rootTwo];
            }
        }

    }

    private void solve() throws IOException {
        int n = _int();
        int m = _int();

        DSU dsu = new DSU(n);

        for (int i = 0; i < m; i++)
            dsu.join(_int()-1, _int()-1);

        int components = 0;
        int[] dsuParents = dsu.parents;

        for (int i = 0; i < n; i++)
            if (dsuParents[i] == i)
            components++;

        out.println((long) Math.pow(2, n - 1 - (components - 1)));
    }

    public void run() {
        try {
            solve();
        } catch (Exception e) {
            System.out.println("System exiting....");
            e.printStackTrace();
            System.exit(888);
        } finally {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new B445().run();
    }

    public B445() throws FileNotFoundException {
        //in = new BufferedReader(new FileReader("A-large.in"));
        //out = new PrintWriter(new File("A-large.out"));
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        setPriority(Thread.MAX_PRIORITY);
    }

    private BufferedReader in;
    private PrintWriter out;
    private StringTokenizer st;

    private int _int() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private double _double() throws IOException {
        return Double.parseDouble(nextToken());
    }

    private long _long() throws IOException {
        return Long.parseLong(nextToken());
    }

    private char[] _chars() throws IOException {
        return nextToken().toCharArray();
    }

    private String nextToken() throws IOException {
        if (st == null || !st.hasMoreElements())
            st = new StringTokenizer(in.readLine(), " \t\r\n");
        return st.nextToken();
    }
}

