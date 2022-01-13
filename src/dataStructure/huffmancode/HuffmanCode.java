package dataStructure.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        System.out.println(contentBytes.length);//40
        //压缩过程
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes) + " 长度=" + huffmanCodeBytes.length);//长度仅仅17,远小于40
        //解压过程
        //System.out.println("str=" + byteToBitString(true, (byte) -1));
        byte[] sourceByte = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串=" + new String(sourceByte));//"i like like like java do you like a java"
        //测试压缩文件
        String srcFile = "c://test//src.png";
        String dstFile = "c://test//dst.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件成功!");
        //分步过程
        /*
        List<Node> nodes = getNode(contentBytes);
        System.out.println(nodes);
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历一下:");
        preOrder(huffmanTreeRoot);
        //测试是否生成了对应的赫夫曼编码
        //getCode(huffmanTreeRoot,"",stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表" + huffmanCodes);
        //测试生成的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//长度仅仅17,远小于40
        //发送huffmanCodeBytes数组
         */
    }

    /**
     * 将一个文件进行压缩
     *
     * @param srcFile 希望压缩的文件的全路径
     * @param dstFile 压缩后的文件存放的目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建文件的输入流
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        //创建文件的输出流
        OutputStream os = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流,存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入赫夫曼编码,为了以后恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*完成数据的解压
    思路:
    1.将huffmanCodeBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    重写转成赫夫曼编码对应的"10101000101111111100..."
    2.赫夫曼编码对应的二进制的字符串"10101000101111111100..." => 对照赫夫曼编码 => "i like like like java do you like a java"
     */

    /**
     * 编写一个方法,完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 压缩后生成的赫夫曼编码字节数组
     * @return 原来字符串对应的字符
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串,形式 10101000101111111100...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换,反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//扫描计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个字符
                String key = stringBuilder.substring(i, i + count);//i不动,让count移动,直到匹配到map中的value
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {//匹配到了
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count位置
        }
        //当for循环结束后,list中就存放了所有字符 "i like like like java do you like a java"
        //把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag flag标志位表示是否需要补高位,如果是true表示需要补高位,如果是false表示不需要补高位(如果是最后一个字节无需补高位)
     * @param b    传入的byte
     * @return 是该byte对应的二进制字符串(注意是按照补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数还需要补高位,例如:1 => 0000 0001
        if (flag) {
            temp |= 256;//按位或256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 使用一个方法,将前面的方法封装起来,便于我们的调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNode(bytes);
        //根据nodes创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据赫夫曼树,生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码,将原始的字节数组压缩,得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 功能:将字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码对应压缩后的byte[]数组
     *
     * @param bytes        这时原始的字符串对应的byte[]数组
     * @param huffmanCodes 生成的赫夫曼编码的map
     * @return 返回赫夫曼编码处理后的byte[]数组
     * 举例:String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的是 字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的byte[] huffmanCodeBytes,即8位对应一个byte,放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000 => 10101000-1 => 10100111(反码) => 11011000=-88]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("stringbuilder="+stringBuilder);
        //将"1010100010111111110..."转成byte[]

        //统计返回 byte[] huffmanCodeBytes的长度
        //concept2:一句话int len=stringBuilder.length()+7/8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//每8位对应一个byte,所以步长+8
            String strByte;
            if (i + 8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte放入huffmanCodeBytes中即可
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //为了调用方便,重载getCode方法
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成赫夫曼树对应的赫夫曼编码表
    //思路:
    //1.将赫夫曼编码表存放在Map<Byte,String>
    //形式{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    //2.在生成赫夫曼编码表示需要去拼接路径,定义一个StringBuilder存储某个叶子节点的路径
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能:将传入的node节点的所有叶子节点的赫夫曼编码得到,并放入到huffmanCodes集合中
     *
     * @param node          传入节点
     * @param code          路径:左子节点是0,右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {//node==null就不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是一个叶子节点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

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
