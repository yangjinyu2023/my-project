package com.example.demo.datastructure.tree;

/**
 * 红黑树 https://blog.csdn.net/u014454538/article/details/120120216
 *
 * @author yangjinyu
 * @time 2021/6/14 22:24
 */
public class RBTree < E extends Comparable < ? super E > > implements Tree < E > {
    TreeNode < E > root;

    @Override
    public TreeNode < E > find(E value) {
        return null;
    }

    @Override
    public boolean insert(E value) {
        System.out.println("insert " + value);
        // root为空
        if (root == null) {
            root = new TreeNode <>(value);
            return true;
        }
        // 插入新节点
        TreeNode < E > x = new TreeNode <>(value, true);
        for (TreeNode < E > node = root;;) {
            int dir = value.compareTo(node.value);
            if (dir < 0) {
                if (node.left == null) {
                    node.left = x;
                    x.parent = node;
                    break;
                }
                else {
                    node = node.left;
                }
            }
            else {
                if (node.right == null) {
                    node.right = x;
                    x.parent = node;
                    break;
                }
                else {
                    node = node.right;
                }
            }
        }
        // 使树重新平衡
        for (TreeNode < E > xp, xpp, xppl, xppr;;) {
            // 1.如果插入节点的父节点为空，插入节点置黑
            if ((xp = x.parent) == null) {
                x.red = false;
                return true;
            }
            // 2.如果插入节点的父节点为黑
            if (!xp.red) {
                return true;
            }
            // 3.如果插入节点的父节点为红，此时祖父节点必定存在
            else {
                xpp = xp.parent;
                xppl = xpp.left;
                xppr = xpp.right;
                // 先处理父节点为祖父节点的左孩子的情况
                if (xp == xppl) {
                    // 3.1 如果父节点兄弟节点为红，需要变色
                    if (xppr != null && xppr.red) {
                        xp.red = false;
                        xppr.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    // 3.2 如果父节点兄弟节点为黑或者空，需要旋转变色
                    else {
                        // 3.2.1 如果插入节点是父节点的左子节点，变色（父亲变黑，祖父变红），对祖父节点进行右旋
                        if (x == xp.left) {
                            xp.red = false;
                            xpp.red = true;
                            rotateRight(xpp);
                        }
                        // 3.2.2 如果插入节点是父节点的右子节点，对父节点进行左旋，转换成3.2.1
                        else {
                            rotateLeft(xp);
                            x = xp;
                        }
                    }
                }
                else { // 处理父节点为祖父节点的右孩子的情况
                    if (xppl != null && xppl.red) {
                        xp.red = false;
                        xppl.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                    else {
                        if (x == xp.right) {
                            xp.red = false;
                            xpp.red = true;
                            rotateLeft(xpp);
                        }
                        else {
                            rotateRight(xp);
                            x = xp;
                        }
                    }
                }
            }
        }
    }

    private void rotateRight(TreeNode < E > x) {
        TreeNode < E > xp = x.parent, xl = x.left, xlr = xl.right;
        xl.right = x;
        x.parent = xl;

        x.left = xlr;
        if (xlr != null) {
            xlr.parent = x;
        }

        if (xp != null) {
            if (x == xp.left) {
                xp.left = xl;
            }
            else {
                xp.right = xl;
            }
            xl.parent = xp;
        }
        else {
            xl.parent = null;
            root = xl;
        }
    }

    private void rotateLeft(TreeNode < E > x) {
        TreeNode < E > xp = x.parent, xr = x.right, xrl = xr.left;

        xr.left = x;
        x.parent = xr;

        x.right = xrl;
        if (xrl != null) {
            xrl.parent = x;
        }

        if (xp != null) {
            if (x == xp.left) {
                xp.left = xr;
            }
            else {
                xp.right = xr;
            }
            xr.parent = xp;
        }
        else {
            xr.parent = null;
            root = xr;
        }
    }

    @Override
    public TreeNode < E > delete(E value) {
        return null;
    }

    public void show() {
        TreeOperation.show(root);
    }

    public static void main(String[] args) {
        RBTree < Integer > tree = new RBTree <>();
        tree.insert(21);
        tree.show();
        tree.insert(7);
        tree.show();
        tree.insert(29);
        tree.show();
        tree.insert(5);
        tree.show();
        tree.insert(50);
        tree.show();
        tree.insert(6);
        tree.show();
        tree.insert(48);
        tree.show();
        tree.insert(55);
        tree.show();
        tree.insert(3);
        tree.show();
        tree.insert(2);
        tree.show();
        tree.insert(1);
        tree.show();
        TraverseTree.inOrderTraverse(tree.root);
    }
}