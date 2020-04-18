package cn.mark.binarysorttree;

/**
 * 二叉排序树介绍：
 * BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
 *
 * @author Mark
 * @create 2020/3/2
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : arr) {
            binarySortTree.add(new Node(value));
        }
        //中序遍历二叉排序树
        // binarySortTree.midOrder();

        //删除叶子节点
        // binarySortTree.delNode(2);

        //删除只有一个子树的节点
        // binarySortTree.delNode(1);

        //删除有两颗子树的节点
        binarySortTree.delNode(3);
        binarySortTree.midOrder();
    }
}

//二叉排序树
class BinarySortTree {
    Node root;//根节点

    /**
     * 以node为起点向左查询最小值，删除最小值节点，并返回最小值
     *
     * @param node
     * @return
     */
    public int searchRightMinValue(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        //找到最小值，删除
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 以node为起点向右查询最大值，删除最大值节点，并返回最大值
     *
     * @param node
     * @return
     */
    public int searchLeftMaxValue(Node node) {
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        //找到最大值，删除
        delNode(temp.value);
        return temp.value;
    }

    //删除节点
    public void delNode(int value) {
        if (this.root == null) {
            return;
        }
        //查找要删除的节点
        Node targetNode = this.searchDelNode(value);
        if (targetNode == null) {//没找到到，则返回空
            return;
        }
        //查找要删除节点的父节点
        Node parentNode = this.searchDelParentNode(value);
        //如果要删除的节点是叶子节点，则如下考虑
        if (targetNode.left == null && targetNode.right == null) {
            //找到删除节点的父节点
            if (parentNode.left != null && parentNode.left.value == value) {
                //删除的节点是父节点的左子节点
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                //删除的节点是父节点的右子节点
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {//删除的节点有两颗子树
            //从删除节点的右边开始查找最小值
            int minValue = this.searchRightMinValue(targetNode.right);
            //删除掉最小值
            delNode(minValue);
            //把最小值赋值给targetNode.value
            targetNode.value = minValue;
        } else {//删除的节点只有一颗子树
            if (targetNode.left != null) {//删除的节点只有左子节点
                if (parentNode != null) {
                    //删除的节点是父节点的左子节点
                    if (parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.left;
                    } else {
                        //删除的节点是父节点的右子节点
                        parentNode.right = targetNode.left;
                    }
                } else {
                    this.root = targetNode.left;
                }
            } else {//删除的节点只有右子节点
                if (parentNode != null) {
                    //删除的节点是父节点的左子节点
                    if (parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.right;
                    } else {
                        //删除的节点是父节点的右子节点
                        parentNode.right = targetNode.right;
                    }
                } else {
                    this.root = targetNode.right;
                }
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchDelParentNode(int value) {
        if (this.root == null) {
            return null;
        }
        return this.root.searchDelParentNode(value);
    }

    //查找要删除的节点
    public Node searchDelNode(int value) {
        if (this.root == null) {
            return null;
        }
        return this.root.searchDelNode(value);
    }

    /**
     * 中序遍历二叉排序树
     */
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉排序树为空，无法遍历~");
        }
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }
}

//节点类
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 根据节点的value查找要删除节点的父节点
     *
     * @param value
     * @return 如果找不到，则返回空
     */
    public Node searchDelParentNode(int value) {
        //如果当前节点的左节点不为空且值等于要删除的值或者如果当前节点的右节点不为空且值等于要删除的值，则当前节点就是父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchDelParentNode(value);//向左递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchDelParentNode(value);//向右递归查找
            } else {
                return null;
            }
        }
    }

    /**
     * 根据节点的value查找要删除的节点
     *
     * @param value
     * @return 如果找不到，则返回空
     */
    public Node searchDelNode(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {//如果删除的值小于当前的值，则向左递归查找
            if (this.left == null) {//说明没找到
                return null;
            }
            return this.left.searchDelNode(value);//向左递归查找要删除的节点
        } else {//如果删除的值大于当前的值，则向右递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.searchDelNode(value);//向右递归查找要删除的节点
        }
    }

    /**
     * 中序遍历二叉排序树
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 添加节点（递归添加）到二叉排序中
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //如果添加的节点值小于当前节点的值
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {//如果添加的节点值大于等于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
