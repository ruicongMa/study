package cn.mark.avltree;

/**
 * 平衡二叉树：
 * 平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为AVL树， 可以保证查询效率较高。
 * 具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等。
 *
 * @author Mark
 * @create 2020/3/3
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int value : arr) {
            avlTree.add(new Node(value));
        }
        avlTree.midOrder();
        System.out.println("平衡处理~");
        System.out.println("当前树的根节点为：" + avlTree.getRoot());
        System.out.println("当前树的高度为：" + avlTree.getRoot().height());
        System.out.println("当前左子树的高度为：" + avlTree.getRoot().leftHeight());
        System.out.println("当前右子树的高度为：" + avlTree.getRoot().rightHeight());
    }
}

//平衡二叉树
class AVLTree {
    Node root;//根节点

    public Node getRoot() {
        return root;
    }

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
     * 返回以当前节点为根节点的左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * 返回以当前节点为根节点的右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    /**
     * 返回以当前节点为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
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
     * 左旋：以当前节点为根节点，(右子树的高度 - 左子树的高度) > 1 则需要左转，降低树右边的高度
     */
    private void leftRotate() {
        //创建一个新的节点，值为当前节点的值
        Node newNode = new Node(this.value);
        //新节点的左节点指向当前节点的左子树
        newNode.left = this.left;
        //新节点的右节点指向当前节点右子树的左子树
        newNode.right = this.right.left;
        //设置当前节点的值为当前节点的右子节点的值
        this.value = this.right.value;
        //设置当前节点右子树为当前节点的右子树的右子树
        this.right = this.right.right;
        //当前节点的左子树指向新的节点
        this.left = newNode;
    }

    /**
     * 右旋：以当前节点为根节点，(左子树的高度 - 右子树的高度) > 1 则需要右旋，降低树左边的高度
     */
    private void rightRotate() {
        //创建一个新节点，值为当前节点的值
        Node newNode = new Node(this.value);
        //新节点的右节点指向当前节点的右子树
        newNode.right = this.right;
        //新节点的左节点指向当前节点的左子树的右子树
        newNode.left = this.left.right;
        //设置当前节点的值为当前节点左子节点的值
        this.value = this.left.value;
        //设置当前节点的左子树为当前节点的左子树的左子树
        this.left = this.left.left;
        //当前节点的右子树指向新的节点
        this.right = newNode;
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
        //左旋
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果当前节点的右节点的左子树的高度大于当前节点的右节点的右子树的高度，需要对当前节点的右节点进行右旋转
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //当前节点的右节点（右子树）右旋
                this.right.rightRotate();
            }
            //当前节点左旋
            this.leftRotate();
            return;
        }
        //右旋
        if (this.leftHeight() - this.rightHeight() > 1) {
            //如果当前节点的左节点的右子树的高度大于当前节点的左节点的左子树的高度，需要对当前节点的左节点进行左旋转
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //当前节点的左节点（左子树）左旋
                this.left.leftRotate();
            }
            //当前节点右旋
            this.rightRotate();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
