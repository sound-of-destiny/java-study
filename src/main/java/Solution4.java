import java.util.*;

public class Solution4 {

    public static class TreeNode {
        int val;
        int level = 0;
        List<TreeNode> sons;
        TreeNode father;
        TreeNode(int x) {
            val = x;
            sons = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        TreeNode[] treeList = new TreeNode[n + 1];
        int[] tmp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            tmp[i] = scanner.nextInt();
            treeList[i] = new TreeNode(i);
        }

        int[][] tree = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            scanner.nextLine();
            tree[i][0] = scanner.nextInt();
            tree[i][1] = scanner.nextInt();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(treeList[1]);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            TreeNode cur = queue.poll();
            for (int i = 0; i < n - 1; i++) {
                if (cur.val == tree[i][0]) {
                    treeList[tree[i][1]].father = cur;
                    cur.sons.add(treeList[tree[i][1]]);
                    treeList[tree[i][1]].level = level;
                    queue.offer(treeList[tree[i][1]]);
                }
                if (cur.val == tree[i][1]) {
                    treeList[tree[i][0]].father = cur;
                    cur.sons.add(treeList[tree[i][0]]);
                    treeList[tree[i][1]].level = level;
                    queue.offer(treeList[tree[i][0]]);
                }
            }
        }

        for (int i = 0; i < q; i++) {
            scanner.nextLine();
            int k = scanner.nextInt();
            int l = scanner.nextInt();

        }
    }
}
