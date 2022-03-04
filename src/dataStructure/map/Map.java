package dataStructure.map;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    //图是一种数据结构,其中节点可以具有零个或者多个相邻元素。两个节点之前的连接称为边。节点也可以成为顶点
    //有两种方式表示图:邻接矩阵(二维数组)和邻接表(数组+链表)
    //邻接矩阵是表示图形中顶点之间的相邻关系的矩阵
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目

    public static void main(String[] args) {
        //测试图的创建
        int n = 5;//顶点的个数
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Map map = new Map(n);
        //循环添加顶点
        for (String vertex : vertexValue) {
            map.insertVertex(vertex);
        }
        //添加边(A-B,A-C,B-C,B-D,B-E)
        map.insertEdge(0, 1, 1);//A-B
        map.insertEdge(0, 2, 1);//A-C
        map.insertEdge(1, 2, 1);//B-C
        map.insertEdge(1, 3, 1);//B-D
        map.insertEdge(1, 4, 1);//B-E
        //显示邻接矩阵
        map.showMap();
    }

    //构造器
    public Map(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //返回顶点的个数
    public int getNumOfEdge() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回顶点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showMap() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入顶点(插入节点)
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边的方法
     *
     * @param v1     两个相关联的第一个顶点的下标 "A"-"B" A点的下标
     * @param v2     两个相关联的第二个顶点的下标 "A"-"B" B点的下标
     * @param weight 矩阵中的权值(1表示能够直接连接,0表示不能直接连接)
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
