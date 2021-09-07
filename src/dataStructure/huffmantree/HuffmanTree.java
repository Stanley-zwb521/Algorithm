package dataStructure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    /*
    赫夫曼树基本介绍:
    给定n个权值作为n个节点,构造一棵二叉树,若该树的带权路径长度(wpl Weighted Path Length)
    达到最小,称这样的二叉树为最优二叉树,也称哈夫曼树(Huffman Tree)
    赫夫曼树是带权路径长度最短的树,权值较大的节点离根较近
     */
    /*
    赫夫曼树几个重要概念:
    1.路径和路径长度:在一棵树中,从一个节点往下可以达到的孩子或孙子节点之间的通路称为路径,
    通路中分支的数目称为路径长度,若规定根节点的层数为1,则从根节点到第L层节点的路径长度为L-1
    2.节点的权及带权路径长度:若将树中节点赋给一个有着某种含义的数值,则这个数值称为该节点的权,
    节点的带权长度为:从根节点到该节点之间的路径长度与该节点的权的乘积
    3.树的带权路径长度:树的带权路径长度规定为所有的叶子节点的带权路径长度之和,记为wpl(weighted
    path length),权值越大的节点离根节点越近的二叉树才是最优二叉树
    4.WPL最小的就是赫夫曼树
     */
    /*
    构成赫夫曼树的步骤:
    1.从小到大的进行排序,将每一个数据,每个数据都是一个节点,每个节点可以看成是一棵最简单的二叉树
    2.取出根节点权值最小的两棵二叉树
    3.组成一棵新的二叉树,该新的二叉树的根节点的权值是前面两棵二叉树根节点权值的和
    4.再将这棵新的二叉树以根节点的权值大小再次进行排序,不断重复1-2-3-4的步骤,直到数列中,所有的
    数据都被处理,就得到一棵赫夫曼树
     */
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        //测试赫夫曼前序遍历以证明赫夫曼树成功创建
        System.out.println("该赫夫曼树前序遍历为:");
        preOrder(root);//67,29,38,15,7,8,23,10,4,1,3,6,13
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树,不能进行遍历");
        }
    }

    /**
     * 创建赫夫曼树的方法
     *
     * @param arr 需要创建成赫夫曼树的数组
     * @return 返回创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每一个元素构成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            System.out.println("nodes=" + nodes);
            //取出根节点权值最小的两棵二叉树
            //1.取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            //2.取出权值第二小的节点(二叉树)
            Node rightNode = nodes.get(1);
            //3.构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4.从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent加入到nodes
            nodes.add(parent);
            //System.out.println("第一次处理后=" + nodes);
        }
        //返回赫夫曼树的root节点
        return nodes.get(0);
    }
}

//创建节点类
//为了让Node对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    //写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
