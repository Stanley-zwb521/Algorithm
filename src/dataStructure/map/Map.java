package dataStructure.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
    /*
    图的广度优先搜索(Broad First Search)
    类似于一个分层搜索的过程,广度优先遍历需要使用一个队列以保持访问过的节点的顺序,以便按这个顺序来访问这些
    节点的邻接节点。
     */
    /*
    广度优先遍历算法步骤:
    1.访问初始节点v并标记节点v为已访问
    2.节点v入队列
    3.当队列非空时,继续执行,否则针对v节点的算法结束
    4.出队列,取得队列头节点u
    5.查找节点u的第一个邻接节点w
    6.若节点u的邻接节点不存在,则转到步骤3;否则循环执行以下三个步骤:
    6.1若节点尚未被访问,则访问节点w并标记为已访问
    6.2节点w入队列
    6.3查找节点u的w的下一个邻接节点w,转到步骤6
     */
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义一个数组boolean[],记录某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试图的创建
        int n = 8;//顶点的个数
        //String vertexValue[] = {"A", "B", "C", "D", "E"};
        String vertexValue[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Map map = new Map(n);
        //循环添加顶点
        for (String vertex : vertexValue) {
            map.insertVertex(vertex);
        }
        //添加边(A-B,A-C,B-C,B-D,B-E)
//        map.insertEdge(0, 1, 1);//A-B
//        map.insertEdge(0, 2, 1);//A-C
//        map.insertEdge(1, 2, 1);//B-C
//        map.insertEdge(1, 3, 1);//B-D
//        map.insertEdge(1, 4, 1);//B-E
        //添加边(1-2,1-3,2-4,2-5,4-8,5-8,3-6,3-7,6-7)
        map.insertEdge(0, 1, 1);//1-2
        map.insertEdge(0, 2, 1);//1-3
        map.insertEdge(1, 3, 1);//2-4
        map.insertEdge(1, 4, 1);//2-5
        map.insertEdge(3, 7, 1);//4-8
        map.insertEdge(4, 7, 1);//5-8
        map.insertEdge(2, 5, 1);//3-6
        map.insertEdge(2, 6, 1);//3-7
        map.insertEdge(5, 6, 1);//6-7
        //显示邻接矩阵
        map.showMap();
        //测试深度优先遍历算法
        System.out.println("深度优先遍历算法:");
        map.depthFirstSearch();//1->2->4->8->5->3->6->7
        //测试广度优先遍历算法
        System.out.println();
        System.out.println("广度优先遍历算法:");
        map.broadFirstSearch();//1->2->3->4->5->6->7->8
    }

    //构造器
    public Map(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
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
    private void depthFirstSearch(boolean[] isVisited, int i) {
        //首先访问该节点并输出
        System.out.print(getValueByIndex(i) + "->");
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
    public void depthFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                depthFirstSearch(isVisited, i);
            }
        }
    }

    //对一个节点进行广度优先遍历的方法
    private void broadFirstSearch(boolean[] isVisited, int i) {
        int u;//表示队列的头节点对应下标
        int w;//表示邻接节点w
        LinkedList queue = new LinkedList();//队列,记录节点访问的顺序
        //访问节点,输出节点信息
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {//说明有邻接节点
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //如果已访问,查找节点u的w的下一个邻接节点w
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }

    //遍历所有的节点,都进行广度优先搜索
    public void broadFirstSearch() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                broadFirstSearch(isVisited, i);
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
