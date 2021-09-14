package dataStructure.huffmancode;

import java.util.*;

public class HuffmanCode {
    /*
    功能:根据赫夫曼编码压缩数据的原理,需要创建"i like like like java do you like a java"
    对应的的赫夫曼树
    思路:
    1.编写Node类,需要data,weight,left,right
    2.得到"i like like like java do you like a java"对应的字符数组byte[]
    3.编写一个getNode方法,通过HashMap将每一个data的weight放入map中,并转换成节点list,
    [Node{data=32, weight=9}, Node{data=97, weight=5},......]
    4.通过list创建对应的赫夫曼树
     */
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        //System.out.println(contentBytes.length);
        List<Node> nodes = getNode(contentBytes);
        System.out.println(nodes);
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历一下:");
        preOrder(huffmanTreeRoot);
    }

    //生成赫夫曼树对应的赫夫曼编码表
    //思路:
    //1.将赫夫曼编码表存放在Map<Byte,String>
    //2.

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     * 将字符数组转换成带有data和权值的list
     *
     * @param bytes 待处理的字符数组
     * @return 返回每一个data和出现的次数Node集合 [Node{data=32, weight=9}, Node{data=97, weight=5},......]
     */
    public static List<Node> getNode(byte[] bytes) {
        //1.创建一个ArrayList为了存储Node
        ArrayList<Node> nodes = new ArrayList<Node>();
        //遍历byte,统计每一个byte出现的次数->map[key,value]
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (Byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {//说明map还没有这个字符的数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每一个键值对转成一个Node对象,并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //取出最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二小的二叉树
            Node rightNode = nodes.get(1);
            //创建一棵新的二叉树,它的根节点没有data,只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //将以处理的两棵二叉树从nodes中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入到nodes
            nodes.add(parent);
        }
        //nodes最后的节点就是huffman树的根节点
        return nodes.get(0);
    }
}

//创建Node,记录数值以及权值
class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //按照weight数值从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", weight=" + weight + '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
