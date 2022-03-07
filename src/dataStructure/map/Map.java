package dataStructure.map;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    //图是一种数据结构,其中节点可以具有零个或者多个相邻元素。两个节点之前的连接称为边。节点也可以成为顶点
    //有两种方式表示图:邻接矩阵(二维数组)和邻接表(数组+链表)
    //邻接矩阵是表示图形中顶点之间的相邻关系的矩阵
    /*
    图的深度优先搜索(depth first search)
    深度优先遍历:从初始节点出发,初始访问节点可能有多个邻接节点,深度优先遍历的策略就是首先访问第一个邻接节点,
    然后再以这个被访问的邻接节点作为初始节点,访问它的第一个邻接节点。可以这样理解:每次都在访问完当前节点后
    首先访问当前节点的第一个邻接节点。
     */
    /*
    深度优先遍历算法步骤:
    1.访问初始节点v,并标记节点v为已访问
    2.查找节点v的第一个邻接节点w
    3.若w存在则继续执行4,如果w不存在则回到第1步,将从v的下一个节点继续
    4.若w未被访问,对w进行深度优先遍历递归(即把w当作另一个v,然后进行步骤123)
    5.若w被访问过,查找节点v的w邻接节点的下一个邻接节点,转到步骤3
     */
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义一个数组boolean[],记录某个节点是否被访问
    private boolean[] isVisited;

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
        //测试深度优先遍历算法
        System.out.println("深度优先遍历算法:");
        map.depthFirstSearch();//A->B->C->D->E
    }

    //构造器
    public Map(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 得到第一个邻接节点的下标 w
     *
     * @param index 当前节点的下标
     * @return 如果存在就返回对应下标, 否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次就是0
    public void depthFirstSearch(boolean[] isVisited, int i) {
        //首先访问该节点并输出
        System.out.printf(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;
        //查找节点v的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有邻接节点
            if (!isVisited[w]) {
                depthFirstSearch(isVisited, w);
            }
            //如果w节点已经被访问过,查找节点v的w邻接节点的下一个邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    //对depthFirstSearch进行一个重载,遍历所有的节点,并进行dfs
    private void depthFirstSearch() {
        //遍历所有的节点进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                depthFirstSearch(isVisited, i);
            }
        }
    }

    //返回顶点的个数
    public int getNumOfVertex() {
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
