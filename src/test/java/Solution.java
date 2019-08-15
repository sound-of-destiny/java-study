public class Solution {

    class UnionFind {
        private int[] roots;
        private int[] rank;
        private int count;

        public UnionFind(char[][] grid) {
            int row = grid.length, col = grid[0].length;
            roots = new int[row*col];
            rank = new int[row*col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] != '0') {
                        roots[i*col + j] = i*col + j;
                        count++;
                    }
                }
            }
        }

        public int find(int i) {
            int root = i;
            while (root != roots[root]) root = roots[root];
            while (i != roots[root]) {
                int tmp = roots[i]; roots[i] = root; i = tmp;
            }
            return root;
        }

        public int getCount() {
            return count;
        }

        public void union(int p, int q) {
            int qroot = find(q);
            int proot = find(p);
            if (qroot != proot) {
                if (rank[proot] > rank[qroot]) roots[proot] = qroot;
                else if (rank[proot] < rank[qroot]) roots[qroot] = proot;
                else {
                    roots[qroot] = proot;
                    rank[proot]++;
                }
                count--;
            }
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != '0') {
                    if (j > 0 && grid[i][j - 1] != '0') uf.union(i*col + j, i*col + j - 1);
                    if (i > 0 && grid[i - 1][j] != '0') uf.union(i*col + j, (i - 1)*col + j);
                    if (j < col - 1 && grid[i][j + 1] != '0') uf.union(i*col + j, i*col + j + 1);
                    if (i < row - 1 && grid[i + 1][j] != '0') uf.union(i*col + j, (i + 1)*col + j);
                }
            }
        }
        return uf.getCount();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = new char[][] {{'1','0','1','1','1'},{'1','0','1','0','1'},{'1','1','1','0','1'}};
        System.out.println(solution.numIslands(grid));
    }
}
