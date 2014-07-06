import java.io.*;
import java.util.*;

public class Solution extends Thread {
  private void solve() throws IOException {

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
    new Solution().run();
  }

  public Solution() throws FileNotFoundException {
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
