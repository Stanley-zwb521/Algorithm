package dataStructure.tree;

public class SequentialStorageOfBinaryTree {
    /*
    顺序存储二叉树从数据存储来看,数组存储方式和树的存储方式可以相互转换,
    即数组可以转换成树,树也可以转换成数组。
    要求:
    1.二叉树的节点以数组的方式来存放[1,2,3,4,5,6,7]
    2.要求在遍历数组arr时,仍然可以以前序遍历,中序遍历和后序遍历的方式
    来完成节点的遍历
    顺序存储二叉树的特点:
    1.顺序二叉树通常只考虑完全二叉树
    2.第n个元素的左子节点为2*n+1
    3.第n个元素的右子节点为2*n+2
    4.第n个元素的父节点为(n-1)/2
    5.注:n表示数组下标或者二叉树中的第几个元素(树的第一个元素按0开始编号)
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();//1245367
    }
}

//编写一个ArrayBinaryTree实现顺序存储二叉树遍历
class ArrayBinaryTree {
    private int[] arr;//存储数据节点的数组
    ArrayBinaryTree left;
    ArrayBinaryTree right;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载preOrder方法,方便调用
     */
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法,完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空,或者arr.length==0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        System.out.println(arr[index]);
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
