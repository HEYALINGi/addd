import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DFS {

    private static final Integer W = 9;
    private static final Integer H = 10;

    private static int[][] vis = new int[H][W];

    static class point {
        int x;
        int y;
    }

    private static point p = new point();

    private static int dir[][] = new int[][]{{1, -2}, {2, -1}, {2, 1}, {1, 2}};

    private static Stack<point> path = new Stack<>();
    private static Stack<point> temp = new Stack<>();

    private static int n, m, count = 0;

    private static final void dfs(int x, int y) {
        if(x == n && y == m) {
            System.out.println("路径" + (++count) + ":");
            while (!path.empty()) {
                point p1 = path.peek();
                path.pop();
                temp.push(p1);
            }
            while (!temp.empty()) {
                point p1 = temp.peek();
                temp.pop();
                path.push(p1);
                System.out.print("(" + p1.x + " " + p1.y + ")");
                if(!temp.empty()) System.out.print(" -> ");
                else System.out.println();
            }
        }

        if(x < 0 || x >= W || y < 0 || y >= H) return;

        for (int i = 0; i < 4; i ++ ) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx >= 0 && nx < W && ny >= 0 && ny < H && vis[ny][nx] == 0)
            {
                vis[ny][nx] = 1;
                p.x = nx;
                p.y = ny;
                path.push(p);

                dfs(nx, ny);

                vis[ny][nx] = 0;
                path.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("坐标轴如下所示:");
        System.out.println("  -------------->");
        System.out.println(" |             x");
        System.out.println(" |");
        System.out.println(" |");
        System.out.println(" |");
        System.out.println(" |");
        System.out.println(" | y");
        System.out.println(" V");
        System.out.println();

        Scanner input = new Scanner(System.in);
        int x;
        int y;
        System.out.println("请输入起始点坐标(0 <= x <= 8, 0 <= y <= 9)");
        x = input.nextInt();
        y = input.nextInt();
        System.out.println("请输入终点坐标(0 <= x <= 8, 0 <= y <= 9)");
        n = input.nextInt();
        m = input.nextInt();

        for(int i = 0; i < m; i ++ )
            for(int j = 0; j < n; j ++ )
                vis[i][j] = 0;

        p.x = x;
        p.y = y;
        path.push(p);

        dfs(x, y);


        System.out.println("总共" + count + "条路径");
    }
}
