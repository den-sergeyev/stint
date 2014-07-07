/**
 * Created with IntelliJ IDEA.
 * User: den
 * Date: 7/6/14
 * Time: 10:45 PM
 * To change this template use File | Settings | File Templates.
 */


import java.io.*;
import java.util.*;

public class A445 extends Thread {
    static char[] colors = {'W', 'B'};

    char invert(char a) {
        return (a == 'W') ? 'B' : 'W';
    }

    void propagate(char[][] a, int i, int j, char color) {
        if (i < 0 || i > a.length-1 || j < 0 || j > a[0].length - 1)
            return;

        if (a[i][j] == '.') {
            a[i][j] = color;

            propagate(a, i + 1, j, invert(color));
            propagate(a, i - 1, j, invert(color));
            propagate(a, i, j - 1, invert(color));
            propagate(a, i, j + 1, invert(color));
        }

    }

    private void solve() throws IOException {
        int n = _int();
        int m = _int();

        char[][] a = new char[n][m];

        for (int i = 0; i < n; i++)
            a[i] = nextToken().toCharArray();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
               propagate(a,i,j,colors[0]);


       for (int i = 0; i < n; i++){
           for (int j = 0; j < m; j++){
               out.print(a[i][j]);
           }
           out.println();
       }


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
        new A445().run();
    }

    public A445() throws FileNotFoundException {
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

