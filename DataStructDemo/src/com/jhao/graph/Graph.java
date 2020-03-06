package com.jhao.graph;

import java.util.zip.ZipEntry;

/**
 * @author JiangHao
 * @date 2020/3/6
 * @describe
 */
public class Graph {
    //节点数目
    private int size;
    //定义数组，保存顶点信息
    char[] nodes = new char[size];

    //定义矩阵保存 边 信息, 边
    int[][] edges = new int[size][size];

    /**
     * v0 v1 v2 v3
     * v0  0  1  1  1
     * v1  1  0  1  1
     * v2  1  1  0  1
     * v3  1  1  1  0
     */
    public Graph() {
        size = 4;
        nodes = new char[size];
        edges = new int[size][size];
        //0节点到 1节点，只记路径不为o的路径
        edges[0][1] = 1;
        edges[0][2] = 1;
        edges[0][3] = 1;
        edges[1][0] = 1;
        edges[1][2] = 1;
        edges[1][3] = 1;
        edges[2][0] = 1;
        edges[2][1] = 1;
        edges[2][3] = 1;
        edges[3][0] = 1;
        edges[3][1] = 1;
        edges[3][2] = 1;
    }
}
