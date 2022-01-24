package dataStructure.binarysorttree;

public class BinarySortTreeDemo {
    /*
    二叉排序树介绍:
    二叉排序树BST(Binary Sort Tree),对于二叉排序树的任何一个非叶子节点,
    要求左子节点的值比当前节点的值小,右子节点的值比当前节点大。
    特别说明:如果有相同的值,可以将该节点放在左子节点或右子节点。
     */
    public static void main(String[] args) {
        int[] array = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < array.length; i++) {
            binarySortTree.add(new Node(array[i]));
        }
        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树:");
        binarySortTree.infixOrder();//1,2,3,5,7,9,10,12
        //测试一下二叉排序树删除节点
        //第一种情况
        //binarySortTree.delNode(5);
        //第二种情况
        //binarySortTree.delNode(1);
        //第三种情况
        binarySortTree.delNode(10);
        System.out.println("删除节点后:");
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {//如果root为空则直接让root指向node
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空,不能遍历~");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 功能:
     * 1.返回以node为根节点的二叉排序树的最小节点的值
     * 2.删除二叉排序树的最小节点
     *
     * @param node 传入的节点(当作二叉排序树的根节点)
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    /**
     * 二叉排序树删除节点思路:
     * 第一种情况:
     * 删除叶子节点(比如:2,5,9,12)
     * 1.需要先找到要删除的节点 targetNode
     * 2.找到targetNode的父节点 parent
     * 3.确定targetNode是parent的左子节点还是右子节点
     * 4.根据前面的情况来对应删除
     * 左子节点 parent.left=null;
     * 右子节点 parent.right=null;
     * 第二种情况:
     * 删除子树中只带有一个叶子节点的父节点(比如:1),保持二叉排序树的性质
     * 1.需要先找到要删除的节点 targetNode
     * 2.找到targetNode的父节点 parent
     * 3.确定targetNode的子节点是左子节点还是右子节点
     * 4.targetNode是parent的左子节点还是右子节点
     * 5.如果targetNode有左子节点
     * 5.1 如果targetNode是parent的左子节点
     * parent.left=targetNode.left;
     * 5.2 如果targetNode是parent的右子节点
     * parent.right=targetNode.left;
     * 6.如果targetNode有右子节点
     * 6.1 如果targetNode是parent的在左子节点
     * parent.left=targetNode.right;
     * 6.2 如果targetNode是parent的在右子节点
     * parent.right=targetNode.right;
     * 第三种情况:
     * 删除有两颗子树的节点(比如:7,3,10)
     * 1.需要先找到要删除的节点 targetNode
     * 2.找到targetNode的父节点 parent
     * 3.从targetNode的右子树找到最小的节点
     * 4.用一个临时变量将最小节点的值保存temp=11
     * 5.删除该最小节点
     * 6.targetNode.value=temp;
     *
     * @param value 需要删除的节点数值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要先找到要删除的节点 targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果发现当前这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //第一种情况,如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //确定targetNode是parent的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {//是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//第三种情况,删除有两颗子树的节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            } else {//第二种情况,删除子树中只带有一个叶子节点的父节点
                //如果要删除的targetNode有左子节点
                if (targetNode.left != null) {
                    //如果targetNode是parent的左子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else {//targetNode是parent的右子节点
                        parent.right = targetNode.left;
                    }
                } else {//要删除的targetNode有右子节点
                    //如果targetNode是parent的左子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else {//targetNode是parent的右子节点
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }
}

//创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点的方法
    //递归的方式添加节点,注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值,和当前子树的根节点的值的关系
        if (node.value < this.value) {
            //如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {//添加的节点值大于当前节点值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点, 否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {//找到就是该节点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点,向左子树递归查找
            if (this.left == null) {//如果左子节点为空不能查找
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前节点,向右子树递归查找
            if (this.right == null) {//如果右子节点为空不能查找
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除的节点的父节点
     *
     * @param value 需要查找的节点的值
     * @return 返回的是要删除的节点的父节点, 如果没有返回null
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除的节点的父节点,就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值,并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}
