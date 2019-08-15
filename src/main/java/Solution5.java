import java.io.*;
import java.util.*;

public class Solution5 {
    public void solve() {
        int n = ni();
        int t = ni();
        int size = n * (n + 1) / 2;
        double[] p = new double[size];
        for (int i = 0; i < t; i++) {
            p[0]++;
            for (int j = 1; j <= n; j++) {
                boolean didSpill = false;
                for (int k = 0; k < j; k++) {
                    int cur = getInd(j, k);
                    if (p[cur] > 0.9999) {
                        if (getInd(j + 1, k) < size) {
                            double h = (p[cur] - 1) / 2;
                            p[getInd(j + 1, k)] += h;
                            p[getInd(j + 1, k + 1)] += h;
                            didSpill = true;
                            p[cur] = 1;
                        }
                    }
                }
                if (!didSpill) break;
            }
        }
        int ans = 0;
        for (int i = 0; i < size; i++) {
            if (p[i] > 0.9999) ans++;
        }
        write(ans + "\n");
    }

    int getInd(int j, int k) {
        return j * (j - 1) / 2 + k;
    }



    public static void main(String[] args) {
        Solution5 m = new Solution5();
        m.solve();
        try {
            m.out.close();
        } catch (IOException e) {}
    }

    BufferedReader in;
    BufferedWriter out;
    StringTokenizer tokenizer;
    public Solution5() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public String n() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(in.readLine());
            } catch (IOException e) {}
        }
        return tokenizer.nextToken();
    }
    public int ni() {
        return Integer.parseInt(n());
    }
    public long nl() {
        return Long.parseLong(n());
    }
    public void write(String s) {
        try {
            out.write(s);
        } catch (IOException e) {}
    }
}
