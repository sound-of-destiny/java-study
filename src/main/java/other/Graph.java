package other;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    class Point {
        String name;
        int id;
        //static int idCounter = 0;

        public Point(String name) {
            this.name = name;
            // id = idCounter++;
        }
    }

    class Map {
        int[][] M;
        private int[] D;

        public Map(int n) {
            M = new int[n][n];
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (i == j) M[i][j] = 0;
                    else M[i][j] = -1;
                }
            }
        }

        ArrayList<Point> points = new ArrayList<Point>();

        public void addEdge(Point a, Point b, int w) {
            M[a.id][b.id] = w;
            M[b.id][a.id] = w;
            points.add(a.id, a);
            points.add(b.id, b);
        }

        public void relax(int u, int v) {
            if (D[v] > D[u] + M[u][v]) {
                D[v] = D[u] + M[u][v];
            }
        }

        public void dijkstra(Point point) {
            D = new int[M.length];
            for (int i = 0; i < M.length; i++) {
                D[i] = 999999999;
            }
            D[point.id] = 0;
            int[] q = new int[M.length];
            int left = 0, right = 0;//队列
            for (int i = 0; i < M.length; i++) q[right++] = i;
            while (left != right) {
                int min = left;
                for (int i = left; i < right; i++) {
                    if (D[q[i]] < D[q[min]]) {
                        min = i;
                    }
                }
                int temp = q[left];
                q[left] = q[min];
                q[min] = temp;
                int u = q[left++];
                for (int i = 0; i < M.length; i++) {
                    if (M[u][i] > 0) {
                        relax(u, i);
                    }
                }
            }
            for (int i = 0; i < M.length; i++) {
                System.out.printf("from %s to %s shortest path is:%d\n", point.name, points.get(i).name, D[i]);
            }
        }

        ArrayList<Integer> dfsPath = new ArrayList<Integer>();
        boolean[] dfsFlag;

        public void DFS(Point s, Point d) {
            dfsPath.clear();
            dfsFlag = new boolean[M.length];
            for (int i = 0; i < M.length; i++) {
                dfsFlag[i] = false;
            }
            dfsPath.add(s.id);
            dfsFlag[s.id] = true;
            dfs(s.id, d.id);
            for (Integer node : dfsPath) {
                System.out.printf("->%s", points.get(node).name);
            }
            System.out.println();
        }

        public void dfs(int sourceId, int destinationId) {
            for (int index = 0; index < M.length; index++) {
                if (sourceId == index) continue;
                if (M[sourceId][index] > 0) {
                    if (index == destinationId) {
                        dfsFlag[index] = true;
                        dfsPath.add(index);
                        return;
                    }
                    if (dfsFlag[index] == false) {
                        dfsFlag[index] = true;
                        dfsPath.add(index);
                        dfs(index, destinationId);
                        if (dfsFlag[destinationId] == false)
                            dfsPath.remove(index);
                        else return;
                    }

                }
            }
        }
    }

    public void run() {
        Map M = new Map(12);
        Point a = new Point("a");
        Point b = new Point("b");
        Point c = new Point("c");
        Point d = new Point("d");
        Point e = new Point("e");
        Point f = new Point("f");
        Point g = new Point("g");
        Point h = new Point("h");
        Point i = new Point("i");
        Point j = new Point("j");
        Point k = new Point("k");
        Point l = new Point("l");

        M.addEdge(a, b, 3);
        M.addEdge(a, c, 5);
        M.addEdge(a, d, 4);

        M.addEdge(b, f, 6);

        M.addEdge(c, d, 2);
        M.addEdge(c, g, 4);

        M.addEdge(d, e, 1);
        M.addEdge(d, h, 5);

        M.addEdge(e, f, 2);
        M.addEdge(e, i, 4);

        M.addEdge(f, j, 5);

        M.addEdge(g, h, 3);
        M.addEdge(g, k, 6);

        M.addEdge(h, i, 6);
        M.addEdge(h, k, 7);

        M.addEdge(i, j, 3);
        M.addEdge(i, l, 5);

        M.addEdge(j, l, 9);

        M.addEdge(k, l, 8);

        M.dijkstra(a);

        M.DFS(a, l);
    }

    public static void main(String[] args) {
        //new Main().run();
    }

}
