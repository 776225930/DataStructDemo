package com.jhao.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * @author JiangHao
 * @date 2020/3/7
 * @describe 图的遍历：1.深度优先算法:一条道走到黑
 * 2.官渡优先算法:
 */
public class GraphCover {

    //节点数目
    private int size;
    //定义数组，保存顶点信息
    String[] nodes = new String[size];

    //定义矩阵保存 边 信息, 边
    int[][] edges = new int[size][size];

    /**
     * A  B C D E F G
     * A  0 0 1 1 0 1 0
     * B  0 0 1 0 0 0 0
     * C  1 1 0 1 0 0 0
     * D  1 0 1 0 0 0 0
     * E  0 0 0 0 0 0 1
     * F  1 0 0 0 0 0 1
     * G  0 0 0 0 1 1 0
     */
    public GraphCover() {
        nodes = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        size = nodes.length;
        visited = new int[size];
        //初始化边---- 为了直观，做一个常量定义
        final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;
        edges = new int[size][size];
        edges[A][C] = 1;
        edges[A][D] = 1;
        edges[A][F] = 1;
        edges[B][C] = 1;
        edges[C][A] = 1;
        edges[C][B] = 1;
        edges[C][D] = 1;
        edges[D][C] = 1;
        edges[D][A] = 1;
        edges[E][G] = 1;
        edges[F][A] = 1;
        edges[F][G] = 1;
        edges[G][F] = 1;
        edges[G][E] = 1;
    }

    /**
     * 遍历标记，防止死循环
     */
    private int[] visited;

    /**
     * 深度优先遍历
     * 一条路走到黑,不撞南墙不回头
     * 对每一个可能的分支路径深入到不能再深入为止
     */
    public void DeepFirst(int start) {//从第n个节点开始遍历(不要想太多，就是从节点数组的第n个开始)
        //标记为1表示该顶点已经被处理过
        visited[start] = 1;
        //输出节点数据
        System.out.println("齐天大圣到-->" + nodes[start] + "一游");
        //开始遍历所有邻接点
        for (int i = 0; i < size; i++) {
            //两节点之间相通的(和该节点有边)
            if (edges[start][i] == 1 && visited[i] == 0) {
                //邻接点
                //标记为1表示该顶点已经被处理过
//                visited[start] = 1;
//                输出节点数据
//                System.out.println("齐天大圣到-->" + nodes[start] + "一游");
                //继续遍历邻接点
//                for ()

                //抽出来成递归
                //邻接点
                DeepFirst(i);
            }
        }

    }

    /**
     * 广度优先遍历
     * 广度优先搜索遍历图的过程中以v 为起始点，由近至远，
     * 依次访问和v 有路径相通且路径长度为1,2,…的顶点
     * 第一批节点的邻接点，?
     */
    public void BreadthFirst(List<Integer> tmp_nodes) {
        List<Integer> lastNodes = new ArrayList<>();
        for (int node : tmp_nodes) {
            visited[node] = 1;
            System.out.println("齐天大圣到-->" + nodes[node] + "一游");
            //找出 所有 邻接点
            for (int j = 0; j < size; j++) {
                //两节点之间相通的(和该节点有边)
                if (edges[node][j] == 1 && visited[j] == 0) {
                    //邻接点
                    lastNodes.add(j);
                }
            }
        }
        //遍历下一批节点
        if (lastNodes.size() > 0) {
            BreadthFirst(lastNodes);
        }

    }

    public static void main(String[] args) {
        GraphCover graphCover = new GraphCover();
//        graphCover.DeepFirst(5);
        List<Integer> lastNodes = new ArrayList<>();
        lastNodes.add(0);
        graphCover.BreadthFirst(lastNodes);

    }
}
